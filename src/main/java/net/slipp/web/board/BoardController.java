package net.slipp.web.board;

import java.util.Collection;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/boards/{boardType}")
public class BoardController {
    private static final Logger log = LoggerFactory.getLogger(BoardController.class);

    private static final int DEFAULT_PAGE_NO = 1;
    private static final int DEFAULT_PAGE_SIZE = 15;

    @Resource(name = "boardService")
    private BoardService boardService;

    @RequestMapping("")
    public String index(@PathVariable BoardType boardType, Integer page, Model model) {
        page = revisedPage(page);
        log.debug("currentPage : {}", page);
        model.addAttribute("boardType", boardType);
        model.addAttribute("boards", boardService.findsBoard(boardType, createPageable(page)));
        return "board/list";
    }

    private Pageable createPageable(Integer page) {
        Sort sort = new Sort(Direction.DESC, Board_.createdDate.getName());
        Pageable pageable = new PageRequest(page - 1, DEFAULT_PAGE_SIZE, sort);
        return pageable;
    }

    @RequestMapping("/form")
    public String createForm(@PathVariable BoardType boardType, HttpSession session, Model model) {
        BoardDto boardDto = new BoardDto();
        session.setAttribute("token", boardDto.getToken());
        model.addAttribute("boardType", boardType);
        model.addAttribute("board", boardDto);
        return "board/form";
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String create(@PathVariable BoardType boardType, BoardDto newBoard, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Object tokenObject = session.getAttribute("token");
        if (tokenObject == null) {
            throw new IllegalStateException("비정상적인 글쓰기입니다.");
        }
        if (!newBoard.validToken((String)tokenObject)) {
            throw new IllegalStateException("비정상적인 글쓰기입니다.");
        }
        
        log.debug("Question : {}", newBoard);
        Board board = boardService.createBoard(newBoard, request.getRemoteAddr());
        return String.format("redirect:/boards/%s/%s", boardType.name(), board.getBoardId());
    }

    @RequestMapping("/{id}/form")
    public String updateForm(@PathVariable BoardType boardType, @PathVariable Long id, HttpSession session, Model model) {
        Board board = boardService.findByBoardId(id);
        BoardDto boardDto = new BoardDto(board);
        session.setAttribute("token", boardDto.getToken());
        model.addAttribute("board", new BoardDto(board));
        return "board/form";
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    public String update(@PathVariable BoardType boardType, BoardDto updatedBoard, Model model) {
        log.debug("Question : {}", updatedBoard);

        try {
            Board board = boardService.updateBoard(updatedBoard);
            return String.format("redirect:/boards/%s/%s", boardType.name(), board.getBoardId());
        } catch (AccessDeniedException e) {
            Board board = boardService.findByBoardId(updatedBoard.getBoardId());
            model.addAttribute("board", new BoardDto(board));
            model.addAttribute("errorMessage", "비밀번호가 틀립니다.");
            return "board/form";
        }
    }

    @RequestMapping("/{id}")
    public String show(@PathVariable BoardType boardType, @PathVariable Long id, Model model) {
        model.addAttribute("boardType", boardType);
        model.addAttribute("board", boardService.showBoard(id));
        model.addAttribute("answer", new Answer());
        return "board/show";
    }

    @RequestMapping(value = "/{id}/deleteForm", method = RequestMethod.GET)
    public String deleteForm(@PathVariable BoardType boardType, @PathVariable Long id, Model model) {
        model.addAttribute("boardType", boardType);
        model.addAttribute("board", new BoardDto(boardService.findByBoardId(id)));
        return "board/delete";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable BoardType boardType, BoardDto deleteBoard, Model model) {
        if (hasRole("ROLE_ADMINISTRATOR")) {
            boardService.deleteBoard(deleteBoard.getBoardId());
            return String.format("redirect:/boards/%s", boardType.name());
        }

        try {
            boardService.deleteBoard(deleteBoard.getBoardId(), deleteBoard.getPassword());
            return String.format("redirect:/boards/%s", boardType.name());
        } catch (AccessDeniedException e) {
            model.addAttribute("boardType", boardType);
            model.addAttribute("board", new BoardDto(boardService.findByBoardId(deleteBoard.getBoardId())));
            model.addAttribute("errorMessage", "비밀번호가 틀립니다.");
            return "board/delete";
        }
    }

    private boolean hasRole(String role) {
        Authentication authentication = getAuthentication();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (GrantedAuthority grantedAuthority : authorities) {
            if (role.equals(grantedAuthority.getAuthority())) {
                return true;
            }
        }
        return false;
    }

    private Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    private Integer revisedPage(Integer page) {
        if (page == null) {
            page = DEFAULT_PAGE_NO;
        }
        return page;
    }
}
