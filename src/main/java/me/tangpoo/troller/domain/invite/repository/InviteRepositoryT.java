package me.tangpoo.troller.domain.invite.repository;

import java.util.List;
import me.tangpoo.troller.domain.invite.entity.Invite;
import me.tangpoo.troller.domain.member.entity.Member;

public interface InviteRepositoryT {

    List<Invite> findAllByMember(Member register);

    boolean existsByBoard_BoardIdAndMember_Username(Long boardId, String username);

    Invite findByBoard_BoardIdAndMember_MemberId(Long boardId, Long memberId);
}
