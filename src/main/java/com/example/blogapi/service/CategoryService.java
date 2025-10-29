package com.example.blogapi.service;

import org.springframework.http.ResponseEntity;

import com.example.blogapi.exception.UnauthorizedException;
import com.example.blogapi.model.Category;
import com.example.blogapi.payload.ApiResponse;
import com.example.blogapi.payload.PagedResponse;
import com.example.blogapi.security.UserPrincipal;

public interface CategoryService {

	PagedResponse<Category> getAllCategories(int page, int size);

	ResponseEntity<Category> getCategory(Long id);

	ResponseEntity<Category> addCategory(Category category, UserPrincipal currentUser);

	ResponseEntity<Category> updateCategory(Long id, Category newCategory, UserPrincipal currentUser)
			throws UnauthorizedException;

	ResponseEntity<ApiResponse> deleteCategory(Long id, UserPrincipal currentUser) throws UnauthorizedException;

}
