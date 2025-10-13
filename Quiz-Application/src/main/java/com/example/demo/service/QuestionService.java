package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.QuestionDao;
import com.example.demo.entity.Question;

@Service
public class QuestionService {

	@Autowired
	private QuestionDao questionDao;

	public List<Question> getAllQuestions() {
		return questionDao.findAll();
	}

	public List<Question> getQuestionsByCategory(String category) {
		return questionDao.findByCategory(category);
	}

}
