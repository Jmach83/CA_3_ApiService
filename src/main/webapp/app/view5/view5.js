'use strict';

angular.module('myApp.view5', ['ngRoute'])

        .config(['$routeProvider', function ($routeProvider) {
                $routeProvider.when('/view5', {
                    templateUrl: 'app/view5/view5.html',
                    controller: 'View5Ctrl',
                    controllerAs: 'ctrl'
                });
            }])

        .controller('View5Ctrl', ['$scope', '$http', function ($scope, $http) {
          
                
                $scope.user = {};
                
                $scope.isCreated = false;
                $scope.createUser = function () {
                    $http.post('api/login/createuser'
                , $scope.user);
                $scope.isCreated = true;                                                                                                                                                                                                                                                                                                                                                               ;
            };
                

            }]);


