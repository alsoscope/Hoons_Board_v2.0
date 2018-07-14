<%@ page language="java" contentType="text/html; charset=UTF-8"
		pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="../include/header.jsp" %>

	<div class="container" style="margin-bottom: 10px">
		<font size="6">글 보기</font><br>
		<a href="/boards/new" class="create btn btn-success btn-wide pull-right">
			<i class="fa fa-pencil"></i> 새 글 쓰기
		</a>
	</div>
	
	<!-- 글 보기 -->
	<div class="container"  style="margin-bottom: 50px">
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
						<td rowspan="2">IMG</td>
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
						<a id="delete-btn" href="${bVO.bno }" class="delete">
							<i class="fa fa-trash-o fa-fw"></i> 삭제
						</a>
					</li>
				</ul>
			</div>
		</div>
		
		<ul class="mailbox-attachments clearfix uploadedList">
		</ul>
		
		<div class="popup back" style="display: none;">
			<div id="popup_front" class="popup front" style="display: none;"></div>
			<img id="popup_img">
		</div>
		
		<hr style="border-width: 1px; border-color: gray">	
	</div>
	
	<!-- 댓글 쓰기 -->
	<div class="container">
		<div class="panel-body">
			<h3 style="display: inline;">댓글</h3>
			<h3 class="replycnt" style="display: inline;"></h3>
			<fieldset class="form">
				<div class="form-group  has-feedback">
					<div class="form-group">
						<input id="replytext" class="form-control" type="text" name="title" placeholder="내용을 입력해 주세요.">
					</div>
				</div>
				<div class="nav" role="navigation">
					<fieldset class="buttons">
						<input id="submit-btn" type="submit" class="create btn btn-success btn-wide pull-right" value="등록">
					</fieldset>
				</div>
			</fieldset>
		</div>
	</div>
	
	<div class="container">
	
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
						<textarea style="resize: none; height: 100px" class="form-control" id="replytext-modal" placeholder="내용을 입력해 주세요." val=""></textarea>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default pull-left" data-dismiss="modal">닫기</button>
					<button type="button" id="reply-edit-btn" class="create btn btn-success btn-wide pull-right">저장</button>
					<button type="button" id="reply-delete-btn" class="create btn btn-danger btn-wide pull-right">삭제</button>
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
				<span class="time">
					<i class="fa fa-clock-o"></i> {{prettifyDate regdate}}
				</span>
				<h5 class="timeline-header"><i class="fa fa-comment"></i> <strong>reply{{rno}}</strong> - {{replywriter}}</h5>
				<div class="timeline-boddy">{{replytext}}</div>
				<div class="timeline-footer" style="margin-top: 10px">
					<button type='button' class='btn btn-info' data-toggle='modal' data-target='#replyModal'>관리</button>
				</div>
			</div>
		</li>
		{{/each}}
	</script>
	
	<script>
		$(document).ready(function() {
			getReplyList(1);
			getAttachList();
		});
	
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
		
		$(".iploadedList").on("click", ".mailbox-attachment-info a", function(event) {
			var fileLink = $(this).attr("href");
			
			if (checkImageType(fileLink)) {
				event.preventDefault();
				
				var imgTag = $("#pop_img");
				imgTag.attr("src", fileLink);
				console.log(imgTag.attr("src"));
				
				$("#popup_img").click(function() {
					$(".popup").hide("slow");
				});
			}
		});
		
		$("#submit-btn").click(function() {
			var bno = "${bVO.bno}";
			var replytext = $("#replytext").val();
		
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
					replywriter:"22"	/////////////////////////////////////////////// 세션처리 후 필히 수정!!
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
		
		$("#reply-delete-btn").click(function() {
			if(confirm("정말로 삭제하시겠습니까?")) {
				var rno = $(".list-group-item").attr("data-rno");
				
				$.ajax({
					type:"DELETE",
					url:"/replies/" + rno,
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
		
		// Handlebars 날짜포맷 Helper 등록
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
	</script>
	
	<!-- 글 관련 -->
	<script>
		$("#delete-btn").click(function(event) {
			if (confirm("정말로 삭제하시겠습니까?")) {
				event.preventDefault();
				
				var bno = "${bVO.bno}";
				
				$.ajax({
					type:"DELETE",
					url:"/boards/" + bno,
					headers:{
						"Content-Type":"application/json",
						"X-HTTP-Method-Override":"DELETE"
					},
					dataType:"text",
					success:function(result) {
						if(result == "SUCCESS") {
							alert("삭제되었습니다.");
							location.href="/boards";
						}
					}
				});
			}
		});
	</script>
<%@ include file="../include/footer.jsp" %>