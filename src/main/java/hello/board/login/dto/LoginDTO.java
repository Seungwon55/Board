package hello.board.login.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class LoginDTO {

    private String member_id;
    private String pw;
    private boolean remember;
}
