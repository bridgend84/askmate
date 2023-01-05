SELECT questions.name, questions.description, u.username, a.description, a.created
FROM questions
FULL JOIN answer a on questions.question_id = a.question_id
FULL JOIN users u on questions.user_id = u.user_id
WHERE a.question_id == az a valltozo
