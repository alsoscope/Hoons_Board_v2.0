package kr.pe.hoon.persistence;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.pe.hoon.domain.LikeVO;

@Repository
public class LikeDAOImpl implements LikeDAO {
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public void create(int bno, String uid) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("bno", bno);
		map.put("uid", uid);
		
		sqlSession.insert("like.create", map);
	}

	@Override
	public LikeVO read(LikeVO lVO) throws Exception {
		return sqlSession.selectOne("like.read", lVO);
	}

	@Override
	public int readCount(int bno) throws Exception {
		return sqlSession.selectOne("like.readCount", bno);
	}
	
	@Override
	public void delete(LikeVO lVO) throws Exception {
		sqlSession.delete("like.delete", lVO);
	}

}
