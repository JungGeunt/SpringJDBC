package org.zerock.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.zerock.command.ScoreVO;
import org.zerock.service.ScoreService;

@Controller
@RequestMapping("/service/*")
public class ScoreController {
	
	@Autowired
	ScoreService scoreService;

	@RequestMapping("")
	public String basic() {
		return "home";	
	}
	
	//등록 화면 처리
	@RequestMapping(value ="/1scoreRegister", method =RequestMethod.GET)
	public String scoreRegister() {
		return "service/1scoreRegister";
	}
	
	//점수 등록 메서드
	@RequestMapping(value ="/scoreForm", method =RequestMethod.POST )
	public String scoreForm(ScoreVO vo) {
		
		//점수 등록 메서드.... 바로 전달 받지 못함 서비스를 통해서 받아야함.
		scoreService.scoreRegist(vo);
		
		return "service/2socreResult";
	}
	
	//결과 출력 메서드 
	@RequestMapping(value = "/3scoreList" ,method =RequestMethod.GET)
	public String scoreList(Model model) {
		
		//점수 결과를 받아서 처리... model에 넣어서 이동...
		List<ScoreVO> list = scoreService.scoreResult();
		model.addAttribute("scoreList" , list);	
		
		return "service/3scoreList";
	}
	
	//삭제 메서드
	@RequestMapping(value = "/scoreDelete")
	public String scoreDelete(@RequestParam("num")String num){
		//점수 삭제..
		scoreService.scoreDelete(num);
		
		//단순히 service/3scoreList로 이동할 경우 화면에 전달할 결과 값이 없음.
		// /service/3scoreList 로 이동 >> 
		//즉, 다시 컨트롤러에 있는 3scoreList에 도달 할수 있게 강제 이동
		//redirect처리를 한다
		return "redirect:/service/3scoreList";
	}
	
	
}
