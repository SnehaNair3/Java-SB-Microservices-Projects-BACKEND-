package com.example.blogapi.service;

import com.example.blogapi.model.Comment;
import com.example.blogapi.payload.ApiResponse;
import com.example.blogapi.payload.CommentRequest;
import com.example.blogapi.payload.PagedResponse;
import com.example.blogapi.security.UserPrincipal;

public interface CommentService {

	PagedResponse<Comment> getAllComments(Long postId, int page, int size);

	Comment addComment(CommentRequest commentRequest, Long postId, UserPrincipal currentUser);

	Comment getComment(Long postId, Long id);

	Comment updateComment(Long postId, Long id, CommentRequest commentRequest, UserPrincipal currentUser);

	ApiResponse deleteComment(Long postId, Long id, UserPrincipal currentUser);

}
