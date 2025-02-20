package hello.board.register.controller;

import hello.board.member.dao.MemberDAO;
import hello.board.member.dto.MemberDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/register")
public class RegisterController {

    private final MemberDAO memberDAO;

    @Autowired
    public RegisterController(MemberDAO memberDAO) {
        this.memberDAO = memberDAO;
    }

    @GetMapping("/add")
    public String registerForm() {

        return "register/registerForm";
    }

    @PostMapping("/add")
    public String register(@RequestParam String pw_repeat, @Valid @ModelAttribute MemberDTO memberDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        // 데이터 검증 조건문(ex. 아이디는 5~20자)
        if (bindingResult.hasErrors()) {
            String message = bindingResult.getFieldErrors().get(0).getDefaultMessage();
            redirectAttributes.addFlashAttribute("message", message);

            return "redirect:/register/add";
        }

        // 비밀번호 확인 시 비밀번호가 다른지 확인하는 조건문
        if (!memberDTO.getPw().equals(pw_repeat)) {
            redirectAttributes.addFlashAttribute("message", "비밀번호가 다릅니다. 다시 입력해주세요.");

            return "redirect:/register/add";
        }

        // 회원 가입 성공
        try {
            memberDAO.insert(memberDTO);
        } catch (Exception e) {
            System.out.println("에러 메시지 : " + e.getMessage());
        }

        return "redirect:/login/login";
    }
}