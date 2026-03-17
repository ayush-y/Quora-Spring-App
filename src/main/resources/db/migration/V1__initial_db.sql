CREATE TABLE answer
(
    id          BIGINT AUTO_INCREMENT NOT NULL,
    content     VARCHAR(255) NULL,
    question_id BIGINT NULL,
    user_id     BIGINT NULL,
    CONSTRAINT pk_answer PRIMARY KEY (id)
);

CREATE TABLE answer_likes
(
    answer_id BIGINT NOT NULL,
    user_id   BIGINT NOT NULL,
    CONSTRAINT pk_answer_likes PRIMARY KEY (answer_id, user_id)
);

CREATE TABLE comment
(
    id                BIGINT AUTO_INCREMENT NOT NULL,
    content           VARCHAR(255) NULL,
    answer_id         BIGINT NULL,
    parent_comment_id BIGINT NULL,
    CONSTRAINT pk_comment PRIMARY KEY (id)
);

CREATE TABLE comment_like
(
    comment_id BIGINT NOT NULL,
    user_id    BIGINT NOT NULL,
    CONSTRAINT pk_comment_like PRIMARY KEY (comment_id, user_id)
);

CREATE TABLE question
(
    id      BIGINT AUTO_INCREMENT NOT NULL,
    title   VARCHAR(255) NULL,
    content VARCHAR(255) NULL,
    user_id BIGINT NULL,
    CONSTRAINT pk_question PRIMARY KEY (id)
);

CREATE TABLE question_tags
(
    question_id BIGINT NOT NULL,
    tag_id      BIGINT NOT NULL,
    CONSTRAINT pk_question_tags PRIMARY KEY (question_id, tag_id)
);

CREATE TABLE tag
(
    id   BIGINT AUTO_INCREMENT NOT NULL,
    name VARCHAR(255) NULL,
    CONSTRAINT pk_tag PRIMARY KEY (id)
);

CREATE TABLE user_tags
(
    tag_id  BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    CONSTRAINT pk_user_tags PRIMARY KEY (tag_id, user_id)
);

CREATE TABLE users
(
    id       BIGINT AUTO_INCREMENT NOT NULL,
    username VARCHAR(255) NULL,
    password VARCHAR(255) NULL,
    CONSTRAINT pk_users PRIMARY KEY (id)
);

ALTER TABLE answer
    ADD CONSTRAINT FK_ANSWER_ON_QUESTION FOREIGN KEY (question_id) REFERENCES question (id);

ALTER TABLE answer
    ADD CONSTRAINT FK_ANSWER_ON_USER FOREIGN KEY (user_id) REFERENCES users (id);

ALTER TABLE comment
    ADD CONSTRAINT FK_COMMENT_ON_ANSWER FOREIGN KEY (answer_id) REFERENCES answer (id);

ALTER TABLE comment
    ADD CONSTRAINT FK_COMMENT_ON_PARENT_COMMENT FOREIGN KEY (parent_comment_id) REFERENCES comment (id);

ALTER TABLE question
    ADD CONSTRAINT FK_QUESTION_ON_USER FOREIGN KEY (user_id) REFERENCES users (id);

ALTER TABLE answer_likes
    ADD CONSTRAINT fk_anslik_on_answer FOREIGN KEY (answer_id) REFERENCES answer (id);

ALTER TABLE answer_likes
    ADD CONSTRAINT fk_anslik_on_users FOREIGN KEY (user_id) REFERENCES users (id);

ALTER TABLE comment_like
    ADD CONSTRAINT fk_comlik_on_comment FOREIGN KEY (comment_id) REFERENCES comment (id);

ALTER TABLE comment_like
    ADD CONSTRAINT fk_comlik_on_users FOREIGN KEY (user_id) REFERENCES users (id);

ALTER TABLE question_tags
    ADD CONSTRAINT fk_quetag_on_question FOREIGN KEY (question_id) REFERENCES question (id);

ALTER TABLE question_tags
    ADD CONSTRAINT fk_quetag_on_tag FOREIGN KEY (tag_id) REFERENCES tag (id);

ALTER TABLE user_tags
    ADD CONSTRAINT fk_user_tags_on_tag FOREIGN KEY (tag_id) REFERENCES tag (id);

ALTER TABLE user_tags
    ADD CONSTRAINT fk_user_tags_on_users FOREIGN KEY (user_id) REFERENCES users (id);