package hello.board.comments.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@Getter @Setter
@ToString
@NoArgsConstructor
public class CommentsDTO {

    private Integer cno;
    private Integer bno;
    private Integer pcno;
    private String comments;
    private Integer member_id;
    private Timestamp reg_date;
    private Timestamp up_date;

    public CommentsDTO(Integer bno, String comments, Integer member_id) {
        this.bno = bno;
        this.comments = comments;
        this.member_id = member_id;
    }
}
