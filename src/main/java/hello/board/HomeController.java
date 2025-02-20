package hello.board;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
public class HomeController {

    @GetMapping
    public String home(@SessionAttribute(name = "memberId", required = false) String memberId, Model model) {
        model.addAttribute("memberId", memberId);

        return "home";
    }
}
