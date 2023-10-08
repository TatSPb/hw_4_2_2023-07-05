package pro.sky.hogwarts.dto;

public class StudentDtoIn {
    private String name;
    private int age;
    private Long id;

    public StudentDtoIn(String name, int age, long id) {
        this.name = name;
        this.age = age;
        this.id = id;
    }

    public StudentDtoIn() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
