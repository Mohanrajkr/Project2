'use strict';
 
app.service('ForumService', ['$http', '$q', function($http, $q){
	console.log("ForumService...")
    var BASE_URL = 'http://localhost:8080/Restfulservices/';

	 var factory = {
		        fetchAllBlogs: fetchAllBlogs,
		        createForum: createForum,
		        updateBlog:updateBlog,
		        AcceptedBlogs : AcceptedBlogs,
/*				notAcceptedBlogs : notAcceptedBlogs,
				accept: accept,*/
		        deleteBlog:deleteBlog
		    };
		 
		    return factory;
		 
		    function fetchAllBlogs() {
				console.log("calling fetchAllblogs ")
				return $http.get(BASE_URL+'/Blog').then(function(response) {
					return response.data;
				}, null);
			};
			function AcceptedBlogs() {
				console.log("calling AcceptedBlogs ")

				return $http.get(BASE_URL + '/acceptedblog').then(
						function(response) {
							console.log('response');
							return response.data;
							console.log(response)
						}, null);
			};
			
			function createForum(Forum) {
				console.log("calling create Forum")
				return $http.post(BASE_URL+'/forum', Forum) // 1
				.then(function(response) {
					return response.data;
				}, function(errResponse) {
					console.error('Error while creating Forum');
					return $q.reject(errResponse);
				});
			};

		 
			function updateBlog(Blog) {
				console.log("calling fetchAllBlogs ")
				return $http.put(BASE_URL+'/updateBlog/', Blog) // 2
				.then(function(response) {
					return response.data;
				}, function(errResponse) {
					console.error('Error while updating Blog');
					return $q.reject(errResponse);
				});
			};
		 
		    function deleteBlog(id) {
		    	console.log("Deleting Blog Request");
				return $http.delete(BASE_URL + '/deleteBlog/'+id).then(function(response){
						
					return response.data;
						},function(errResponse) {
							console.error('Error while deleting Blog request');
							return $q.reject(errResponse);
						});
		
			};

}]);