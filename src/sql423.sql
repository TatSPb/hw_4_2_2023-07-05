-- 1й J0IN-запрос: получить информацию обо всех студентах (достаточно получить только имя и возраст студента)
-- школы Хогвартс вместе с названиями факультетов.
SELECT s. name, s.age
FROM students s
LEFT JOIN faculties f on s.faculty_id = f.id;

-- 2й JOIN-запрос: получить только тех студентов, у которых есть аватарки
SELECT s.name, s.age, a.id
FROM students s
INNER JOIN avatar a ON s.id =  a.student_id;