insert into CATEGORIES(parent_id, name)
values  (1,'js'),
        (2, 'java'),
        (3, 'python'),
        (4, 'project manager');

insert into users(id, name, surname, age, email, password, phone_number, avatar, account_type)
values
    (1, 'applicantTom', 'maksimov', 20, 'tommaksimov@gmail.com', 'qw12', '996707123456', 'data/images/ava.jpg', 'applicant'),
    (2, 'employerAlex', 'ashimov', 19, 'alexashimov@gmail.com', 'alex12', '996700231546', 'data/images/ava.jpg', 'employer'),
    (3, 'employerJohn', 'doe', 30, 'johndoe@example.com', 'john123', '996700987654', 'data/images/ava.jpg', 'employer');



insert into RESUME( applicant_id, name, category_id, salary, is_active, created_date, update_time)
values (1, 'java junior', 2 , 60000, true, now(), now()),
       (2, 'senior backend developer', 2, 200000, true, now(), now()),
       (2, 'frontend developer', 1, 90000, true,now(), now());


insert into VACANCYUSR(name, description, category_id, salary, exp_from, exp_to, is_active, author_id)
values
    ('junior java developer', 'looking for a junior java developer', 1, 50000, 0, 2, true, 1),
    ('senior javascript developer', 'looking for a senior javascript developer', 2, 80000, 3, 5, true, 2),
    ('frontend developer', 'join our team as a frontend developer', 3, 70000, 1, 3, true, 3),
    ('backend developer', 'looking for a backend developer', 4, 90000, 2, 5, true, 1);


insert into RESPONDED_APPLICANT(resume_id, vacancy_id, confirmation)
values (1, 2, true);

insert into CONTACT_TYPES (TYPE)
values ('email'),
       ('телефон'),
       ('telegram'),
       ('WhatsApp'),
       ('LinkedIn');

INSERT INTO education_info (resume_id, institution, program, start_date, end_date, degree)
VALUES
    (1, 'Harvard University', 'Computer Science', '2015-09-01', '2019-06-30', 'Bachelor'),
    (2, 'Stanford University', 'Electrical Engineering', '2016-09-01', '2020-06-30', 'Master'),
    (1, 'MIT', 'Data Science', '2017-09-01', '2021-06-30', 'PhD'),
    (2, 'University of Oxford', 'Mechanical Engineering', '2014-10-01', '2018-06-30', 'Master'),
    (1, 'University of Cambridge', 'Physics', '2015-09-01', '2019-06-30', 'Bachelor');
