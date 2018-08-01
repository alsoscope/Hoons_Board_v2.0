package kr.pe.hoon.service;

import kr.pe.hoon.domain.LikeVO;

public interface LikeService {
	public void doLike(int bno, String uid) throws Exception;
	
	public LikeVO read(LikeVO lVO) throws Exception;
	
	public int readCount(int bno) throws Exception;
	
	public void delete(LikeVO lVO) throws Exception;
}
