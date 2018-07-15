package kr.pe.hoon.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import kr.pe.hoon.domain.ReplyVO;
import kr.pe.hoon.service.BoardService;
import kr.pe.hoon.service.ReplyService;
import kr.pe.hoon.service.ReplyServiceImpl;

@RestController
@RequestMapping("replies")
public class ReplyController {
	private static final Logger logger = LoggerFactory.getLogger(ReplyServiceImpl.class);
	
	@Autowired
	private ReplyService replyService;
	
	@Autowired
	private BoardService boardService;
	
	@RequestMapping(value="new", method=RequestMethod.POST)
	public ResponseEntity<String> create(@RequestBody ReplyVO rVO) {
		ResponseEntity<String> entity = null;
		
		try {
			replyService.create(rVO);
			entity = new ResponseEntity<>("SUCCESS", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	@RequestMapping(value="{rno}", method={RequestMethod.PUT, RequestMethod.PATCH})
	public ResponseEntity<String> update(@PathVariable int rno, @RequestBody ReplyVO rVO) {
		ResponseEntity<String> entity = null;
		
		try {
			rVO.setRno(rno);
			replyService.update(rVO);
			entity = new ResponseEntity<>("SUCCESS", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	@RequestMapping(value="{rno}", method=RequestMethod.DELETE)
	public ResponseEntity<String> delete(@PathVariable int rno, int bno) {
		ResponseEntity<String> entity = null;
		
		try {
			replyService.delete(rno, bno);
			entity = new ResponseEntity<>("SUCCESS", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
}
