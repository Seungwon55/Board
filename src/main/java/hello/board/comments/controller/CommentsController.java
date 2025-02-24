package hello.board.comments.controller;

import hello.board.comments.dto.CommentsDTO;
import hello.board.comments.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentsController {

    private final CommentsService commentsService;

    @Autowired
    public CommentsController(CommentsService commentsService) {
        this.commentsService = commentsService;
    }

    @GetMapping("/comments")
    public ResponseEntity<List<CommentsDTO>> read(@RequestParam Integer bno) {

        try {
            List<CommentsDTO> commentsDTOList = commentsService.getList(bno);

            return new ResponseEntity<>(commentsDTOList, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("에러 : " + e.getMessage());

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/comments")
    public ResponseEntity<String> write(@RequestParam Integer bno, @RequestBody CommentsDTO commentsDTO) {
        commentsDTO.setBno(bno);

        try {
            int writeCnt = commentsService.write(commentsDTO);
        } catch (Exception e) {
            System.out.println("에러 : " + e.getMessage());

            return new ResponseEntity<>("Write Fail", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("Write Success", HttpStatus.OK);
    }

    @PatchMapping("/comments/{cno}")
    public ResponseEntity<String> update(@PathVariable Integer cno, @RequestBody CommentsDTO commentsDTO) {
        commentsDTO.setCno(cno);

        try {
            int updateCnt = commentsService.modify(commentsDTO);
        } catch (Exception e) {
            System.out.println("에러 : " + e.getMessage());

            return new ResponseEntity<>("Update Fail", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("Update Success", HttpStatus.OK);
    }

    @DeleteMapping("/comments/{cno}")
    public ResponseEntity<String> delete(@PathVariable Integer cno, @RequestParam Integer bno) {
        try {
            int deleteCnt = commentsService.remove(cno, bno, 44);
        } catch (Exception e) {
            System.out.println("에러 : " + e.getMessage());

            return new ResponseEntity<>("Delete Fail", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("Delete Success", HttpStatus.OK);
    }
}
