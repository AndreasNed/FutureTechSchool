package futuretechschool.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Course implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Basic
    private String name;

    @Basic
    private int points;

    @ManyToMany(cascade = CascadeType.PERSIST)
    private List<Student> students;

    @ManyToMany
    private List<Teacher> teachers;

    @ManyToMany(mappedBy = "courses", cascade = CascadeType.PERSIST)
    private List<Education> educations;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoints() {
        return this.points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public List<Student> getStudents() {
        if (students == null) {
            students = new ArrayList<>();
        }
        return this.students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public void addStudent(Student student) {
        getStudents().add(student);
    }

    public void removeStudent(Student student) {
        getStudents().remove(student);
    }

    public List<Teacher> getTeachers() {
        if (teachers == null) {
            teachers = new ArrayList<>();
        }
        return this.teachers;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }

    public void addTeacher(Teacher teacher) {
        getTeachers().add(teacher);
    }

    public void removeTeacher(Teacher teacher) {
        teachers.remove(teacher);
    }

    public List<Education> getEducations() {
        if (educations == null) {
            educations = new ArrayList<>();
        }
        return this.educations;
    }

    public void setEducations(List<Education> educations) {
        this.educations = educations;
    }

    public void addEducation(Education education) {
        getEducations().add(education);
        education.getCourses().add(this);
    }

    public void removeEducation(Education education) {
        getEducations().remove(education);
        education.getCourses().remove(this);
    }

    @Override
    public boolean equals(Object obj) {
        return this.getId() == ((Course) obj).getId();
    }

    public String toString() {
        return String.format("%6s|%-3s|%-20s", "Course", "#" + this.getId(), this.getName()).toUpperCase();
    }

}
