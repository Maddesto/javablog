<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

   <%@ include file="../layout/taglip.jsp" %>
<script>
$(document).ready(function(){
	$('.trigger-remove').click(function(){
		$('#modal-remove .remove-btn').attr('href', $(this).attr('href'));
		$('#modal-remove').modal();
		return false;
	});
});
</script>
<table class="table table-bordered table-hover table-striped">
	<thead>
		<tr>
			<th>User name</th>
			<th>operations</th> 
		</tr>
	</thead>
	<tbody>
		<jstl:forEach items="${users}" var="user">
			<tr>
				<td>
					<a href='<spring:url value="/users/${user.id}.html"></spring:url>'><jstl:out value="${user.name}"/></a>
				</td>
				<td>
					<a href='<spring:url value="/users/remove/${user.id}.html"></spring:url>' class="btn btn-danger trigger-remove">Remove</a>
				</td>
			</tr>
		</jstl:forEach>
	</tbody>
</table>

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

