<%@ page language="java" import="java.util.*,vip.itellyou.pojo.User" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>参与投票</title>
<link type="text/css" rel="stylesheet" href="css/style.css" />
<script>!function(e){var c={nonSecure:"8123",secure:"8124"},t={nonSecure:"http://",secure:"https://"},r={nonSecure:"127.0.0.1",secure:"gapdebug.local.genuitec.com"},n="https:"===window.location.protocol?"secure":"nonSecure";script=e.createElement("script"),script.type="text/javascript",script.async=!0,script.src=t[n]+r[n]+":"+c[n]+"/codelive-assets/bundle.js",e.getElementsByTagName("head")[0].appendChild(script)}(document);</script></head>
<body>

<div id="header" class="wrap" data-genuitec-lp-enabled="false" data-genuitec-file-id="wc4-5" data-genuitec-path="/WebRoot/WEB-INF/pages/top.jsp">
	<img src="${pageContext.request.contextPath}/images/logo.gif" />
</div>
<div id="navbar" class="wrap">
	<div class="profile">
		<c:choose>
			<c:when test="${sessionScope.name!=null }">
				您好，${sessionScope.name}
			</c:when>
			<c:when test="${requestScope.name!=null }">
				您好，${requestScope.name}
			</c:when>
			<c:otherwise>
				<a href="${pageContext.request.contextPath}/login">请登录</a>
			</c:otherwise>
		</c:choose>
		<span class="return"><a href="/vote/list">返回列表</a></span>
		<span class="addnew"><a href="/vote/m/add">添加新投票</a></span>
		<span class="modify"><a href="/vote/m/modify">维护</a></span>		
	</div>
	<div class="search">
		<form method="post" action="/vote/search">
			<input type="text" name="keywords" class="input-text" value=""/><input type="submit" name="submit" class="input-button" value="" />
		</form>
	</div>
</div>
         
<div id="vote" class="wrap" data-genuitec-lp-enabled="false" data-genuitec-file-id="wc4-6" data-genuitec-path="/WebRoot/WEB-INF/pages/vote.jsp">
	<h2>参与投票</h2>
	<ul class="list">
		<li>
			<h4>TEST Five</h4>
			<p class="info">共有 2个选项，已有1个网友参与了投票。</p>
			<form method="post" action="/vote/m/doVote">
			    <input type="hidden" name="subjectId" value="6"/> 
				<ol>
				  
				      
					     <li><input type="radio" name="options"  value="1"/>aaaa</li>
				      
					     <li><input type="radio" name="options"  value="2"/>bbbb</li>
				     
				 				 
				</ol>
				<p class="voteView"><input type="image" src="${pageContext.request.contextPath}/images/button_vote.gif" /><a href="/vote/m/view"><img src="${pageContext.request.contextPath}/images/button_view.gif" /></a></p>
			</form>
			<div class="error"></div>		
		
		    
		</li>
	</ul>
</div>
<div id="footer" class="wrap">
	艾特优软件 &copy; 版权所有
</div>
</body>
</html>
         
