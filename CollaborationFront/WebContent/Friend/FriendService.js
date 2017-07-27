'use strict';
 
app.factory('FriendService', ['$http', '$q','$rootScope', function($http, $q,$rootScope){
	
	console.log("FriendService...")
	
    var BASE_URL = 'http://localhost:8080/Restfulservices';

	 var factory = {
				fetchAllFriends: fetchAllFriends,
				createFriend: createFriend,
				updateFriend:updateFriend,
				fetchAllRequestedfriends:fetchAllRequestedfriends  ,
				fetchRequestedfriends : fetchRequestedfriends,
				updateFriendReq: updateFriendReq,
				fetchAcceptedFriends:fetchAcceptedFriends,
				fetchAcceptedFriends1:fetchAcceptedFriends1,
                deleteFriendRequest:deleteFriendRequest
			    };
				
				
				
		    return factory;

		    function fetchAllFriends() {
					console.log("calling fetchAllFriends ")
					return $http.get(BASE_URL + '/friend')
					.then(function(response) {
								return response.data;
							}, null);
				};
				
				
				 function fetchAllRequestedfriends(userId) {
						console.log("calling fetchBy User Id ")
						return $http.get(BASE_URL + '/friend/' +userId).then(
								function(response) {
									return response.data;
								}, null);
					};
					
					
					function fetchRequestedfriends(userName) {
						console.log("calling fetchBy userName ")
						return $http.get(BASE_URL + '/friends1/' +userName).then(
								function(response) {
									return response.data;
								}, null);
					};
					
					function fetchAcceptedFriends(friendName) {
						console.log("calling fetchBy friendName ")
						return $http.get(BASE_URL + '/friendsAccepted/' +friendName).then(
								function(response) {
									return response.data;
								}, null);
					};

					function fetchAcceptedFriends1(friendname) {
						console.log("calling fetchBy User name ")
						return $http.get(BASE_URL + '/friendsAccepted1/' +friendname).then(
								function(response) {
									return response.data;
								}, null);
					};
					
				function createFriend(friendUser) {
					console.log("calling create Friend")
					return $http.post(BASE_URL + '/friend', friendUser) // 1
					.then(function(response) {
						console.log(response.data)
						return response.data;
					}, function(errResponse) {
						console.error('Error while creating friends');
						return $q.reject(errResponse);
					});
				};

				function updateFriend(id) {
					console.log("calling fetchAllFriends ")
					return $http.put(BASE_URL + '/friends/', id).then(function(response) {
						return response.data;
					}, function(errResponse) {
						console.error('Error while updating Friend');
						return $q.reject(errResponse);
					});
				};
				
				function updateFriendReq(friend) {
					console.log("updating Friends Requested")
					return $http.put(BASE_URL + '/friendAccept/', friend).then(function(response) {
						return response.data;
					}, function(errResponse) {
						console.error('Error while updating Friend');
						return $q.reject(errResponse);
					});
				};
				
				function deleteFriendRequest(id){
					console.log("Deleting friend Request");
					return $http.delete(BASE_URL + '/friends/'+id).then(function(response){
							
						return response.data;
							},function(errResponse) {
								console.error('Error while deleting Friend request');
								return $q.reject(errResponse);
							});
			
				};


} ]);