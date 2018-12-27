<%@ page language="java" import="java.util.*,vip.itellyou.pojo.Subject,vip.itellyou.pojo.Option" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>管理投票</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/css/style.css" />
</head>
<body>
<div id="header" class="wrap" data-genuitec-lp-enabled="false" data-genuitec-file-id="wc4-5" data-genuitec-path="/vote/WebRoot/WEB-INF/pages/top.jsp">
	<img src="${pageContext.request.contextPath}/images/logo.gif" />
</div>
<div id="navbar" class="wrap">
	<div class="profile">
		<c:if test="${CurrentUser==null}">
			您好，请先<a href="${pageContext.request.contextPath}/login">登录</a>
		</c:if>
		<c:if test="${CurrentUser!=null}">
			您好，${CurrentUser.name}/<a href="${pageContext.request.contextPath}/logout">退出</a>
		</c:if>
		<span class="return"><a href="${pageContext.request.contextPath}/list">返回列表</a></span>
		<span class="addnew"><a href="${pageContext.request.contextPath}/m/add">添加新投票</a></span>
		<span class="modify"><a href="${pageContext.request.contextPath}/m/modify">维护</a></span>	
	</div>
</div>

<div id="vote" class="wrap">
	<h2>投票列表</h2>
	<ul class="list">
	
	<c:forEach items="${subjectList}" var="subject" varStatus="status">
		<li  <c:if test="${status.index%2==0 }">class="odd" </c:if>>
			<h4>
				<em><a href="${pageContext.request.contextPath }/m/add?id=${subject.id}">维护</a></em>
				<a href="${pageContext.request.contextPath }/m/add?id=${subject.id}">${subject.title}</a>
			</h4>
			<p class="info">共有${fn:length(subject.options)}个选项，已有${subject.count}个网友参与了投票。</p>
		</li>
	</c:forEach>
	</ul>
</div>
<div id="footer" class="wrap">
	艾特优软件 &copy; 版权所有
</div>
</body>
</html>
