<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/taglip.jsp" %>

<!-- Button trigger modal -->
<button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal">
  New blog
</button>

<!-- Modal -->
<form:form commandName="blog" cssClass="form-horizontal newblog-form">
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">New blog</h4>
      </div>
      <div class="modal-body">
        
	 <div class="form-group">
	  	<form:label path="name" cssClass="col-sm-2 control-label">Name:</form:label>
	    <div class="col-sm-10">
	      <form:input path="name" cssClass="form-control" id="name"/>
	      <form:errors path="name"/>
	    </div>
	  </div>
	  <div class="form-group">
	  	<form:label path="url" cssClass="col-sm-2 control-label">URL:</form:label>
	    <div class="col-sm-10">
	      <form:input path="url" cssClass="form-control"/>
	      <form:errors path="url"/>
	    </div>
	  </div>       
        
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <input type="submit" class="btn btn-primary" value="Submit">
      </div>
    </div>
  </div>
</div>
</form:form>



<!-- Modal delete -->
<div class="modal fade" id="modal-remove" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">Remove blog</h4>
      </div>
      <div class="modal-body">
   		<b> Really want to remove?</b>   
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
        <a href="" class="btn btn-danger remove-btn">Remove</a>
      </div>
    </div>
  </div>
</div>

<br><br>
<script>
$(document).ready(function(){
	$('.nav-tabs a:first').tab('show') // Select first tab
	$('.trigger-remove').click(function(e){
		$('#modal-remove .remove-btn').attr('href', $(this).attr('href'));
		$('#modal-remove').modal();
		return false;
	});
	$(".newblog-form").validate({
		rules: {
			name: {
				required: true,
				minlength: 3
			},
			url: {
				required: true,
				url: true
			}
		},
		highlight: function(element){
			$(element).closest('.form-group').removeClass('has-success').addClass('has-error');
		},
		unhighlight: function(element){
			$(element).closest('.form-group').removeClass('has-error').addClass('has-success');
		}
	});
});

</script>
<div role="tabpanel">

  <!-- Nav tabs -->
  <ul class="nav nav-tabs" role="tablist">
  <jstl:forEach items="${user.blogs}" var="blog">
  	<li role="presentation"><a href="#blog_${blog.id}" aria-controls="home" role="tab" data-toggle="tab"><jstl:out value="${blog.name}"/></a></li>
  </jstl:forEach>
  </ul>

  <!-- Tab panes -->
  <div class="tab-content">
  	<jstl:forEach items="${user.blogs}" var="blog">
  	<div role="tabpanel" class="tab-pane" id="blog_${blog.id}">
  		<p>${blog.url}</p>
  		<p><a href='<spring:url value="/blog/remove/${blog.id}.html"></spring:url>' class="btn btn-danger trigger-remove">Remove blog</a></p>
		<table class="table table-bordered table-hover table-striped">
			<thead>
				<tr>
					<th>Date</th>
					<!-- <th>Link</th> -->
					<th>Description</th>
				</tr>
			</thead>
			<tbody>
			<jstl:forEach items="${blog.items}" var="item">
				<tr>
					<td><jstl:out value="${item.publishDate}"/></td>
					<td><a href='<spring:url value="${item.link}"></spring:url>' target="_blank"><jstl:out value="${item.title}"/></a>
						<p>${item.description}</p>
					</td>
				</tr>
			</jstl:forEach>
			</tbody>
		</table>
	</div>
   </jstl:forEach>
  </div>

</div>

