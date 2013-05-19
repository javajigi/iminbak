package net.slipp.domain.board;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class QuestionTest {
	@Test
	public void 새로운_질문() throws Exception {
		// given
		String title = "title";
		String contents = "contents";

		// when
		Board newQuestion = new Board(title, contents);

		// then
		assertThat(newQuestion.getTitle(), is(title));
		assertThat(newQuestion.getContents(), is(contents));
	}

	@Test
	public void 질문_수정() throws Exception {
		// given
		Board newQuestion = new Board("title", "contents");

		// when
		String updateTitle = "update title";
		String updateContents = "update contents";
		newQuestion.update(updateTitle, updateContents);
		assertThat(newQuestion.getTitle(), is(updateTitle));
		assertThat(newQuestion.getContents(), is(updateContents));
	}
}
