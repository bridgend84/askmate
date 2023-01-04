SELECT questions.description, answer.description, answer.created, users.username
FROM questions
         FULL JOIN answer ON questions.question_id = answer.question_id
        Join users ON questions.user_id = users.user_id
GROUP BY  questions.description,answer.description, answer.created, users.username;