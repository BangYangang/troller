package me.tangpoo.troller.domain.board.repository;


import me.tangpoo.troller.domain.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board,Long> {

}