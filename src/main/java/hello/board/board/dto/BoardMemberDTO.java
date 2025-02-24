package hello.board.board.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@Getter @Setter
@ToString
public class BoardMemberDTO {

    private Integer bno;
    private String title;
    private String name;
    private Timestamp reg_date;
    private int view_cnt;
}
