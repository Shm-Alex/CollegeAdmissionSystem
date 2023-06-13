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
        System.out.println("===========================departments====================================");
         ShowObject(departments);
        System.out.println("===========================departments====================================");
         /*
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
        }*/
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
            Student [] students
                    = new Student[]
                    {
                            m._studentRepository.CreateStudent("Shmykov","Alexander "+new  Date (),"Mitrich","Shmykov.alexander@Gmail.com","9031597221", new Date()),
                            m._studentRepository.CreateStudent("Nikita","Lilia","","LNikita@bi-telco.com","", new Date()),
                            m._studentRepository.CreateStudent("Levkovich","Roman","","rlevkovich@bi-telco.com","", new Date()),
                            m._studentRepository.CreateStudent("Kovaleva","Ekaterina","","ekovaleva@bi-telco.com","", new Date()),
                            m._studentRepository.CreateStudent("Zhokhov","Artem","","azhokhov@bi-telco.com","", new Date()),

                    } ;

            //m._departmentRepository.getDepartments();

            for (int i = 0; i <students.length; i++)
            {
                var courses=departments.get(0).Directions.get(i).Courses;
                for (Course course : courses) {
                    students[i]=m._studentRepository.AddCourseToStudent(students[i].Id,course.Id);
                }

            }
            System.out.println("===========================students====================================");
            ShowObject(students);
            System.out.println("===========================students====================================");
            /*
            for (Student student : students) {
                ShowStudent(student);
            }*/
            for (Student student : students) {
                for (Course course : student.Courses) {
                    m._studentRepository.RemoveCourseFromStudent(student.Id,course.Id);
                }
            }


        }
        catch (Exception e )
        {
            throw new RuntimeException(e);
        }

    }

    private static void ShowObject(Object o) {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        try {
            System.out.println( ow.writeValueAsString(o));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private static void ShowStudent(Student student) {
        System.out.println("===========================student====================================");
        ShowObject(student);
        System.out.println("===========================student====================================");
    }
}
