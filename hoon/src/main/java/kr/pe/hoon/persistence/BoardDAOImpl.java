package kr.pe.hoon.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.pe.hoon.domain.BoardVO;
import kr.pe.hoon.domain.SearchCriteria;

@Repository
public class BoardDAOImpl implements BoardDAO {
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public void create(BoardVO bVO) throws Exception {
		sqlSession.insert("board.create", bVO);
	}

	@Override
	public BoardVO read(int bno) throws Exception {
		return sqlSession.selectOne("board.read", bno);
	}

	@Override
	public void update(BoardVO bVO) throws Exception {
		sqlSession.update("board.update", bVO);
	}

	@Override
	public void updateViewcnt(int bno, int cnt) throws Exception {
		Map<String, Integer> map = new HashMap<>();
		map.put("bno", bno);
		map.put("cnt", cnt);
		
		sqlSession.update("board.updateViewcnt", map);
	}
	
	@Override
	public void updateReplycnt(int bno, int cnt) throws Exception {
		Map<String, Integer> map = new HashMap<>();
		map.put("bno", bno);
		map.put("cnt", cnt);
		
		sqlSession.update("board.updateReplycnt", map);
	}
	
	@Override
	public void delete(int bno) throws Exception {
		sqlSession.delete("board.delete", bno);
	}

	@Override
	public List<BoardVO> readAll(SearchCriteria cri) throws Exception {
		return sqlSession.selectList("board.readAll", cri);
	}

	@Override
	public int readCount(SearchCriteria cri) throws Exception {
		return sqlSession.selectOne("board.countAll", cri);
	}

}
