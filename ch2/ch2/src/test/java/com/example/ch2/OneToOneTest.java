package com.example.ch2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class OneToOneTest {
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private CartRepository cartRepository;

    @Test
    public void saveTest(){
        Member member = new Member();
        member.setId("aaa");
        member.setName("에이");
        member.setEmail("aaa@naver.com");
        member.setPassword("1234");
        memberRepository.save(member);

        Cart cart = new Cart();
        cart.setId(1L);
        cart.setMember(member); // 객체를 통째로 넣으면 DB에는 member_id만 저장됨
        cartRepository.save(cart);
    }

    @Test
    @DisplayName("Cart에서 Member정보 꺼내기")
    public void findTest(){
        saveTest(); // 데이터 준비

        Cart cart = cartRepository.findById(1L).orElse(null);
        assertNotNull(cart);

        // JOIN 쿼리가 자동 적용되어 회원 정보가 모두 반환됨.
        System.out.println("Cart 주인 : " + cart.getMember().getName());
        assertEquals("에이", cart.getMember().getName());
    }

    @Test
    @DisplayName("Member에서 Cart정보 꺼내기")
    public void findCartTest(){
        saveTest(); // 데이터 준비

        Member member = memberRepository.findById("aaa").orElse(null);
        assertNotNull(member);

        // JOIN 쿼리가 자동 적용되어 회원 정보가 모두 반환됨.
        System.out.println("내 Cart : " + member.getCart().getId());
        assertEquals(1L, member.getCart().getId());
    }

    @Test
    @DisplayName("제약 위반 - 회원 없이 장바구니 만들기")
    public void nullMemberTest(){
        Cart cart = new Cart();
        cart.setId(2L);
        assertThrows(Exception.class, () -> cartRepository.save(cart));
    }

}
