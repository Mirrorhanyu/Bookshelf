(function(angular){

    var myAPP = angular.module('shoppingcart', []);
    myAPP.controller('shoppigcartController', function($scope) {

        ajaxToGetShoppingcart(function(result){
            $scope.$apply(function(){
                $scope.books = jQuery.parseJSON(result);
            });
        });

        ajaxToGetQuanityAndTotal(function(result){
            $scope.$apply(function(){
                var resultJson = jQuery.parseJSON(result);
                $scope.total = resultJson.total;
                if(Number($scope.total) == 0)
                    $scope.button = "Go To Shopping";
            });
        });

        $scope.plus = function(info){
            ajaxToAddBooks(info[0].id, info[0].price, function(result){
                ajaxToGetShoppingcart(function(result){
                    $scope.$apply(function(){
                        $scope.books = jQuery.parseJSON(result);
                        $scope.total = Number($scope.total) + Number(info[0].price);
                    });
                });
            });
        }

        $scope.subtract = function(info){
            ajaxToSubBooks(info[0].id, info[0].price, function(result){
                ajaxToGetShoppingcart(function(result){
                    $scope.$apply(function(){
                        $scope.books = jQuery.parseJSON(result);
                        $scope.total = Number($scope.total) - Number(info[0].price);
                        if(Number($scope.total) == 0)
                            $scope.button = "Go To Shopping";
                    });
                });
            });
        }

        $scope.goHome = function(){
            window.location.href = "/Bookself";
        }

        ajaxToGetQuanityAndTotal(function(result){
            $scope.$apply(function(){
                var resultJson = jQuery.parseJSON(result);
                $scope.total = resultJson.total;
            });
        });
        
        $scope.success = function () {
            if(Number($scope.total) == 0)
                window.location.href = "/Bookself/";
            else
                window.location.href = "success";
        }


    });

})(angular);


function ajaxToGetShoppingcart(callback){
    $.ajax({
        type: 'GET',
        url: 'api/getshoppingcart',
        success : callback
    });
}

function ajaxToAddBooks(id, price, callback){
    $.ajax({
        type: 'POST',
        url: 'api/add',
        data: {
            'id': id,
            'price':price
        },
        success : callback
    });
}

function ajaxToSubBooks(id, price, callback){
    $.ajax({
        type: 'POST',
        url: 'api/sub',
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
        url: 'api/getquanityandtotal',
        success : callback
    });
}