package org.zerock.mapper;


import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.MemberVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class MemberMapperTests {
	
	@Setter(onMethod_ = @Autowired)
	private MemberMapper mapper;
	
	@Test
	public void testGetList() {
		List<MemberVO> list = mapper.getList();
		
	@Test
	public void testinsertTest() {
		
		MemberVO member = new MemberVO();
		member.setId("---");
		member.setPassword("수정된 비밀번호");
		member.setName("수정된 이름");
		member.setNickname("수정된 닉네임");		
		member.setPhone("수정된 번호");
		member.setEmail("수정된 이메일");
		
		
		mapper.insert(member);
		
		log.info(member);
		
	@Test
	public void testRead() {
			
		MemberVO member = mapper.read(5L);
			
		log.info(member);
	
	@Test
	public void testDelete() {
		
		log.info("DELETE COUNT: " + mapper.delete(3L));
		
	@Test
	public void testUpdate() {
		MemberVO member = new MemberVO();
		member.setId("---");
		member.setPassword("수정된 비밀번호");
		member.setName("수정된 이름");
		member.setNickname("수정된 닉네임");		
		member.setPhone("수정된 번호");
		member.setEmail("수정된 이메일");
		
		
	}
		
		
	}
		
		}
		
		
		
	}
		
		
	}
}









