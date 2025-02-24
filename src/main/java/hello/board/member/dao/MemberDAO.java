package hello.board.member.dao;

import hello.board.member.dto.MemberDTO;

import java.util.List;

public interface MemberDAO {
    MemberDTO select(Integer id) throws Exception;
    MemberDTO selectById(String login_id) throws Exception;
    int insert(MemberDTO memberDTO) throws Exception;
    int update(MemberDTO memberDTO) throws Exception;
    int delete(Integer id) throws Exception;
    List<MemberDTO> selectAll() throws Exception;

}