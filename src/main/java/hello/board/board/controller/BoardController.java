package hello.board.board.controller;

import hello.board.board.PageHandler;
import hello.board.board.SearchCondition;
import hello.board.board.aop.LoginCheck;
import hello.board.board.dto.BoardDTO;
import hello.board.board.dto.BoardMemberDTO;
import hello.board.board.service.BoardMemberService;
import hello.board.board.service.BoardService;
import hello.board.likes.service.LikesService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;
    private final BoardMemberService boardMemberService;
    private final LikesService likesService;

    @Autowired
    public BoardController(BoardService boardService, BoardMemberService boardMemberService, LikesService likesService) {
        this.boardService = boardService;
        this.boardMemberService = boardMemberService;
        this.likesService = likesService;
    }

    @GetMapping("/list")
    public String boardList(@ModelAttribute SearchCondition sc, Model model) {
        try {
            // 해당 페이지의 게시물을 가져오기 위한 변수 세팅
            int totalCnt = boardMemberService.getResultCnt(sc);

            PageHandler ph = new PageHandler(totalCnt, sc);

            // 해당 페이지의 게시물 가져오기
            List<BoardMemberDTO> boardMemberDTOList = boardMemberService.getResultList(sc);

            model.addAttribute("pageHandler", ph);
            model.addAttribute("boardMemberList", boardMemberDTOList);
        } catch (Exception e) {
            System.out.println("에러 : " + e.getMessage());
        }

        return "board/boardList";
    }

    @GetMapping("/read/{bno}")
    @LoginCheck // 로그인이 되어 있지 않다면 로그인 화면으로 이동, AOP
    public String boardRead(@PathVariable Integer bno, @RequestParam(required = false, defaultValue = "1") Integer page,
                            @SessionAttribute(required = false) Integer memberId,
                            Model model, HttpServletRequest request, HttpServletResponse response) {
        // 조회수 중복 증가 방지를 위해 쿠키 존재 확인
        int cookieCnt = 0;

        // 쿠키 이름과 값을 통해 어느 회원이 어느 게시물을 접근했는지 확인
        Cookie[] cookies = request.getCookies();
        for (Cookie c : cookies) {
            if (c.getName().equals("board_viewed_" + bno)) {
                if (c.getValue().equals(String.valueOf(memberId))) {
                    cookieCnt += 1;
                }
            }
        }

        try {
            if (cookieCnt == 0) {
                // 게시물 첫 방문 시 쿠키 생성
                Cookie cookie = new Cookie("board_viewed_" + bno, String.valueOf(memberId));
                cookie.setMaxAge(60 * 3);
                response.addCookie(cookie);

                // 조회수 증가
                boardService.increaseViewCnt(bno);
            }
            BoardDTO boardDTO = boardService.read(bno);
            // 게시글의 좋아요 개수
            int likesCnt = likesService.getLikesCnt(bno, 0);

            model.addAttribute("board", boardDTO);
            model.addAttribute("page", page);
            model.addAttribute("likesCnt", likesCnt);
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

    @PostMapping("/updateLikes/{bno}")
    public String updateLikes(@PathVariable Integer bno, @SessionAttribute Integer memberId) {
        try {
            likesService.modifyLikes(bno, memberId);
        } catch (Exception e) {
            System.out.println("에러 : " + e.getMessage());
        }

        return "redirect:/board/read/{bno}";
    }
}