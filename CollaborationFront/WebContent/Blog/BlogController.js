'use strict';
app.controller('BlogController',['$scope', '$location', 'BlogService','$rootScope', '$http',
	function($scope, $location, BlogService,$rootScope,$http) {
	console.log("BlogController...")
	
	var self = this;
	self.blog = {blogId : '',title : '',description : '',createdate : '',status :''};
	
	self.blogs = [];
	self.submit = submit;
   
    
    fetchAllBlogs();
   
    function fetchAllBlogs(){
    	BlogService.fetchAllBlogs()
            .then(
            function(d) {
                self.blogs = d;
            },
            function(errResponse){
                console.error('Error while fetching Blogs');
            }
        );
    }
    
    function createBlog(blog){
		console.log("createBlog...")
		BlogService.createBlog(blog).then(function(d) {
			alert("Thank you for creating message")
			$location.path("/login")
		}, function(errResponse) {
			console.error('Error while creating Blog.');
		});
	};

    
    function submit() {
        console.log('Creating New Blog', self.blog);
           createBlog(self.blog);
       
   };
   
    
    
	
}]);