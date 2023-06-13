package CollegeAdmissionSystem.Repository;

import CollegeAdmissionSystem.Entity.Student;

import java.util.Date;
import java.util.List;

public interface IStudentRepository {
    public List<Student> getStudents();
    public Student getStudentByEmail(String Email );
    public Student CreateStudent(
             String LastName,
             String Name,
             String SurName,
             String Email,
             String Phone,
             Date Birthday
    );
    public Student AddCourseToStudent(int StudentId,int CourseId);
    public Student RemoveCourseFromStudent(int StudentId,int CourseId);
    public Student Update(int id, String lastName, String name, String surName, String email, String phone, Date birthday);
}
