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
    public String Name;
    List<Course> Courses;
}

