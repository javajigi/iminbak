package net.slipp.web;

import javax.annotation.Resource;

import net.slipp.service.board.BoardService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	@Value("#{applicationProperties['environment']}")
	private String environment;
	
	@Resource(name = "boardService")
	private BoardService boardService;
	
	@RequestMapping("/")
	public String home(Model model) {
		return "index";
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
