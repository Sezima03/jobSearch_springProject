create table if not exists users(
                                    id int auto_increment primary key,
                                    name varchar(50),
    surname varchar(50),
    age int ,
    email varchar(60),
    password varchar(60),
    phone_number varchar(40),
    avatar varchar(250),
    account_type ENUM('applicant', 'employer')

    );

create table if not exists categories(
                                         id int auto_increment primary key,
                                         parent_id int,
                                         name varchar(50)
    );

create table if not exists resume(
                                     id int auto_increment primary key,
                                     applicant_id int,
                                     name varchar(50),
    category_id int,
    salary double,
    is_active boolean,
    created_date timestamp default current_timestamp,
    update_time timestamp default current_timestamp on update current_timestamp,
    foreign key(category_id) references categories(id) on delete restrict on update cascade

    );

create table if not exists vacancy(
                                      id int auto_increment primary key,
                                      name varchar(50),
    description varchar(500),
    category_id int,
    salary double,
    exp_from int,
    exp_to int,
    is_active boolean,
    author_id int,
    created_date timestamp,
    update_time timestamp,
    foreign key(category_id) references categories(id) on delete restrict on update cascade
    );

create table if not exists responded_applicant(
                                                  id int auto_increment primary key,
                                                  resume_id int,
                                                  vacancy_id int,
                                                  confirmation boolean,

                                                  foreign key(resume_id) references resume(id)
    on delete restrict on update cascade,

    foreign key(vacancy_id) references vacancy(id)
    on delete restrict on update cascade
    );


insert into users(name,
                  surname,
                  age,
                  email,
                  password,
                  phone_number,
                  avatar,
                  account_type)
values ('applicantTom', 'maksimov', 20, 'tommaksimov@gmail.com', 'qw12', '996707123456', 'data/images/ava.jpg', 'applicant'),
       ('employerAlex', 'ashimov', 19, 'alexashimov@gmail.com', 'alex12', '996700231546', 'data/images/ava.jpg', 'employer');

insert into resume(
    applicant_id,
    name,
    category_id,
    salary,
    is_active,
    created_date,
    update_time)
values (1, 'java junior', 2 , 60000, true, now(), now()),
       (1, 'senior backend developer', 2, 200000, true, now(), now()),
       (1, 'frontend developer', 1, 90000, true,now(), now());


insert into vacancy(
    name,
    description,
    salary,
    exp_from,
    exp_to,
    is_active,
    author_id,
    created_date,
    update_time)
values ('Frontend Developer', 'Join our frontend team to develop user interfaces using React.js and modern JavaScript frameworks',100000, 1, 3, true, 2, now(), now()),
       ('Senior Backend Developer', 'We are looking for a Senior Backend Developer',180000, 3, 6, true, 2, now(), now());


insert into responded_applicant(resume_id,
                                vacancy_id,
                                confirmation)
values (1, 2, true);

insert into categories(parent_id, name) values
                                            (1,'js'),
                                            (2, 'java'),
                                            (3, 'python'),
                                            (4, 'project manager')