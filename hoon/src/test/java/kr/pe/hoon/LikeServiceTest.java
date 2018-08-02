package kr.pe.hoon;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.pe.hoon.domain.LikeVO;
import kr.pe.hoon.service.LikeService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath*:spring/root-context.xml"})
public class LikeServiceTest {
	@Autowired
	private LikeService likeService;
	
	@Test @Ignore
	public void testRead() throws Exception {
//		LikeVO lVO = new LikeVO();
//		lVO.setBno(162);
//		lVO.setUid("aaa");
		
//		likeService.read(lVO);
	}
	
	@Test
	public void testReadCount() throws Exception {
//		likeService.readCount(162);
	}
	
	@Test @Ignore
	public void testDelete() throws Exception {
//		LikeVO lVO = new LikeVO();
//		lVO.setBno(162);
//		lVO.setUid("aaa");
		
//		likeService.delete(lVO);
	}
}
