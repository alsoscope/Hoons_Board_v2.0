package kr.pe.hoon.service;

import java.util.List;

import kr.pe.hoon.domain.BoardVO;
import kr.pe.hoon.domain.SearchCriteria;

public interface BoardService {
	public void create(BoardVO bVO) throws Exception;
	
	public BoardVO read(int bno) throws Exception;
	
	public BoardVO readNoViewcnt(int bno) throws Exception;

	public void update(BoardVO bVO) throws Exception;
	
	public void delete(int bno) throws Exception;
	
	public List<BoardVO> readAll(SearchCriteria cri) throws Exception;
	
	public int readCount(SearchCriteria cri) throws Exception;
}
