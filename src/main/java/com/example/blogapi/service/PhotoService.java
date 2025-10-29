package com.example.blogapi.service;

import com.example.blogapi.payload.ApiResponse;
import com.example.blogapi.payload.PagedResponse;
import com.example.blogapi.payload.PhotoRequest;
import com.example.blogapi.payload.PhotoResponse;
import com.example.blogapi.security.UserPrincipal;

public interface PhotoService {

	PagedResponse<PhotoResponse> getAllPhotos(int page, int size);

	PhotoResponse getPhoto(Long id);

	PhotoResponse updatePhoto(Long id, PhotoRequest photoRequest, UserPrincipal currentUser);

	PhotoResponse addPhoto(PhotoRequest photoRequest, UserPrincipal currentUser);

	ApiResponse deletePhoto(Long id, UserPrincipal currentUser);

	PagedResponse<PhotoResponse> getAllPhotosByAlbum(Long albumId, int page, int size);

}