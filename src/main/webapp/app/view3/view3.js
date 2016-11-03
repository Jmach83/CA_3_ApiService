'use strict';

angular.module('myApp.view3', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/view3', {
    templateUrl: 'app/view3/view3.html',
    controller: 'View3Ctrl'
  });
}])

.controller('View3Ctrl', function($http,$scope) {
    
  $http.get('api/admin/users')
            .success(function (data, status, headers, config) {
              $scope.data = data;
              console.log($scope.data);
            })
            .error(function (data, status, headers, config) {
                
              console.log("status " + status );
                 $scope.message = status;
                 console.log($scope.data.message);
             });
     
     $scope.deleteUser = function(username) {
         $http.delete('api/admin/delete/'+username).success(function(){
             $scope.refresh();
         });
         
     };
     
     $scope.refresh = function() {
         $http.get('api/admin/users').success(function(data) {
            $scope.data = data; 
         });
     };
});