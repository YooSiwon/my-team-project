package org.zerock.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zerock.domain.MemberVO;
import org.zerock.service.MemberService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
@Controller
@AllArgsConstructor
@RequestMapping("/member/*")
@Log4j
public class MemberController {

	private MemberService service;

	// ##회원가입 - GET
	@GetMapping("/join")
	public void register() {
		
	}

	// ##회원가입 - POST
	@PostMapping("/join")
	public String register(MemberVO member, HttpServletRequest req) {
		
		Map<String, Boolean> errors = new HashMap<String, Boolean>();
		req.setAttribute("errors", errors);
		validate(errors, member);
		
		if (errors.isEmpty()) {
			service.register(member);
			// 서비스에 명령
			return "redirect:joinSuccess";
			

		} else {
			return "/member/join";
		}
		
	}
	// ##회원가입 - 아이디 중복 체크
	@GetMapping("/join/idDupCheck")
	@ResponseBody
	public String idDupCheck(String inputId) {
		
		//아이디 값이 있으면
		
		if(inputId.equals("")) {
			return "-2";
		} else {
			MemberVO member = service.getMember(inputId);
			
			if(member == null) {
				return "0"; // 회원없으면 0 
			} else {
				return "-1"; //회원있으면 -1 
			}
		}
	}
	
	// ##회원가입 - 닉네임 중복 체크
	@GetMapping("/join/nicknameDupCheck")
	@ResponseBody
	public String nicknameDupCheck(String inputNickname) {
		
		//닉네임 값이 있으면
		
		if(inputNickname.equals("")) {
			return "-2";
		} else {
			MemberVO member = service.getMember(inputNickname);
			
			if(member == null) {
				return "0"; //회원이 없으면 0 리턴
			} else {
				return "-1"; //회원있으면 -1 리턴
			}
		}
	}
	
	// ##로그인 성공 메시지
	@GetMapping("/joinSuccess")
	public void joinSuccess() {
	}

	// ##로그인 - GET
	@GetMapping("/login")
	public void login() {
	}

	// ##로그인 test - POST
	@PostMapping("/login")
	@ResponseBody
	public ResponseEntity<String> login(String inputId, String inputPw, HttpSession session) {
		
		log.info(inputId);
		log.info(inputPw);
		
		MemberVO user = service.getMember(inputId);
		
		//사용자의 아이디를 가진 회원이 있다면
		if(user != null && inputPw != null) {
			// member.getPassword(); 사용자가 적은 비밀번호
			// loginMember.getPassword(); 아이디로 검색해서 꺼낸 회원의 비밀번호
			
			boolean checkMemberPw = service.checkMember(inputPw, user.getPassword());
			//비밀번호 확인
			
			if(checkMemberPw) {
				session.setAttribute("authUser", user);
				
				
			}
				return new ResponseEntity<> ("success", HttpStatus.OK);
			} else {
				return new ResponseEntity<> (HttpStatus.INTERNAL_SERVER_ERROR);		
			}
		}	
	
	
	// ##로그아웃 
	@GetMapping("/logout")
	public String logout(MemberVO member, HttpSession session) {
		
		if(session != null) {
			session.invalidate();
		}
		
		return "redirect:/index.jsp";
	}
	
	
	// ##joinErrors 
	public void validate(Map<String, Boolean> errors, MemberVO member) {
		checkEmpty(errors, member.getId(), "memberId");
		checkEmpty(errors, member.getPassword(), "memberPw");
		checkEmpty(errors, member.getName(), "memberName");
		checkEmpty(errors, member.getNickname(), "memberNickname");
		checkEmpty(errors, member.getPhone(), "memberPhone");
		checkEmpty(errors, member.getEmail(), "memberEmail");		
	
	}
	
	public void checkEmpty(Map<String, Boolean> errors, String value, String fieldName) {
		
		if(value == null || value.isEmpty()) {
			errors.put(fieldName, true);
		}
	}
	
	
	// ##내 정보 보기
	@GetMapping("/myHome")
	public String myHome() {
		return "/member/myHome";
		
	}
	
	// ##내 정보 수정 - GET
	@GetMapping("/myModify")
	public void myModifyPage() {
	
	}
	
	// ##내 정보 수정 - POST
	@PostMapping("/myModify")
	public String myModify(MemberVO member, HttpSession session) {
		
		MemberVO user = (MemberVO) session.getAttribute("authUser");
		log.info(user);
		log.info(service);
		log.info(member);
		MemberVO userMember = service.getMember(user.getId());
		
		boolean checkMember = service.checkMember(userMember.getId(), member.getId());
		// 동일 아이디 체크
		
		if(checkMember) {
			service.modify(member); 
			
			log.info(member);
			
			session.setAttribute("authUser", member);
			//수정된 정보 세션 저장
			
			return "/member/myHome";
			
		
		}
	
		return "/member/myHome";
		
	}
	
	
	 // 내 정보 수정	
	
	@DeleteMapping("/delete")
	@ResponseBody
	public ResponseEntity<String> delete(String userId, String pwConfirm, HttpSession session) {
		//회원 삭제
		log.info(userId);
		log.info(pwConfirm);
			
		
		
		MemberVO userMember = service.getMember(userId);
		
		if(userMember.getPassword().equals(pwConfirm)) {
			service.remove(userId);
			log.info("회원탈퇴성공");
			
			if(session != null) {
				session.invalidate();
			}
			
			return new ResponseEntity<> ("success", HttpStatus.OK);
		} else {
			return new ResponseEntity<> (HttpStatus.INTERNAL_SERVER_ERROR);		
		}
	}
	
	

	
}