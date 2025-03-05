package hello.board.likes.service;

public interface LikesService {
    int getLikesCnt(Integer bno, Integer memberId) throws Exception;
    int write(Integer bno, Integer memberId) throws Exception;
    int delete(Integer bno, Integer memberId) throws Exception;

    int modifyLikes(Integer bno, Integer memberId) throws Exception;
}
