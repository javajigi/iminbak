package net.slipp.service.qna;

import static org.mockito.Mockito.*;
import net.slipp.domain.qna.Answer;
import net.slipp.domain.qna.Question;
import net.slipp.domain.qna.QuestionDto;
import net.slipp.domain.user.SocialUser;
import net.slipp.repository.qna.AnswerRepository;
import net.slipp.repository.qna.QuestionRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.access.AccessDeniedException;

@RunWith(MockitoJUnitRunner.class)
public class QnaServiceTest {
	@Mock
	private QuestionRepository questionRepository;
	
	@Mock
	private AnswerRepository answerRepository;
	
	@InjectMocks
	private QnaService dut = new QnaService();
	
	@Test
	public void updateQuestion_sameUser() {
		// given
		SocialUser loginUser = new SocialUser(10);
		QuestionDto dto = new QuestionDto(1L, "title", "contents", "java javascript");
		Question existedQuestion = new Question(1L, loginUser, dto.getTitle(), dto.getContents());
		when(questionRepository.findOne(dto.getQuestionId())).thenReturn(existedQuestion);
		
		// when
		dut.updateQuestion(loginUser, dto);
	}
	
	@Test
	public void deleteAnswer_sameUser() throws Exception {
		// given
		SocialUser loginUser = new SocialUser(10);
		Answer answer = new Answer(2L);
		answer.writedBy(loginUser);
		Question question = new Question(1L, loginUser, null, null);
		when(answerRepository.findOne(answer.getAnswerId())).thenReturn(answer);
		when(questionRepository.findOne(question.getQuestionId())).thenReturn(question);
		
		// when
		dut.deleteAnswer(loginUser, question.getQuestionId(), answer.getAnswerId());
		
		// then
		verify(answerRepository).delete(answer);
	}
	
	@Test (expected=AccessDeniedException.class)
	public void deleteAnswer_differentUser() throws Exception {
		// given
		SocialUser loginUser = new SocialUser(10);
		Long questionId = 1L;
		Answer answer = new Answer(2L);
		answer.writedBy(new SocialUser(11));
		when(answerRepository.findOne(answer.getAnswerId())).thenReturn(answer);
		
		// when
		dut.deleteAnswer(loginUser, questionId, answer.getAnswerId());
	}
}

