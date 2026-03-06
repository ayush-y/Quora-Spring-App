package org.example.quoraappcloneapplication.dtos;

import lombok.Data;

import java.util.Set;

@Data
public class AnswerDTO {

    private Long id;

    private Long userId;

    private String content;

    private Long questionId;
}
