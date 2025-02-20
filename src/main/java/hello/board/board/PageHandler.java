package hello.board.board;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class PageHandler {
    private int totalCnt;
    private int pageSize;
    private int totalPage;
    private int naviSize = 10;
    private int beginPage;
    private int endPage;
    private int page;
    boolean showPrev;
    boolean showNext;

    public PageHandler(int totalCnt, int page) {
        this(totalCnt, page, 10);
    }

    public PageHandler(int totalCnt, int page, int pageSize) {
        this.totalCnt = totalCnt;
        this.page = page;
        this.pageSize = pageSize;

        totalPage = (int) Math.ceil((double)totalCnt / pageSize);
        beginPage = ((page - 1) / naviSize) * naviSize + 1;
        endPage = Math.min(beginPage + naviSize - 1, totalPage);
        showPrev = beginPage != 1;
        showNext = (endPage != totalPage);
    }

    // navigation 출력 메서드
    void print() {
        System.out.println("page = " + page);
        System.out.println(showPrev ? "[Prev]" : "");

        for (int i = beginPage; i <= endPage; i++) {
            System.out.print(i + " ");
        }

        System.out.println(showNext ? "[Next]" : "");
    }
}
