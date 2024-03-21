package me.tangpoo.troller.domain.invite.repository;

import static me.tangpoo.troller.domain.invite.entity.QInvite.invite;

import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import me.tangpoo.troller.domain.invite.entity.Invite;
import me.tangpoo.troller.domain.member.entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class InviteRepositoryImpl extends QuerydslRepositorySupport implements InviteRepositoryT {

    @Autowired
    private JPAQueryFactory queryFactory;

    public InviteRepositoryImpl() {
        super(Invite.class);
    }

    @Override
    public List<Invite> findAllByMember(Member register) {
        return queryFactory.selectFrom(invite)
            .where(invite.member.eq(register))
            .fetch();
    }

    @Override
    public boolean existsByBoard_BoardIdAndMember_Username(Long boardId, String username) {
        return queryFactory.selectOne()
            .from(invite)
            .where(invite.board.boardId.eq(boardId)
                .and(invite.member.username.eq(username)))
            .fetchFirst() != null;
    }

    @Override
    public Invite findByBoard_BoardIdAndMember_MemberId(Long boardId, Long memberId) {
        return queryFactory.selectFrom(invite)
            .where(invite.board.boardId.eq(boardId)
                .and(invite.member.memberId.eq(memberId)))
            .fetchFirst();
    }
}
