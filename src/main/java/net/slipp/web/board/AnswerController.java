package net.slipp.web.board;

import javax.annotation.Resource;

import net.slipp.domain.board.Answer;
import net.slipp.service.board.BoardService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/boards/{questionId}/answers")
public class AnswerController {
	private static final Logger logger = LoggerFactory.getLogger(AnswerController.class);

	@Resource(name = "boardService")
	private BoardService boardService;
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public String create(@PathVariable Long questionId, Answer answer)
			throws Exception {
		logger.debug("questionId :{}, answer : {}", questionId, answer);
		boardService.createAnswer(questionId, answer);
		return String.format("redirect:/boards/%d", questionId);
	}

	@RequestMapping(value = "{answerId}", method = RequestMethod.DELETE)
	public String delete(@PathVariable Long questionId, @PathVariable Long answerId)
			throws Exception {
		boardService.deleteAnswer(questionId, answerId);
		return String.format("redirect:/boards/%d", questionId);
	}
	
	@RequestMapping(value = "{answerId}/form", method = RequestMethod.GET)
	public String updateForm(@PathVariable Long questionId, @PathVariable Long answerId, Model model)
		throws Exception {
		Answer answer = boardService.findAnswerById(answerId);
		model.addAttribute("question", boardService.findByQuestionId(questionId));
		model.addAttribute("answer", answer);
		return "board/answer";
	}
	
	@RequestMapping(value = "{answerId}", method = RequestMethod.PUT)
	public String update(@PathVariable Long questionId, @PathVariable Long answerId, Answer answer) throws Exception {
		boardService.updateAnswer(answer);
		return String.format("redirect:/boards/%d#answer-%d", questionId, answerId);
	}
}
