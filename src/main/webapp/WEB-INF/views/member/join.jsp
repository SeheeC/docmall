<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Hugo 0.88.1">
    <title>Pricing example · Bootstrap v4.6</title>

    <link rel="canonical" href="https://getbootstrap.com/docs/4.6/examples/pricing/">

    <style>
      .bd-placeholder-img {
        font-size: 1.125rem;
        text-anchor: middle;
        -webkit-user-select: none;
        -moz-user-select: none;
        -ms-user-select: none;
        user-select: none;
      }

      @media (min-width: 768px) {
        .bd-placeholder-img-lg {
          font-size: 3.5rem;
        }
      }
    </style>

    
    <!-- Custom styles for this template -->
    <link href="pricing.css" rel="stylesheet">
  </head>
  <body>
    
<%@include file="/WEB-INF/views/include/header.jsp" %>

<!-- 
<div class="pricing-header px-3 py-3 pt-md-5 pb-md-4 mx-auto text-center">
  <h1 class="display-4">Pricing</h1>
  <p class="lead">Quickly build an effective pricing table for your potential customers with this Bootstrap example. It’s built with default Bootstrap components and utilities with little customization.</p>
</div>
 -->
<div class="container">
  
  <!-- 회원가입 폼 작업 -->
  <h3>회원가입 폼</h3>
  <form action="/member/join" method="post">
  <div class="form-row">
    <div class="col-md-10">
	    <label for="mbsp_id">아이디</label>
	    <input type="text" class="form-control" id="mbsp_id" name="mbsp_id">
	</div>
	<div class="col-md-2">
		<label id="idUseState">&nbsp;</label>
	    <button type="button" class="form-control" id="btnUseIDChk">중복체크</button>
    </div>
  </div>
  <div class="form-group">
    <label for="mbsp_password">비밀번호</label>
    <input type="password" class="form-control" id="mbsp_password" name="mbsp_password">
  </div>
  <div class="form-group">
    <label for="exampleInputEmail1">비밀번호 확인</label>
    <input type="text" class="form-control" id="exampleInputEmail1">
  </div>
  <div class="form-group">
    <label for="mbsp_name">이름</label>
    <input type="text" class="form-control" id="mbsp_name" name="mbsp_name">
  </div>
  <div class="form-row">
    <div class="col-md-6">
	    <label for="mbsp_email">전자우편</label>
	    <input type="text" class="form-control" id="mbsp_email" name="mbsp_email">
	</div>
	<div class="col-md-2">
		<label for="btnMailAuthReq">메일확인바랍니다.</label>
	    <button type="button" class="form-control" id="btnMailAuthReq">메일인증요청</button>
    </div>
    <div class="col-md-2">
	    <label for="auth_mail">메일인증코드입력</label>
	    <input type="text" class="form-control" id="auth_mail" name="auth_mail">
	</div>
	<div class="col-md-2">
		<label id="authMailState">&nbsp;</label>
	    <button type="button" class="form-control" id="btnMailAuthConfirm">메일인증확인</button>
    </div>
  </div>
 
   <div class="form-row">
    <div class="col-md-5">
      <label for="mbsp_addr">기본주소</label>
      <input type="text" class="form-control" id="mbsp_addr" name="mbsp_addr">
    </div>
    <div class="col-md-5">
      <label for="mbsp_deaddr">나머지주소</label>
      <input type="text" class="form-control" id="mbsp_deaddr" name="mbsp_deaddr">
    </div>
    <div class="col-md-2">
      <label for="mbsp_zipcode">우편번호</label>
      <input type="text" class="form-control" id="mbsp_zipcode" name="mbsp_zipcode">
    </div>
   </div>
   
   <div class="form-group">
    <label for="mbsp_phone">전화번호</label>
    <input type="text" class="form-control" id="mbsp_phone" name="mbsp_phone">
  </div>

  <div class="form-group form-check">
    <input type="checkbox" class="form-check-input" id="mbsp_receive" name="mbsp_receive" value="Y">
    <label class="form-check-label" for="mbsp_receive">메일수신여부</label>
  </div>
  <button type="submit" class="btn btn-primary">회원가입</button>
</form>
  
  

  <%@include file="/WEB-INF/views/include/footer.jsp" %>
</div>

<script>

  $(document).ready(function(){

    //아이디중복체크
    $("#btnUseIDChk").on("click", function(){

      let mbsp_id = $("#mbsp_id");

      if(mbsp_id.val() == "" || mbsp_id.val() == null){
        alert("아이디를 입력하세요");
        mbsp_id.focus();
        return;
      }

      $.ajax({
        url: '/member/checkID',
        type: 'get',
        dataType: 'text',
        data: { mbsp_id : mbsp_id.val() },
        success: function(data){
          
          $("#idUseState").css("color","red");
          if(data == "Y"){
            $("#idUseState").html("아이디 사용가능");
          }else if(data == "N"){
            mbsp_id.val("");
            $("#idUseState").html("아이디 사용불가능");
          }
        }
      });
    });


    //메일인증요청
    $("#btnMailAuthReq").on("click", function(){

      let mbsp_email = $("#mbsp_email");

      if(mbsp_email.val() == "" || mbsp_email.val() == null){
        alert("메일주소를 입력하세요.");
        mbsp_email.focus();
        return;
      }

      $.ajax({
        url: '/member/sendMailAuth',
        type: 'get',
        dataType: 'text',
        data: { mbsp_email : mbsp_email.val() },
        success: function(data){
          
          if(data == "success"){
            alert("인증요청 메일발송됨.");
          }else if(data == "fail"){
        	alert("인증요청 메일발송 에러.");
          }
        }
      });
    });

  });


</script>



    
  </body>
</html>
