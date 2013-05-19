package net.slipp.service.board;

import javax.annotation.Resource;

import net.slipp.domain.board.Answer;
import net.slipp.domain.board.Board;
import net.slipp.domain.board.BoardDto;
import net.slipp.domain.board.QnaSpecifications;
import net.slipp.repository.board.AnswerRepository;
import net.slipp.repository.board.BoardRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

	public Board createBoard(BoardDto boardDto) {
		Assert.notNull(boardDto, "question should be not null!");

		Board newBoard = boardDto.createBoard(passwordEncoder);
		Board savedBoard = boardRepository.save(newBoard);
		return savedBoard;
	}

	public Board updateQuestion(BoardDto boardDto) {
		Assert.notNull(boardDto, "question should be not null!");

		Board question = boardRepository.findOne(boardDto.getBoardId());

		question.update(boardDto.getTitle(), boardDto.getContents());
		return question;
	}

	public void deleteQuestion(Long questionId) {
		Assert.notNull(questionId, "questionId should be not null!");

		Board question = boardRepository.findOne(questionId);
		question.delete();
	}

	public Board showBoard(Long id) {
		
		Board question = boardRepository.findOne(id);
		question.show();

		return question;
	}

	public Page<Board> findsQuestion(Pageable pageable) {
		return boardRepository.findAll(QnaSpecifications.equalsIsDelete(false), pageable);
	}

	public Board findByQuestionId(Long id) {
		return boardRepository.findOne(id);
	}

	public Answer findAnswerById(Long answerId) {
		return answerRepository.findOne(answerId);
	}

	public void createAnswer(Long questionId, Answer answer) {
		Board question = boardRepository.findOne(questionId);
		answer.answerTo(question);
		answerRepository.save(answer);
	}

	public void updateAnswer(Answer answerDto) {
		Answer answer = answerRepository.findOne(answerDto.getAnswerId());
		answer.updateAnswer(answerDto);
	}

	public void deleteAnswer(Long questionId, Long answerId) {
		Assert.notNull(questionId, "questionId should be not null!");
		Assert.notNull(answerId, "answerId should be not null!");

		Answer answer = answerRepository.findOne(answerId);
		answerRepository.delete(answer);
		Board question = boardRepository.findOne(questionId);
		question.deAnswered();
	}
}
