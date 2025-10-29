package com.example.blogapi.service;

import com.example.blogapi.model.Post;
import com.example.blogapi.payload.ApiResponse;
import com.example.blogapi.payload.PagedResponse;
import com.example.blogapi.payload.PostRequest;
import com.example.blogapi.payload.PostResponse;
import com.example.blogapi.security.UserPrincipal;

public interface PostService {

	PagedResponse<Post> getAllPosts(int page, int size);

	PagedResponse<Post> getPostsByCreatedBy(String username, int page, int size);

	PagedResponse<Post> getPostsByCategory(Long id, int page, int size);

	PagedResponse<Post> getPostsByTag(Long id, int page, int size);

	Post updatePost(Long id, PostRequest newPostRequest, UserPrincipal currentUser);

	ApiResponse deletePost(Long id, UserPrincipal currentUser);

	PostResponse addPost(PostRequest postRequest, UserPrincipal currentUser);

	Post getPost(Long id);

}
