package hello.board.board.mapper;

import hello.board.board.SearchCondition;
import hello.board.board.dto.BoardDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface BoardMapper {
    BoardDTO select(Integer bno);
    int delete(Map<String, Integer> map);
    int insert(BoardDTO boardDTO);
    int update(BoardDTO boardDTO);
    int increaseViewCnt(Integer bno);
    List<BoardDTO> selectPage(Map<String, Integer> map);
    List<BoardDTO> selectAll();
    int deleteAll();
    int count();
    int selectResultCnt(SearchCondition sc);
    List<BoardDTO> selectAllByCondition(SearchCondition sc);
    int updateCommentCnt(Map<String, Integer> map);
}