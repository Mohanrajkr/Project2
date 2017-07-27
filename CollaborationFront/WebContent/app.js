var app = angular.module("myApp", [ 'ngRoute','ngCookies']);
app.config(function($routeProvider) {
	$routeProvider.when('/', {
		templateUrl : 'Home/Home.html'
	})
	
	.when('/login', {
		templateUrl : 'User/Login.html'
		
	})
	
	.when('/register', {
		templateUrl : 'User/Register.html',
		controller : 'UserController',
		controllerAs : 'control'
	})
	
	.when('/admin', {
		templateUrl : 'Admin/AdminHome.html'

	})
	.when('/adminBlog', {
		templateUrl : 'Admin/BlogDetails.html',
		controller : 'BlogController',
		controllerAs : 'bc'
	})
	
	.when('/adminForum', {
		templateUrl : 'Admin/ForumDetails.html',
		controller : 'ForumController',
		controllerAs : 'fcc'
	})
	
	
	
	.when('/blog', {
		templateUrl : 'Blog/Blog.html',
		controller : 'BlogController',
		controllerAs : 'crtl'
	})

	.when('/viewblog', {
		templateUrl : 'Blog/ViewBlog.html',
		controller : 'BlogController',
		controllerAs : 'crtl'

	})
	
	.when('/viewblogdetails', {
		templateUrl : 'Blog/ViewDetails.html',
		controller : 'BlogController',
		controllerAs : 'crtl'

	})
	
	.when('/job', {
		templateUrl : 'Job/Job.html',
		controller : 'JobController',
		controllerAs : 'controll'
	})

	.when('/viewjob', {
		templateUrl : 'Job/ViewJob.html',
		controller : 'JobController',
		controllerAs : 'controll'
	})
	
	.when('/forum', {
		templateUrl : 'Forum/Forum.html',
		controller : 'ForumController',
		controllerAs : 'fc'
	})

	.when('/viewforum', {
		templateUrl : 'Forum/ViewForum.html',
		controller : 'ForumController',
		controllerAs : 'fc'
	})
	
	 .when('/find', {
		templateUrl : 'User/FriendRequest.html',
		controller : 'UserController',
		controllerAs : 'uc'
	})
	
	.when('/chat',{
		templateUrl:'Chat/Chat.html',
		controller:'ChatController'
	})
	
	
	.otherwise({
		redirectTo : '/'
	});
});