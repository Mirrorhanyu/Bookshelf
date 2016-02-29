<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title></title>
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.min.js"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/shoppingcartfragment.css"/>
</head>
<body>
<div class="titlebar">
    <div class="logo">
        <p class="logotitle">BOOKSHOP</p>
    </div>
    <div class="shoppingcart">
        <span>Quanity: </span>
        <span ng-bind="quanity" class="quanity"></span>
        <span>Total: </span>
        <span ng-bind="total" class="total"></span>
    </div>
</div>
</body>
</html>
