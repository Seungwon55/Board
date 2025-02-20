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
    private String writer;
    private int view_cnt;
    private int comment_cnt;
    private Timestamp reg_date;
    private Timestamp up_date;

    public BoardDTO() {

    }

    public BoardDTO(String title, String content, String writer) {
        this.title = title;
        this.content = content;
        this.writer = writer;
    }
}
