SELECT questions.description, answer.description, answer.created, questions.user_id
FROM questions
         FULL JOIN answer ON questions.question_id = answer.question_id
GROUP BY  questions.description,answer.description, answer.created, questions.user_id;