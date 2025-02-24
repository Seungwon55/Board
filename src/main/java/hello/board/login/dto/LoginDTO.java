package hello.board.login.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class LoginDTO {

    private String login_id;
    private String pw;
    private boolean remember;
}
