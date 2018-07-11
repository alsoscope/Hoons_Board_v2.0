package kr.pe.hoon.persistence;

import java.util.List;

import kr.pe.hoon.domain.BoardVO;
import kr.pe.hoon.domain.SearchCriteria;

public interface BoardDAO {
	public void create(BoardVO bVO) throws Exception;
	
	public BoardVO read(int bno) throws Exception;
	
	public void update(BoardVO bVO) throws Exception;
	
	public void updateViewcnt(int bno, int cnt) throws Exception;
	
	public void updateReplycnt(int bno, int cnt) throws Exception;
	
	public void delete(int bno) throws Exception;
	
	public List<BoardVO> readAll(SearchCriteria cri) throws Exception;
	
	public int readCount(SearchCriteria cri) throws Exception;
}
