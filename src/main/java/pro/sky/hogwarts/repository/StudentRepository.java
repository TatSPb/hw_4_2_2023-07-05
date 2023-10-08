package pro.sky.hogwarts.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pro.sky.hogwarts.dto.StudentDtoOut;
import pro.sky.hogwarts.entity.Student;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findAllStudentsByAge(int age);

    List<Student> findStudentsByAgeBetween(int ageFrom, int ageTo);

    List<Student> findAllStudentsByFaculty_Id(long facultyId);

    @Query("SELECT count(s) FROM Student s")
    int getCountOfStudents();

    @Query("SELECT avg(s.age) FROM Student s")
    double getAverageAge();

    //вариант-1 (с проекцией. Тройные кавычки позволяют исп-ть переносы)

//    @Query("""
//            SELECT new pro.sky.hogwarts.dto.StudentDtoOut(s.id, s.name, s.age, f.id, f.name, f.color, a.id)
//            FROM Student s
//                LEFT JOIN Faculty f ON s.faculty = f
//                LEFT JOIN Avatar a ON a.student = s
//            ORDER BY s.id DESC
//            """)
//    List<StudentDtoOut> getLastStudents(Pageable pageable);

    //вариант-2(без проекции)
    @Query("SELECT s FROM Student s ORDER BY s.id DESC")
     List<Student> getLastStudents(Pageable pageable);

    //вариант-3 (нативный запрос)
//    @Query(value = "SELECT s.* FROM students s ORDER BY s.id DESC LIMIT :count", nativeQuery = true)
//    List<Student> getLastStudents(@Param("count") int count);


}
