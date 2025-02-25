package hello.board.board.mapper;

import hello.board.board.SearchCondition;
import hello.board.board.dto.BoardMemberDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMemberMapper {
    int selectResultCnt(SearchCondition sc);
    List<BoardMemberDTO> selectResultList(SearchCondition sc);
}
