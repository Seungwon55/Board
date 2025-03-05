package hello.board.likes.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface LikesMapper {
    int selectLikesCnt(Map<String, Integer> map);
    int insert(Map<String, Integer> map);
    int delete(Map<String, Integer> map);
}
