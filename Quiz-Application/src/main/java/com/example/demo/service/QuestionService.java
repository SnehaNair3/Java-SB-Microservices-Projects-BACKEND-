package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.dao.QuestionDao;
import com.example.demo.entity.Question;

@Service
public class QuestionService {

	@Autowired
	private QuestionDao questionDao;

	public ResponseEntity<List<Question>> getAllQuestions() {
		try {
			List<Question> questions = questionDao.findAll();
			return ResponseEntity.ok().body(questions);
		} catch (Exception e) {
//			throw new RuntimeException("Coudnt fetch all questions.Please try again."); 
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity getQuestionsByCategory(String category) {
		try {
			List<Question> questions = questionDao.findByCategory(category);
			return new ResponseEntity(questions, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity getQuestionById(Integer id) {
		Optional<Question> question = questionDao.findById(id);
		return new ResponseEntity(question, HttpStatus.OK);
	}

	public ResponseEntity<String> addQuestion(Question question) {
		questionDao.save(question);
		return new ResponseEntity<>("Success", HttpStatus.CREATED);
	}

	public ResponseEntity<String> deleteQuestion(Integer id) {
		questionDao.deleteById(id);
		return new ResponseEntity<String>("Deleted", HttpStatus.CREATED);
	}

	public ResponseEntity<Question> updateQuestion(Integer id, Question question) {
		Question updateQuestion = questionDao.findById(id).orElse(null);
		if (updateQuestion != null) {
			updateQuestion = questionDao.save(question);
			return ResponseEntity.ok().body(updateQuestion);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

}
