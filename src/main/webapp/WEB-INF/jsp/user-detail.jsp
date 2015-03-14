<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/taglip.jsp" %>
<h1>${user.name}</h1>

<br><br>
<script>
$(document).ready(function(){
	$('.nav-tabs a:first').tab('show') // Select first tab
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
					<th>Tile</th>
					<!-- <th>Link</th> -->
					<th>Description</th>
				</tr>
			</thead>
			<tbody>
			<jstl:forEach items="${blog.items}" var="item">
				<tr>
					<td><jstl:out value="${item.title}"/></td>
					<td>
						<a href='<spring:url value="${item.link}"></spring:url>'><jstl:out value="${item.link}"/></a>
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

