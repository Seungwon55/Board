package hello.board.login.controller;

import hello.board.login.dto.LoginDTO;
import hello.board.member.dao.MemberDAO;
import hello.board.member.dto.MemberDTO;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/login")
public class LoginController {

    private final MemberDAO memberDAO;

    @Autowired
    public LoginController(MemberDAO memberDAO) {
        this.memberDAO = memberDAO;
    }

    @GetMapping("/login")
    public String loginForm(@CookieValue(name = "loginId", required = false) String loginId,
                            @RequestParam(required = false) String uri, Model model) {
        model.addAttribute("loginId", loginId);
        model.addAttribute("uri", uri);

        return "login/loginForm";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute LoginDTO loginDTO, RedirectAttributes redirectAttributes,
                        HttpServletRequest request, HttpServletResponse response) {
        MemberDTO memberDTO = null;

        // 입력한 아이디와 같은 회원 가져오기
        try {
            memberDTO = memberDAO.selectById(loginDTO.getLogin_id());
        } catch (Exception e) {
            System.out.println("에러 : " + e.getMessage());
        }

        // 아이디가 같은 회원이 없거나, 비밀번호가 다르면 메시지와 함께 로그인 화면으로 이동
        if (loginCheck(loginDTO, memberDTO)) {
            redirectAttributes.addFlashAttribute("message", "아이디 혹은 비밀번호가 일치하지 않습니다.");

            return "redirect:/login/login";
        }
        // 로그인 성공
        // 로그인 여부 확인을 위해 세션 저장소에 아이디 저장
        HttpSession session = request.getSession();
        session.setAttribute("memberId", memberDTO.getId());

        // 아이디 저장이 체크되어 있으면 쿠키에 아이디 저장
        Cookie cookie;
        if (loginDTO.isRemember()) {
            cookie = new Cookie("loginId", memberDTO.getLogin_id());
            cookie.setMaxAge(60 * 5);
        }
        // 아이디 저장이 체크되어 있지 않으면 쿠키 삭제
        else {
            cookie = new Cookie("loginId", null);
            cookie.setMaxAge(0);
        }
        response.addCookie(cookie);


        // 이전에 참고하려는 사이트가 있었으면 해당 사이트로 이동, 없으면 홈 화면으로 이동
        String uri = request.getParameter("uri").isBlank() ? "/" : request.getParameter("uri");

        return "redirect:" + uri;
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();

        return "redirect:/";
    }

    private static boolean loginCheck(LoginDTO loginDTO, MemberDTO memberDTO) {
        return memberDTO == null || !memberDTO.getPw().equals(loginDTO.getPw());
    }
}
