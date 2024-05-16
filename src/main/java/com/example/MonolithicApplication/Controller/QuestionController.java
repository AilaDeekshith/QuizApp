package com.example.MonolithicApplication.Controller;

import com.example.MonolithicApplication.Model.Question;
import com.example.MonolithicApplication.Service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("Question")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @GetMapping("/allQuestions")
    public List<Question> getAllQuestions(){
        return questionService.getAllQuestions();
    }

    @GetMapping("/category/{category}")
    public List<Question> getAllQuestionsByCategory(@PathVariable String category){
        return questionService.getAllQuestionsByCategory(category);
    }

    @PostMapping("/addQuestion")
    public String addQuestion(@RequestBody Question question){
        System.out.println(question.toString());
        return questionService.addQuestion(question);
    }
    @PutMapping("/updateQuestion")
    public String updateQuestion(@RequestBody Question question){
        return questionService.updateQuestion(question);
    }

    @DeleteMapping("/deleteQuestion/{id}")
    public String updateQuestion(@PathVariable int id){
        return questionService.deleteQuestion(id);
    }
}
