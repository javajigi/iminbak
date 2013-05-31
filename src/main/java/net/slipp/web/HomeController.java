package net.slipp.web;

import javax.annotation.Resource;

import net.slipp.domain.blog.Blog_;
import net.slipp.repository.blog.BlogRepository;
import net.slipp.service.board.BoardService;

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
	private static final int DEFAULT_PAGE_NO = 1;
	private static final int DEFAULT_PAGE_SIZE = 2;
	
	@Value("#{applicationProperties['environment']}")
	private String environment;
	
	@Resource(name = "boardService")
	private BoardService boardService;
	
	@Resource(name = "blogRepository")
	private BlogRepository blogRepository;
	
	@RequestMapping("/")
	public String home(Model model) {
		model.addAttribute("blogs", blogRepository.findAll(createPageable(DEFAULT_PAGE_NO)));
		return "index";
	}
	
	private Pageable createPageable(Integer page) {
		Sort sort = new Sort(Direction.DESC, Blog_.createdDate.getName());
		Pageable pageable = new PageRequest(page - 1, DEFAULT_PAGE_SIZE, sort);
		return pageable;
	}

    @RequestMapping("/login")
    public String login() {
        return "login";
    }
    
    @RequestMapping("/reservations")
    public String reservations() {
        return "reservations";
    }
    
    @RequestMapping("/maps")
    public String maps() {
        return "maps";
    } 
}
