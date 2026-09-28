package com.example.ch2;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    // 목록 : GET /board/list
    // - 기본 : 페이징 목록 [미션3]
    // - ?keyword=... : 검색 모드 [미션4]
    // - ?writer=...  : 작성자 모드 [미션5]
    @GetMapping("/list")
    public void getList(@RequestParam(defaultValue = "0") int page,
                        @RequestParam(required = false) String searchType,
                        @RequestParam(required = false) String keyword,
                        @RequestParam(required = false) String writer,
                        Model model){
        Page<Board> boardPage = boardService.getList(page, searchType, keyword, writer);
        model.addAttribute("list", boardPage.getContent());
        model.addAttribute("page", boardPage);
        model.addAttribute("searchType", searchType);
        model.addAttribute("keyword", keyword);
        model.addAttribute("writer", writer);
        model.addAttribute("writerCount", boardPage.getTotalElements());
    }

    // 읽기 : GET /board/read?bno=1
    // 매개변수 이름과 테이블의 필드가 같으면 자동 바인딩 & String -> Long 자동형변환
    @GetMapping("/read")
    public void read(Long bno, Model model){
        Board board = boardService.read(bno); // [미션1] 조회수 +1
        model.addAttribute("board",board);
    }

    // 작성 : GET /board/write
    @GetMapping("/write")
    public void showWriteForm(Model model){
        Board board = new Board();
        User user = new User();
        user.setId(1L); // 로그인 기능 구현 X 임시 데이터
        board.setUser(user);
        model.addAttribute("board",board);
    }

    // 작성 처리 : POST /board/write
    @PostMapping("/write")
    public String write(Board board){
        board.setViewCnt(0L); // 조회수 초기화
        boardService.write(board);
        return "redirect:/board/list";
    }

    // 수정 처리 : POST /board/modify
    @PostMapping("/modify")
    public String modify(Board board){
        boardService.modify(board);
        return "redirect:/board/list";
    }

    // 삭제 처리 : POST /board/remove
    @PostMapping("/remove")
    public String remove(Long bno){
        boardService.remove(bno);
        return "redirect:/board/list";
    }

    // 통계 페이지 : GET /board/stats
    @GetMapping("/stats")
    public String stats(Model model){
        model.addAttribute("stats", boardService.getStats());
        return "board/stats";
    }


}
