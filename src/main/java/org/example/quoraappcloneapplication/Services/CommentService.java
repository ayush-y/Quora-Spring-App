package org.example.quoraappcloneapplication.Services;

import org.example.quoraappcloneapplication.dtos.CommentDTO;
import org.example.quoraappcloneapplication.modles.Answer;
import org.example.quoraappcloneapplication.modles.Comment;
import org.example.quoraappcloneapplication.repositories.AnswerRepository;
import org.example.quoraappcloneapplication.repositories.CommentRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    private CommentRepository commentRepository;

    private AnswerRepository answerRepository;

    public CommentService(CommentRepository commentRepository, AnswerRepository answerRepository){

        this.commentRepository = commentRepository;
        this.answerRepository = answerRepository;
    }

    public List<Comment> getCommentsByAnswerId(Long answerId, int page, int size){

        return commentRepository.findByAnswerId(answerId, PageRequest.of(page, size)).getContent();
    }

    public List<Comment> getRepliesByCommentId(Long commentId, int page, int size){
        return commentRepository.findByParentCommentId(commentId, PageRequest.of(page, size)).getContent();
    }

    public Optional<Comment> getCommentById(Long id){
        return commentRepository.findById(id);
    }
    public void deleteCommentById(Long id){
        commentRepository.deleteById(id);;
    }
    public Comment createComment(CommentDTO commentDTO){

        Comment comment = new Comment();
        comment.setContent(commentDTO.getContent());

        Optional<Answer> answer = answerRepository.findById(commentDTO.getAnswerId());
        answer.ifPresent(comment::setAnswer);

        if(commentDTO.getParentCommentId() != null){

            Optional<Comment> parentComment = commentRepository.findById(commentDTO.getParentCommentId());
            parentComment.ifPresent(comment::setParentComment);
        }

        return commentRepository.save(comment);
    }
}
