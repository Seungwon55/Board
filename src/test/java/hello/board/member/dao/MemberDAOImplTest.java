package hello.board.member.dao;

import hello.board.member.dto.MemberDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberDAOImplTest {

    @Autowired
    MemberDAO memberDAO;

    @Test
    public void selectTest() throws Exception {
        MemberDTO memberDTO = memberDAO.select(12);

        assertNotNull(memberDTO);
    }

    @Test
    @Transactional
    public void insertTest() throws Exception {
        MemberDTO memberDTO = new MemberDTO("qwer", "1234", "zxcv", "qwer@naver.com", Date.valueOf("1989-07-14"), "instagram");
        int rowCnt = memberDAO.insert(memberDTO);

        assertEquals(rowCnt, 1);
    }

    @Test
    public void selectByIdTest() throws Exception {
        MemberDTO memberDTO = memberDAO.selectById("spring");

        assertNotNull(memberDTO);
    }

    @Test
    @Transactional
    public void updateTest() throws Exception {
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setLogin_id("zxcv");
        memberDTO.setSns("kakaotalk");
        memberDTO.setId(12);

        int rowCnt = memberDAO.update(memberDTO);

        assertEquals(rowCnt, 1);
    }

    @Test
    @Transactional
    public void deleteTest() throws Exception {
        int delete = memberDAO.delete(12);

        assertEquals(delete, 1);
    }

    @Test
    public void selectAllTest() throws Exception {
        List<MemberDTO> memberDTOList = memberDAO.selectAll();

        assertEquals(memberDTOList.size(), 4);
    }
}