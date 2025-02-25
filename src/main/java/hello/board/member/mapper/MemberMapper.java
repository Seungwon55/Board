package hello.board.member.mapper;

import hello.board.member.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper {
    MemberDTO select(Integer id);
    MemberDTO selectById(String login_id);
    int insert(MemberDTO memberDTO);
    int update(MemberDTO memberDTO);
    int delete(Integer id);
    List<MemberDTO> selectAll();
}
