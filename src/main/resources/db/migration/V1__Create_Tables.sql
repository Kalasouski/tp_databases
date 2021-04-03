create table retiree (
    id serial primary key,
    surname varchar not null,
    name varchar not null,
    patronymic varchar,
    gender char(1) check ( gender in ('F', 'M') ),
    nationality varchar,
    birth_year int check ( birth_year > 0 ),
    phone int8 check ( phone > 0 ),
    address varchar,
    retirement_experience int check ( retirement_experience > 0 ),
    retirement float check ( retirement > 0 )
);

create table job (
    id serial primary key,
    job_position varchar not null ,
    job_period int check ( job_period > 0 )
);

insert into retiree (surname, name, patronymic, gender, nationality,
                     birth_year, phone, address, retirement_experience, retirement)
    values ('Suchareva', 'Natalia', 'Victorovna', 'F', 'Belarus',
            1975, 294567890, '222160, Zhodino, 8 marta, 16', 7, 1000),
     ('Buyvidovich', 'Vasiliy', 'Michaylovich', 'M', 'Belarus',
            1963, 292849045, '222162, Zhodino, Gagarina 4, 45', 16, 1500),
     ('Bruzhas', 'Irina', 'Borisovna', 'F', 'Russia',
            1990, 294567200, '222160, Zhodino, pr Lenina 18, 83', 2, 500),
    ('Petrov', 'Ivan', 'Alexeevish', 'M', 'Ukraine',
            1978, 445699857, '222161, Zhodino, pr Pushkina 17, 76', 35, 2000),
    ('Oreshko', 'Ihar', 'Heorgievich', 'M', 'Belarus',
            1967, 294567200, '222160, Zhodino, Stalina 15, 2', 2, 500);

ALTER TABLE retiree
    ADD job_id int,
    ADD FOREIGN KEY (job_id)
        REFERENCES job (id);

insert into job (job_position, job_period)
    values ('teachers higher category', 10), ('nurse', 2),('doctor', 9);

update retiree
    set job_id = 1
    where id = 1;

update retiree
    set job_id = 3
    where id = 2;

update retiree
    set job_id = 2
    where id = 3;

update retiree
    set job_id = 2
    where id = 4;

update retiree
    set job_id = 1
    where id = 5;

insert into job (job_position, job_period) values ('president',25);









