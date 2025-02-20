package hello.board.board.service;

import hello.board.board.dto.BoardDTO;

import java.util.List;
import java.util.Map;

public interface BoardService {
    int getCount() throws Exception;
    int remove(Integer bno, String writer) throws Exception;
    int write(BoardDTO boardDto) throws Exception;
    List<BoardDTO> getList() throws Exception;
    BoardDTO read(Integer bno) throws Exception;
    List<BoardDTO> getPage(Map<String, Integer> map) throws Exception;
    int modify(BoardDTO boardDto) throws Exception;
}
