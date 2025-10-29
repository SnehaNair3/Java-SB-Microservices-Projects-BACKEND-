package com.example.blogapi.service;

import org.springframework.http.ResponseEntity;

import com.example.blogapi.model.Album;
import com.example.blogapi.payload.AlbumResponse;
import com.example.blogapi.payload.ApiResponse;
import com.example.blogapi.payload.PagedResponse;
import com.example.blogapi.payload.request.AlbumRequest;
import com.example.blogapi.security.UserPrincipal;

public interface AlbumService {

	PagedResponse<AlbumResponse> getAllAlbums(int page, int size);

	ResponseEntity<Album> addAlbum(AlbumRequest albumRequest, UserPrincipal currentUser);

	ResponseEntity<Album> getAlbum(Long id);

	ResponseEntity<AlbumResponse> updateAlbum(Long id, AlbumRequest newAlbum, UserPrincipal currentUser);

	ResponseEntity<ApiResponse> deleteAlbum(Long id, UserPrincipal currentUser);

	PagedResponse<Album> getUserAlbums(String username, int page, int size);

}
