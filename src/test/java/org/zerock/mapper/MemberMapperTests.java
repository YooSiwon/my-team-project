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
	
	private static final Long Member = null;
	private static final Long Id = null;
	private static final Long Password = null;
	private static final Long Name = null;
	private static final Long Nickname = null;
	private static final Long PHONE = null;
	private static final Long EMAIL = null;
	@Setter(onMethod_ = @Autowired)
	private MemberMapper mapper;
	
	@Test
	public void testGetList() {
		List<MemberVO> list = mapper.getList();
		
		
	}	
	@Test
	public void testInsert() {
		
		MemberVO member = new MemberVO();
		member.setId("KIM");
		member.setPassword("나의password1");
		member.setName("김치");
		member.setNickname("여행자");
		member.setPhone("0105555555");
		member.setEmail("jsijdai");
			
		
		mapper.insert(member);
		
		log.info(member);
		
	}
	
	@Test
	public void testRead() {
		
		MemberVO member = mapper.read("new id");
				
		
		log.info(member);
	}
	
	@Test
	public void testDelete() {
		
		log.info("DELETE : " + mapper.delete("new id"));
	}

	@Test
	public void testUpdate() {
		
		MemberVO member = new MemberVO();
		
		member.setId("KIM");
		member.setPassword("wnddkd12123");
		member.setName("kim맨");
		member.setNickname("여행인");
		member.setPhone("01056456");
		member.setEmail("malsklakslnkf");
		
		
		int id = mapper.update(member);
		log.info("UPDATE id: " + "change id");
		
		
	}

}









