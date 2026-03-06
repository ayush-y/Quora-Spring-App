package org.example.quoraappcloneapplication.repositories;

import org.example.quoraappcloneapplication.modles.Answer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {

    Page<Answer> findQuestionById(Long questionId, Pageable pageable);
}
