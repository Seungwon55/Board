package hello.board.board.controller;

import hello.board.board.dto.BoardDTO;
import hello.board.board.service.BoardService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/list")
    public String board(@SessionAttribute(name = "memberId", required = false) String memberId,
                        RedirectAttributes redirectAttributes, HttpServletRequest request) {

        // 로그인이 되어 있지 않다면 로그인 페이지로 이동
        if (memberId == null) {
            redirectAttributes.addFlashAttribute("uri", request.getRequestURI());

            return "redirect:/login/login";
        }
        // 로그인이 되어 있다면, 게시물 목록 보여주기
        try {
            List<BoardDTO> boardList = boardService.getList();
        } catch (Exception e) {
            System.out.println("에러 : " + e.getMessage());
        }

        return "board/boardList";
    }
}
