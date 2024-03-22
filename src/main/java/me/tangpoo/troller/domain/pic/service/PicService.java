package me.tangpoo.troller.domain.pic.service;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import me.tangpoo.troller.domain.card.entity.Card;
import me.tangpoo.troller.domain.card.repository.CardRepository;
import me.tangpoo.troller.domain.invite.repository.InviteRepository;
import me.tangpoo.troller.domain.member.entity.Member;
import me.tangpoo.troller.domain.member.repository.MemberRepository;
import me.tangpoo.troller.domain.pic.dto.PicRequestDto;
import me.tangpoo.troller.domain.pic.entity.Pic;
import me.tangpoo.troller.domain.pic.repository.PicRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PicService {

  private final MemberRepository memberRepository;
  private final PicRepository picRepository;
  private final CardRepository cardRepository;
  private final InviteRepository inviteRepository;
  public String join(Long boardId, Long cardId, String username, PicRequestDto dto) {
    userValidate(boardId, dto.getUsername());
    picValidate(cardId, dto.getUsername());

    Member member = findMemberBy(dto.getUsername());
    Card card = findCardBy(cardId);

    picRepository.save(new Pic(member, card));

    return "작업 초대를 성공하였습니다.";
  }

  private void picValidate(Long cardId, String username) {
    if(picRepository.existsByCard_IdAndMember_Username(cardId, username)){
      throw new EntityExistsException("이미 속해있는 작업자입니다.");
    }
  }


  private Card findCardBy(Long cardId) {
    return cardRepository.findById(cardId).orElseThrow(
        () -> new EntityNotFoundException("해당 카드는 존재하지 않습니다.")
    );
  }

  private Member findMemberBy(String username) {
    return memberRepository.findByUsername(username).orElseThrow(
        () -> new EntityNotFoundException("해당 유저는 존재하지 않습니다.")
    );
  }

  private void userValidate(Long boardId, String username) {
    if(!inviteRepository.existsByBoard_BoardIdAndMember_Username(boardId, username)){
      throw new EntityExistsException("권한이 없거나, 해당 보드에 속해있지 않습니다.");
    }
  }
}
