package hello.board.member.dao;

import hello.board.member.dto.MemberDTO;
import hello.board.member.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MemberDAOImpl implements MemberDAO {

    private final MemberMapper mapper;

    @Autowired
    public MemberDAOImpl(MemberMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public int insert(MemberDTO memberDTO) throws Exception {
        return mapper.insert(memberDTO);
    }

    @Override
    public MemberDTO select(Integer id) throws Exception {
        return mapper.select(id);
    }

    @Override
    public MemberDTO selectById(String login_id) {
        return mapper.selectById(login_id);
    }

    @Override
    public int update(MemberDTO memberDTO) throws Exception {
        return mapper.update(memberDTO);
    }

    @Override
    public int delete(Integer id) throws Exception {
        return mapper.delete(id);
    }

    @Override
    public List<MemberDTO> selectAll() throws Exception {
        return mapper.selectAll();
    }
}
