package net.slipp.domain.board;

import java.util.List;

import net.slipp.domain.board.Answer;
import net.slipp.domain.board.Board;

import com.google.common.collect.Lists;

public class QuestionBuilder {
	private String title;
	private String contents;
	private List<Answer> answers = Lists.newArrayList();
	
	public static QuestionBuilder aQuestion() {
		return new QuestionBuilder();
	}
	
	public QuestionBuilder withTitle(String title) {
		this.title = title;
		return this;
	}
	
	public QuestionBuilder withContents(String contents) {
		this.contents = contents;
		return this;
	}
	
	public QuestionBuilder withAnswer(Answer answer) {
		answers.add(answer);
		return this;
	}
	
	public Board build() {
		Board question = new Board(title, contents) {
			@Override
			public List<Answer> getAnswers() {
				return answers;
			}
		};
		
		return question;
	}
}
