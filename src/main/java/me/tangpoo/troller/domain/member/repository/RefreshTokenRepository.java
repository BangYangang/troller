package me.tangpoo.troller.domain.member.repository;

import java.util.Optional;
import me.tangpoo.troller.domain.member.entity.RefreshToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefreshTokenRepository extends CrudRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByMember_MemberId(Long id);
}
