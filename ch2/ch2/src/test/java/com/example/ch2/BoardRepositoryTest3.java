//package com.example.ch2;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.MethodOrderer;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestMethodOrder;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//class BoardRepositoryTest3 {
//    @Autowired
//    private BoardRepository boardRepository;
//
//    @BeforeEach // 모든 테스트 실행 전에 먼저 실행
//    public void insertTest(){
//        for(int i=1; i<=100; i++){
//            Board board = new Board();
//            board.setTitle("title"+i);
//            board.setWriter("writer"+(i%5)); // 0,1,2,3,4 -> 20개
//            board.setContent("content"+i);
//            board.setViewCnt(Math.round(Math.random()*100)); // 0~99
//            boardRepository.save(board);
//        }
//    }
//
//    @Test
//    public void pagingTest(){
//        // PageRequest.of(페이지번호, 페이지당 개수, 정렬방향, 정렬기준필드)
//        // 페이지 번호 0부터 시작 (0 = 첫 페이지)
//        Pageable pageable = PageRequest.of(0, 10, Sort.Direction.DESC, "bno");
//        Page<Board> page = boardRepository.findAll(pageable);
//
//        // page객체에 모든 페이징 정보가 담겨있다.
//        // 전체 글 갯수
//        System.out.println("전체 글 갯수: " + page.getTotalElements()); // 100
//        // 전체 페이지 수
//        System.out.println("전체 페이지 수: " + page.getTotalPages()); // 10
//        // 현재 페이지
//        System.out.println("현재 페이지: " + page.getNumber()); // 0
//        // 이전 페이지 여부
//        System.out.println("이전 페이지 여부: " + page.hasPrevious()); // false
//        // 다음 페이지 여부
//        System.out.println("다음 페이지 여부: " + page.hasNext()); // true
//
//        List<Board> boards = page.getContent(); // 실제 게시판 글 데이터 꺼내기
//        boards.forEach(System.out::println);
//
//        assertEquals(10, boards.size());
//        assertEquals(100, page.getTotalElements());
//    }
//}