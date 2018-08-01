package kr.pe.hoon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.pe.hoon.domain.LikeVO;
import kr.pe.hoon.persistence.BoardDAO;
import kr.pe.hoon.persistence.LikeDAO;

@Service
public class LikeServiceImpl implements LikeService {
	@Autowired
	private LikeDAO lDAO;
	
	@Autowired
	private BoardDAO bDAO;
	
	@Override
	@Transactional
	public void doLike(int bno, String uid) throws Exception {
		lDAO.create(bno, uid);
		bDAO.updateLikecnt(bno, 1);
	}

	@Override
	public LikeVO read(LikeVO lVO) throws Exception {
		return lDAO.read(lVO);
	}

	@Override
	public int readCount(int bno) throws Exception {
		return lDAO.readCount(bno);
	}

	@Override
	public void delete(LikeVO lVO) throws Exception {
		lDAO.delete(lVO);
	}

}
