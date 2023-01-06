SELECT questions.name, questions.description, users.username
FROM questions
    FULL JOIN users on questions.user_id = users.user_id WHERE questions.question_id == id;
