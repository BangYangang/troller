package me.tangpoo.troller.domain.member.repository;

import java.util.Optional;
import me.tangpoo.troller.domain.member.entity.Member;
import me.tangpoo.troller.domain.member.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByMember(Member member);
}
