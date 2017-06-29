var app = angular.module("myApp", ['ngRoute']);
app.config(function($routeProvider) {
    $routeProvider
    .when('/', {
        templateUrl : 'User/index.html'
    })
    .when('/login', {
        templateUrl : 'User/login.html'
    })
    .when('/Register', {
        templateUrl : 'User/Register.html'
    });
});