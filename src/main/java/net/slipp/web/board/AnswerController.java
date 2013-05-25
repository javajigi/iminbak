package net.slipp.web.board;

import javax.annotation.Resource;

import net.slipp.domain.board.Answer;
import net.slipp.domain.board.BoardType;
import net.slipp.service.board.BoardService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/boards/{boardType}/{boardId}/answers")
public class AnswerController {
	private static final Logger logger = LoggerFactory.getLogger(AnswerController.class);

	@Resource(name = "boardService")
	private BoardService boardService;
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public String create(@PathVariable BoardType boardType, @PathVariable Long boardId, Answer answer)
			throws Exception {
		logger.debug("questionId :{}, answer : {}", boardId, answer);
		boardService.createAnswer(boardId, answer);
		return String.format("redirect:/boards/%s/%d", boardType.name(), boardId);
	}
	
	@RequestMapping(value="{answerId}/deleteForm", method=RequestMethod.GET)
	public String deleteForm(@PathVariable BoardType boardType, @PathVariable Long boardId, @PathVariable Long answerId, Model model) {
		model.addAttribute("boardType", boardType);
		model.addAttribute("board", boardService.findByBoardId(boardId));
		model.addAttribute("answer", boardService.findAnswerById(answerId));
		return "board/deleteAnswer";
	}

	@RequestMapping(value = "{answerId}", method = RequestMethod.DELETE)
	public String delete(@PathVariable BoardType boardType, @PathVariable Long boardId, Answer answer, Model model)
			throws Exception {
		try {
			boardService.deleteAnswer(boardId, answer.getAnswerId(), answer.getRawPassword());
			return String.format("redirect:/boards/%s/%d", boardType.name(), boardId);
		} catch (AccessDeniedException e) {
			model.addAttribute("boardType", boardType);
			model.addAttribute("board", boardService.findByBoardId(boardId));
			model.addAttribute("answer", boardService.findAnswerById(answer.getAnswerId()));
			model.addAttribute("errorMessage", "비밀번호가 틀립니다.");
			return "board/deleteAnswer";
		}
	}
	
	@RequestMapping(value = "{answerId}/form", method = RequestMethod.GET)
	public String updateForm(@PathVariable BoardType boardType, @PathVariable Long boardId, @PathVariable Long answerId, Model model)
		throws Exception {
		Answer answer = boardService.findAnswerById(answerId);
		model.addAttribute("boardType", boardType);
		model.addAttribute("board", boardService.findByBoardId(boardId));
		model.addAttribute("answer", answer);
		return "board/answer";
	}
	
	@RequestMapping(value = "{answerId}", method = RequestMethod.PUT)
	public String update(@PathVariable BoardType boardType, @PathVariable Long boardId, @PathVariable Long answerId, Answer answer, Model model) throws Exception {
		try {
			boardService.updateAnswer(answer);
			return String.format("redirect:/boards/%s/%d#answer-%d", boardType.name(), boardId, answerId);
		} catch (AccessDeniedException e) {
			Answer persistedAnswer = boardService.findAnswerById(answerId);
			model.addAttribute("boardType", boardType);
			model.addAttribute("board", boardService.findByBoardId(boardId));
			model.addAttribute("answer", persistedAnswer);
			model.addAttribute("errorMessage", "비밀번호가 틀립니다.");
			return "board/answer";
		}
	}
}
