package com.example.ch2;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.ch2.QBoard.board;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    //QueryDSL 검색/통계 기능 구현시 EntityManager 사용방법(JPA 표준 주입 방식)
    @PersistenceContext
    private EntityManager em;

    // 전체 게시물 조회
    public Page<Board> getList(int page, String searchType, String keyword, String writer){
        Pageable pageable = PageRequest.of(page,5, Sort.by("bno").descending());
        JPAQueryFactory qf = new JPAQueryFactory(em);
        BooleanBuilder builder = new BooleanBuilder();

        if(writer != null && !writer.isBlank()){
            builder.and(board.user.firstName.eq(writer));
        }else if(keyword != null && !keyword.isBlank() && searchType != null && !searchType.isBlank()) {
            switch (searchType) {
                case "T" -> builder.and(board.title.containsIgnoreCase(keyword));
                case "C" -> builder.and(board.content.containsIgnoreCase(keyword));
                case "TC" -> builder.and(board.content.containsIgnoreCase(keyword)
                        .or(board.title.containsIgnoreCase(keyword)));
                // board와 user에 이미 연관관계가 성립되므로 자동으로 JOIN 쿼리 생성
                case "W" -> builder.and(board.user.firstName.containsIgnoreCase(keyword));
            }
        }
        // 조건이 하나도 없으면 builder가 비어있고, 이경우 WHERE 없이 전체 조회
        return boardRepository.findAll(builder, pageable);
    }

    // 게시물 저장
    public Board write(Board board){
        return boardRepository.save(board);
    }

    // 게시물 상세보기 (없으면 null 반환)
    public Board read(Long bno){
        Board board = boardRepository.findById(bno).orElse(null);
        if (board != null){
            board.setViewCnt(board.getViewCnt()+1);
            boardRepository.save(board); // UPDATE 실행
        }
        return board;
    }

    // 게시물 수정
    // 클라이언트가 요청한 newBoard를 그대로 save하면 안됨
    // 게시글 수정 폼에는 title/content만 있기때문에 나머지 Board의 필드는 null 또는 초기화
    // -> DB에서 원본 게시글 읽기 -> 바뀐 필드만 수정
    public Board modify(Board newBoard){
        Board board = boardRepository.findById(newBoard.getBno()).orElse(null);
        if(board == null) return null;

        board.setTitle(newBoard.getTitle());
        board.setContent(newBoard.getContent());
        return boardRepository.save(board);
    }

    // 게시물 삭제 (게시글 확인 후 삭제)
    public void remove(Long bno){
        Board board = boardRepository.findById(bno).orElse(null);
        if(board != null){
            boardRepository.deleteById(bno);
        }
    }

    // 작성자별 통계 (글 수, 총 조회수, 평균 조회수)
    public List<BoardStats> getStats() {
        JPAQueryFactory qf = new JPAQueryFactory(em);

        // Projections.constructor : 조회 결과를 BoardStats 생성자에 순서대로 전달하여 객체 생성
        return qf.select(Projections.constructor(BoardStats.class,
                    board.user.id,
                    board.count(),
                    board.viewCnt.sum(),
                    board.viewCnt.avg()))
                .from(board)
                .groupBy(board.user.id)
                .orderBy(board.viewCnt.sum().desc())
                .fetch();
    }
}
