package me.tangpoo.troller.domain.pic.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.tangpoo.troller.domain.card.entity.Card;
import me.tangpoo.troller.domain.member.entity.Member;

@Entity
@NoArgsConstructor
@Getter
public class Pic {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;


  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "member_id")
  private Member member;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "card_id")
  private Card card;


  public Pic(Member member, Card card) {
    this.member = member;
    this.card = card;
  }
}
