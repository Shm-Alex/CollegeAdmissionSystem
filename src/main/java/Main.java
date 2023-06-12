//package main.java;

import CollegeAdmissionSystem.DepartmentCreateRepository.CourseSaveDbException;
import CollegeAdmissionSystem.DepartmentCreateRepository.DepartmentCreateRepository;
import CollegeAdmissionSystem.Entity.*;
import CollegeAdmissionSystem.Repository.IDepartmentCreateRepository;
import CollegeAdmissionSystem.Repository.IDepartmentReadonlyRepository;
import CollegeAdmissionSystem.Repository.IStudentRepository;
import CollegeAdmissionSystem.RepositoryImplentation.DepartmentReadonlyRepository;
import CollegeAdmissionSystem.RepositoryImplentation.StudentRepository;
import CollegeAdmissionSystem.RepositoryStub.DepartmentReadonlyRepositoryStub;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class Main {
    private IDepartmentReadonlyRepository _departmentRepository;
    private IStudentRepository _studentRepository;

    public Main(
            IDepartmentReadonlyRepository departmentRepository,
            IStudentRepository studentRepository

    ) {
        _departmentRepository = departmentRepository;
        _studentRepository=studentRepository;
    }


    public List<Department> ShowDepartments(){
         var departments= _departmentRepository.getDepartments();
        for (Department department : departments) {
            System.out.println(department.Id + department.Name);
            var directions= department.Directions;
            for (Direction direction : directions) {
                System.out.println(direction.Id + direction.Name);
                var courses= direction.Courses;
                for (Course course : courses) {
                    System.out.println(course.Id + course.Name +course.HoursPerWeek);
                }
            }
        }
    return departments;
    }


    /*
    * Добавляет в базу список курсов
    * */
public static void SaveCorsesToDb(String pathToDb){
    if (pathToDb==null||pathToDb.isBlank())
            pathToDb="C:\\Education\\Java\\sqlite-tools-win32-x86-3420000/CollegeAdmissionSystem";
    IDepartmentReadonlyRepository departmentRepository = new DepartmentReadonlyRepositoryStub();
    //var m= new Main(departmentRepository);
    //m.ShowDepartments();


    String connectionString = "jdbc:sqlite:"+pathToDb;
    try {
        var conn=  DriverManager.getConnection(connectionString);
        IDepartmentCreateRepository departmentCreateRepository=new DepartmentCreateRepository(conn);
        departmentCreateRepository.SaveDepartments(departmentRepository.getDepartments());
    } catch (SQLException e) {

        throw new RuntimeException(e);
    } catch (CourseSaveDbException e) {
        throw new RuntimeException(e);
    }
}

    public static void main(String[] args) {
        String         pathToDb="C:\\Education\\Java\\sqlite-tools-win32-x86-3420000/CollegeAdmissionSystem";
        String connectionString = "jdbc:sqlite:"+pathToDb;
        try{

            var m= new Main(
                    new DepartmentReadonlyRepository(DriverManager.getConnection("jdbc:sqlite:"+pathToDb)),
                    new StudentRepository(DriverManager.getConnection("jdbc:sqlite:"+pathToDb))
            );
            var departments=m.ShowDepartments();
            var student=m._studentRepository.CreateStudent("Shmykov","Alexander","Mitrich","Shmykov.alexander@Gmail.com","9031597221", new Date()) ;

            //m._departmentRepository.getDepartments();
            var courses=departments.get(0).Directions.get(0).Courses;
            for (Course course : courses) {
                ShowStudent(student);
                student=m._studentRepository.AddCourseToStudent(student.Id,course.Id);
            }

            ShowStudent(student);

        }
        catch (Exception e )
        {
            throw new RuntimeException(e);
        }

    }

    private static void ShowStudent(Student student) {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        try {
            System.out.println( ow.writeValueAsString(student));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
