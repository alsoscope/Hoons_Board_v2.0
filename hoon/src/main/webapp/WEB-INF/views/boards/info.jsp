<%@ page language="java" contentType="text/html; charset=UTF-8"
		pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="../include/header.jsp" %>

	<div class="container" style="margin-bottom: 10px; max-width: 70%;">
		<font size="6">글 보기</font><br>
		<a href="/boards/new" class="create btn btn-success btn-wide pull-right">
			<i class="fa fa-pencil"></i> 새 글 쓰기
		</a>
	</div>
	
	<!-- 글 보기 -->
	<div class="container"  style="margin-bottom: 50px; max-width: 70%;">
		<div class="panel-body">
			<div class="table-responsive">
				<div>
					<font size="4" color="gray"> &#35; <i>${bVO.bno }</i></font>
					<div class="content-identity pull-right" style="margin-bottom: 10px">
						<div class="content-identity-count" style="width: 100px">
							<a style="align-items: right" href="#">
								<i id="like" class="fa fa-heart fa-2x"></i>
								<font size="4"> ${bVO.likecnt }</font>
							</a>
						</div>
						<div class="content-identity-count" style="width: 100px">
							<i class="fa fa-comment fa-2x"></i>
							<font size="4" class="replycnt"></font>
						</div>
						<div class="content-identity-count" style="width: 100px">
							<i class="fa fa-eye fa-2x"></i>
							<font size="4"> ${bVO.viewcnt }</font>
						</div>
					</div>
				</div>
				
				<table class="table table-striped" style="text-align: center; border: 1px solid #dddddd">
					<tr>
						<th>제목</th>
						<th colspan="4">${bVO.title }</th>
					</tr>
					<tr>
						<th rowspan="2">작성자</th>
						<td rowspan="2">
							<a href="#">
<!-- 								<img class="media-object" src="..." alt="IMG"> -->
							</a>
						</td>
						<td>${bVO.writer }</td>
						<fmt:formatDate var="regdate" value="${bVO.regdate }" pattern="yyyy-MM-dd hh:mm:ss"/>
					</tr>
					<tr>
						<td><i class="fa fa-clock-o"></i> ${regdate }</td>
					</tr>
					<tr>
						<th>내용</th>
						<td colspan="3" align="left" height="200">${bVO.content }</td>
					</tr>
				</table>
			</div>
			<c:if test="${login.uid == bVO.writer }">
			<div class="dropdown" align="left" style="margin-left: 10px">
				<a href="javascript://" data-toggle="dropdown">
					<i class="fa fa-cog fa-2x" data-toggle="tooltip" data-placement="left" data-original-title="게시물 설정"></i>
				</a>
				<ul class="dropdown-menu" role="menu">
					<li>
						<a href="/boards/edit/${bVO.bno }" class="edit">
							<i class="fa fa-edit fa-fw"></i> 수정
						</a>
					</li>
					<li>
						<a id="del-btn" href="${bVO.bno }" class="delete">
							<i class="fa fa-trash-o fa-fw"></i> 삭제
						</a>
					</li>
				</ul>
			</c:if>
			</div>
		</div>
		
		<form id="del-info-form" action="/boards/${bVO.bno }" method="post">
			<input type="hidden" name="_method" value="DELETE">
			<input type="hidden" name="bno" value="${bVO.bno }">
		</form>
		
		<ul class="mailbox-attachments clearfix uploadedList">
		</ul>
		
		<hr style="border-width: 1px; border-color: gray">	
	</div>
	
	<!-- 댓글 쓰기 -->
	<div class="container" style="max-width: 70%;">
		<div class="panel-body">
			<h3 style="display: inline;">댓글</h3>
			<h3 class="replycnt" style="display: inline;"></h3>
			<c:if test="${login.uid != null }">
			<fieldset class="form">
				<div class="form-group  has-feedback">
					<div class="form-group">
						<input id="replytext" class="form-control" type="text" name="title" placeholder="내용을 입력해 주세요.">
					</div>
				</div>
				<div class="nav" role="navigation">
					<fieldset class="buttons">
						<input id="reply-submit-btn" type="submit" class="create btn btn-success btn-wide pull-right" value="등록">
					</fieldset>
				</div>
			</fieldset>
			</c:if>
			<c:if test="${login.uid == null }">
				<div class="panel-body" style="background: white; margin-top: 20px;">
					<h5 class="text-center">
						<a href="/user/login" class="link">로그인</a>을 하시면 답변을 등록할 수 있습니다.
					</h5>
            	</div>
			</c:if>
		</div>
	</div>
	
	<div class="container" style="max-width: 70%;">
	
		<!-- 댓글 목록 -->
		<div>
			<ul id="replies" class="list-group">
			</ul>
		</div>
	
		<!-- 댓글 페이징 -->
		<div align="center" style="margin-bottom: 20px">
			<nav>
				<ul class="pagination">
				</ul>
			</nav>
		</div>
	</div>	

	<!-- 댓글 관리 모달창 -->
	<div class="modal fade" id="replyModal" tabindex="-1" role="dialog" aria-labelledby="replyModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title" id="exampleModalLabel">댓글</h4>
				</div>
				<div class="modal-body">
					<div class="form-group">
						<label for="replytext" class="control-label">Message:</label>
						<textarea style="resize: none; height: 100%" class="form-control" id="replytext-modal" placeholder="내용을 입력해 주세요."></textarea>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default pull-left" data-dismiss="modal">닫기</button>
					<button type="button" id="reply-edit-btn" class="create btn btn-success btn-wide pull-right">저장</button>
					<button type="button" id="reply-del-btn" class="create btn btn-danger btn-wide pull-right">삭제</button>
				</div>
			</div>
		</div>
	</div>
	
	<script id="attached-template" type="text/x-handlebars-template">
		<li data-src="{{fullName}}">
			<span class="mailbox-attachment-icon has-img">
				<img src="{{imgSrc}}" alt="Attachment">
			</span>
			<div class="mailbox-attachment-info">
				<a href="{{getLink}}" class="mailbox-attachment-name">{{fileName}}</a>
			</div>
		</li>
	</script>
	
	<script id="reply-template" type="text/x-handlebars-template">
		{{#each .}}
			<li class="list-group-item" data-rno={{rno}}>
				<div class="timeline-item">
					<div class="media-left" style="margin-top: 10px; margin-right: 10px;">
						<a href="#">
							<img class="media-object" src="..." alt="IMG">
						</a>
					</div>
					<div class="media-body">
						<h4 class="media-heading">{{replywriter}}</h4>
						<span class="time">
							<i class="fa fa-clock-o"></i> {{prettifyDate regdate}}
						</span>
					</div>
					<div class="timeline-boddy" style="margin-top: 10px; margin-bottom: 30px;">{{replytext}}</div>
					{{#eqReplywriter replywriter}}
						<div class="timeline-footer" style="margin-top: 10px">
							<button type='button' class='btn btn-info' data-toggle='modal' data-target='#replyModal'>관리</button>
						</div>
					{{/eqReplywriter}}
				</div>
			</li>
		{{/each}}
	</script>
	
	<script>
		getReplyList(1);
		getAttachList();
	
		function getAttachList() {
			var bno = "${bVO.bno}";
			var source = $("#attached-template").html();
			var template = Handlebars.compile(source);
			
			$.ajax({
				type:"GET",
				url:"/boards/" + bno + "/attaches",
				dataType:"json",
				success:function(result) {
					$(result).each(function() {
						var fileInfo = getFileInfo(this);
						var html = template(fileInfo);
						
						$(".uploadedList").append(html);
					});
				}
			});
		}
		
		$("#reply-submit-btn").click(function() {
			var bno = "${bVO.bno}";
			var replytext = $("#replytext").val();
			var replywriter = "${login.uid}";
		
			$.ajax({
				type:"POST",
				url:"/replies/new",
				headers:{
					"Content-Type":"application/json",
					"X-HTTP-Method-Override":"POST"
				},
				data:JSON.stringify({
					bno:bno,
					replytext:replytext,
					replywriter:replywriter
				}),
				dataType:"text",
				success:function() {
					alert("등록되었습니다.");
					$("#replytext").val("");
					getReplyList(1); 
				}
			});
		});
		
		function getReplyList(page) {
			var bno = "${bVO.bno}";
			
			$.ajax({
				type:"GET",
				url:"/boards/" + bno + "/replies?page=" + page,
				headers:{
					"Content-Type":"application/json",
					"X-HTTP-Method-Override":"GET"
				},
				dataType:"json",
				success:function(result) {
					var replyList = result.replyList;
					var pageMaker = result.pageMaker;
					var boardInfo = result.boardInfo;
					
					var source = $("#reply-template").html();
					var template = Handlebars.compile(source);
					
					$("#replies").html(template(replyList));
					getReplyPages(pageMaker);
					getSubInfo(boardInfo);
				}
			});
		}
		
		function getReplyPages(pageMaker) {
			str = ""; 
			
			if (pageMaker.prev) {
				str += "<li><a href='" + (pageMaker.startPage - pageMaker.displayPageNum) + "'>&laquo;</a></li>";
			}
			
			for (var i = pageMaker.startPage; i <= pageMaker.endPage; i++) {
				var activeClass = pageMaker.cri.page == i ? "class='active'" : "";
				str += "<li " + activeClass + "><a href='" + i + "'>" + i + "</a></li>";
			}
			
			if (pageMaker.next) {
				str += "<li><a href='" + (pageMaker.startPage + pageMaker.displayPageNum) + "'>&raquo;</a></li>";
			}
			
			$(".pagination").html(str);
		}
		
		function getSubInfo(boardInfo) {
			var replycnt = boardInfo.replycnt;
			
			$(".replycnt").html(" " + replycnt);
		}
		
		$("#replies").on("click", ".list-group-item", function() {
			var reply = $(this);
			var timelineheader = reply.find(".timeline-header").html();
			var replytext = reply.find(".timeline-boddy").text();
			
			$("#replytext-modal").val(replytext);
			$(".modal-header").html(timelineheader);
		});
		
		$("#reply-del-btn").click(function() {
			if(confirm("정말로 삭제하시겠습니까?")) {
				var rno = $(".list-group-item").attr("data-rno");
				var bno = "${bVO.bno}";
				
				$.ajax({
					type:"DELETE",
					url:"/replies/" + rno + "?bno=" + bno,
					headers:{
						"Content-Type":"application/json",
						"X-HTTP-Method-Override":"DELETE"
					},
					dataType:"text",
					success:function(result) {
						if(result == "SUCCESS") {
							alert("삭제되었습니다.");
							$("#replyModal").modal("hide");
							getReplyList(1);
						}
					}
				});
			}
		});
		
		$("#reply-edit-btn").click(function() {
			var rno = $(".list-group-item").attr("data-rno");
			var replytext = $("#replytext-modal").val();
			
			$.ajax({
				type:"PUT",
				url:"/replies/" + rno,
				data:JSON.stringify({replytext:replytext}),
				headers:{
					"Content-Type":"application/json",
					"X-HTTP-Method-Override":"PUT"
				},
				dataType:"text",
				success:function(result) {
					if(result == "SUCCESS") {
						alert("수정되었습니다.");
						$("#replyModal").modal("hide");
						getReplyList(1);
					}
				}
			});
		});
		
		$(".pagination").on("click", "li a", function(event) {
			event.preventDefault();
			
			var page = $(this).attr("href");
			
			getReplyList(page);
		});
		
		Handlebars.registerHelper("prettifyDate", function(date) {
			var dateObj = new Date(date);
			
			var year = dateObj.getFullYear();
			var month = dateObj.getMonth();
			var date = dateObj.getDate();
			var hours = dateObj.getHours();
			var minutes = dateObj.getMinutes();
			var seconds = dateObj.getSeconds();
			
			return year + "-" + month + "-" + date + " " + hours + ":" + minutes + ":" + seconds;
		});
		
		Handlebars.registerHelper("eqReplywriter", function(replywriter, block) {
			var accum = "";
		
			if (replywriter == "${login.uid}") {
				accum += block.fn();
			}
			
			return accum;
		});
	</script>
	
	<!-- Board 관련 -->
	<script>
		$("#del-btn").click(function(event) {
			if (confirm("정말로 삭제하시겠습니까?")) {
				var replycnt = $(".replycnt").html().replace(/[^0-9]/g, "");
				
				if (replycnt > 0) {
					alert("댓글이 있는 글은 삭제할 수 없습니다.");
					
					return;
				} else {
					event.preventDefault();
					
					var delInfoForm = $("#del-info-form");
					var files = [];
					
					$(".uploadedList li").each(function(index) {
						files.push($(this).attr("data-src"));
					});
					
					if (files.length > 0) {
						$.ajax({
							type:"POST",
							url:"/boards/deleteAllFiles",
							data:{files:files}
						});
					}
					
					delInfoForm.submit();
				}
			}
		});
	</script>
<%@ include file="../include/footer.jsp" %>