﻿<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="../../lib/jquery.js"></script>
<script type="text/javascript" src="../../js/test/test.js"></script>
<title>接口测试</title>
</head>
<body>

<div>-----------------UserAction-------------------------</div>
<fieldset>
    <legend>register</legend>
    <button onclick='register()'>注册</button>
    <div id="register"></div>
 </fieldset>
 
  <fieldset>
    <legend>activate</legend>
    <button onclick='activate()'>激活</button>
    <div id="activate"></div>
 </fieldset>
 
 <fieldset>
    <legend>login</legend>
    <button onclick='login()'>登陆</button>
    <div id="login"></div>
 </fieldset>

<div>-----------------AuthorizationAction-------------------------</div>
<fieldset>
    <legend>getUserAuthorization</legend>
    <button onclick='getUserAuthorization()'>获取用户授权</button>
    <div id="getUserAuthorization"></div>
 </fieldset>
 
 <fieldset>
    <legend>updateAuthorization</legend>
    <button onclick='updateAuthorization()'>更新用户授权</button>
    <div id="updateAuthorization"></div>
 </fieldset>


<div>-----------------PermissionAction-------------------------</div>
<fieldset>
    <legend>createPermission</legend>
    <button onclick='createPermission()'>创建权限</button>
    <div id="createPermission"></div>
 </fieldset>

<div>-----------------PositionAction-------------------------</div>
<fieldset>
    <legend>createPosition</legend>
    <button onclick='createPosition()'>创建职位</button>
    <div id="createPosition"></div>
 </fieldset>

<div>-----------------DepartmentAction-------------------------</div>
<fieldset>
    <legend>createDepartment</legend>
    <button onclick='createDepartment()'>创建部门</button>
    <div id="createDepartment"></div>
 </fieldset>


</body>
</html>