(function(angular){

    var myAPP = angular.module('bookshelf', []);

    myAPP.controller('bookshelfController', function($scope) {

        $scope.goToDetail = function(string){
            window.location.href = "detail/" + string;
        }

        $scope.search = function(){
            ajaxToGetBooks($scope.input, updateBookPage);
        }

        $scope.goToShoppingcart = function(){
            window.location.href = "shoppingcart";
        }

        var updateBookPage = function(result){
            $scope.$apply(function(){
                $scope.books = jQuery.parseJSON(result);
            });
        }

        ajaxToGetBooks("", updateBookPage);

        ajaxToGetQuanityAndTotal(function(result){
            $scope.$apply(function(){
                var resultJson = jQuery.parseJSON(result);
                $scope.quanity = resultJson.quanity;
                $scope.total = resultJson.total;
            });
        });

    });

})(angular);

function ajaxToGetBooks(title, callback){
    $.ajax({
        type: 'POST',
        url: 'api/searchbytitle',
        data: {
            'title': title
        },
        success : callback,
        error : function(err){
            console.log(err);
        }
    });
}

function ajaxToGetQuanityAndTotal(callback){
    $.ajax({
        type: 'POST',
        url: 'api/getquanityandtotal',
        success : callback
    });
}
