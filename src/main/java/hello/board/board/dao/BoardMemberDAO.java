package hello.board.board.dao;

import hello.board.board.SearchCondition;
import hello.board.board.dto.BoardMemberDTO;
import hello.board.board.mapper.BoardMemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BoardMemberDAO {

    private final BoardMemberMapper mapper;

    @Autowired
    public BoardMemberDAO(BoardMemberMapper mapper) {
        this.mapper = mapper;
    }

    public int selectResultCnt(SearchCondition sc) throws Exception {
        return mapper.selectResultCnt(sc);
    }

    public List<BoardMemberDTO> selectResultList(SearchCondition sc) throws Exception {
        return mapper.selectResultList(sc);
    }
}
