package hello.board.board.dao;

import hello.board.board.PageHandler;
import hello.board.board.SearchCondition;
import hello.board.board.dto.BoardDTO;
import hello.board.member.dto.MemberDTO;

import java.util.*;

public interface BoardDAO {
    BoardDTO select(Integer bno) throws Exception;
    int delete(Integer bno, Integer member_id) throws Exception;
    int insert(BoardDTO boardDTO) throws Exception;
    int update(BoardDTO boardDTO) throws Exception;
    int increaseViewCnt(Integer bno) throws Exception;
    List<BoardDTO> selectPage(Map<String, Integer> map) throws Exception;
    List<BoardDTO> selectAll() throws Exception;
    int deleteAll() throws Exception;
    int count() throws Exception;
    int selectResultCnt(SearchCondition sc) throws Exception;
    List<BoardDTO> selectAllByCondition(SearchCondition sc) throws Exception;
    int updateCommentCnt(Integer bno, int num) throws Exception;
}
