package hello.board.board.dao;

import hello.board.board.SearchCondition;
import hello.board.board.dto.BoardDTO;
import hello.board.board.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class BoardDAOImpl implements BoardDAO {

    private final BoardMapper mapper;

    @Autowired
    public BoardDAOImpl(BoardMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public BoardDTO select(Integer bno) throws Exception {
         return mapper.select(bno);
    }

    @Override
    public int delete(Integer bno, Integer member_id) throws Exception {
        return mapper.delete(Map.of("bno", bno, "member_id", member_id));
    }

    @Override
    public int insert(BoardDTO boardDTO) throws Exception {
        return mapper.insert(boardDTO);
    }

    @Override
    public int update(BoardDTO boardDTO) throws Exception {
        return mapper.update(boardDTO);
    }

    @Override
    public int increaseViewCnt(Integer bno) throws Exception {
        return mapper.increaseViewCnt(bno);
    }

    @Override
    public List<BoardDTO> selectPage(int beginRow, int endRow) throws Exception {
        return mapper.selectPage(Map.of("beginRow", beginRow, "endRow", endRow));
    }

    @Override
    public List<BoardDTO> selectAll() throws Exception {
        return mapper.selectAll();
    }

    @Override
    public int deleteAll() throws Exception {
        return mapper.deleteAll();
    }

    @Override
    public int count() throws Exception {
        return mapper.count();
    }

    @Override
    public int selectResultCnt(SearchCondition sc) throws Exception {
        return mapper.selectResultCnt(sc);
    }

    @Override
    public List<BoardDTO> selectAllByCondition(SearchCondition sc) throws Exception {
        return mapper.selectAllByCondition(sc);
    }

    @Override
    public int updateCommentCnt(Integer bno, int num) throws Exception {
        return mapper.updateCommentCnt(Map.of("bno", bno, "num", num));
    }
}
