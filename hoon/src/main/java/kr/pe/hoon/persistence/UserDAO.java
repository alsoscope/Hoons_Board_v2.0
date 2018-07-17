package kr.pe.hoon.persistence;

import kr.pe.hoon.domain.UserVO;
import kr.pe.hoon.dto.LoginDTO;

public interface UserDAO {
	public UserVO read(LoginDTO lDTO) throws Exception;
}
