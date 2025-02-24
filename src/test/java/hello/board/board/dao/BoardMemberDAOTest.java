package hello.board.board.dao;

import hello.board.board.PageHandler;
import hello.board.board.SearchCondition;
import hello.board.board.dto.BoardMemberDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BoardMemberDAOTest {

    @Autowired
    BoardMemberDAO boardMemberDAO;

    @Test
    public void selectResultCntTest() throws Exception {
        SearchCondition sc = new SearchCondition(1, "W", "멤버");
        int resultCnt = boardMemberDAO.selectResultCnt(sc);

        assertEquals(resultCnt, 256);
    }

    @Test
    public void selectTest() throws Exception {
        SearchCondition sc = new SearchCondition(7, "T", "아메");
        int resultCnt = boardMemberDAO.selectResultCnt(sc);
        PageHandler ph = new PageHandler(resultCnt, sc);

        List<BoardMemberDTO> boardMemberDTOList = boardMemberDAO.selectResultList(sc);

        assertEquals(boardMemberDTOList.size(), 4);
    }
}