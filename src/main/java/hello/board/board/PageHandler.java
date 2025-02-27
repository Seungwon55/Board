package hello.board.board;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class PageHandler {
    private SearchCondition sc;
    private int totalCnt;
    private int totalPage;
    private int pageSize = 10;
    private int naviSize = 10;
    private int beginPage;
    private int endPage;
    private int beginRow;
    private int endRow;
    private boolean showPrev;
    private boolean showNext;

    public PageHandler(int totalCnt, int page) {
        this(totalCnt, new SearchCondition(page));
    }

    public PageHandler(int totalCnt, SearchCondition sc) {
        this.totalCnt = totalCnt;
        this.sc = sc;

        calculateInfo();
    }

    private void calculateInfo() {
        totalPage = (int) Math.ceil((double)totalCnt / pageSize);
        beginPage = ((sc.getPage() - 1) / naviSize) * naviSize + 1;
        endPage = Math.min(beginPage + naviSize - 1, totalPage);
        beginRow = (sc.getPage() - 1) * pageSize + 1;
        endRow = sc.getPage() * pageSize;
        showPrev = sc.getPage() != 1;
        showNext = sc.getPage() != totalPage && totalPage != 0;

        sc.setBeginRow(beginRow);
        sc.setEndRow(endRow);
    }

    // navigation 출력 메서드
    void print() {
        System.out.println("page = " + sc.getPage());
        System.out.println(showPrev ? "[Prev]" : "");

        for (int i = beginPage; i <= endPage; i++) {
            System.out.print(i + " ");
        }

        System.out.println(showNext ? "[Next]" : "");
    }
}
