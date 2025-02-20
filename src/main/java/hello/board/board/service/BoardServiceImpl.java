package hello.board.board.service;

import hello.board.board.dao.BoardDAO;
import hello.board.board.dto.BoardDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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
    public int remove(Integer bno, String writer) throws Exception {
        return boardDAO.delete(bno, writer);
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
}