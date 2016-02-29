<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html ng-app="bookDetail">
<head>
    <meta charset="utf-8">
    <title>Bookinfo</title>
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/angular.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/detail.js"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/detail.css"/>
</head>
<body ng-controller="bookDetailController">
<div class="detail">
    <div ng-click="goToShoppingcart()">
        <%@include file="shoppingcartfragment.jsp"%>
    </div>
    <div class="detailbook">
        <div class="bookpage">
            <div ng-repeat="bookinfo in bookinfos">
                <button class="add" ng-click="add(bookinfo.price)">Choose</button>
                <img class="image" src={{bookinfo.image}}>
                <p ng-bind="bookinfo.title"></p>
                <div class="authorsdiv" ng-repeat="author in bookinfo.author">
                    <p>{{author}} </p>
                </div>
                <p ng-bind="bookinfo.publisher"></p>
                <div class="tagsdiv" ng-repeat="tag in bookinfo.tags">
                    <p>{{tag.title}} </p>
                </div>
                <p ng-bind="bookinfo.summary"></p>
                <p class="price">{{bookinfo.price}}RMB</p>
            </div>
        </div>
    </div>
</div>
</body>
</html>
