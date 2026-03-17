package org.example.quoraappcloneapplication.Services;

import org.example.quoraappcloneapplication.dtos.AnswerDTO;
import org.example.quoraappcloneapplication.modles.Answer;
import org.example.quoraappcloneapplication.modles.Question;
import org.example.quoraappcloneapplication.modles.Users;
import org.example.quoraappcloneapplication.repositories.AnswerRepository;
import org.example.quoraappcloneapplication.repositories.QuestionRepository;
import org.example.quoraappcloneapplication.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnswerService {

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Answer> getAnswerByQuestionId(Long questionId, int page, int size){
        return answerRepository.findQuestionById(questionId, PageRequest.of(page,size)).getContent();
    }

    public Optional<Answer> getAnswerById(Long id){
        return answerRepository.findById(id);
    }

    public void deleteAnswerById(Long id){
        answerRepository.deleteById(id);
    }

    public Answer createAnswer(AnswerDTO answerDTO){
        Answer answer = new Answer();
        answer.setContent(answerDTO.getContent());

        Optional<Question>  question = questionRepository.findById(answerDTO.getQuestionId());
        question.ifPresent(answer::setQuestion);

        Optional<Users> user = userRepository.findById(answerDTO.getUserId());
        user.ifPresent(answer::setUser);

        return answerRepository.save(answer);
    }
}
