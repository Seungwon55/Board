package hello.board.board.service;

import hello.board.board.SearchCondition;
import hello.board.board.dao.BoardDAO;
import hello.board.board.dto.BoardDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class BoardServiceImpl implements BoardService {

    private final BoardDAO boardDAO;

    @Autowired
    public BoardServiceImpl(BoardDAO boardDAO) {
        this.boardDAO = boardDAO;
    }

    @Override
    public int getCount() throws Exception {
        return boardDAO.count();
    }

    @Override
    public int remove(Integer bno, Integer member_id) throws Exception {
        return boardDAO.delete(bno, member_id);
    }

    @Override
    public int write(BoardDTO boardDto) throws Exception {
        return boardDAO.insert(boardDto);
    }

    @Override
    public List<BoardDTO> getList() throws Exception {
        return boardDAO.selectAll();
    }

    @Override
    public BoardDTO read(Integer bno) throws Exception {
        BoardDTO boardDTO = boardDAO.select(bno);
        boardDAO.increaseViewCnt(bno);

        return boardDTO;
    }

    @Override
    public List<BoardDTO> getPage(Map<String, Integer> map) throws Exception {
        return boardDAO.selectPage(map);
    }

    @Override
    public int modify(BoardDTO boardDto) throws Exception {
        return boardDAO.update(boardDto);
    }

    @Override
    public int increaseViewCnt(Integer bno) throws Exception {
        return boardDAO.increaseViewCnt(bno);
    }

    @Override
    public boolean isOwner(Integer bno, Integer loginMemberId) throws Exception {
        BoardDTO boardDTO = boardDAO.select(bno);
        Integer boardMemberId = boardDTO.getMember_id();

        return Objects.equals(boardMemberId, loginMemberId);
    }

    @Override
    public int getResultCnt(SearchCondition sc) throws Exception {
        return boardDAO.selectResultCnt(sc);
    }

    @Override
    public List<BoardDTO> getResultList(SearchCondition sc) throws Exception {
        return boardDAO.selectAllByCondition(sc);
    }

}