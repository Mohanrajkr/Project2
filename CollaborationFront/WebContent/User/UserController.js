'use strict';

app.controller('UserController',['$scope','UserService','FriendService','$location','$rootScope','$cookieStore','$http','$cookies',
						function($scope, UserService,FriendService, $location, $rootScope,$cookieStore, $http,$cookies) {
							console.log("UserController...")
							var i = 0;
							var j = 0;
							
							var self = this;
							self.user = {userId : '',userName : '',email :'',password : '',mobileNumber : '',address : '',role : ''};

							self.userLoggedIn = "";

							self.currentUser = {userId : '',userName : '',email :'',password : '',mobileNumber : '',address : '',role : ''};							   
							self.friend = {id:'',friendId:'',friendName:'',userId : '',userName : '',email :'',password : '',mobileNumber : '',address : '',role : ''};							   

							self.users = []; // json array
							self.friends = [];// json array
							var arr=[];
							var friendarr=[];
							$scope.orderByMe = function(x) {
								$scope.myOrderBy = x;
							}
							
							var currentLoginUser = $cookies.getObject('currentLoginUser');
							console.log(currentLoginUser);
							
							self.fetchAllUsers = function() {
								self.asd = null;
								self.us = '';
								console.log("fetchAllUsers...")
								$scope.loginUser =$rootScope.currentUser;
								console.log("fetchUserList...")
								UserService .fetchAllUsers().then(function(d) {
									self.users = d;
									for(i=0; i<self.users.length; i++)
										{
										if(self.users[i].role!='admin'){
											arr.push(self.users[i])													
										}
										}
									self.us = arr;	
									console.log(self.us)	
									
									console.log("fetchAllRequestedFriend...")
									FriendService.fetchAllRequestedfriends($scope.loginUser.userId).then(function(d) {
										self.friends = d;
										console.log(self.friends)					
							
										
											for(j=0; j<self.us.length; j++){
												for(i=0; i<self.friends.length; i++){
												if(self.friends[i].friendId === self.us[j].userId){
													self.us.splice(j, 1);
													console.log(self.us)
												}
											}
										}
										
										self.asd = self.us;
										
										
										},function(errResponse) {
											console.error('Error while fetching Friends');
										} );
									
									
									},function(errResponse) {
										console.error('Error while fetching Users');
									});		
							};

							

							self.createUser = function(user) {
								console.log("createUser...")
								UserService.createUser(user)
										.then(function(d) {
													alert("Thank you for registration")
													$location.path('/')
												},
												function(errResponse) {
													console.error('Error while creating User.');
												});
							};

							self.myProfile = function() {
								console.log("myProfile...")
								UserService.myProfile()
										.then(function(d) {
													self.user = d;
													$location.path("/myProfile")
												},
												function(errResponse) {
													console.error('Error while fetch profile.');
												});
							};

							self.accept = function(id) {
								console.log("accept...")
								UserService.accept(id)
										.then(function(d) {
													self.user = d;
													self.fetchAllUsers
													$location.path("/manage_users")
													alert(self.user.errorMessage)

												},

												function(errResponse) {
													console.error('Error while updating User.');
												});
							};

							self.reject = function(id) {
								console.log("reject...")
								var reason = prompt("Please enter the reason");
								UserService.reject(id, reason).then(
										function(d) {
											self.user = d;
											self.fetchAllUsers
											$location.path("/manage_users")
											alert(self.user.errorMessage)

										}, null);
							};

							self.updateUser = function(currentUser) {
								console.log("updateUser...")
								UserService.updateUser(currentUser).then(
										self.fetchAllUsers, null);
							};

							self.update = function() {
								{
									console.log('Update the user details',$rootScope.currentUser);
									self.updateUser($rootScope.currentUser);
								}
								self.reset();
							};

							self.authenticate = function(user) {
								console.log("authenticate...")
								UserService.authenticate(user)
										.then(function(d) {
                                                    self.user = d;
													console.log("user.errorCode: "+ self.user.errorCode)
													if (self.user.errorCode == "404")

													{
														alert(self.user.errorMessage)

														self.user.id = "";
														self.user.password = "";
														$location.path('/');

													} else {
														console.log("Valid credentials. Navigating to admin page")
														
														console.log('Current user : '+ self.user)
														$rootScope.currentUser = self.user
														console.log($rootScope.currentUser)
														$cookieStore.put('currentUser',self.user);
														
														self.userLoggedIn = "true"
														if (self.user.role == "admin") {
															console.log("You are admin")
															 $location.path('/admin')
															
														}else if (self.user.role == "employee") {
															console.log("You are employee")
															 $location.path('/blog')
															
														}
														else{
														
														 $location.path('/')
														
														}
													}

												},
												function(errResponse) {

													console.error('Error while authenticate Users');
												});
							};

							self.logout = function() {
								console.log("logout")
								self.userLoggedIn = "false"
								$rootScope.currentUser = {};
								$cookieStore.remove('currentUser');
								UserService.logout()
								$location.path('/login');

							}

							// self.fetchAllUsers(); //calling the method

						
							self.login = function() {
								{
									console.log('login validation????????',
											self.user);
									self.authenticate(self.user);
								}

							};

							self.submit = function() {
								{
									console.log('Saving New User', self.user);
									self.createUser(self.user);
								}
								self.reset();
							};

							self.send = function(friendUser){
								console.log("sending friend request...")
								FriendService.createFriend(friendUser).then(function(d) {
									console.log(d)
									self.frndreq=d;
									console.log(self.frndreq)
										$location.path("/find")
									
												},
												function(errResponse) {
													console.error('Error while creating friend..');
												});
							};
							self.reset = function() {
								self.user = {userId : '',userName : '',email :'',password : '',mobileNumber : '',address : '',role : ''};

								/*$scope.myForm.$setPristine();*/ // reset Form
								self.users = [];
							};

						} ]);