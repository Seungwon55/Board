package hello.board.comments.service;

import hello.board.board.dao.BoardDAO;
import hello.board.comments.dao.CommentsDAO;
import hello.board.comments.dto.CommentsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class CommentsServiceImpl implements CommentsService {

    private final BoardDAO boardDAO;
    private final CommentsDAO commentsDAO;

    @Autowired
    public CommentsServiceImpl(BoardDAO boardDAO, CommentsDAO commentsDAO) {
        this.boardDAO = boardDAO;
        this.commentsDAO = commentsDAO;
    }

    @Override
    public int getCount(Integer bno) throws Exception {
        return commentsDAO.count(bno);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int remove(Integer cno, Integer bno, Integer member_id) throws Exception {
        boardDAO.updateCommentCnt(bno, -1);
        return commentsDAO.delete(Map.of("cno", cno, "member_id", member_id));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int write(CommentsDTO commentsDTO) throws Exception {
        boardDAO.updateCommentCnt(commentsDTO.getBno(), 1);
        return commentsDAO.insert(commentsDTO);
    }

    @Override
    public List<CommentsDTO> getList(Integer bno) throws Exception {
        return commentsDAO.selectAll(bno);
    }

    @Override
    public CommentsDTO read(Integer cno) throws Exception {
        return commentsDAO.select(cno);
    }

    @Override
    public int modify(CommentsDTO commentsDTO) throws Exception {
        return commentsDAO.update(commentsDTO);
    }
}
