<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/taglip.jsp" %>

<form:form commandName="user" cssClass="form-horizontal registration-form">
 <jstl:if test="${param.success eq true}">
 	<div class="alert alert-success">
 		Registration successfull!
 	</div>
 </jstl:if>
  <div class="form-group">
  	<form:label path="name" cssClass="col-sm-2 control-label">Name:</form:label>
    <div class="col-sm-10">
      <form:input path="name" cssClass="form-control" id="name"/>
      <form:errors path="name"/>
    </div>
  </div>
  <div class="form-group">
  	<form:label path="email" cssClass="col-sm-2 control-label">Email:</form:label>
    <div class="col-sm-10">
      <form:input path="email" cssClass="form-control"/>
      <form:errors path="email"/>
    </div>
  </div>
   <div class="form-group">
  	<form:label path="password" cssClass="col-sm-2 control-label">Password:</form:label>
    <div class="col-sm-10">
      <form:password path="password" cssClass="form-control"/>
      <form:errors path="password"/>
    </div>
  </div>
   <div class="form-group">
  	<label for="repeat_password" class="col-sm-2 control-label">Repeat password:</label>
    <div class="col-sm-10">
      <input type="password" name = "repeat_password" id="repeat_password" class="form-control"/>
    </div>
  </div>
   <div class="form-group">
    <div class="col-sm-2">
      <input type="submit" class="btn btn-primary btn-lg" value="Submit">
    </div>
  </div>
</form:form>

<script type="text/javascript">
	$(document).ready(function(){
		$(".registration-form").validate({
			rules: {
				name: {
					required: true,
					minlength: 3,
					remote : {
						url : "<spring:url value='/registration/available.html'/>",
						type : "get",
						data : {
							username : function(){
								return $("#name").val();
							}
						}
					}
				},
				email: {
					required: true,
					email: true
				},
				password: {
					required: true,
					minlength: 5
				},
				repeat_password: {
					required: true,
					minlength: 5,
					equalTo: '#password'
				}
			},
			highlight: function(element){
				$(element).closest('.form-group').removeClass('has-success').addClass('has-error');
			},
			unhighlight: function(element){
				$(element).closest('.form-group').removeClass('has-error').addClass('has-success');
			},
			messages: {
				name : {
					remote : "Such username already exists"
				}
			}
		});
	});
</script>
