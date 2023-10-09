-- имена студентов должны быть уникальными и не равны нулю
ALTER TABLE students
ADD CONSTRAINT age_more_than_15 CHECK (age >= 16);

-- имена студентов должны быть уникальными и не равны нулю
ALTER TABLE students
ADD CONSTRAINT name_unique UNIQUE (name);

-- имена студентов должны не должны быть null
ALTER TABLE students
ALTER COLUMN name SET NOT NULL;

-- пара ("значение названия" - "цвет факультета") должна быть уникальной
ALTER TABLE faculties
ADD CONSTRAINT name_color_unique UNIQUE (name, color);

-- при создании студента без возраста ему автоматически должно присваиваться 20 лет
ALTER TABLE students
ALTER COLUMN age SET DEFAULT 20;