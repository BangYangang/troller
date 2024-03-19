package me.tangpoo.troller.domain.board.repository;

import java.util.List;
import me.tangpoo.troller.domain.board.entity.Board;
import me.tangpoo.troller.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {

    List<Board> findAllByMember(Member register);
}
