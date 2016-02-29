<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html ng-app="bookshelf">
<head>
    <meta charset="utf-8">
    <title>Bookshelf</title>
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/angular.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/home.js"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/home.css"/>
</head>
<body ng-controller="bookshelfController">
    <div class="bookself">
        <div ng-click="goToShoppingcart()">
            <%@include file="shoppingcartfragment.jsp"%>
        </div>
        <div class="bookpage">
            <div class="searchbar">
                <div class="search">
                    <input class="searchinput" type="text" ng-model="input" ng-change="search()"/>
                </div>
            </div>
            <div class="bookshow">
                <div  class="book" ng-repeat="book in books"  ng-click="goToDetail(book.id)">
                    <div class="image">
                        <img src={{book.image}}>
                    </div>
                    <p ng-bind="book.title"></p>
                    <div class="authors" ng-repeat="author in book.author">
                        <p class="author">{{author}} </p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
