package org.zerock.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.MemberVO;
import org.zerock.mapper.MemberMapper;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class MemberServiceTests {
	
	@Autowired
	private MemberService service;
	
	@Autowired
	private MemberMapper mapper;
	
	//회원 등록
	@Test
	public void testRegister() {
		MemberVO member = new MemberVO();
		member.setId("KIM11");
		member.setPassword("password111");
		member.setName("김11");
		member.setNickname("여행객11");
		member.setPhone("01011111111");
		member.setEmail("KIM11@naver.com");
		
		int before = mapper.getList().size();
		
		service.register(member);
		
		int after = mapper.getList().size();
		
		assertEquals(before+1, after);
		
	}
	
	//회원 정보 읽기 - 아이디 
	@Test
	public void testGetMemberId() {
		MemberVO member = service.getMemberId("KIM11");
		
		log.info(member);
		
	}	
	
	
	//회원 정보 수정
	@Test
	public void testModify() {
		MemberVO member = new MemberVO();
		member.setId("KIM11");
		member.setEmail("sololol@nave.com");
		member.setPassword("새비밀번호");
		member.setName("KIM변경");
		member.setNickname("수정여행객");
		
		service.register(member);
		
		MemberVO modifyMember = new MemberVO();
		modifyMember.setEmail("KIM11@naver.com");
		modifyMember.setPassword("password111");
		modifyMember.setNickname("여행객11");
		
		assertTrue(service.modify(modifyMember));
				
		assertEquals("KIM11@naver.com", member.getEmail());
		assertEquals("password111", member.getPassword());
		assertEquals("여행객11", member.getNickname());

	}
	
	//회원 탈퇴(삭제)
	@Test
	public void testRemove() {
		assertTrue(service.remove("KIM11"));
	}
	
	
	
	
	
	
}