<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html ng-app="shoppingcart">
<head>
    <meta charset="utf-8">
    <title>Shoppingcart</title>
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/angular.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/shoppingcart.js"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/shoppingcart.css"/>
</head>
<body ng-controller="shoppigcartController">
<div>
    <p class="title" ng-click="goHome()">SHOPPINGCART</p>
    <div ng-repeat="book in books" class="item">
        <div class="addorsub">
            <button class="add" ng-click="plus(book.bookinfo)">+</button>
            <span class="quanity" ng-bind="book.quanity"></span>
            <button class="sub" ng-click="subtract(book.bookinfo)">-</button>
        </div>
        <div ng-repeat="info in book.bookinfo">
            <img class="image" src={{info.image}}>
            <p ng-bind="info.title"></p>
            <p class="price">{{info.price}}RMB</p>
        </div>
        <hr>
    </div>
    <p class="total" ng-bind="total"></p>
    <button class="success" ng-bind="button || 'Pay'" ng-click="success()"></button>
</div>
</body>
</html>
