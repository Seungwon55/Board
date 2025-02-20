package hello.board;

import hello.board.board.PageHandler;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PageHandlerTest {

    @Test
    public void test() {
        PageHandler pageHandler = new PageHandler(250, 1);

        assertEquals(pageHandler.getBeginPage(), 1);
        assertEquals(pageHandler.getEndPage(), 10);
        assertFalse(pageHandler.isShowPrev());
        assertTrue(pageHandler.isShowNext());
    }

    /*@Test
    public void test2() {
        PageHandler pageHandler = new PageHandler(255, 25);

        assertEquals(pageHandler.getBeginPage(), 21);
        assertEquals(pageHandler.getEndPage(), 26);
        assertFalse(pageHandler.isShowPrev());
        assertTrue(pageHandler.isShowNext());
    }*/

    /*@Test
    public void test3() {
        PageHandler pageHandler = new PageHandler(255, 20);

        assertEquals(pageHandler.getBeginPage(), 11);
        assertEquals(pageHandler.getEndPage(), 20);
        assertFalse(pageHandler.isShowPrev());
        assertTrue(pageHandler.isShowNext());
    }*/
}