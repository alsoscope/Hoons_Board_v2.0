package kr.pe.hoon.persistence;

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
	public UserVO read(LoginDTO lDTO) throws Exception {
		return sqlSession.selectOne("user.read", lDTO);
	}
	
}
