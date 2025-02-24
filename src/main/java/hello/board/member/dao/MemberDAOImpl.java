package hello.board.member.dao;

import hello.board.member.dto.MemberDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MemberDAOImpl implements MemberDAO {

    private final String namespace = "hello.board.member.dao.MemberMapper.";
    private final SqlSession sql;

    @Autowired
    public MemberDAOImpl(SqlSession sql) {
        this.sql = sql;
    }


    @Override
    public int insert(MemberDTO memberDTO) throws Exception {
        return sql.insert(namespace + "insert", memberDTO);
    }

    @Override
    public MemberDTO select(Integer id) throws Exception {
        return sql.selectOne(namespace + "select", id);
    }

    @Override
    public MemberDTO selectById(String login_id) {
        return sql.selectOne(namespace + "selectById", login_id);
    }

    @Override
    public int update(MemberDTO memberDTO) throws Exception {
        return sql.update(namespace + "update", memberDTO);
    }

    @Override
    public int delete(Integer id) throws Exception {
        return sql.delete(namespace + "delete", id);
    }

    @Override
    public List<MemberDTO> selectAll() throws Exception {
        return sql.selectList(namespace + "selectAll");
    }
}
