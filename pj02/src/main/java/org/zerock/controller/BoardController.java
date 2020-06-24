package org.zerock.controller;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.service.BoardService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/board/*")
@AllArgsConstructor
public class BoardController {
	
	//서비스 객체 얻기
	private BoardService service;
	
	// 게시물 목록 얻기
	@GetMapping("/list")
	public void list(Criteria cri, Model model) { // 모델 객체가 자동으로 주입됨.
		log.info("list: "+ cri);
		model.addAttribute("list", service.getList(cri)); // 결과를 모델에 담아야 한다.
		
		//return ;//view 의 이름. 
	}
	
	@GetMapping("/register")
	public void register() {
		
	}
	
	@GetMapping({"/get", "/modify"})
	public void get(@RequestParam("bno") Long bno, Model model) {
		log.info("/get");
		model.addAttribute("board", service.get(bno));
		
		// get.jsp 로 이동 / modify.jsp로 이동
		
	}
	
	// 게시글 등록
	@PostMapping("/register")
	public String register(BoardVO board, RedirectAttributes rttr) {
		log.info("register: "+board);
		service.register(board);
		
		// redirect 할 곳에 결과를 전해줌
		rttr.addFlashAttribute("result", board.getBno());
		// 게시글 등록 후 목록 보여줌
		return "redirect:/board/list"; // 목록 페이지를 다시 요청하라고 요청함
	}
	
	
	@PostMapping("/modify")
	public String modify(BoardVO board, RedirectAttributes rttr) {
		log.info("modify:"+board);
		
		if(service.modify(board)) {
			rttr.addFlashAttribute("result","success");
		}
		
		return "redirect:/board/list";
	}
	
	@PostMapping("/remove")
	public String remove(@RequestParam("bno") Long bno, RedirectAttributes rttr) {
		log.info("remove....."+bno);
		
		if(service.remove(bno)) {
			rttr.addFlashAttribute("result","success");
		}
		
		return "redirect:/board/list";
	}
}
