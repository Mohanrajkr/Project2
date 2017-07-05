var app = angular.module("myApp", ['ngRoute']);
app.config(function($routeProvider) {
    $routeProvider
    .when('/', {
        templateUrl : 'Home/Home.html'
    })
    .when('/login', {
        templateUrl : 'User/Login.html'
    })
    .when('/register', {
        templateUrl : 'User/Register.html',
        controller: 'UserController',
        	controllerAs:'control'
    })
    .when('/blog', {
		templateUrl : 'Blog/Blog.html',
controller:'BlogController',
controllerAs:'crtl'
	})
	
	.when('/job', {
		templateUrl : 'Job/Job.html',
controller:'JobController',
controllerAs:'controll'
	})
	
    .otherwise({
		resirectTo : '/'
	});
});