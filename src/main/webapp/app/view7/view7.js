'use strict';

angular.module('myApp.view7', ['ngRoute'])

        .config(['$routeProvider', function ($routeProvider) {
            $routeProvider.when('/view7', {
              templateUrl: 'app/view7/view7.html',
              controller: 'View7Ctrl'
            });
          }])

        .controller('View7Ctrl', function ($http, $scope) {
          $http({
            method: 'GET',
            url: 'api/currency/dailyrates'
          }).then(function successCallback(res) {
            $scope.data = res.data;
          }, function errorCallback(res) {
            $scope.error = res.status + ": "+ res.data.statusText;
          });
         
         $scope.amount;
        // $scope.fromVal = " ";
       //  $scope.toVal = " ";
         $scope.cal = function () {
          console.log($scope.fromVal);
          console.log($scope.toVal);
             $http({
             method: 'GET',
             url: 'api/currency/calculator/'+$scope.amount+'/'+$scope.fromVal+'/'+$scope.toVal 
         }).then(function successCalback(res) {
             
             $scope.calResult = res.data;
             console.log($scope.calResult);
            
         });
         };
         
        });