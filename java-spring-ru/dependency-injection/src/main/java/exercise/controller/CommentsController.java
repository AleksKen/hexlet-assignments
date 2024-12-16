package exercise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.http.HttpStatus;

import java.util.List;

import exercise.model.Comment;
import exercise.repository.CommentRepository;
import exercise.exception.ResourceNotFoundException;

// BEGIN
@RestController
@RequestMapping(path = "/comments")
public class CommentsController {
    @Autowired
    private CommentRepository commentRepository;

    @GetMapping
    public List<Comment> index() {
        return commentRepository.findAll();
    }

    @GetMapping("/{id}")
    public Comment show(@PathVariable String id) {
        return commentRepository.findById(Long.parseLong(id))
                .orElseThrow(() -> new ResourceNotFoundException("Comment with id " + id + " not found"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Comment create(@RequestBody Comment comment) {
        return commentRepository.save(comment);
    }

    @PutMapping(path = "/{id}")
    public Comment update(@PathVariable String id, @RequestBody Comment comment) {
        var maybeComment = commentRepository.findById(Long.parseLong(id))
                .orElseThrow(() -> new ResourceNotFoundException("Comment with id " + id + " not found"));
        maybeComment.setId(Long.parseLong(id));
        maybeComment.setPostId(comment.getPostId());
        maybeComment.setBody(comment.getBody());
        maybeComment.setCreatedAt(comment.getCreatedAt());
        return comment;
    }

    @DeleteMapping(path = "/{id}")
    public void destroy(@PathVariable String id) {
        commentRepository.deleteById(Long.parseLong(id));
    }
}
// END
