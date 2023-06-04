//package main.java;

import CollegeAdmissionSystem.Entity.Course;
import CollegeAdmissionSystem.Entity.Department;
import CollegeAdmissionSystem.Entity.Direction;
import CollegeAdmissionSystem.Repository.IDepartmentReadonlyRepository;
import CollegeAdmissionSystem.RepositoryStub.DepartmentReadonlyRepositoryStub;

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
    public static void main(String[] args) {
        var m= new Main(new DepartmentReadonlyRepositoryStub());
        m.ShowDepartments();

    }
}
