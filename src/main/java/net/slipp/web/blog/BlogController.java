package net.slipp.web.blog;

import javax.annotation.Resource;

import net.slipp.domain.blog.Blog;
import net.slipp.domain.blog.Blog_;
import net.slipp.repository.blog.BlogRepository;

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
@RequestMapping("/blogs")
public class BlogController {
	private static final int DEFAULT_PAGE_NO = 1;
	private static final int DEFAULT_PAGE_SIZE = 1;
	
	@Resource(name="blogRepository")
	private BlogRepository blogRepository;
	
	@RequestMapping("")
	public String index(Integer page, Model model) throws Exception {
		page = revisedPage(page);
		model.addAttribute("blogs", blogRepository.findAll(createPageable(page)));
		return "blog/index";
	}
	
	private Pageable createPageable(Integer page) {
		Sort sort = new Sort(Direction.DESC, Blog_.createdDate.getName());
		Pageable pageable = new PageRequest(page - 1, DEFAULT_PAGE_SIZE, sort);
		return pageable;
	}

	@RequestMapping("/form")
	public String createForm(Model model) throws Exception {
		model.addAttribute("blog", new Blog());
		return "blog/form";
	}
	
	@RequestMapping(value="", method=RequestMethod.POST)
	public String create(Blog blog) throws Exception {
		blogRepository.save(blog);
		return "redirect:/blogs";
	}
	
	@RequestMapping("/{id}/form")
	public String updateForm(@PathVariable Long id, Model model) throws Exception {
		model.addAttribute("blog", blogRepository.findOne(id));
		return "blog/form";
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.POST)
	public String update(Blog blog, Model model) throws Exception {
		Blog persisted = blogRepository.findOne(blog.getBlogId());
		persisted.update(blog);
		blogRepository.save(persisted);
		return "redirect:/blogs";
	}
	
	private Integer revisedPage(Integer page) {
		if (page == null) {
			page = DEFAULT_PAGE_NO;
		}
		return page;
	}
}
