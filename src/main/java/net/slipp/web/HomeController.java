package net.slipp.web;

import javax.annotation.Resource;

import net.slipp.domain.qna.Question_;
import net.slipp.service.qna.QnaService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	private static final int DEFAULT_PAGE_NO = 0;
	private static final int DEFAULT_PAGE_SIZE = 15;	
	
	@Value("#{applicationProperties['environment']}")
	private String environment;
	
	@Resource(name = "qnaService")
	private QnaService qnaService;
	
	@RequestMapping("/")
	public String home(Model model) {
		model.addAttribute("questions", qnaService.findsQuestion(createPageable()));
		return "index";
	}

	private Pageable createPageable() {
		Sort sort = new Sort(Direction.DESC, Question_.createdDate.getName());
		return new PageRequest(DEFAULT_PAGE_NO, DEFAULT_PAGE_SIZE, sort);
	}
	
    @RequestMapping("/login")
    public String login() {
        return "login";
    }
    
    @RequestMapping("/fblogout")
    public String logout() {
        return "fblogout";
    }
}
