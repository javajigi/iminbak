package net.slipp.web.qna;

import javax.annotation.Resource;

import net.slipp.domain.qna.Answer;
import net.slipp.service.qna.QnaService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/questions/{questionId}/answers")
public class AnswerController {
	private static final Logger logger = LoggerFactory.getLogger(AnswerController.class);

	@Resource(name = "qnaService")
	private QnaService qnaService;
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public String create(@PathVariable Long questionId, Answer answer)
			throws Exception {
		logger.debug("questionId :{}, answer : {}", questionId, answer);
		qnaService.createAnswer(questionId, answer);
		return String.format("redirect:/questions/%d", questionId);
	}

	@RequestMapping(value = "{answerId}", method = RequestMethod.DELETE)
	public String delete(@PathVariable Long questionId, @PathVariable Long answerId)
			throws Exception {
		qnaService.deleteAnswer(questionId, answerId);
		return String.format("redirect:/questions/%d", questionId);
	}
	
	@RequestMapping(value = "{answerId}/form", method = RequestMethod.GET)
	public String updateForm(@PathVariable Long questionId, @PathVariable Long answerId, Model model)
		throws Exception {
		Answer answer = qnaService.findAnswerById(answerId);
		model.addAttribute("question", qnaService.findByQuestionId(questionId));
		model.addAttribute("answer", answer);
		return "qna/answer";
	}
	
	@RequestMapping(value = "{answerId}", method = RequestMethod.PUT)
	public String update(@PathVariable Long questionId, @PathVariable Long answerId, Answer answer) throws Exception {
		qnaService.updateAnswer(answer);
		return String.format("redirect:/questions/%d#answer-%d", questionId, answerId);
	}
}
