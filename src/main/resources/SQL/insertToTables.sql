INSERT INTO users(user_id, username)
VALUES (1, 'User1'),
       (2, 'User2'),
       (3, 'User3');

INSERT INTO questions(question_id, user_id, name, description, created)
VALUES (1, 1, 'Why do cats purr?', 'I was wondering why were they making that sound, when you pet them?', '2022-11-11'),
       (2, 1, 'Cat food', 'With what should i feed my cat?', '2022-11-12'),
       (3, 2, 'Cats tail', 'Is there any use for the cats tails?', '2022-11-13');

INSERT INTO answer(answer_id, question_id, description, created)
VALUES (1, 1, 'It means mostly they are in a calm environment.', '2022-11-14'),
       (2, 2, 'Salmon, lamp etc', '2022-11-15'),
       (3, 2, 'I personally always give them leftovers from dinner', '2022-11-16');

INSERT INTO tags(tag_id, name)
VALUES (1, 'purr'),
       (2, 'tail'),
       (3, 'sound');

INSERT INTO question_tags(tag_id, question_id)
VALUES (1, 1),
       (2, 3),
       (3, 1);