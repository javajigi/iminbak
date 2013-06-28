package net.slipp.service.board;

import java.util.List;

import javax.annotation.Resource;

import net.slipp.domain.board.Answer;
import net.slipp.domain.board.Board;
import net.slipp.domain.board.BoardDto;
import net.slipp.domain.board.BoardSpecifications;
import net.slipp.domain.board.BoardType;
import net.slipp.repository.board.AnswerRepository;
import net.slipp.repository.board.BoardRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service("boardService")
@Transactional
public class BoardService {
	@Resource(name = "boardRepository")
	private BoardRepository boardRepository;

	@Resource(name = "answerRepository")
	private AnswerRepository answerRepository;
	
    @Resource(name = "passwordEncoder")
    private PasswordEncoder passwordEncoder;

	public Board createBoard(BoardDto boardDto, String ipaddress) {
		Assert.notNull(boardDto, "question should be not null!");

		Board newBoard = boardDto.createBoard(passwordEncoder, ipaddress);
		Board savedBoard = boardRepository.save(newBoard);
		return savedBoard;
	}

	public Board updateBoard(BoardDto boardDto) {
		Assert.notNull(boardDto, "question should be not null!");

		Board board = boardRepository.findOne(boardDto.getBoardId());
		String encodedPassword = passwordEncoder.encodePassword(boardDto.getPassword(), null);
		board.update(boardDto.getName(), encodedPassword, boardDto.getTitle(), boardDto.getContents());
		return board;
	}

	public void deleteBoard(Long boardId, String password) {
		Assert.notNull(boardId, "questionId should be not null!");

		Board board = boardRepository.findOne(boardId);
		board.delete(passwordEncoder.encodePassword(password, null));
	}
	
	public void deleteBoard(Long boardId) {
		Assert.notNull(boardId, "questionId should be not null!");
		Board board = boardRepository.findOne(boardId);
		List<Answer> answers = answerRepository.findByBoard(board);
		for (Answer answer : answers) {
			answerRepository.delete(answer);
		}
		boardRepository.delete(board);
	}

	public Board showBoard(Long id) {
		Board question = boardRepository.findOne(id);
		question.show();

		return question;
	}

	public Page<Board> findsBoard(BoardType boardType, Pageable pageable) {
		return boardRepository.findAll(BoardSpecifications.findBoards(boardType, false), pageable);
	}

	public Board findByBoardId(Long id) {
		return boardRepository.findOne(id);
	}

	public Answer findAnswerById(Long answerId) {
		return answerRepository.findOne(answerId);
	}

	public void createAnswer(Long questionId, Answer answer) {
		Board board = boardRepository.findOne(questionId);
		answer.answerTo(board);
		answer.encodePassword(passwordEncoder);
		answerRepository.save(answer);
	}

	public void updateAnswer(Answer answerDto) {
		Answer answer = answerRepository.findOne(answerDto.getAnswerId());
		answer.updateAnswer(answerDto, passwordEncoder);
	}

	public void deleteAnswer(Long boardId, Long answerId, String password) {
		Assert.notNull(boardId, "questionId should be not null!");
		Assert.notNull(answerId, "answerId should be not null!");

		Answer answer = answerRepository.findOne(answerId);
		String encodedPassword = passwordEncoder.encodePassword(password, null);
		if (!answer.isSamePassword(encodedPassword)) {
			 throw new AccessDeniedException("Password mismatch");
		}
		
		answerRepository.delete(answer);
		Board board = boardRepository.findOne(boardId);
		board.deAnswered();
	}
}
