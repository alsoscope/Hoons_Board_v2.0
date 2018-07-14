<%@ page language="java" contentType="text/html; charset=UTF-8"
		pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %>

	<div class="container">
		<div class="panel-body">
			<h2>새 글 쓰기</h2>
			<div class="form-group">
			<form action="/boards/new" method="post" id="create-form" class="create-form">
				<fieldset class="form">
					<div class="form-group  has-feedback">
						<div class="form-group">
							<input class="form-control" type="text" name="title" placeholder="제목을 입력해 주세요.">
						</div>
						<div class="form-group">
							<textarea class="form-control" name="content" style="height: 200px; resize: none" placeholder="내용을 입력해 주세요."></textarea>
						</div>
					</div>
				</fieldset>
				
				<hr color="gray">
				
				<div class="form-group" style="margin-top: 30px">
					<label>아래 영역에 첨부하실 파일을 올려주세요.</label>
					<div class="fileDrop">
					</div>
				</div>
				
				<div class="box-footer" style="background: #f8f8f8">
					<div>
						<hr color="gray">
					</div>
					
					<ul class="mailbox-attachments clearfix uploadedList">
					</ul>
				</div>
				
				<div class="nav" role="navigation">
					<fieldset class="buttons">
						<a href="/boards" class="btn btn-default btn-wide" onclick="return confirm('정말로 취소하시겠습니까?')">취소</a> 
						<input type="submit" class="create btn btn-success btn-wide pull-right" value="저장">
					</fieldset>
				</div>
			</form>
		</div>
	</div>
	
	<script id="attach-template" type="text/x-handlebars-template">
		<li>
			<span class="mailbox-attachment-icon has-img">
				<img src="{{imgSrc}}" alt="Attachment">
			</span>
			<div class="mailbox-attachment-info">
				<a href="{{getLink}}" class="mailbox-attachment-name">{{fileName}}</a>
				<a href="{{fullName}}" class="btn btn-default btn-xs pull-right del-btn">
					<i class="fa fa-fw fa-remove"></i>
				</a>
			</div>
		</li>
	</script>
	
	<script>
		var source = $("#attach-template").html();
		var template = Handlebars.compile(source);
		
		$(".fileDrop").on("dragenter dragover", function(event) {
			event.preventDefault();
		});
		
		$(".fileDrop").on("drop", function(event) {
			event.preventDefault();
			
			var files = event.originalEvent.dataTransfer.files;
			var file = files[0];
			
			var formData = new FormData();
			formData.append("file", file);
			
			$.ajax({
				type:"POST",
				url:"/boards/uploadFile",
				data:formData,
				processData:false,
				contentType:false,
				dataType:"text",
				success:function(result) {
					var fileInfo = getFileInfo(result);
					var html = template(fileInfo);
					
					$(".uploadedList").append(html);
				}
			});
		});
		
		$("#create-form").submit(function(event) {
			event.preventDefault();
			
			var str = "";
			
			$(".uploadedList .del-btn").each(function (index) {
				str += "<input type='hidden' name='files[" + index + "]' value='" + $(this).attr("href") + "' >";
			});
			
			$(this).append(str);
			$(this).get(0).submit();
		});
	</script>
	
<%@ include file="../include/footer.jsp" %>