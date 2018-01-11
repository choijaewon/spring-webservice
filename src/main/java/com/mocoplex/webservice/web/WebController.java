package com.mocoplex.webservice.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.mocoplex.webservice.service.PostsService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class WebController {

	private PostsService postsService;
	
	/*
	 * Spring 4.3 부터는 @RequestMapping을 대체할 수 있는 여러 매핑 어노테이션이 추가 되었습니다. 
	   @GetMapping은 이전으로 보면 @RequestMapping(value="/", method = RequestMethod.GET)과 동일
	 */	
	@GetMapping("/")
	public String main(Model model) {
		model.addAttribute("posts", postsService.findAllDesc());
		return "main";
	}
		
}
