package hello.board.comments.dao;

import hello.board.comments.dto.CommentsDTO;

import java.util.List;
import java.util.Map;

public interface CommentsDAO {
    List<CommentsDTO> selectAll(Integer bno) throws Exception;
    int insert(CommentsDTO commentsDTO) throws Exception;
    int deleteAll(Integer bno) throws Exception;
    int count(Integer bno) throws Exception;
    CommentsDTO select(Integer cno) throws Exception;
    int update(CommentsDTO commentsDTO) throws Exception;
    int delete(Map<String, Integer> map) throws Exception;
}
