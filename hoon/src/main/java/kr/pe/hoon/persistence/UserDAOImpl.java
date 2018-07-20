package kr.pe.hoon.persistence;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.pe.hoon.domain.UserVO;
import kr.pe.hoon.dto.LoginDTO;

@Repository
public class UserDAOImpl implements UserDAO {
	@Autowired
	private SqlSession sqlSession;

	@Override
	public void create(UserVO uVO) throws Exception {
		sqlSession.insert("user.create", uVO);
	}

	@Override
	public UserVO read(LoginDTO lDTO) throws Exception {
		return sqlSession.selectOne("user.read", lDTO);
	}

	@Override
	public UserVO readByUid(String uid) throws Exception {
		return sqlSession.selectOne("user.readByUid", uid);
	}
	
	@Override
	public UserVO readByEmail(String email) throws Exception {
		return sqlSession.selectOne("user.readByEmail", email);
	}
	
	@Override
	public UserVO readForCheckSession(String value) throws Exception {
		return sqlSession.selectOne("user.readForCheckSession", value);
	}

	@Override
	public void updateForLogin(String uid, String sessionid, Date next) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("uid", uid);
		map.put("sessionid", sessionid);
		map.put("next", next);
		
		sqlSession.update("user.updateForLogin", map);
	}
	
}
