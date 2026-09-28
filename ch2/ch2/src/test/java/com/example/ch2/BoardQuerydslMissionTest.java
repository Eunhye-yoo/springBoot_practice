//package com.example.ch2;
//
//import com.querydsl.core.BooleanBuilder;
//import com.querydsl.core.Tuple;
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
//import static com.example.ch2.QBoard.board;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//@SpringBootTest
//@Transactional // 테스트 끝나면 DB 롤백
//public class BoardQuerydslMissionTest {
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
//    @DisplayName("미션1 - writer2의 글 중 조회수 50 이상, title과 viewCnt만 조회")
//    public void mission1() {
//        // selectFrom -> Entity의 모든 필드 반환 -> List<Board>
//        // select -> Entity의 원하는 필드 반환 ->  List<Tuple>
//        List<Tuple> list = qf.select(board.title, board.viewCnt)
//                .from(board)
//                .where(board.writer.eq("writer2"), board.viewCnt.goe(50L))
//                .fetch();
//
//        for (Tuple tuple : list) {
//            System.out.println(tuple.get(board.title) + " : " +  tuple.get(board.viewCnt));
//        }
//
//        // writer2가 작성한 글 총 20개,  i = 52 ~ 97 -> 10건
//        assertEquals(10, list.size());
//        list.forEach(tuple -> assertTrue(tuple.get(board.viewCnt)>=50));
//    }
//
//    @Test
//    @DisplayName("미션2 - 작성자별 글 개수와 조회수 합계 (합계 200 이상만, writer 오름차순)")
//    public void mission2() {
//        // SELECT writer, COUNT(*), SUM(viewCnt) FROM Board
//        // GROUP BY writer HAVING SUM(viewCnt) >=200
//        // ORDER BY writer ASC;
//        List<Tuple> list =
//        qf.select(board.writer, board.count(), board.viewCnt.sum())
//                .from(board)
//                .groupBy(board.writer)
//                .having(board.viewCnt.sum().goe(200L))
//                .orderBy(board.writer.asc())
//                .fetch();
//
//        for (Tuple tuple : list) {
//            System.out.println(tuple.get(board.writer) + " : "
//                    +  tuple.get(board.count())+ " : " +  tuple.get(board.viewCnt.sum()));
//        }
//
////        writer0 : 20 : 1050
////        writer1 : 20 : 970
////        writer2 : 20 : 990
////        writer3 : 20 : 1010
////        writer4 : 20 : 1030
//        assertEquals(5, list.size());
//        assertEquals("writer0", list.get(0).get(board.writer));
//        assertEquals("writer4", list.get(4).get(board.writer));
//    }
//
//    private List<Board> search(String writer, Long minViewCnt, String titleKeyword) {
//        BooleanBuilder builder = new BooleanBuilder();
//        // 매개변수가 전달됨에 따라 조건 추가
//        if(writer != null){
//            builder.and(board.writer.eq(writer));
//        }
//        if(minViewCnt != null){
//            builder.and(board.viewCnt.goe(minViewCnt));
//        }
//        if(titleKeyword != null){
//            builder.and(board.title.contains(titleKeyword)); // like() - '%titleKeyword%'
//        }
//
//        return qf.selectFrom(board)
//                .where(builder) // builder에 아무 조건도 담기지 않으면 WHERE절 생략
//                .orderBy(board.viewCnt.desc())
//                .fetch();
//
//    }
//
//    @Test
//    @DisplayName("미션3 - 조건 조합형 동적 검색")
//    public void mission3() {
//        List<Board> case1 = search("writer0", 70L, "title");
//        case1.forEach(b->{
//            assertEquals("writer0",b.getWriter());
//            assertTrue(b.getViewCnt()>=70);
//            assertTrue(b.getTitle().contains("title"));
//        });
//
//        List<Board> case2 = search("writer4", null, null);
//        assertEquals(20, case2.size());
//
//        List<Board> case3 = search(null, 90L, null);
//        assertEquals(11, case3.size());
//
//        List<Board> case4 = search(null, null, "100");
//        assertEquals(1, case4.size());
//
//        List<Board> case5 = search(null, null, null);
//        assertEquals(100, case5.size());
//    }
//}
