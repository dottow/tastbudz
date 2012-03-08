<%@ include file="../taglibs.jsp"%>
<%@ taglib uri="http://www.springframework.org/spring-social/facebook/tags" prefix="facebook" %>
<%@ page session="false" %>

<h3>Connect to Facebook</h3>

<form action="/tastbudz/connect/facebook"  method="POST">
	<input type="hidden" name="scope" value="publish_stream,user_photos,offline_access" />
	<div class="formInfo">
		<p>You aren't connected to Facebook yet. Click the button to connect Tastbudz with your Facebook account.</p>
	</div>
	<p><button type="submit"><img src="/tastbudz/images/facebook/connect_light_medium_short.gif"/></button></p>
	<label for="postToWall"><input id="postToWall" type="checkbox" name="postToWall" /> Tell your friends about Tastbudz on your Facebook wall</label>
</form>


