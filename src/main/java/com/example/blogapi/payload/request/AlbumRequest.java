package com.example.blogapi.payload.request;

import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.example.blogapi.model.Photo;
import com.example.blogapi.model.user.User;
import com.example.blogapi.payload.UserDateAuditPayload;

@Data
public class AlbumRequest extends UserDateAuditPayload {

	private Long id;

	private String title;

	private User user;

	private List<Photo> photo;

	public List<Photo> getPhoto() {

		return photo == null ? null : new ArrayList<>(photo);
	}

	public void setPhoto(List<Photo> photo) {

		if (photo == null) {
			this.photo = null;
		} else {
			this.photo = Collections.unmodifiableList(photo);
		}
	}
}
