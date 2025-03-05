package hello.board.likes.dao;

import hello.board.likes.mapper.LikesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class LikesDAOImpl implements LikesDAO {

    private final LikesMapper mapper;

    @Autowired
    public LikesDAOImpl(LikesMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public int selectLikesCnt(Integer bno, Integer memberId) throws Exception {
        return mapper.selectLikesCnt(Map.of("bno", bno, "memberId", memberId));
    }

    @Override
    public int insert(Integer bno, Integer memberId) throws Exception {
        return mapper.insert(Map.of("bno", bno, "memberId", memberId));
    }

    @Override
    public int delete(Integer bno, Integer memberId) throws Exception {
        return mapper.delete(Map.of("bno", bno, "memberId", memberId));
    }
}
