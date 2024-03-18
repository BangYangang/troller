package me.tangpoo.troller.domain.member.repository;

import me.tangpoo.troller.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

}
