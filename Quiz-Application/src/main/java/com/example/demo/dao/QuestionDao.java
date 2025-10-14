package com.example.demo.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Question;

@Repository
public interface QuestionDao extends JpaRepository<Question, Integer> {
  List<Question> findByCategory(String category);

  void save(Optional<Question> updateQuestion);

  @NativeQuery("SELECT * FROM Question q WHERE q.category=:category ORDER BY RANDOM() LIMIT :numQ")
  List<Question> findRandomQuestionsByCategory(String category, int numQ);
}
