package net.slipp.domain.board;


public class AnswerBuilder {
	public static AnswerBuilder anAnswer() {
		return new AnswerBuilder();
	}
	
	public Answer build() {
		return new Answer();
	}
}
