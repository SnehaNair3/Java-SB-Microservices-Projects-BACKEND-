package com.example.blogapi.service;

import com.example.blogapi.model.user.User;
import com.example.blogapi.payload.ApiResponse;
import com.example.blogapi.payload.InfoRequest;
import com.example.blogapi.payload.UserIdentityAvailability;
import com.example.blogapi.payload.UserProfile;
import com.example.blogapi.payload.UserSummary;
import com.example.blogapi.security.UserPrincipal;

public interface UserService {

	UserSummary getCurrentUser(UserPrincipal currentUser);

	UserIdentityAvailability checkUsernameAvailability(String username);

	UserIdentityAvailability checkEmailAvailability(String email);

	UserProfile getUserProfile(String username);

	User addUser(User user);

	User updateUser(User newUser, String username, UserPrincipal currentUser);

	ApiResponse deleteUser(String username, UserPrincipal currentUser);

	ApiResponse giveAdmin(String username);

	ApiResponse removeAdmin(String username);

	UserProfile setOrUpdateInfo(UserPrincipal currentUser, InfoRequest infoRequest);

}