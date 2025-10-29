package com.example.blogapi.service;

import com.example.blogapi.model.Tag;
import com.example.blogapi.payload.ApiResponse;
import com.example.blogapi.payload.PagedResponse;
import com.example.blogapi.security.UserPrincipal;

public interface TagService {

	PagedResponse<Tag> getAllTags(int page, int size);

	Tag getTag(Long id);

	Tag addTag(Tag tag, UserPrincipal currentUser);

	Tag updateTag(Long id, Tag newTag, UserPrincipal currentUser);

	ApiResponse deleteTag(Long id, UserPrincipal currentUser);

}
