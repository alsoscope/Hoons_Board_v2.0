package kr.pe.hoon.service;

import kr.pe.hoon.domain.UserVO;
import kr.pe.hoon.dto.LoginDTO;

public interface UserService {
	public UserVO login(LoginDTO lDTO) throws Exception;
}
