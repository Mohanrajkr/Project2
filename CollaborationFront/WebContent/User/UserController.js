'use strict';

app.controller('UserController',['$scope','UserService','$location','$rootScope','$cookieStore','$http',
						function($scope, UserService, $location, $rootScope, $cookieStore, $http) {
							console.log("UserController...")
							var self = this;
							self.user = {userId : '',userName : '',email :'',password : '',mobileNumber : '',address : '',role : ''};

							self.userLoggedIn = "";

							
							self.createUser = function(user) {
								console.log("createUser...")
								UserService.createUser(user).then(function(d) {
													alert("Thank you for registration")
													$location.path("/login")
												},
												function(errResponse) {
													console.error('Error while creating User.');
												});
							};
							
							self.login = function() {
								UserService.login(self.user).then(function(response) {
									console.log(response.status)
									$scope.user = response.data;
								
									$rootScope.currentUser = response.data;
									
									
							
									$location.path('/blog');
								}, function(response) {
									console.log(response.status)
									$scope.message = response.data.message
									$location.path('/login')
								})  
							};
							
							
							self.submit = function() {
						
									console.log('Saving New User', self.user);
									self.createUser(self.user);
								self.reset();
							};

						} ]);