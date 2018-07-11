package kr.pe.hoon.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.pe.hoon.domain.Criteria;
import kr.pe.hoon.domain.ReplyVO;
import kr.pe.hoon.persistence.BoardDAO;
import kr.pe.hoon.persistence.ReplyDAO;

@Service
public class ReplyServiceImpl implements ReplyService {
	@Autowired
	private ReplyDAO rDAO;
	
	@Autowired
	private BoardDAO bDAO;
	
	@Override
	@Transactional
	public void create(ReplyVO rVO) throws Exception {
		rDAO.create(rVO);
		bDAO.updateReplycnt(rVO.getBno(), 1);
	}

	@Override
	public List<ReplyVO> readAll(int bno, Criteria cri) throws Exception {
		return rDAO.readAll(bno, cri);
	}

	@Override
	public void update(ReplyVO rVO) throws Exception {
		rDAO.update(rVO);
	}

	@Override
	public void delete(int rno) throws Exception {
		rDAO.delete(rno);
	}

	@Override
	public int readCount(int bno) throws Exception {
		return rDAO.readCount(bno);
	}

}
