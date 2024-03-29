package me.tangpoo.troller.domain.invite.repository;

import java.util.List;
import java.util.Optional;
import me.tangpoo.troller.domain.board.entity.Board;
import me.tangpoo.troller.domain.invite.entity.Invite;
import me.tangpoo.troller.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InviteRepository extends JpaRepository<Invite, Long> {

    List<Invite> findAllByMember(Member register);

    boolean existsByBoard_BoardIdAndMember_Username(Long boardId, String username);

    Invite findByBoard_BoardIdAndMember_MemberId(Long boardId, Long memberId);
}
