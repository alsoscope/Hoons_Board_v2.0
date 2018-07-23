package kr.pe.hoon.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import kr.pe.hoon.domain.BoardVO;
import kr.pe.hoon.domain.Criteria;
import kr.pe.hoon.domain.PageMaker;
import kr.pe.hoon.domain.ReplyVO;
import kr.pe.hoon.domain.SearchCriteria;
import kr.pe.hoon.service.BoardService;
import kr.pe.hoon.service.ReplyService;
import kr.pe.hoon.util.MediaUtils;
import kr.pe.hoon.util.UploadFileUtils;

@Controller
@RequestMapping("boards")
public class BoardController {
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	private static final String uploadPath = "c:\\file\\upload";
	
	@Autowired
	private BoardService boardService;
	
	@Autowired
	private ReplyService replyService;
	
	// tbl_board 관련
	@RequestMapping(value="new", method=RequestMethod.GET)
	public String createGET() throws Exception {
		
		return "boards/new";
	}
	
	@RequestMapping(value="new", method=RequestMethod.POST)
	public String createPOST(BoardVO bVO) throws Exception {
		boardService.create(bVO);
		
		return "redirect:/boards";
	}
	
	@RequestMapping(value="{bno}", method=RequestMethod.GET)
	public String read(@PathVariable int bno, Model model) throws Exception {
		logger.info(boardService.readNoViewcnt(bno).toString());
		model.addAttribute("bVO", boardService.read(bno));
		
		return "boards/info";
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
	
	@RequestMapping(value="{bno}/edit", method=RequestMethod.GET)
	public String updateGET(@PathVariable int bno, Model model) throws Exception {
		logger.info(boardService.readNoViewcnt(bno).toString());
		model.addAttribute("bVO", boardService.readNoViewcnt(bno));
		
		return "boards/edit";
	}
	
	@RequestMapping(value="{bno}", method=RequestMethod.PUT)
	public String updatePOST(@ModelAttribute BoardVO bVO) throws Exception {
		boardService.update(bVO);
		
		return "redirect:/boards/" + bVO.getBno();
	}
	
	@RequestMapping(value="{bno}", method=RequestMethod.DELETE)
	public String delete(int bno) throws Exception {
		boardService.delete(bno);
		
		return "redirect:/boards";
	}
	
	// tbl_reply 관련 ////////////////////////////////////////////////////////////////
	@ResponseBody
	@RequestMapping(value="{bno}/replies", method=RequestMethod.GET)
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
	
	@ResponseBody
	@RequestMapping(value="{bno}/replies/new", method=RequestMethod.POST)
	public ResponseEntity<String> create(@PathVariable int bno, @RequestBody ReplyVO rVO) {
		ResponseEntity<String> entity = null;
		
		try {
			rVO.setBno(bno);
			replyService.create(rVO);
			entity = new ResponseEntity<>("SUCCESS", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	@ResponseBody
	@RequestMapping(value="{bno}/replies/{rno}", method=RequestMethod.PUT)
	public ResponseEntity<String> update(@PathVariable int bno, @PathVariable int rno, @RequestBody ReplyVO rVO) {
		ResponseEntity<String> entity = null;
		
		try {
			rVO.setBno(bno);
			rVO.setRno(rno);
			replyService.update(rVO);
			entity = new ResponseEntity<>("SUCCESS", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	@ResponseBody
	@RequestMapping(value="{bno}/replies/{rno}", method=RequestMethod.DELETE)
	public ResponseEntity<String> delete(@PathVariable int bno, @PathVariable int rno) {
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
	
	// rbl_attach 관련 ////////////////////////////////////////////////////////////////
	@ResponseBody
	@RequestMapping("{bno}/attaches")
	public List<String> readAllAttaches(@PathVariable int bno) throws Exception {
		logger.info(boardService.readAllAttaches(bno).toString());
		
		return boardService.readAllAttaches(bno);
	}
	
	@ResponseBody
	@RequestMapping(value="uploadFile", method=RequestMethod.POST, produces="text/plain;charset=utf-8")
	public ResponseEntity<String> uploadFile(MultipartFile file) throws Exception {
		logger.info("originalName: " + file.getOriginalFilename());
		
		return new ResponseEntity<>(UploadFileUtils.uploadFiles(uploadPath, file.getOriginalFilename(), file.getBytes()), HttpStatus.CREATED);
	}
	
	@ResponseBody
	@RequestMapping("{bno}/displayFile")
	public ResponseEntity<byte[]> displayFile(String fileName) throws Exception {
		InputStream in = null;
		ResponseEntity<byte[]> entity = null;
		
		logger.info("FILE NAME: " + fileName);
		
		try {
			String formatName = fileName.substring(fileName.lastIndexOf(".") + 1);
			MediaType mType = MediaUtils.getMediaType(formatName);
			HttpHeaders headers = new HttpHeaders();
			
			in = new FileInputStream(uploadPath + fileName);
			
			if (mType != null) {
				headers.setContentType(mType);
			} else {
				fileName = fileName.substring(fileName.lastIndexOf("_") + 1);
				headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
				headers.add("Content-Disposition", "attachment; filename=\"" + new String(fileName.getBytes("utf-8"), "ISO-8859-1") + "\"");
			}
			
			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers, HttpStatus.CREATED);
		} catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
		} finally {
			in.close();
		}
		
		return entity;
	}
	
	@ResponseBody
	@RequestMapping(value="{bno}/deleteFile", method=RequestMethod.POST)
	public ResponseEntity<String> deleteFile(String fileName) {
		logger.info("delete file: " + fileName);
		String formatName = fileName.substring(fileName.indexOf(".") + 1);
		
		MediaType mType = MediaUtils.getMediaType(formatName);
		
		if (mType != null) {
			String front = fileName.substring(0, 12);
			String end = fileName.substring(14);
			new File(uploadPath + (front + end).replace('/', File.separatorChar)).delete();
		}
		
		new File(uploadPath + fileName.replace('/', File.separatorChar)).delete();
		
		return new ResponseEntity<>("deleted", HttpStatus.OK);
	}
	
	@ResponseBody
	@RequestMapping(value="{bno}/deleteAllFiles", method=RequestMethod.POST)
	public ResponseEntity<String> deleteAllFiles(@RequestParam("files[]") String[] files) {
		logger.info("delete all files: " + files);
		
		if (files == null || files.length == 0) {
			return new ResponseEntity<>("deleted", HttpStatus.OK);
		}
		
		for (String fileName : files) {
			String formatName = fileName.substring(fileName.indexOf(".") + 1);
			
			MediaType mType = MediaUtils.getMediaType(formatName);
			
			if (mType != null) {
				String front = fileName.substring(0, 12);
				String end = fileName.substring(14);
				new File(uploadPath + (front + end).replace('/', File.separatorChar)).delete();
			}
			
			new File(uploadPath + fileName.replace('/', File.separatorChar)).delete();
		}
		
		return new ResponseEntity<>("deleted", HttpStatus.OK);
	}
}
