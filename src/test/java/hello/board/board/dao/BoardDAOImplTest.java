package hello.board.board.dao;

import hello.board.board.PageHandler;
import hello.board.board.SearchCondition;
import hello.board.board.dto.BoardDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BoardDAOImplTest {

    @Autowired
    BoardDAO boardDAO;

    @Test
    public void selectTest() throws Exception {
        BoardDTO boardDTO = boardDAO.select(1329);
    }

    @Test
    @Transactional
    public void insertTest() throws Exception {
        BoardDTO boardDTO = new BoardDTO("TDD 작성을 위한 게시글", "Java에서 테스트 중 입니다.", 12);
        int rowCnt = boardDAO.insert(boardDTO);

        assertEquals(rowCnt, 1);
    }

    @Test
    @Transactional(rollbackFor = Exception.class)
    public void deleteTest() throws Exception {
        int rowCnt = boardDAO.delete(1329, 12);

        assertEquals(rowCnt, 1);
    }

    @Test
    @Transactional
    public void updateTest() throws Exception {
        int bno = 1329;

        BoardDTO boardDTO = boardDAO.select(bno);
        boardDTO.setContent("Spring에서 테스트 중 입니다.");

        boardDAO.update(boardDTO);
        BoardDTO selectDTO = boardDAO.select(bno);

        assertEquals(selectDTO.getContent(), boardDTO.getContent());
    }

    @Test
    @Transactional
    public void increaseViewCntTest() throws Exception {
        int bno = 1329;

        BoardDTO before = boardDAO.select(bno);
        boardDAO.increaseViewCnt(bno);

        BoardDTO after = boardDAO.select(bno);
        assertEquals(before.getView_cnt() + 1, after.getView_cnt());
    }

    @Test
    @Transactional
    public void deleteAllTest() throws Exception {
        boardDAO.deleteAll();
        List<BoardDTO> boardDTOList = boardDAO.selectAll();

        assertEquals(boardDTOList.size(), 0);
    }

/*    @Test
    @Transactional
    public void selectPage() throws Exception {
        // 테스트 데이터 131개 삽입 (boardDAO 사용)
        for (int i = 0; i < 131; i++) {
            BoardDTO boardDTO = new BoardDTO("테스트" + i, "테스트용 데이터 삽입", "asdf");
            boardDAO.insert(boardDTO);
        }

        List<BoardDTO> boardDTOList = boardDAO.selectAll();
        assertEquals(boardDTOList.size(), 133);

        // Map 생성 후 Map에 beginPage, endPage 추가
        Map<String, Integer> map = new HashMap<>();
        map.put("beginPage", 21);
        map.put("endPage", 30);

        // 페이지 테스트(boardDAO, Map 사용)
        List<BoardDTO> pageBoardDTOList = boardDAO.selectPage(map);
        assertEquals(pageBoardDTOList.size(), 10);
    }*/

    @Test
    public void selectResultCntTest() throws Exception {
        SearchCondition sc = new SearchCondition(1, "T", "아메");
        SearchCondition sc2 = new SearchCondition(1, "", "");

        int rowCnt = boardDAO.selectResultCnt(sc);
        int rowCnt2 = boardDAO.selectResultCnt(sc2);

        assertEquals(rowCnt, 64);
        assertEquals(rowCnt2, 383);
    }

    @Test
    public void selectAllByConditionTest() throws Exception {
        SearchCondition sc = new SearchCondition(1, "", "");
        int resultCnt = boardDAO.selectResultCnt(sc);
        PageHandler ph = new PageHandler(resultCnt, sc);

        System.out.println("sc = " + sc);

        List<BoardDTO> boardDTOList = boardDAO.selectAllByCondition(sc);

        assertEquals(boardDTOList.size(), 10);
    }

    @Test
    @Transactional
    public void updateCommentCntTest() throws Exception {
        int bno = 1709;

        BoardDTO beforeBoardDTO = boardDAO.select(bno);
        int rowCnt = boardDAO.updateCommentCnt(bno, -1);
        BoardDTO afterBoardDTO = boardDAO.select(bno);

        assertEquals(afterBoardDTO.getComment_cnt(), beforeBoardDTO.getComment_cnt() - 1);
    }
}