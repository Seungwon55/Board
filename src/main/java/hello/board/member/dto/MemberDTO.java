package hello.board.member.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;
import java.sql.Timestamp;

@Getter @Setter
@ToString
public class MemberDTO {
    private Integer id;

    @Size(min = 5, max = 20, message = "아이디는 5자 이상 20자 이하로 입력해야 합니다.")
    private String member_id;

    @Size(min = 8, max = 20, message = "비밀번호는 8자 이상 20자 이하로 입력해야 합니다.")
    private String pw;

    @NotBlank(message = "이름은 빈 문자열을 허용하지 않습니다.")
    private String name;

    @Email
    private String email;

    private Date birth;

    private String sns;

    private Timestamp reg_date;

    public MemberDTO() {

    }

    public MemberDTO(String member_id, String pw, String name, String email, Date birth, String sns) {
        this.member_id = member_id;
        this.pw = pw;
        this.name = name;
        this.email = email;
        this.birth = birth;
        this.sns = sns;
    }
}
