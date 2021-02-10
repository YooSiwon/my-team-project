package org.zerock.service;

import java.util.List;

import org.zerock.domain.MemberVO;

public interface MemberService {
	public void insert(MemberVO member);
	
	public MemberVO get(Long bno);
	
	public boolean modify(MemberVO member);
	
	public boolean remove(Long bno);
	
	public List<MemberVO> getList();
	
}
