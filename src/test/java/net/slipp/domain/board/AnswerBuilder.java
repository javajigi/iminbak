package net.slipp.domain.board;

import net.slipp.domain.board.Answer;

public class AnswerBuilder {
	private int totalLiked = 0;
	
	public static AnswerBuilder anAnswer() {
		return new AnswerBuilder();
	}
	
	public AnswerBuilder withTotalLiked(int totalLiked) {
		this.totalLiked = totalLiked;
		return this;
	}
	
	public Answer build() {
		Answer answer = new Answer() {
			@Override
			public Integer getSumLike() {
				return totalLiked;
			}
		};
		return answer;
	}
}
