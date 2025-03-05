package hello.board.likes.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LikesDAOImplTest {

    @Autowired
    LikesDAO likesDAO;

    @Test
    public void selectLikesCntTest() throws Exception {
        int bno = 1718;
        int memberId = 43;

        int likesCnt1 = likesDAO.selectLikesCnt(bno, 0);
        int likesCnt2 = likesDAO.selectLikesCnt(bno, memberId);

        assertEquals(likesCnt1, 3);
        assertEquals(likesCnt2, 1);
    }

    @Test
    @Transactional
    public void insertAndDeleteTest() throws Exception {
        int bno = 1718;
        int memberId = 12;

        int insertCnt = likesDAO.insert(bno, memberId);
        int insertLikesCnt = likesDAO.selectLikesCnt(bno, memberId);
        int deleteCnt = likesDAO.delete(bno, memberId);
        int deleteLikesCnt = likesDAO.selectLikesCnt(bno, memberId);

        assertEquals(insertCnt, 1);
        assertEquals(deleteLikesCnt, 0);
    }
}