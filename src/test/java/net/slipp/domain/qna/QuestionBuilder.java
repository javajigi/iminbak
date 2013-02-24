package net.slipp.domain.qna;

import java.util.List;

import net.slipp.domain.user.SocialUser;

import com.google.common.collect.Lists;

public class QuestionBuilder {
	private SocialUser writer;
	private String title;
	private String contents;
	private List<Answer> answers = Lists.newArrayList();
	
	public static QuestionBuilder aQuestion() {
		return new QuestionBuilder();
	}
	
	public QuestionBuilder withWriter(SocialUser writer) {
		this.writer = writer;
		return this;
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
	
	public Question build() {
		Question question = new Question(writer, title, contents) {
			@Override
			public List<Answer> getAnswers() {
				return answers;
			}
		};
		
		return question;
	}
}
