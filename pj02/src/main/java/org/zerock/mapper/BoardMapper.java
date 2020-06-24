package org.zerock.mapper;

import java.util.List;

import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;

public interface BoardMapper {
	//@Select("select * from tbl_board where bno > 0 ")
	public List<BoardVO> getList();
	
	// 게시글 삽입
	public void insert(BoardVO board);
	
	public Integer insertSelectKey(BoardVO board); // 키 반환
	
	// 게시글 조회
	public BoardVO read(Long bno);
	
	public int delete(Long bno);
	
	public int update(BoardVO board);
	
	public List<BoardVO > getListWithPaging (Criteria cri);
}
