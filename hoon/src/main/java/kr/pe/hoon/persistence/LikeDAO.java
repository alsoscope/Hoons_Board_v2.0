package kr.pe.hoon.persistence;

import kr.pe.hoon.domain.LikeVO;

public interface LikeDAO {
	public void create(int bno, String uid) throws Exception;
	
	public LikeVO read(LikeVO lVO) throws Exception;
	
	public int readCount(int bno) throws Exception;
	
	public void delete(LikeVO lVO) throws Exception;
}
