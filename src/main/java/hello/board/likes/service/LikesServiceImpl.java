package hello.board.likes.service;

import hello.board.likes.dao.LikesDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LikesServiceImpl implements LikesService {

    private final LikesDAO likesDAO;

    @Autowired
    public LikesServiceImpl(LikesDAO likesDAO) {
        this.likesDAO = likesDAO;
    }

    @Override
    public int getLikesCnt(Integer bno, Integer memberId) throws Exception {
        return likesDAO.selectLikesCnt(bno, memberId);
    }

    @Override
    public int write(Integer bno, Integer memberId) throws Exception {
        return likesDAO.insert(bno, memberId);
    }

    @Override
    public int delete(Integer bno, Integer memberId) throws Exception {
        return likesDAO.delete(bno, memberId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int modifyLikes(Integer bno, Integer memberId) throws Exception {
        int likesCnt = likesDAO.selectLikesCnt(bno, memberId);

        if (likesCnt == 1)
            return likesDAO.delete(bno, memberId);
        else
            return likesDAO.insert(bno, memberId);
    }
}
