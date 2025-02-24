package hello.board.comments.service;

import hello.board.comments.dto.CommentsDTO;

import java.util.List;

public interface CommentsService {
    public int getCount(Integer bno) throws Exception;
    public int remove(Integer cno, Integer bno, Integer member_id) throws Exception;
    public int write(CommentsDTO commentDto) throws Exception;
    public List<CommentsDTO> getList(Integer bno) throws Exception;
    public CommentsDTO read(Integer cno) throws Exception;
    public int modify(CommentsDTO commentDto) throws Exception;
}
