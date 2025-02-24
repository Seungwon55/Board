package hello.board.board.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@Getter @Setter
@ToString
public class BoardDTO {
    private Integer bno;
    private String title;
    private String content;
    private Integer member_id;
    private int view_cnt;
    private int comment_cnt;
    private Timestamp reg_date;
    private Timestamp up_date;


    public BoardDTO() {

    }

    public BoardDTO(String title, String content, Integer member_id) {
        this.title = title;
        this.content = content;
        this.member_id = member_id;
    }
}
