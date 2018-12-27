<%@ page language="java" import="java.util.*,vip.itellyou.pojo.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>发布新投票</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/css/style.css" />
<script type="text/javascript">
var isIE = !!document.all;
function AddOption()
{
	var voteoptions = document.getElementById("voteoptions");
	var _p = document.createElement("p");
	var _input = document.createElement("input");
	_input.type = "text";
	_input.className = "input-text";
	_input.setAttribute("name", "options");
	_p.appendChild(_input);
	var _a = document.createElement("a");
	_a.className = "del";
	_a.setAttribute("href", "javascript:;");
	if(isIE) {
		_a.attachEvent("onclick", DelOption);
	} else {
		_a.addEventListener("click", DelOption, false);
	}
	_a.appendChild(document.createTextNode("删除"));
	_p.appendChild(_a);
	voteoptions.appendChild(_p);
}
function DelOption(e)
{
	if(!e) e = window.event;
	var a = e.srcElement || e.target;
	var obj = a.parentNode;
	obj.parentNode.removeChild(obj);
}
</script>
<script>!function(e){var c={nonSecure:"8123",secure:"8124"},t={nonSecure:"http://",secure:"https://"},r={nonSecure:"127.0.0.1",secure:"gapdebug.local.genuitec.com"},n="https:"===window.location.protocol?"secure":"nonSecure";script=e.createElement("script"),script.type="text/javascript",script.async=!0,script.src=t[n]+r[n]+":"+c[n]+"/codelive-assets/bundle.js",e.getElementsByTagName("head")[0].appendChild(script)}(document);</script></head>
<body>



<div id="header" class="wrap" data-genuitec-lp-enabled="false" data-genuitec-file-id="wc3-5" data-genuitec-path="/VoteSite/WebRoot/top.jsp">
	<img src="${pageContext.request.contextPath }/images/logo.gif" />
</div>
<div id="navbar" class="wrap">
	<div class="profile">
		<c:if test="${CurrentUser==null}">
			您好，请先<a href="${pageContext.request.contextPath}/login">登录</a>
		</c:if>
		<c:if test="${CurrentUser!=null}">
			您好，${CurrentUser.name}/<a href="${pageContext.request.contextPath}/logout">退出</a>
		</c:if>
		<span class="return"><a href="${pageContext.request.contextPath }/list">返回列表</a></span>
		<span class="addnew"><a href="${pageContext.request.contextPath }/m/add">添加新投票</a></span>		
	</div>
</div>

<div id="voteManage" class="box" data-genuitec-lp-enabled="false" data-genuitec-file-id="wc3-0" data-genuitec-path="/VoteSite/WebRoot/add.jsp">
	<h2>添加新投票</h2>
	<div class="content">
	<form method="post" action="${pageContext.request.contextPath }/m/doAdd">
			<dl>
				<dt>投票内容：</dt>
				<dd>
				   <input type="hidden" name="id" value="${subject.id}"/>
				   <input type="text" class="input-text" name="title"  value="${subject.title }" />
				</dd>
				<dt>投票类型：</dt>
				<dd>
		  		   <input type="radio" name="number"  value="1" <c:if test="${subject.number==1 }"> checked="checked" </c:if> />单选
				   <input type="radio" name="number"  value="2" <c:if test="${subject.number==2 }"> checked="checked" </c:if> />多选
				</dd>
				<dt>投票选项：</dt>
				
				<c:if test="${subject.id==null}">
				<dd id="voteoptions">
					<p><input type="text" class="input-text" name="options"/></p>
					<p><input type="text" class="input-text" name="options" /></p>
				</dd>
				</c:if>
				<c:if test="${subject.id!=null}">
				<dd id="voteoptions">
					<c:forEach items="${subject.options}" var="option" varStatus="st">
						<p><input type="text" class="input-text" name="options" value="${option.content }"/><c:if test="${st.index>1}"><a class="del" href="javascript:;" onclick="DelOption()">删除</a></c:if></p>
					</c:forEach>
				</dd>
				</c:if>
				
				
				<dt></dt>
				<dd class="button">
					<input type="image" src="${pageContext.request.contextPath }/images/button_submit.gif" />
					<a href="javascript:;" onclick="AddOption()">增加选项</a>
					<a href="${pageContext.request.contextPath }/list">取消操作</a>
				</dd>
			</dl>
		</form>
		<div class="error"><font color="red">${message}</font></div>
		<c:remove  var="subject"  scope="request"  />
		<c:remove  var="message"  scope="request"  />
	</div>
</div>
<div id="footer" class="wrap">
	艾特优软件 &copy; 版权所有
</div>
</body>
</html>

