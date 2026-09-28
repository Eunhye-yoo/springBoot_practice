//package com.example.ch2;
//
//import org.junit.jupiter.api.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//class BoardRepositoryTest2 {
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
//    void countAllByWriter() {
//        int cnt = boardRepository.countAllByWriter("writer1");
//        assertTrue(cnt == 20);
//    }
//
//    @Test
//    void findByTitleAndWriter() {
//        List<Board> boards = boardRepository.findByTitleAndWriter("title1", "writer1");
//        boards.forEach(System.out::println);
//
//        assertTrue(boards.size() == 1);
//        assertEquals(boards.get(0).getWriter(), "writer1");
//    }
//
//    @Test
//    void findByTitleAndWriterNotResult() {
//        List<Board> boards = boardRepository.findByTitleAndWriter("title1", "writer2");
//        assertTrue(boards.size() == 0);
//        System.out.println(boards);
//        // 검색 결과가 존재하지 않으면 빈 리스트 반환
//        assertNotNull(boards);
//    }
//
//    @Test
//    void deleteByTitle() {
//        assertTrue(boardRepository.deleteByTitle("title1") == 1);
//        List<Board> boards = boardRepository.findByWriter("writer1");
////        assertTrue(boards.size() == 19);
//        boards.forEach(System.out::println);
//    }
//
//    @Test
//    void findAllByOrderByViewCntDesc() {
//        List<Board> boards = boardRepository.findAllByOrderByViewCntDesc();
//        boards.forEach(System.out::println);
//        // 정렬 검증 : 앞 게시글의 조회수가 뒤 게시물보다 크거나 같아야함
//        for (int i = 0; i < boards.size()-1; i++) {
//            assertTrue(boards.get(i).getViewCnt() >= boards.get(i+1).getViewCnt());
//        }
//    }
//
//    @Test
//    void findByWriterOrderByViewCntDesc() {
//        List<Board> boards = boardRepository.findByWriterOrderByViewCntDesc("writer1");
//        boards.forEach(System.out::println);
//        assertTrue(boards.size() == 20);
//        // 검색 검증
//        boards.forEach(board -> assertEquals("writer1", board.getWriter()));
//        // 정렬 검증
//        for (int i = 0; i < boards.size()-1; i++) {
//            assertTrue(boards.get(i).getViewCnt() >= boards.get(i+1).getViewCnt());
//        }
//    }
//
//}