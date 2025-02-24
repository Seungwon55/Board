package hello.board.board.service;

import hello.board.board.dto.BoardDTO;
import hello.board.comments.dto.CommentsDTO;
import hello.board.comments.service.CommentsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CommentsServiceImplTest {

    @Autowired
    CommentsService commentsService;
    @Autowired
    BoardService boardService;

    @Test
    @Transactional
    public void removeTest() throws Exception {
        int cno = 1;
        int bno = 1613;
        int member_id = 12;

        BoardDTO befofeBoardDTO = boardService.read(bno);
        int removeCnt = commentsService.remove(cno, bno, member_id);
        CommentsDTO commentsDTO = commentsService.read(bno);
        BoardDTO afterBoardDTO = boardService.read(bno);

        assertEquals(afterBoardDTO.getComment_cnt(), befofeBoardDTO.getComment_cnt() - 1);
        assertNull(commentsDTO);
    }

    @Test
    @Transactional
    public void writeTest() throws Exception {
        int bno = 1714;
        String comments = "writeTest";
        int member_id = 12;

        BoardDTO beforeBoardDTO = boardService.read(bno);
        int beforeCnt = commentsService.getCount(bno);

        CommentsDTO commentsDTO = new CommentsDTO(bno, comments, member_id);
        int writeCnt = commentsService.write(commentsDTO);

        BoardDTO afterBoardDTO = boardService.read(bno);
        int afterCnt = commentsService.getCount(bno);

        assertEquals(beforeBoardDTO.getComment_cnt() + 1, afterBoardDTO.getComment_cnt());
        assertEquals(afterCnt, beforeCnt + 1);
    }
}