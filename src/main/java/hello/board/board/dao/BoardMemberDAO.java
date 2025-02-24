package hello.board.board.dao;

import hello.board.board.SearchCondition;
import hello.board.board.dto.BoardMemberDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BoardMemberDAO {

    private final String namespace = "hello.board.board.dao.BoardMemberMapper.";
    private final SqlSession sql;

    @Autowired
    public BoardMemberDAO(SqlSession sql) {
        this.sql = sql;
    }

    public int selectResultCnt(SearchCondition sc) throws Exception {
        return sql.selectOne(namespace + "selectResultCnt", sc);
    }

    public List<BoardMemberDTO> selectResultList(SearchCondition sc) throws Exception {
        return sql.selectList(namespace + "selectResultList", sc);
    }
}
