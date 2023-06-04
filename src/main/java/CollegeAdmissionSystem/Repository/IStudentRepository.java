package CollegeAdmissionSystem.Repository;

import CollegeAdmissionSystem.Entity.Student;

import java.util.Date;
import java.util.List;

public interface IStudentRepository {
public Student getStudentByEmail(String Email );
    public Student CreateStudent(
             String LastName,
             String Name,
             String SurName,
             String Email,
             String Phone,
             Date Birthday,
            List<Integer> CoursesIds
    );
    public Student Update(
            String LastName,
            String Name,
            String SurName,
            String Email,
            String Phone,
            Date Birthday,
            List<Integer> CoursesIds
    );
}
