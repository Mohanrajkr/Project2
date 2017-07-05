'use strict';

app	.controller('JobController',['JobService','$location', '$rootScope',
						function(JobService,  $location, $rootScope) {
							console.log("JobController...")
							var self = this;

							this.job = {
								jobId : '',
								jobName : '',
								jobCategory : '',
								jobDetails: ''
							};
							this.jobs = [];
							
							this.applyForJob = applyForJob

							function applyForJob(jobId) {
								console.log("applyForJob");
								var currentUser = $rootScope.currentUser
								console.log("currentUser.id:" + currentUser.id)
								//if(currentUser) -> not null, not empty and defined
								
								if (typeof currentUser.id == 'undefined') 
									{
									   alert("Please login to apply for the job")
	                                     console.log("User not logged in.  Can not apply for job")
	                                     /*$location
											.path('/login');*/
									   return
									
									}
								console.log("->userId :" + currentUser.id
										+ "  applying for job:" + jobId)
										
										
								JobService
										.applyForJob(jobId)
										.then(
												function(data) {
													self.job = data;
													alert(self.job.errorMessage)
												},
												function(errResponse) {
													console
															.error('Error while applying for job request');
												});

							}

							self.getMyAppliedJobs = function() {
								console.log('calling the method getMyAppliedJobs');
								JobService.getMyAppliedJobs()
								.then(
									function(d) {
								    	self.jobs = d;
									/* $location.path('/view_friend'); */
								    	$location.path("/view_applied_jobs")
								    }, 
								    
								    function(errResponse) {
									console.error('Error while fetching Jobs');
								});
							};

							self.rejectJobApplication = function(userId) {
						    var jobId =$rootScope.selectedJob.id;
								JobService.rejectJobApplication(userId,jobId												)
										.then(
												function(d) {
													self.job = d;
													alert("You have successfully rejected the job application of the " +
															"user : " +userId)
												},
												function(errResponse) {
													console
															.error('Error while rejecting Job application.');
												});
							};

							self.callForInterview = function(userId) {
								var jobId =$rootScope.selectedJob.id;	
								JobService
										.callForInterview(userId,
												jobId)
										.then(
												function(d) {
													self.job = d;
													alert("Application status changed as call for interview")
												},
												function(errResponse) {
													console
															.error('Error while changing the status "call for interview" ');
												});
							};
							self.selectUser = function(userId) {
								var jobId =$rootScope.selectedJob.id;		
								JobService
										.selectUser(userId, jobId)
										.then(
												function(d) {
													self.job = d;
													alert("Application status sta as selected")
												},
												function(errResponse) {
													console
															.error('Error while changing the status "select user" ');
												});
							};

							//definition of getAllJobs function
							self.getAllJobs = function() {
								console.log('calling the method getAllJobs');
								JobService
										.getAllJobs()
										.then(
												function(d) {
													self.jobs = d;
												},
												function(errResponse) {
													console
															.error('Error while fetching All opend jobs');
												});
							};

							self.getAllJobs(); // calling getAllJobs function

							self.submit = function() {
								{
									console.log('submit a new job', self.job);
									self.postAJob(self.job);
								}
								self.reset();
							};

							self.postAJob = function(job) {
								console.log('submit a new job', self.job);
								JobService.postAJob(job).then(function(d) {
								alert("You successfully posted the job")
								}, function(errResponse) {
									console.error('Error while posting job.');
								});
							};

							self.getJobDetails = getJobDetails

							function getJobDetails(jobId) {
								console.log('get Job details of the id', jobId);
								JobService
										.getJobDetails(jobId)
										.then(
												function(d) {
													self.job = d;
													
													$location
															.path('/view_job_details');
												},
												function(errResponse) {
													console
															.error('Error while fetching blog details');
												});
							}
							;

							self.reset = function() {
								console.log('resetting the Job');
								self.job = {
										jobId : '',
										jobName : '',
										jobCategory : '',
										jobDetails: ''
									};
								//$scope.myForm.$setPristine(); // reset Form
							};

						} ]);