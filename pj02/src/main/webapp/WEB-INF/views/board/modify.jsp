<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri ="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@include file="../includes/header.jsp" %>

<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">Modify Board</h1>
	</div>
</div>
<div class="row">
	<div class="col-lg-12">
		<div class="panel panle-default">

			<form role="form" action="/board/modify" method="post">
				<div class="panel-heading">게시물 수정</div>
				<div class="panel-body">

					<form role="form" action="/board/modify" method="post">

						<input type='hidden' name='pageNum'
							value='<c:out value="${cri.pageNum }"/>'> <input
							type='hidden' name='amount'
							value='<c:out value="${cri.amount }"/>'> <input
							type='hidden' name='type' value='<c:out value="${cri.type }"/>'>
						<input type='hidden' name='keyword'
							value='<c:out value="${cri.keyword }"/>'>


						<div class="form-group">
							<label>Bno</label> <input class="form-control" name='bno'
								value='<c:out value="${board.bno }"/>' readonly="readonly">
						</div>

						<div class="form-group">
							<label>Title</label> <input class="form-control" name='title'
								value='<c:out value="${board.title }"/>'>
						</div>

						<div class="form-group">
							<label>Text area</label>
							<textarea class="form-control" rows="3" name='content' cols=""><c:out
									value="${board.content}" /></textarea>
						</div>

						<div class="form-group">
							<label>Writer</label> <input class="form-control" name='writer'
								value='<c:out value="${board.writer}"/>' readonly="readonly">
						</div>

						<div class="form-group">
							<label>RegDate</label> <input class="form-control" name='regDate'
								value='<fmt:formatDate pattern = "yyyy/MM/dd" value = "${board.regdate}" />'
								readonly="readonly">
						</div>

						<div class="form-group">
							<label>Update Date</label> <input class="form-control"
								name='updateDate'
								value='<fmt:formatDate pattern = "yyyy/MM/dd" value = "${board.updateDate}" />'
								readonly="readonly">
						</div>



						<button type="submit" data-oper='modify' class="btn btn-default">Modify</button>
						<button type="submit" data-oper='remove' class="btn btn-danger">Remove</button>
						<button type="submit" data-oper='list' class="btn btn-info">List</button>
					</form>
 		</div>
      <!--  end panel-body -->

    </div>
    <!--  end panel-body -->
  </div>
  <!-- end panel -->
</div>
<!-- /.row -->

<script type="text/javascript">
$(document).ready(function(){
	var formObj = $("form");
	
	//remove 버튼이 눌려지면, action 속성 값을 /board/remove로 변경함
	// <button> 이 클릭되면 함수를 실행해라. 
	$('button').on("click", function(e){
		e.preventDefault();
		
		var operation = $(this).data("oper"); // 클릭된 버튼의 data-oper 속성값
		if(operation === 'remove'){
			// <form>의 action 속성을 바꿔야 한다
			// 먼저 form 을 얻어야 한다
			formObj.attr("action","/board/remove");
		} else if(operation === 'list'){
			self.location="/board/list";
			return;
		}
		
		formObj.submit(); // 서버에 <input>에 담긴 데이터를 전달하면서 요청
		
	});
	
});
</script>

<%@include file="../includes/footer.jsp" %>


