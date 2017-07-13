'use strict';
 
app.service('BlogService', ['$http', '$q', function($http, $q){
	console.log("BlogService...")
    var BASE_URL = 'http://localhost:8259/Restfulservices/';

	 var factory = {
		        fetchAllBlogs: fetchAllBlogs,
		        createBlog: createBlog,
		        
		    };
		 
		    return factory;
		 
		    function fetchAllBlogs() {
				console.log("calling fetchAllblogs ")
				return $http.get(BASE_URL+'/Blog').then(function(response) {
					return response.data;
				}, null);
			};
			
			
			function createBlog(Blog) {
				console.log("calling create Blog")
				return $http.post(BASE_URL+'/blog', Blog) // 1
				.then(function(response) {
					return response.data;
				}, function(errResponse) {
					console.error('Error while creating Blog');
					return $q.reject(errResponse);
				});
			};

		 
			

}]);