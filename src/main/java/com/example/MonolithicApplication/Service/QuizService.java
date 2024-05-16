package com.example.MonolithicApplication.Service;

import com.example.MonolithicApplication.Model.Question;
import com.example.MonolithicApplication.Model.QuestionWrapper;
import com.example.MonolithicApplication.Model.Quiz;
import com.example.MonolithicApplication.Model.Response;
import com.example.MonolithicApplication.Repository.QuestionRepo;
import com.example.MonolithicApplication.Repository.QuizRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    private QuizRepo quizRepo;

    @Autowired
    private QuestionRepo questionRepo;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {

        List<Question> questionList = questionRepo.findRandomQuestionsByCategory(category,numQ);

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionsList(questionList);
        quizRepo.save(quiz);

        return new ResponseEntity<>("success", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(int id) {
        Optional<Quiz> quiz = quizRepo.findById(id);

        List<Question> questionList = quiz.get().getQuestionsList();
        List<QuestionWrapper> questionWrapperList = new ArrayList<>();
        for(Question q:questionList){
            QuestionWrapper questionWrapper = new QuestionWrapper(q.getQuestion(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
            questionWrapperList.add(questionWrapper);
        }
        return new ResponseEntity<>(questionWrapperList,HttpStatus.CREATED);

    }

    public ResponseEntity<Integer> calculateResult(int id, List<Response> response) {
        Optional<Quiz> quiz = quizRepo.findById(id);
        List<Question> questionList = quiz.get().getQuestionsList();
        int right = 0;
        int i = 0;
        for(Response response1:response){
            if(response1.getOptionSelected().equals(questionList.get(i).getCorrectOption())){
                right++;
            }
            i++;
        }
        return new ResponseEntity<>(right,HttpStatus.OK);
    }
}
