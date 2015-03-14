<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/taglip.jsp" %>
<h1>Latest items</h1>
  <!-- Tab panes -->
		<table class="table table-bordered table-hover table-striped">
			<thead>
				<tr>
					<th>Date</th>
					<!-- <th>Link</th> -->
					<th>Description</th>
				</tr>
			</thead>
			<tbody>
			<jstl:forEach items="${items}" var="item">
				<tr>
					<td><jstl:out value="${item.publishDate}"/></td>
					<td><a href='<spring:url value="${item.link}"></spring:url>' target="_blank"><jstl:out value="${item.title}"/></a>
						<p>${item.description}</p>
					</td>
				</tr>
			</jstl:forEach>
			</tbody>
		</table>
