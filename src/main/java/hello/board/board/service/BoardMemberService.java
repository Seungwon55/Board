package hello.board.board.service;

import hello.board.board.SearchCondition;
import hello.board.board.dao.BoardMemberDAO;
import hello.board.board.dto.BoardMemberDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardMemberService {

    private final BoardMemberDAO boardMemberDAO;

    @Autowired
    public BoardMemberService(BoardMemberDAO boardMemberDAO) {
        this.boardMemberDAO = boardMemberDAO;
    }

    public int getResultCnt(SearchCondition sc) throws Exception {
        return boardMemberDAO.selectResultCnt(sc);
    }

    public List<BoardMemberDTO> getResultList(SearchCondition sc) throws Exception {
        return boardMemberDAO.selectResultList(sc);
    }
}
