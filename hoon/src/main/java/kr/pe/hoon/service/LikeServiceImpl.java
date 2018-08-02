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
	public void Like(int bno, String uid) throws Exception {
		LikeVO lVO = lDAO.read(bno, uid);
		
		if (lVO == null) {
			lDAO.create(bno, uid);
			bDAO.updateLikecnt(bno, 1);
		} else {
			lDAO.delete(bno, uid);
			bDAO.updateLikecnt(bno, -1);
		}
	}

	@Override
	public LikeVO read(int bno, String uid) throws Exception {
		return lDAO.read(bno, uid);
	}

	@Override
	public void delete(int bno, String uid) throws Exception {
		lDAO.delete(bno, uid);
	}

}
