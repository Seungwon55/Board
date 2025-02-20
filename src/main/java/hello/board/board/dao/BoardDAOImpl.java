package hello.board.board.dao;

import hello.board.board.dto.BoardDTO;
import hello.board.member.dto.MemberDTO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class BoardDAOImpl implements BoardDAO {

    private final String namespace = "hello.board.board.dao.BoardMapper.";
    private final SqlSession sql;

    @Autowired
    public BoardDAOImpl(SqlSession sql) {
        this.sql = sql;
    }

    @Override
    public BoardDTO select(Integer bno) throws Exception {
         return sql.selectOne(namespace + "select", bno);
    }

    @Override
    public int delete(@Param("bno") Integer bno, @Param("writer") String writer) throws Exception {
        return sql.delete(namespace + "delete", Map.of("bno", bno, "writer", writer));
    }

    @Override
    public int insert(BoardDTO boardDTO) throws Exception {
        return sql.insert(namespace + "insert", boardDTO);
    }

    @Override
    public int update(BoardDTO boardDTO) throws Exception {
        return sql.update(namespace + "update", boardDTO);
    }

    @Override
    public int increaseViewCnt(Integer bno) throws Exception {
        return sql.update(namespace + "increaseViewCnt", bno);
    }

    @Override
    public List<BoardDTO> selectPage(Map<String, Integer> map) throws Exception {
        return sql.selectList(namespace + "selectPage", map);
    }

    @Override
    public List<BoardDTO> selectAll() throws Exception {
        return sql.selectList(namespace + "selectAll");
    }

    @Override
    public int deleteAll() throws Exception {
        return sql.delete(namespace + "deleteAll");
    }

    @Override
    public int count() throws Exception {
        return sql.selectOne(namespace + "count");
    }
}
