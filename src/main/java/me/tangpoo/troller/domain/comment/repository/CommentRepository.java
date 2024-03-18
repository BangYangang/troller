package me.tangpoo.troller.domain.comment.repository;

import me.tangpoo.troller.domain.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Long, Comment> {

}
