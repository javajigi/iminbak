package net.slipp.domain.qna;

import static net.slipp.domain.qna.AnswerBuilder.*;
import static net.slipp.domain.qna.QuestionBuilder.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import net.slipp.domain.user.SocialUser;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class QuestionTest {
	private Question dut;

	@Before
	public void setup() {
		dut = new Question();
	}

	@Test
	public void 질문한_사람이_같다() throws Exception {
		SocialUser writer = new SocialUser(10);
		dut = aQuestion().withWriter(writer).build();

		assertThat(dut.isWritedBy(writer), is(true));
	}

	@Test
	public void 질문한_사람이_다르다() throws Exception {
		SocialUser writer = new SocialUser(10);
		dut = aQuestion().withWriter(writer).build();

		boolean actual = dut.isWritedBy(new SocialUser(11));
		assertThat(actual, is(false));
	}

	@Test
	public void 새로운_질문() throws Exception {
		// given
		SocialUser writer = new SocialUser();
		String title = "title";
		String contents = "contents";

		// when
		Question newQuestion = new Question(writer, title, contents);

		// then
		assertThat(newQuestion.getTitle(), is(title));
		assertThat(newQuestion.getContents(), is(contents));
	}

	@Test
	public void 질문_수정() throws Exception {
		// given
		SocialUser writer = new SocialUser();
		Question newQuestion = new Question(writer, "title", "contents");

		// when
		String updateTitle = "update title";
		String updateContents = "update contents";
		newQuestion.update(writer, updateTitle, updateContents);
		assertThat(newQuestion.getTitle(), is(updateTitle));
		assertThat(newQuestion.getContents(), is(updateContents));
	}

	@Test
	public void 화제의_답변이_존재한다() throws Exception {
		Question dut = aQuestion().withAnswer(anAnswer().withTotalLiked(1).build())
				.withAnswer(anAnswer().withTotalLiked(0).build()).withAnswer(anAnswer().withTotalLiked(3).build())
				.build();
		Answer bestAnswer = dut.getBestAnswer();
		assertThat(bestAnswer.getSumLike(), is(3));
	}

	@Test
	public void 화제의_답변이_존재하지_않는다() throws Exception {
		Question dut = aQuestion().withAnswer(anAnswer().withTotalLiked(1).build())
				.withAnswer(anAnswer().withTotalLiked(0).build()).build();

		assertThat(dut.getBestAnswer(), is(nullValue()));
	}

	@Test
	public void 답변이_존재하지_않는다() throws Exception {
		Question dut = aQuestion().build();
		assertThat(dut.getBestAnswer(), is(nullValue()));
	}

	@Test
	public void 질문을_삭제한다() throws Exception {
		SocialUser writer = new SocialUser();
		Question dut = aQuestion().withWriter(writer).build();
		dut.delete(writer);
		assertThat(dut.isDeleted(), is(true));
	}
}
