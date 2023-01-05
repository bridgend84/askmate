SELECT questions.question_id, questions.name, questions.created, COUNT(answer.question_id) AS answerCount
FROM questions
         FULL JOIN answer ON questions.question_id = answer.question_id
GROUP BY questions.question_id, name, questions.name, questions.created
ORDER BY answerCount ASC;
