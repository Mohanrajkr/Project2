'use strict';
 
app.service('BlogService', ['$http', '$q', function($http, $q){
	console.log("BlogService...") 
    var BASE_URL = 'http://localhost:8080/Restfulservices/';
 
	 return {
         
         fetchAllBlogs: function() {
         	console.log("calling fetchAllBlogs ")
                 return $http.get(BASE_URL+'/blog')
                         .then(
                                 function(response){
                                     return response.data;
                                 }, 
                                null
                         );
         },
         accept: function(id) {
         	console.log("calling approve ")
                 return $http.get(BASE_URL+'/accept/'+id)
                         .then(function(response){
                        	 console.log(response.data)  
                                     return response.data;
                                 }, 
                                 function(errResponse){
                                     console.error('Error while accept registration');
                                    
                                 }
                         );
         },
         
         reject: function(id, reason) {
         	console.log("calling reject ")
                 return $http.get(BASE_URL+'/reject/'+id+'/'+reason)
                         .then(
                                 function(response){
                                     return response.data;
                                 }, 
                                 null
                         );
         },
          
         createBlog: function(blog){
         	console.log("calling create blog")
                 return $http.post(BASE_URL+'/blog', blog)
                         .then(function(response){
                        	 console.log(response.data)  
                                     return response.data;
                                 }, 
                                 function(errResponse){
                                     console.error('Error while creating blog');
                                     return $q.reject(errResponse);
                                 }
                         );
         },
          
         updateBlog: function(blog, id){
         	console.log("calling fetchAllBlogs ")
                 return $http.put(BASE_URL+'/blog/', blog)  //2
                         .then(
                                 function(response){
                                     return response.data;
                                 }, 
                                 function(errResponse){
                                     console.error('Error while updating blog');
                                     return $q.reject(errResponse);
                                 }
                           );
         },
          
           
         logout: function(){
         	console.log('logout....')
             return $http.get(BASE_URL+'/logout')
                     .then(
                             function(response){
                                 return response.data;
                             }, 
                           null
                     );
     },
     
     
     login: function(blog){
  	   console.log("Calling the method authenticate with the blog :"+blog)
		 
      return $http.post(BASE_URL+'login',blog)
              .then(
                      function(response){
                          return response; 
                      }, 
                     null
              );
}

};
    
}]);
