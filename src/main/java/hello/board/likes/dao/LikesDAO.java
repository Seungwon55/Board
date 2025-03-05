package hello.board.likes.dao;

import java.util.Map;

public interface LikesDAO {
    int selectLikesCnt(Integer bno, Integer memberId) throws Exception;
    int insert(Integer bno, Integer memberId) throws Exception;
    int delete(Integer bno, Integer memberId) throws Exception;
}
