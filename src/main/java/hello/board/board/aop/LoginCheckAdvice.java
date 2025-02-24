package hello.board.board.aop;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class LoginCheckAdvice {
    @Before("@annotation(LoginCheck)") // 해당 어노테이션이 붙은 메서드에 AOP 적용
    public void loginCheck() throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        HttpServletResponse response = attributes.getResponse();

        HttpSession session = request.getSession();
        Integer memberId = (Integer) session.getAttribute("memberId");

        if (memberId == null) {
            response.sendRedirect("/login/login?uri=" + request.getRequestURI());
        }
    }
}