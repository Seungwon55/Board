package hello.board.board.dao;

import hello.board.comments.dto.CommentsDTO;
import hello.board.comments.dao.CommentsDAO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CommentsDAOImplTest {

    @Autowired
    CommentsDAO commentsDAO;

    @Test
    @Transactional
    public void insertTest() throws Exception {
        CommentsDTO commentsDTO = new CommentsDTO(1719, "TDD라는 말처럼 테스트를 생활화 합시다.", 43);

        int rowCnt = commentsDAO.insert(commentsDTO);

        assertEquals(rowCnt, 1);
    }

    @Test
    public void selectTest() throws Exception {
        CommentsDTO commentsDTO = commentsDAO.select(1);

        assertEquals(commentsDTO.getComments(), "첫 댓글입니다.");
    }

    @Test
    @Transactional
    public void updateTest() throws Exception {
        int cno = 1;
        String comments = "업데이트";

        CommentsDTO commentsDTO = new CommentsDTO(null, comments, 12);
        commentsDTO.setCno(cno);

        int update = commentsDAO.update(commentsDTO);

        assertEquals(commentsDTO.getComments(), comments);
    }

    @Test
    @Transactional
    public void deleteTest() throws Exception {
        int cno = 1;
        int member_id = 12;

        Map<String, Integer> map = new HashMap<>();
        map.put("cno", cno);
        map.put("member_id", member_id);

        int delete = commentsDAO.delete(map);
        CommentsDTO commentsDTO = commentsDAO.select(cno);

        assertNull(commentsDTO);
    }
}