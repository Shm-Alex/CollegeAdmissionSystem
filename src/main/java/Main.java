//package main.java;

import CollegeAdmissionSystem.DepartmentCreateRepository.CourseSaveDbException;
import CollegeAdmissionSystem.DepartmentCreateRepository.DepartmentCreateRepository;
import CollegeAdmissionSystem.Entity.Course;
import CollegeAdmissionSystem.Entity.Department;
import CollegeAdmissionSystem.Entity.Direction;
import CollegeAdmissionSystem.Repository.IDepartmentCreateRepository;
import CollegeAdmissionSystem.Repository.IDepartmentReadonlyRepository;
import CollegeAdmissionSystem.RepositoryImplentation.DepartmentReadonlyRepository;
import CollegeAdmissionSystem.RepositoryStub.DepartmentReadonlyRepositoryStub;

import java.lang.reflect.Field;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    private IDepartmentReadonlyRepository departmentRepository;

    public Main(IDepartmentReadonlyRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }


    public void  ShowDepartments(){
         var departments= departmentRepository.getDepartments();
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

            var m= new Main(new DepartmentReadonlyRepository(DriverManager.getConnection("jdbc:sqlite:"+pathToDb)));
            m.ShowDepartments();
        }
        catch (Exception e )
        {
            throw new RuntimeException(e);
        }

    }
}
