package com.example.MonolithicApplication.Service;

import com.example.MonolithicApplication.Model.Question;
import com.example.MonolithicApplication.Repository.QuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private QuestionRepo questionRepo;

    public String addQuestion(Question question){
        questionRepo.save(question);
        return "Question Added Successfully";
    }

    public String updateQuestion(Question question) {
        questionRepo.save(question);
        return "Question "+question.getId()+ " updated Successfully";
    }

    public List<Question> getAllQuestions() {
        return questionRepo.findAll();
    }

    public List<Question> getAllQuestionsByCategory(String category) {
        return questionRepo.findByCategory(category);
    }

    public String deleteQuestion(int id) {
        questionRepo.deleteById(id);
        return "Question number "+id+" deleted";
    }
}
