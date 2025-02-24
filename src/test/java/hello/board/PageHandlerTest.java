package hello.board;

import hello.board.board.PageHandler;
import hello.board.board.SearchCondition;
import hello.board.board.service.BoardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PageHandlerTest {

    @Autowired
    BoardService boardService;

    @Test
    public void test() {
        PageHandler pageHandler = new PageHandler(250, 1);

        assertEquals(pageHandler.getBeginPage(), 1);
        assertEquals(pageHandler.getEndPage(), 10);
        assertFalse(pageHandler.isShowPrev());
        assertTrue(pageHandler.isShowNext());
    }

    @Test
    public void showTest() throws Exception {
        int totalCnt = boardService.getCount();
        PageHandler ph = new PageHandler(totalCnt, 2);

        assertTrue(ph.isShowPrev());
    }

    @Test
    public void pageHandlerWithSearchCondition() throws Exception {
        SearchCondition sc = new SearchCondition(1, "T", "아메");
        int resultCnt = boardService.getResultCnt(sc);
        PageHandler ph = new PageHandler(resultCnt, sc);

        assertEquals(ph.getTotalPage(), 7);
        assertEquals(ph.getBeginPage(), 1);
        assertEquals(ph.getEndPage(), 7);
    }
}