<%@ page language="java" contentType="text/html; charset=UTF-8"
		pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %>

	<div class="container">
		<div class="panel-body">
			<h2>새 글 쓰기</h2>
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
	
					<div class="nav" role="navigation">
						<fieldset class="buttons">
							<a href="/boards" class="btn btn-default btn-wide" onclick="return confirm('정말로 취소하시겠습니까?')">취소</a> 
							<input type="submit" class="create btn btn-success btn-wide pull-right" value="등록">
						</fieldset>
					</div>
				</fieldset>
			</form>
		</div>
	</div>
	
<%@ include file="../include/footer.jsp" %>