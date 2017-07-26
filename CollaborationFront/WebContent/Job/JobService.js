'use strict';
 
app.service('JobService', ['$http', '$q','$rootScope', function($http, $q,$rootScope){
	
	console.log("JobService...")
	
    var BASE_URL = 'http://localhost:8080/Restfulservices/';
		
    return {
         
		applyForJob: function(jobId) {
                    return $http.post(BASE_URL+"/appliedjobs/"+jobId)
                            .then(
                                    function(response){
                                        return response.data;
                                    }, 
                                   null
                            );
            },
            
            getJobDetails: function(jobId) {
            	console.log("Getting job details of " + jobId)
                return $http.get(BASE_URL+"/getJobDetails/" + jobId)
                        .then(
                                function(response){
                                	$rootScope.selectedJob = response.data
                                    return response.data;
                                }, 
                                function(errResponse){
                                    console.error('Error while getting job details');
                                   
                                }
                        );
        },
        
             
            getMyAppliedJobs: function(){
                    return $http.get(BASE_URL+'/getMyAppliedJobs/')
                            .then(
                                    function(response){
                                        return response.data;
                                    }, 
                                    function(errResponse){
                                        console.error('Error while getting applyied jobs');
                                        return $q.reject(errResponse);
                                    }
                            );
            },
            postAJob: function(job){
                return $http.post(BASE_URL+'/job', job)
                        .then(
                                function(response){
                                    return response.data;
                                }, 
                                function(errResponse){
                                    console.error('Error while posting job');
                                    return $q.reject(errResponse);
                                }
                        );
        },
             
            rejectJobApplication: function(userId, jobId){
                    return $http.put(BASE_URL+'/rejectJobApplication/'+userId+ "/" + jobId)
                            .then(
                                    function(response){
                                        return response.data;
                                    }, 
                                    function(errResponse){
                                        console.error('Error while rejecting job');
                                        return $q.reject(errResponse);
                                    }
                            );
            },
             
            callForInterview: function(userId, jobId){
            	  return $http.put(BASE_URL+'/callForInterview/'+userId, jobId)
                            .then(
                                    function(response){
                                        return response.data;
                                    }, 
                                    function(errResponse){
                                        console.error('Error while call for interview');
                                        return $q.reject(errResponse);
                                    }
                            );
            },
            selectUser: function(userId,jobId){
            	  return $http.put(BASE_URL+'/selectUser/'+userId +"/"+ jobId)
                            .then(
                                function(response){
                                    return response.data;
                                }, 
                                function(errResponse){
                                    console.error('Error while selecting the user for job');
                                    return $q.reject(errResponse);
                                }
                        );
        }
        ,
        getAllJobs: function(){
            return $http.get(BASE_URL+'/job/')
                    .then(
                            function(response){
                                return response.data;
                            }, 
                            function(errResponse){
                                console.error('Error while getting all jobs');
                                return $q.reject(errResponse);
                            }
                    );
    }
    
            
           
         
    };
 
}]);