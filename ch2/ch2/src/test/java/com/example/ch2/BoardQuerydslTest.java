//package com.example.ch2;
//
//import com.querydsl.jpa.impl.JPAQueryFactory;
//import jakarta.persistence.EntityManager;
//import jakarta.transaction.Transactional;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//import static com.example.ch2.QBoard.board;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//@SpringBootTest
//@Transactional // 테스트 끝나면 DB 롤백
//public class BoardQuerydslTest {
//    @Autowired
//    EntityManager em;
//    @Autowired
//    BoardRepository boardRepository;
//
//    JPAQueryFactory qf; // QueryDSL의 모든 쿼리의 시작점
//
//    @BeforeEach
//    public void setup() {
//        qf = new JPAQueryFactory(em);
//        for (int i = 1; i <= 100; i++){
//            Board list = new Board();
//            list.setTitle("title" + i);
//            list.setContent("content" + i);
//            list.setWriter("writer" + (i%5));
//            list.setViewCnt((long)i);
//            boardRepository.save(list);
//        }
//    }
//
//    @Test
//    @DisplayName("기본조회 - 작성자로 글 찾기")
//    public void findByWriter(){
//        List<Board> boards = qf.selectFrom(board)
//                .where(board.writer.eq("writer1"))
//                .fetch();
//
//        assertEquals(20, boards.size());
//    }
//
//    @Test
//    @DisplayName("여러 조건")
//    public void conditionTest(){
//        // SELECT * FROM board WHERE title Like "title1%" AND viewCnt >= 50
//        List<Board> list = qf.selectFrom(board)
//                .where(board.title.like("title1%"),
//                        board.viewCnt.goe(50L))
//                .orderBy(board.viewCnt.desc())
//                .fetch();
////                .where(board.title.like("title1%")
////                        .and(board.viewCnt.goe(50L)))
//        list.forEach(board -> assertTrue(board.getViewCnt() >= 50));
//        list.forEach(board -> assertTrue(board.getTitle().startsWith("title1")));
//        list.forEach(System.out::println);
//
//    }
//
//}
