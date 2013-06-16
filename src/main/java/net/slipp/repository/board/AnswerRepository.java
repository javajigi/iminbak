package net.slipp.repository.board;

import java.util.List;

import net.slipp.domain.board.Answer;
import net.slipp.domain.board.Board;

import org.springframework.data.repository.CrudRepository;

public interface AnswerRepository extends CrudRepository<Answer, Long> {
	List<Answer> findByBoard(Board board);
}
