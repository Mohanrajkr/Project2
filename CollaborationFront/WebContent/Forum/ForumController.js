'use strict';
app.controller('ForumController',['$scope', '$location', 'ForumService','$rootScope','$cookieStore', '$http',
	function($scope, $location, ForumService,$rootScope,$cookieStore,$http) {
	console.log("ForumController...")
	
	var self = this;
	self.forum = {forumId : '',userName : '',Message : ''};
	
	self.forums = [];
	self.submit = submit;
   
    self.get = get;
   
    self.AcceptedForums = AcceptedForums;
    self.notAcceptedForums = notAcceptedForums;
    self.adminGet = adminGet;
    self.accept = accept;
    self.rejectForum = rejectForum;
    
    fetchAllForums();
   // AcceptedForums();
    reset();
   
    function fetchAllForums(){
    	ForumService.fetchAllForums().then(function(d) {
                self.forums = d;
            },
            function(errResponse){
                console.error('Error while fetching Forums');
            }
        );
    }
    
    function AcceptedForums() {
		console.log("AcceptedForums...")
		ForumService.AcceptedForums().then(function(d) {
							
			console.log(d)
							self.forumsAccept = d;
						},
						function(errResponse) {
							console.error('Error while creating AcceptedForums.');
						});
	};
	function notAcceptedForums() {
		console.log("notAcceptedForums...")
		ForumService.notAcceptedForums().then(function(d) {
							
			console.log(d)
							self.forumsNotAccepted = d;
							console.log(self.forumsNotAccepted)
						},
						function(errResponse) {
							console.error('Error while creating notAcceptedForums.');
						});
	};
    
    function createForum(Forum){
		console.log("createForum...")
		ForumService.createForum(forum).then(function(d) {
			alert("Thank you for creating message")
			$location.path("/login")
		}, function(errResponse) {
			console.error('Error while creating Forum.');
		});
	};

	function deleteforum(id){
    	ForumService.deleteForum(id)
            .then(
            		fetchAllForums,
            function(errResponse){
                console.error('Error while deleting jobs');
            }
        );
    }
    
    function edit(id){
        console.log('id to be edited', id);
        for(var i = 0; i < self.forums.length; i++){
            if(self.forums[i].forumid === id) {
                self.forum = angular.copy(self.forums[i]);
                break;
            }
        }
    }
    function updateForum(forum, id){
    	ForumService.updateForum(forum, id)
            .then(
            		fetchAllForums,
            function(errResponse){
                console.error('Error while updating jobs');
            }
        );
    }
 
    function remove(id){
        console.log('id to be deleted', id);
        if(self.forum.forumid === id) {//clean form if the user to be deleted is shown there.
            reset();
        }
        deleteforum(id);
    }
    function accept(ViewForums) {
		{
			console.log('accept the Forum details')
				
			ForumService.accept(ViewForums);
			console.log(ViewForums)
			$location.path("/admin")
		}
		
	};
	function rejectForum(ViewForums){
    	ForumService.deleteForumRequest(viewForums.forumid).then(function(d) {
			self.deleteForumRequestId = d;		    			
			console.log(self.deleteForumRequestId);
    			$location.path("/admin")
    	}, function(errResponse){
                console.error('Error while deleting ForumRequest');
            });
    };
    function get(forum) {
    	$scope.bc=forum;
		console.log($scope.bc);
		$rootScope.forum=$scope.crtl;
		$location.path("/ViewForum");
    	
	}
    function adminGet(forums){
		$scope.bvv=forums;
		console.log($scope.bvv);
		$rootScope.ViewForums=$scope.bvv;
		$location.path("/adminForum");
	}
    
    function submit() {
        console.log('Creating New Forum', self.forum);
           createForum(self.forum);
       
   };
   
   function reset(){
		self.forum = {forumId : '',userName : '',Message : ''};

      //$scope.myform.$setPristine(); //reset Form
   };
   
    
	
}]);