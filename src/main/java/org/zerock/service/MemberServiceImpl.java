package org.zerock.service;

import org.zerock.domain.MemberVO;
import org.zerock.mapper.MemberMapper;

public class MemberServiceImpl implements MemberService {
	
	MemberMapper mapper;
	
	@Override
	public void insert(MemberVO member) {
		mapper.insert(member);
		
	}

}
