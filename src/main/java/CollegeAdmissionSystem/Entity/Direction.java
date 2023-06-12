package CollegeAdmissionSystem.Entity;

import java.util.List;

/*
 create table Direction(
 Id integer primary key AUTOINCREMENT ,
 Name varchar(40) not null,
 DepartmentId INTEGER ,
 FOREIGN KEY (DepartmentId)  REFERENCES department (id)
 );
* */
public class Direction extends Entity {

    public Direction(int id, String name, List<Course> courses) {
        super(id);
        Name = name;
        Courses = courses;
    }

    public String Name;
    public List<Course> Courses;
    public int DepartmentId;
    public  Department Department;
    public Direction() {
        super(0);
        Name = null;
    }
}

