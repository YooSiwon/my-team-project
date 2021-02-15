package org.zerock.mapper;


import java.util.Date;
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
	private Date regdate;
	
	@Test
	public void testGetList() {
		List<MemberVO> list = mapper.getList();
		
		
	}	
	@Test
	public void testInsert() {
		
		MemberVO member = new MemberVO();
		member.setId("KIM6");
		member.setPassword("나password6");
		member.setName("김치6");
		member.setNickname("여행자6");
		member.setPhone("01066666666");
		member.setEmail("traver@naver.com");
		member.setRegdate(regdate);	
		
		mapper.insert(member);
		
		log.info(member);
		
	}
	
	@Test
	public void testRead() {
		
		MemberVO member = mapper.read("KIM6");
				
		
		log.info(member);
	}
	
	@Test
	public void testDelete() {
		
		log.info("DELETE : " + mapper.delete("KIM6"));
	}

	@Test
	public void testUpdate() {
		
		MemberVO member = new MemberVO();
		
		member.setId("KIM6");
		member.setPassword("나password6");
		member.setName("kim61번");
		member.setNickname("수정여행");
		member.setPhone("01066666667");
		member.setEmail("pas@naver.com");
		
		
		int id = mapper.update(member);
		log.info("UPDATE id: " + "change id");
		
		
	}

}









