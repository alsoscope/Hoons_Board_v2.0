package kr.pe.hoon.service;

import java.util.List;

import kr.pe.hoon.domain.Criteria;
import kr.pe.hoon.domain.ReplyVO;

public interface ReplyService {
	public void create(ReplyVO rVO) throws Exception;
	
	public List<ReplyVO> readAll(int bno, Criteria cri) throws Exception;
	
	public int readCount(int bno) throws Exception;
	
	public void update(ReplyVO rVO) throws Exception;
	
	public void delete(int rno, int bno) throws Exception;
}
