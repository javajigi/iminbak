package net.slipp.web.board;

import javax.annotation.Resource;

import net.slipp.domain.board.Answer;
import net.slipp.domain.board.Board;
import net.slipp.domain.board.BoardDto;
import net.slipp.domain.board.BoardType;
import net.slipp.domain.board.Board_;
import net.slipp.service.board.BoardService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/boards/{boardType}")
public class BoardController {
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

	private static final int DEFAULT_PAGE_NO = 1;
	private static final int DEFAULT_PAGE_SIZE = 15;

	@Resource(name = "boardService")
	private BoardService boardService;
	
	@RequestMapping("")
	public String index(@PathVariable BoardType boardType, Integer page, Model model) {
		page = revisedPage(page);
		logger.debug("currentPage : {}", page);
		model.addAttribute("boardType", boardType);
		model.addAttribute("boards", boardService.findsQuestion(createPageable(page)));
		return "board/list";
	}

	private Pageable createPageable(Integer page) {
		Sort sort = new Sort(Direction.DESC, Board_.createdDate.getName());
		Pageable pageable = new PageRequest(page - 1, DEFAULT_PAGE_SIZE, sort);
		return pageable;
	}

	@RequestMapping("/form")
	public String createForm(@PathVariable BoardType boardType, Model model) {
		model.addAttribute("boardType", boardType);
		model.addAttribute("board", new BoardDto());
		return "board/form";
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public String create(@PathVariable BoardType boardType, BoardDto newBoard) {
		logger.debug("Question : {}", newBoard);
		Board board = boardService.createBoard(newBoard);
		return String.format("redirect:/boards/%s/%s", boardType.name(), board.getBoardId());
	}

	@RequestMapping("/{id}/form")
	public String updateForm(@PathVariable Long id, Model model) {
		Board question = boardService.findByQuestionId(id);
		model.addAttribute("question", question);
		return "board/form";
	}

	@RequestMapping(value = "", method = RequestMethod.PUT)
	public String update(BoardDto updatedQuestion) {
		logger.debug("Question : {}", updatedQuestion);

		Board question = boardService.updateQuestion(updatedQuestion);
		return String.format("redirect:/questions/%s", question.getBoardId());
	}

	@RequestMapping("/{id}")
	public String show(@PathVariable BoardType boardType, @PathVariable Long id, Model model) {
		model.addAttribute("boardType", boardType);
		model.addAttribute("board", boardService.showBoard(id));
		model.addAttribute("answer", new Answer());
		return "board/show";
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public String delete(@PathVariable Long id) {
		boardService.deleteQuestion(id);
		return "redirect:/questions";
	}

	private Integer revisedPage(Integer page) {
		if (page == null) {
			page = DEFAULT_PAGE_NO;
		}
		return page;
	}
}
