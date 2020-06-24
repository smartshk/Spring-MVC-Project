package org.zerock.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.domain.SampleVO;
import org.zerock.domain.Ticket;

import lombok.extern.log4j.Log4j;

@RestController
@RequestMapping("/sample")
@Log4j
public class SampleController {
	@GetMapping(value="/getText", produces="text/plain; charset=UTF-8")
	public String getText() {
		log.info("MIME TYPE: "+MediaType.TEXT_PLAIN_VALUE);
		return "안녕하세요";
	}
	
	@GetMapping(value="/getSample", produces= {MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_XML_VALUE})
	public SampleVO getSample() {
		// view 가 리턴되는게 아니라
		// 객체가 리턴될 수 있도록 한다. 주로 XML이나 JSON형태!
		return new SampleVO(112, "스타", "로드"); /*중요*/
	}
	
	@GetMapping(value="/getList")
	public List<SampleVO> getList(){
		// 컬렉션 타입의 객체 반환
		return IntStream.range(1,10)
				.mapToObj(i->new SampleVO(i,i+"First",i+" Last"))
				.collect(Collectors.toList());
	}
	
	@GetMapping(value="/getMap")
	public Map<String, SampleVO> getMap(){
		Map<String, SampleVO> map = new HashMap<>();
		map.put("First", new SampleVO(111, "그루트", "아이엠"));
		map.put("Second", new SampleVO(222, "서현", "김"));
		
		return map;
	}
	
	@GetMapping(value="/check", headers="Accept=*/*", params= { "height","weight" })
	public ResponseEntity<SampleVO> check(Double height, Double weight){
		//응답객체. 데이터와 함꼐 HTTP헤더의 상태 메시지 등을 같이 전달하는 용도로 사용
		SampleVO vo = new SampleVO (000, "" + height, "" + weight);
		ResponseEntity<SampleVO> result = null;
		if (height < 150) {
			result = ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(vo);
		} else {
			result = ResponseEntity.status(HttpStatus.OK).body(vo);
		}
		return result;
	}
	
	@GetMapping("/product/{cat}/{pid}")
	public String[] getPath(
			@PathVariable("cat") String cat, // URL에서 {}의 이름을 처리 -> 컨트롤러 메서드에서 변수로 처리 가능 
			@PathVariable("pid") Integer pid ) { // int, double은 사용불가
		return new String[] {"category: "+cat, "productid:"+pid};
	}
	
	@PostMapping("/ticket") // @RequeatBody가 요청한 내용을 처리하기 때문.
	public Ticket convert(@RequestBody Ticket ticket) {
		// @RequestBody => 클라이언트가 json으로 요청하면 ticket 객체로 변환하려고 함. 
		log.info("convert.....ticket"+ticket);
		return ticket;
	}
}
