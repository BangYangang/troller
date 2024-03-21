package me.tangpoo.troller.domain.pic.repository;

import me.tangpoo.troller.domain.pic.entity.Pic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PicRepository extends JpaRepository<Pic, Long> {

  boolean existsByCard_IdAndMember_Username(Long cardId, String username);
}
