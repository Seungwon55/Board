package hello.board.board;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class SearchCondition {
    private Integer page = 1;
    private String option = "";
    private String keyword = "";
    private Integer beginRow;
    private Integer endRow;

    public SearchCondition() {

    }

    public SearchCondition(Integer page) {
        this.page = page;
    }

    public SearchCondition(Integer page, String option, String keyword) {
        this.page = page;
        this.option = option;
        this.keyword = keyword;
    }
}
