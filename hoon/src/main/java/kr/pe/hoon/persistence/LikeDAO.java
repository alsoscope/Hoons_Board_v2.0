package kr.pe.hoon.persistence;

import kr.pe.hoon.domain.LikeVO;

public interface LikeDAO {
	public void create(int bno, String uid) throws Exception;
	
	public LikeVO read(int bno, String uid) throws Exception;
	
	public void delete(int bno, String uid) throws Exception;
}
