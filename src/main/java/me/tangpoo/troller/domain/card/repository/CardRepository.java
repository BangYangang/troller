package me.tangpoo.troller.domain.card.repository;

import me.tangpoo.troller.domain.card.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long> {

}
