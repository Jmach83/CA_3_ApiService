'use strict';

angular.module('myApp.view6', ['ngRoute'])

        .config(['$routeProvider', function ($routeProvider) {
                $routeProvider.when('/view6', {
                    templateUrl: 'app/view6/view6.html',
                    controller: 'View6Ctrl',
                    controllerAs: 'ctrl'
                });
            }])

        .controller('View6Ctrl', ['$scope', function ($scope) {
                $scope.groupMembers = [
                    {firstname: 'Lasse', lastname: 'Wenger'},
                    {firstname: 'Hamza', lastname: 'Laroussi'},
                    {firstname: 'Jonas', lastname: 'Machon'},
                    {firstname: 'Emma', lastname: 'Blomsterberg'},
                    {firstname: 'Mia', lastname: 'Ryvard'},
                ];
                console.log($scope.groupMembers[1].firstname);



            }]);


