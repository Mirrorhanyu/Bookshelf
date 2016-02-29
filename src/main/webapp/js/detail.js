(function(angular){

    var myAPP = angular.module('bookDetail', []);
    myAPP.controller('bookDetailController', function($scope) {

        $scope.add = function(price){
            ajaxToAddBooks(id, price, function(result){
                var resultJson = jQuery.parseJSON(result);
                $scope.$apply(function(){
                    $scope.quanity = resultJson.quanity;
                    $scope.total = resultJson.total;
                });
            });
        }

        $scope.goToShoppingcart = function(){
            window.location.href = "../shoppingcart";
        }

        var id = window.location.href.split("/").pop();

        ajaxToGetBooks(id, function(result){
            $scope.$apply(function(){
                $scope.bookinfos = jQuery.parseJSON(result);
            });
        });

        ajaxToGetQuanityAndTotal(function(result){
            $scope.$apply(function(){
                var resultJson = jQuery.parseJSON(result);
                $scope.quanity = resultJson.quanity;
                $scope.total = resultJson.total;
            });
        });

    });
})(angular);

function ajaxToGetBooks(id, callback){
    $.ajax({
        type: 'POST',
        url: '../api/searchbyid',
        data: {
            'id': id
        },
        success : callback
    });
}

function ajaxToAddBooks(id, price, callback){
    $.ajax({
        type: 'POST',
        url: '../api/add',
        data: {
            'id': id,
            'price':price
        },
        success : callback
    });
}

function ajaxToGetQuanityAndTotal(callback){
    $.ajax({
        type: 'POST',
        url: '../api/getquanityandtotal',
        success : callback
    });
}

