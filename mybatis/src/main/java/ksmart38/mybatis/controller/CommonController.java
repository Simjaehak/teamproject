package ksmart38.mybatis.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ksmart38.mybatis.service.MemberService;

@Controller
public class CommonController {
	
	private final MemberService memberService;
	
	public CommonController(MemberService memberService) {
		this.memberService = memberService;
	}

	@GetMapping("loginHistory")
	public String loginHistory(Model model
								,@RequestParam(value="currentPage", required = false, defaultValue = "1") int currentPage) {
																			//String인 값 알아서 캐스팅 해줌
		Map<String, Object> resultMap = memberService.getLoginHistory(currentPage);
		
		model.addAttribute("currentPage"		, currentPage);
		model.addAttribute("lastPage"			, resultMap.get("lastPage"));
		model.addAttribute("loginHistoryList"	, resultMap.get("loginHistory"));
		model.addAttribute("startPageNum"	, resultMap.get("startPageNum"));
		model.addAttribute("endPageNum"	, resultMap.get("endPageNum"));
		return "login/loginHistory";
		
	}
	
	@GetMapping("/")
	public String main() {
		return "main";
	}
}
