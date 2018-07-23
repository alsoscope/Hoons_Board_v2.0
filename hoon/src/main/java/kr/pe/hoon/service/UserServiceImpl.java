package kr.pe.hoon.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.pe.hoon.domain.TempKey;
import kr.pe.hoon.domain.UserVO;
import kr.pe.hoon.dto.LoginDTO;
import kr.pe.hoon.persistence.UserDAO;
import kr.pe.hoon.util.MailUtils;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDAO uDAO;
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Override
	@Transactional
	public void create(UserVO uVO) throws Exception {
		uDAO.create(uVO);
		
        String authkey = new TempKey().getKey(50, false);
        
        uVO.setAuthkey(authkey);
        uDAO.updateAuthkey(uVO);
        
        MailUtils sendMail = new MailUtils(mailSender);
        
        sendMail.setSubject("[Hoon's Board v2.0] 회원가입 이메일 인증");
        sendMail.setText(new StringBuffer().append("<h1>[이메일 인증]</h1>")
        									.append("<p>아래 링크를 클릭하시면 이메일 인증이 완료됩니다.</p>")
							                .append("<a href='http://localhost:8080/user/joinConfirm?uid=")
							                .append(uVO.getUid())
							                .append("&email=")
							                .append(uVO.getEmail())
							                .append("&authkey=")
							                .append(authkey)
							                .append("' target='_blenk'>이메일 인증 확인</a>")
							                .toString());
        sendMail.setFrom("zxczoxc125@gmail.com", "admin");
        sendMail.setTo(uVO.getEmail());
        sendMail.send();
	}
	
	@Override
	public UserVO login(LoginDTO lDTO) throws Exception {
		return uDAO.read(lDTO);
	}

	@Override
	public UserVO readByUid(String uid) throws Exception {
		return uDAO.readByUid(uid);
	}
	
	@Override
	public UserVO readByEmail(String email) throws Exception {
		return uDAO.readByEmail(email);
	}
	
	@Override
	public UserVO readForCheckSession(String value) throws Exception {
		return uDAO.readForCheckSession(value);
	}

	@Override
	public void update(UserVO uVO) throws Exception {
		uDAO.update(uVO);
	}
	
	@Override
	public void updateAuthstatus(UserVO uVO) throws Exception {
		uDAO.updateAuthstatus(uVO);
	}
	
	@Override
	public void updateForCookie(String uid, String sessionid, Date next) throws Exception {
		uDAO.updateForCookie(uid, sessionid, next);
	}

}
