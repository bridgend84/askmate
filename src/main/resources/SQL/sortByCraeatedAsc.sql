SELECT questions.name, questions.created, COUNT(answer.question_id) AS answerCount
FROM questions
         FULL JOIN answer ON questions.question_id = answer.question_id
GROUP BY name, questions.name, questions.created
ORDER BY questions.created ASC;
