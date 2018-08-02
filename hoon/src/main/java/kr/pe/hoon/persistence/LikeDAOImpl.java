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
	public LikeVO read(int bno, String uid) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("bno", bno);
		map.put("uid", uid);
		
		return sqlSession.selectOne("like.read", map);
	}

	@Override
	public void delete(int bno, String uid) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("bno", bno);
		map.put("uid", uid);
		
		sqlSession.delete("like.delete", map);
	}

}
