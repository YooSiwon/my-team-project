package org.zerock.mapper;

import java.util.List;


import org.apache.ibatis.annotations.Select;
import org.zerock.domain.MemberVO;

public interface MemberMapper {
	
	//@Select("select * from Member")
	public List<MemberVO> getList();
	
	public void insert(MemberVO member);
	
	public void insertSelectKey(MemberVO member);
	
	public MemberVO read(Long bno);
	
	public int delete(Long bno);
	
	public int update(MemberVO member);
	
	
	
}






