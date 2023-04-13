package com.example.coursework.facade;

import com.example.coursework.dto.CommentDTO;
import com.example.coursework.entity.Comment;
import org.springframework.stereotype.Component;

@Component
public class CommentFacade {

    public CommentDTO commentToCommentDTO(Comment comment) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setCommentId(comment.getCommentId());
        commentDTO.setUser(comment.getUser());
        commentDTO.setCommentText(comment.getCommentText());

        return commentDTO;
    }

}
