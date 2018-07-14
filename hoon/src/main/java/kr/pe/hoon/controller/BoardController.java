package kr.pe.hoon.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.pe.hoon.domain.BoardVO;
import kr.pe.hoon.domain.Criteria;
import kr.pe.hoon.domain.PageMaker;
import kr.pe.hoon.domain.SearchCriteria;
import kr.pe.hoon.service.BoardService;
import kr.pe.hoon.service.ReplyService;

@Controller
@RequestMapping("boards")
public class BoardController {
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Autowired
	private BoardService boardService;
	
	@Autowired
	private ReplyService replyService;
	
	@RequestMapping(value="new", method=RequestMethod.GET)
	public String createGET() throws Exception {
		
		return "boards/new";
	}
	
	@RequestMapping(value="new", method=RequestMethod.POST)
	public String createPOST(BoardVO bVO) throws Exception {
		boardService.create(bVO);
		
		return "redirect:/boards";
	}
	
	@RequestMapping("")
	public String readAll(@ModelAttribute("cri") SearchCriteria cri, Model model) throws Exception {
		logger.info(boardService.readAll(cri).toString());
		model.addAttribute("list", boardService.readAll(cri));
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(boardService.readCount(cri));
		
		model.addAttribute("pageMaker", pageMaker);
		
		return "boards/list";
	}
	
	@RequestMapping(value="{bno}", method=RequestMethod.GET)
	public String read(@PathVariable int bno, Model model) throws Exception {
		logger.info(boardService.readNoViewcnt(bno).toString());
		model.addAttribute("bVO", boardService.read(bno));
		
		return "boards/info";
	}
	
	@RequestMapping("{bno}/attaches")
	@ResponseBody
	public List<String> readAllAttaches(@PathVariable int bno) throws Exception {
		return boardService.readAllAttaches(bno);
	}
	
	@RequestMapping(value="{bno}/replies", method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> readAllReplies(@PathVariable int bno, int page) {
		ResponseEntity<Map<String, Object>> entity = null;
		Map<String, Object> map = new HashMap<>();
		
		try {
			Criteria cri = new Criteria();
			cri.setPage(page);
			
			PageMaker pageMaker = new PageMaker();
			pageMaker.setCri(cri);
			pageMaker.setTotalCount(replyService.readCount(bno));
			map.put("pageMaker", pageMaker);
			
			logger.info(replyService.readAll(bno, cri).toString());
			map.put("replyList", replyService.readAll(bno, cri));
			
			map.put("boardInfo", boardService.readNoViewcnt(bno));
			
			entity = new ResponseEntity<>(map, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	@RequestMapping("test")
	public String test() throws Exception {
		return "test";
	}
	
	@RequestMapping(value="edit/{bno}", method=RequestMethod.GET)
	public String updateGET(@PathVariable int bno, Model model) throws Exception {
		logger.info(boardService.readNoViewcnt(bno).toString());
		model.addAttribute("bVO", boardService.readNoViewcnt(bno));
		
		return "boards/edit";
	}
	
	@RequestMapping(value="edit/{bno}", method=RequestMethod.POST)
	public String updatePOST(@ModelAttribute BoardVO bVO) throws Exception {
		boardService.update(bVO);
		
		return "redirect:/boards/" + bVO.getBno();
	}
	
	@RequestMapping(value="{bno}", method=RequestMethod.DELETE)
	@ResponseBody
	public ResponseEntity<String> delete(@PathVariable int bno) {
		ResponseEntity<String> entity = null;

		try {
			boardService.delete(bno);
			entity = new ResponseEntity<>("SUCCESS", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
}
