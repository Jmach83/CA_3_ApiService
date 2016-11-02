'use strict';
angular.module('myApp.view2', ['ngRoute'])

        .config(['$routeProvider', function ($routeProvider) {
                $routeProvider.when('/view2', {
                    templateUrl: 'app/view2/view2.html',
                    controller: 'View2Ctrl'
                });
            }])

        .controller('View2Ctrl', function ($http, $scope) {
            $http({
                method: 'GET',
                url: 'api/demouser'
            }).then(function successCallback(res) {
                $scope.data = res.data.message;
            }, function errorCallback(res) {
                $scope.error = res.status + ": " + res.data.statusText;
            });
        })
        .controller('cvrController', ['$scope', 'cvrInfoFactory', function ($scope, cvrInfoFactory) {
                $scope.cvrInfoList = {};
                $scope.status;
                $scope.oneAtATime = true;
                
                $scope.getCVR = function(){
                    console.log('hej');
                    console.log($scope.searchtext+'   '+$scope.country);
                    cvrInfoFactory.getInfo($scope.searchtext,$scope.country)
                            .then(function (response) {
                                $scope.cvrInfoList = response.data;
//                                $scope.cvrInfoList.push(response.data);
//                                $scope.cvrInfoList.push({name:'mia'});
                                console.log($scope.cvrInfoList);
                            })
                            , function (error) {
                                $scope.status = 'unable to get data ' + error.message;
                            };
                };
                

//                function getcvr() {
//                    console.log('hej');
//                    cvrInfoFactory.getInfo(name)
//                            .then(function (response) {
//                                console.log(name)
//                                $scope.cvrInfoList = response.data;
////                                $scope.cvrInfoList.push(response.data);
////                                $scope.cvrInfoList.push({name:'mia'});
//                                console.log($scope.cvrInfoList.name);
//                            })
//                            , function (error) {
//                                $scope.status = 'unable to get data ' + error.message;
//                            };
//                }
//                ;

            }])
        .factory('cvrInfoFactory', function ($http) {
            var cvrInfoFactory = {};
            cvrInfoFactory.getInfo = function (value,country) {
                return $http({
                    url: 'http://cvrapi.dk/api?name='+value+'&country='+country,
                    skipAuthorization: 'true',
                    method: 'GET'
                });
            };
            return cvrInfoFactory;
        });