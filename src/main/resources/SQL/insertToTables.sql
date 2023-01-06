INSERT INTO users(username)
VALUES ('User1'),
       ('User2'),
       ('User3');

INSERT INTO questions(user_id, name, description, created)
VALUES (1, 'Why do cats purr?', 'I was wondering why were they making that sound, when you pet them?', '2022-11-11'),
       (1, 'Cat food', 'With what should i feed my cat?', '2022-11-12'),
       (2, 'Cats tail', 'Is there any use for the cats tails?', '2022-11-13');

INSERT INTO answer(question_id, description, created)
VALUES (1, 'It means mostly they are in a calm environment.', '2022-11-14'),
       (2, 'Salmon, lamp etc', '2022-11-15'),
       (2, 'I personally always give them leftovers from dinner', '2022-11-16');

INSERT INTO tags(name)
VALUES ('purr'),
       ('tail'),
       ('sound');

INSERT INTO question_tags(tag_id, question_id)
VALUES (1, 1),
       (2, 3),
       (3, 1);