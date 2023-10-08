package pro.sky.hogwarts.dto;

public class StudentDtoOut {
    private long id;
    private String name;
    private int age;
    private FacultyDtoOut faculty;
    private AvatarDto avatar;

    public StudentDtoOut() {
    }

    public StudentDtoOut(long id,
                         String name,
                         int age,
                         Long facultyId,
                         String facultyName,
                         String facultyColor,
                         Long avatarId) {
        this.id = id;
        this.name = name;
        this.age = age;
        if (facultyId != null && facultyName != null && facultyColor != null) {
            this.faculty = new FacultyDtoOut(facultyId, facultyName, facultyColor);
        }
//        setAvatar(avatar.getAvatarUrl());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public FacultyDtoOut getFaculty() {
        return faculty;
    }

    public void setFaculty(FacultyDtoOut faculty) {
        this.faculty = faculty;
    }

//    public String getAvatarUrl() {
//        return avatarUrl;
//    }
//
//    public void setAvatarUrl(Long avatarId) {
//        if (avatarId != null) {
//            this.avatarUrl = "http://localhost:8088/avatars/" + avatarId + "/from-db";
//        }
//    }

    public AvatarDto getAvatar() {
        return avatar;
    }

    public void setAvatar(AvatarDto avatar) {
        this.avatar = avatar;
    }
}
