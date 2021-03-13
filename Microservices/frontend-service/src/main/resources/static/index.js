angular.module('app',[]).controller('controller', function ($scope, $http) {

    const path = 'http://localhost:8189/app/products';

    $scope.getProductList = function () {
        $http.get(path)
            .then(function (response) {
                console.log(response.data)
                $scope.ProductList = response.data;
            })
    }

    $scope.getProductList();

});