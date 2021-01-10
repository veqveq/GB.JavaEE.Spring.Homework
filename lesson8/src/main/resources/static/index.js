angular.module('app', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/app/products';

    $scope.fillTable = function () {
        $http({
            url: contextPath + '/find',
            method: 'GET',
            params: {
                id: $scope.filter ? $scope.filter.id : null,
                min: $scope.filter ? $scope.filter.min : null,
                max: $scope.filter ? $scope.filter.max : null,
                size: $scope.filter ? $scope.filter.size : 10,
                numb: $scope.pageIndex
            }
        }).then(function (response) {
            $scope.ProductsList = response.data;
            $scope.requestPagesCount();
        });
    };

    $scope.requestPagesCount = function () {
        $http({
            url: contextPath + '/pagesCount',
            method: 'GET',
            params: {
                pagination: $scope.filter ? $scope.filter.size : 10
            }
        }).then(function (response) {
            $scope.createPagesArray(response.data);
        })
    };

    $scope.createPagesArray = function (size) {
        let array = [];
        for (let i = 0; i < size; i++) {
            array.push(i + 1);
        }
        $scope.PageCount = array;
    };

    $scope.cleanFilter = function () {
        $scope.getElementById("min-price").val(null)
        $scope.getElementById("max-price").val(null)
    };

    $scope.changePagination = function () {
        let selectElement = document.getElementById("pagination").options.selectedIndex;
        let val = document.getElementById("pagination").options[selectElement].value;
        if ($scope.value !== val) {
            $scope.value = val;
            $scope.pageIndex = 1;
            $scope.fillTable();
        }
    };

    $scope.changePage = function (index) {
        console.log(index)
        $scope.pageIndex = index;
        $scope.fillTable();
    }

    $scope.createNewProduct = function () {
        $http.post(contextPath + '/save', $scope.NewProduct)
            .then(function (response) {
                console.log('sended:');
                console.log($scope.NewProduct);
                console.log('received');
                console.log(response.data);
                $scope.NewProduct = null;
                $scope.fillTable();
            });
    };

    $scope.deleteProductById = function (id) {
        $http.get(contextPath + '/delete/' + id)
            .then(function (response) {
                $scope.fillTable();
            });
    };

    $scope.fillTable();
});
