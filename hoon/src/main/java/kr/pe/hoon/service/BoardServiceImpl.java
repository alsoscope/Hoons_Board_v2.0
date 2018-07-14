package kr.pe.hoon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.pe.hoon.domain.BoardVO;
import kr.pe.hoon.domain.SearchCriteria;
import kr.pe.hoon.persistence.BoardDAO;

@Service
public class BoardServiceImpl implements BoardService {
	@Autowired
	private BoardDAO bDAO;
	
	@Override
	@Transactional
	public void create(BoardVO bVO) throws Exception {
		bDAO.create(bVO);
		
		String[] files = bVO.getFiles();
		
		if (files == null) {
			return;
		}
		
		for (String fileName : files) {
			bDAO.createAttach(fileName);
		}
	}
	
	@Override
	@Transactional
	public BoardVO read(int bno) throws Exception {
		bDAO.updateViewcnt(bno, 1);
		return bDAO.read(bno);
	}
	
	@Override
	public List<String> readAllAttaches(int bno) throws Exception {
		return bDAO.readAllAttaches(bno);
	}
	
	@Override
	public BoardVO readNoViewcnt(int bno) throws Exception {
		return bDAO.read(bno);
	}

	@Override
	public void update(BoardVO bVO) throws Exception {
		bDAO.update(bVO);
	}

	@Override
	public void delete(int bno) throws Exception {
		bDAO.delete(bno);
	}

	@Override
	public List<BoardVO> readAll(SearchCriteria cri) throws Exception {
		return bDAO.readAll(cri);
	}

	@Override
	public int readCount(SearchCriteria cri) throws Exception {
		return bDAO.readCount(cri);
	}

}
