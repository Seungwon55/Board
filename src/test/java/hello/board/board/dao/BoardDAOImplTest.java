package hello.board.board.dao;

import hello.board.board.dto.BoardDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BoardDAOImplTest {

    @Autowired
    BoardDAO boardDAO;

    @Test
    @Transactional
    public void insertTest() throws Exception {
        BoardDTO boardDTO = new BoardDTO("TDD 작성을 위한 게시글", "Java에서 테스트 중 입니다.", "Java");
        int rowCnt = boardDAO.insert(boardDTO);

        assertEquals(rowCnt, 1);
    }

    @Test
    @Transactional(rollbackFor = Exception.class)
    public void deleteTest() throws Exception {
        int rowCnt = boardDAO.delete(3, "Spring");

        assertEquals(rowCnt, 1);
    }

    @Test
    @Transactional
    public void updateTest() throws Exception {
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setBno(3);
        boardDTO.setWriter("Spring");

        boardDAO.update(boardDTO);

        BoardDTO selectDTO = boardDAO.select(3);

        assertEquals(selectDTO.getWriter(), boardDTO.getWriter());
    }

    @Test
    @Transactional
    public void increaseViewCntTest() throws Exception {
        int bno = 1;

        BoardDTO before = boardDAO.select(bno);
        boardDAO.increaseViewCnt(bno);

        BoardDTO after = boardDAO.select(bno);
        assertEquals(before.getView_cnt() + 1, after.getView_cnt());
    }

    @Test
    public void selectAllTest() throws Exception {
        List<BoardDTO> boardDTOList = boardDAO.selectAll();

        assertEquals(boardDTOList.size(), 2);
    }

    @Test
    @Transactional
    public void deleteAllTest() throws Exception {
        boardDAO.deleteAll();
        List<BoardDTO> boardDTOList = boardDAO.selectAll();

        assertEquals(boardDTOList.size(), 0);
    }

    @Test
    public void countTest() throws Exception {
        int count = boardDAO.count();

        assertEquals(count, 2);
    }

    @Test
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
    }
}