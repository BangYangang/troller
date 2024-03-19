package me.tangpoo.troller.domain.comment.repository;

import java.util.List;
import me.tangpoo.troller.domain.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

  List<Comment> findAllByCard_Id(Long cardId);

}
