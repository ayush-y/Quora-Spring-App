package org.example.quoraappcloneapplication.Services;

import org.example.quoraappcloneapplication.dtos.QuestionDTO;
import org.example.quoraappcloneapplication.modles.Question;
import org.example.quoraappcloneapplication.modles.Tag;
import org.example.quoraappcloneapplication.modles.Users;
import org.example.quoraappcloneapplication.repositories.QuestionRepository;
import org.example.quoraappcloneapplication.repositories.TagRepository;
import org.example.quoraappcloneapplication.repositories.UserRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class QuestionService {

    private TagRepository tagRepository;

    private QuestionRepository questionRepository;

    private UserRepository userRepository;

    public QuestionService(TagRepository tagRepository, QuestionRepository questionRepository, UserRepository userRepository){

        this.tagRepository = tagRepository;
        this.questionRepository = questionRepository;
        this.userRepository = userRepository;
    }

    public void deleteQuestionById(Long id){

        questionRepository.deleteById(id);
    }

    public List<Question> getQuestions(int offset, int limit){

        return questionRepository.findAll(PageRequest.of(offset, limit)).getContent();

    }

    public Optional<Question> getQuestionById(Long id){

        return questionRepository.findById(id);
    }

//    public Question createQuestion(QuestionDTO questionDTO){
//
//        Question question = new Question();
//        questionDTO.setTitle(questionDTO.getTitle());
//        questionDTO.setContent(questionDTO.getContent());
//
//        Optional<User> user = userRepository.findById(questionDTO.getUserId());
//        user.ifPresent(question::setUser);
//        Set<Tag> tags = questionDTO.getTagIds().stream() Stream<Long>
//                .map(tagRepository::findById) Stream<Optional<...>>
//                .filter(Optional::ifPresent)
//                .map(Optional::get)
//                .collect(java.util.stream.Collectors.toSet());
//
//        question.setTags(tags);
//
//    }

    public Question createQuestion(QuestionDTO questionDTO){

        Question question = new Question();

        // ✅ Set title & content
        question.setTitle(questionDTO.getTitle());
        question.setContent(questionDTO.getContent());

        // ✅ Set User
        Optional<Users> user = userRepository.findById(questionDTO.getUserId());
        user.ifPresent(question::setUser);

        // ✅ Convert tagIds -> Tag entities
        Set<Tag> tags = questionDTO.getTagIds().stream()
                .map(tagRepository::findById)     // Optional<Tag>
                .filter(Optional::isPresent)      // Keep only present ones
                .map(Optional::get)               // Extract Tag
                .collect(java.util.stream.Collectors.toSet());

        question.setTags(tags);

        // ✅ Save and return
        return questionRepository.save(question);
    }

}
