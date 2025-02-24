package hello.board.board.controller;

import hello.board.board.PageHandler;
import hello.board.board.SearchCondition;
import hello.board.board.aop.LoginCheck;
import hello.board.board.dto.BoardDTO;
import hello.board.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/list")
    public String boardList(@ModelAttribute SearchCondition sc, Model model) {
        // 로그인이 되어 있다면, 게시물 목록 보여주기
        try {
            // 해당 페이지의 게시물을 가져오기 위한 변수 세팅
            int totalCnt = boardService.getResultCnt(sc);

            PageHandler ph = new PageHandler(totalCnt, sc);

            // 해당 페이지의 게시물 가져오기
            List<BoardDTO> boardDTOList = boardService.getResultList(sc);

            model.addAttribute("pageHandler", ph);
            model.addAttribute("boardList", boardDTOList);
        } catch (Exception e) {
            System.out.println("에러 : " + e.getMessage());
        }

        return "board/boardList";
    }

    @GetMapping("/read/{bno}")
    @LoginCheck // 로그인이 되어 있지 않다면 로그인 화면으로 이동, AOP
    public String boardRead(@PathVariable Integer bno, Model model, @RequestParam(required = false, defaultValue = "1") Integer page) {

        try {
            BoardDTO boardDTO = boardService.read(bno);

            model.addAttribute("board", boardDTO);
            model.addAttribute("page", page);
        } catch (Exception e) {
            System.out.println("에러 : " + e.getMessage());
        }

        return "board/boardDetail";
    }

    @PostMapping("/delete/{bno}")
    public String boardDelete(@SessionAttribute Integer memberId,
                              @PathVariable Integer bno, RedirectAttributes redirectAttributes) {
        try {
            // 게시물 작성자 id와 로그인 id가 다르다면 메세지와 함께 상세 페이지로 이동
            if (!boardService.isOwner(bno, memberId)) {
                redirectAttributes.addFlashAttribute("message", "회원 님의 게시물이 아닙니다.");

                return "redirect:/board/read/{bno}";
            }
            // 같다면 게시물 삭제
            boardService.remove(bno, memberId);
        } catch (Exception e) {
            System.out.println("에러 : " + e.getMessage());
        }

        return "redirect:/board/list";
    }

    @GetMapping("/update/{bno}")
    @LoginCheck
    public String boardUpdateForm(@PathVariable Integer bno, @SessionAttribute Integer memberId,
                                  RedirectAttributes redirectAttributes, Model model) {
        try {
            if (!boardService.isOwner(bno, memberId)) {
                redirectAttributes.addFlashAttribute("message", "회원 님의 게시물이 아닙니다.");

                return "redirect:/board/read/{bno}";
            }

            BoardDTO boardDTO = boardService.read(bno);

            model.addAttribute("board", boardDTO);
        } catch (Exception e) {
            System.out.println("에러 : " + e.getMessage());
        }

        return "board/boardUpdateForm";
    }

    @PostMapping("/update/{bno}")
    public String boardUpdate(@ModelAttribute BoardDTO boardDTO) {
        try {
            boardService.modify(boardDTO);
        } catch (Exception e) {
            System.out.println("에러 : " + e.getMessage());
        }

        return "redirect:/board/read/{bno}";
    }

    @GetMapping("/write")
    @LoginCheck
    public String boardWriteForm(Model model) {

        return "board/boardWriteForm";
    }

    @PostMapping("/write")
    public String boardWrite(@ModelAttribute BoardDTO boardDTO) {
        try {
            boardService.write(boardDTO);
        } catch (Exception e) {
            System.out.println("에러 : " + e.getMessage());
        }

        return "redirect:/board/list";
    }
}