'use strict';

app.controller('BlogController',['$scope', '$location', 'BlogService', function($scope, $location, BlogService) {

	console.log("BlogController...")
      var self=this;
	
	this.blog ={blogId:'',title:'',description:''};
	self.blogs=[];
	
	 self.submit = submit;
	 self.edit = edit;
     self.remove = remove;
	 self.reset = reset; 

	 /*fetchAllUsers();
	    reset();*/

	    function fetchAllBlogs(){
	        UserService.fetchAllUsers()
	            .then(
	            function(d) { 	
	                self.blogs = d;
	            },
	            function(errResponse){
	                console.error('Error while fetching Blogs');
	            }
	        );
	    };
	   function createBlog(blog) {
			console.log("createBlog...")  
			BlogService.createBlog(blog).then(function(d) {
								self.cblog = d;
								console.log(self.cblog)  
								alert("Thank you for registration");
								$location.path("/login")
							}, 
							function(errResponse) {
								console.error('Error while creating Blog.');
							});
		};

		 function updateBlog(blog, id){
			 BlogService.updateBlog(blog, id)
	            .then(
	            fetchAllBlogs,
	            function(errResponse){
	                console.error('Error while updating Blog');
	            }
	        );
	    };
	    
	    function deleteBlog(id){
	    	 BlogService.deleteBlog(id)
	            .then(
	            fetchAllBlogs,
	            function(errResponse){
	                console.error('Error while deleting Blog');
	            }
	        );
	    };
	 
	    function submit() {
	       
	            console.log('Saving New  Blog', self.blog);
	            createBlog(self.blog);
	      
	    };
	 
	    function edit(id){
	        console.log('id to be edited', id);
	        for(var i = 0; i < self.blogs.length; i++){
	            if(self.blogs[i].blogid === id) {
	                self.blog = angular.copy(self.blogs[i]);
	                break;
	            }
	        }
	    };
	 
	    function remove(id){
	        console.log('id to be deleted', id);
	        if(self.blog.blogid === id) {//clean form if the blog to be deleted is shown there.
	            reset();
	        }
	        deleteBlog(id);
	    };
	 
	 
	    function reset(){
	    	self.blog ={blogId:'',title:'',description:''};	       
	        //$scope.myForm.$setPristine(); //reset Form
	    };
}]);