package net.slipp.service.qna;

import javax.annotation.Resource;

import net.slipp.domain.qna.Answer;
import net.slipp.domain.qna.QnaSpecifications;
import net.slipp.domain.qna.Question;
import net.slipp.domain.qna.QuestionDto;
import net.slipp.domain.user.SocialUser;
import net.slipp.repository.qna.AnswerRepository;
import net.slipp.repository.qna.QuestionRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service("qnaService")
@Transactional
public class QnaService {
	@Resource(name = "questionRepository")
	private QuestionRepository questionRepository;

	@Resource(name = "answerRepository")
	private AnswerRepository answerRepository;

	public Question createQuestion(QuestionDto questionDto) {
		Assert.notNull(questionDto, "question should be not null!");

		Question newQuestion = new Question(questionDto.getTitle(), questionDto.getContents());
		Question savedQuestion = questionRepository.save(newQuestion);
		return savedQuestion;
	}

	public Question updateQuestion(QuestionDto questionDto) {
		Assert.notNull(questionDto, "question should be not null!");

		Question question = questionRepository.findOne(questionDto.getQuestionId());

		question.update(questionDto.getTitle(), questionDto.getContents());
		return question;
	}

	public void deleteQuestion(Long questionId) {
		Assert.notNull(questionId, "questionId should be not null!");

		Question question = questionRepository.findOne(questionId);
		question.delete();
	}

	public Question showQuestion(Long id) {
		
		Question question = questionRepository.findOne(id);
		question.show();

		return question;
	}

	public Page<Question> findsQuestion(Pageable pageable) {
		return questionRepository.findAll(QnaSpecifications.equalsIsDelete(false), pageable);
	}

	public Question findByQuestionId(Long id) {
		return questionRepository.findOne(id);
	}

	public Answer findAnswerById(Long answerId) {
		return answerRepository.findOne(answerId);
	}

	public void createAnswer(Long questionId, Answer answer) {
		Question question = questionRepository.findOne(questionId);
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
		Question question = questionRepository.findOne(questionId);
		question.deAnswered();
	}
}
