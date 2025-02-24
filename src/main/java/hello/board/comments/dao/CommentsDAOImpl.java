package hello.board.comments.dao;

import hello.board.comments.dto.CommentsDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class CommentsDAOImpl implements CommentsDAO {

    private final String namespace = "hello.board.board.dao.CommentsMapper.";
    private final SqlSession sql;

    @Autowired
    public CommentsDAOImpl(SqlSession sql) {
        this.sql = sql;
    }

    @Override
    public List<CommentsDTO> selectAll(Integer bno) throws Exception {
        return sql.selectList(namespace + "selectAll", bno);
    }

    @Override
    public int insert(CommentsDTO commentsDTO) throws Exception {
        return sql.insert(namespace + "insert", commentsDTO);
    }

    @Override
    public int deleteAll(Integer bno) throws Exception {
        return sql.delete(namespace + "deleteAll", bno);
    }

    @Override
    public int count(Integer bno) throws Exception {
        return sql.selectOne(namespace + "count", bno);
    }

    @Override
    public CommentsDTO select(Integer cno) throws Exception {
        return sql.selectOne(namespace + "select", cno);
    }

    @Override
    public int update(CommentsDTO commentsDTO) throws Exception {
        return sql.update(namespace + "update", commentsDTO);
    }

    @Override
    public int delete(Map<String, Integer> map) throws Exception {
        return sql.delete(namespace + "delete", map);
    }
}
