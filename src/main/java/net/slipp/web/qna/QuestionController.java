package net.slipp.web.qna;

import javax.annotation.Resource;

import net.slipp.domain.qna.Answer;
import net.slipp.domain.qna.Question;
import net.slipp.domain.qna.QuestionDto;
import net.slipp.domain.qna.Question_;
import net.slipp.domain.user.SocialUser;
import net.slipp.service.qna.QnaService;
import net.slipp.support.web.argumentresolver.LoginUser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/questions")
public class QuestionController {
	private static final Logger logger = LoggerFactory.getLogger(QuestionController.class);

	private static final int DEFAULT_PAGE_NO = 1;
	private static final int DEFAULT_PAGE_SIZE = 15;

	@Resource(name = "qnaService")
	private QnaService qnaService;
	
	@RequestMapping("")
	public String index(Integer page, Model model) {
		page = revisedPage(page);
		logger.debug("currentPage : {}", page);
		model.addAttribute("questions", qnaService.findsQuestion(createPageable(page)));
		return "qna/list";
	}

	private Pageable createPageable(Integer page) {
		Sort sort = new Sort(Direction.DESC, Question_.createdDate.getName());
		Pageable pageable = new PageRequest(page - 1, DEFAULT_PAGE_SIZE, sort);
		return pageable;
	}

	@RequestMapping("/form")
	public String createForm(@LoginUser SocialUser loginUser, Model model) {
		model.addAttribute("question", new QuestionDto());
		return "qna/form";
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public String create(@LoginUser SocialUser loginUser, QuestionDto newQuestion) {
		logger.debug("Question : {}", newQuestion);

		Question question = qnaService.createQuestion(loginUser, newQuestion);
		return String.format("redirect:/questions/%s", question.getQuestionId());
	}

	@RequestMapping("/{id}/form")
	public String updateForm(@LoginUser SocialUser loginUser, @PathVariable Long id, Model model) {
		Question question = qnaService.findByQuestionId(id);
		if (!question.isWritedBy(loginUser)) {
			throw new AccessDeniedException(loginUser.getUserId() + " is not owner!");
		}
		model.addAttribute("question", question);
		return "qna/form";
	}

	@RequestMapping(value = "", method = RequestMethod.PUT)
	public String update(@LoginUser SocialUser loginUser, QuestionDto updatedQuestion) {
		logger.debug("Question : {}", updatedQuestion);

		Question question = qnaService.updateQuestion(loginUser, updatedQuestion);
		return String.format("redirect:/questions/%s", question.getQuestionId());
	}

	@RequestMapping("{id}")
	public String show(@PathVariable Long id, Model model) {
		model.addAttribute("question", qnaService.showQuestion(id));
		model.addAttribute("answer", new Answer());
		return "qna/show";
	}
	
	@RequestMapping(value="{id}", method=RequestMethod.DELETE)
	public String delete(@LoginUser SocialUser loginUser, @PathVariable Long id) {
		qnaService.deleteQuestion(loginUser, id);
		return "redirect:/questions";
	}

	private Integer revisedPage(Integer page) {
		if (page == null) {
			page = DEFAULT_PAGE_NO;
		}
		return page;
	}
}
