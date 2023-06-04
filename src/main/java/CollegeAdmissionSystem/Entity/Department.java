package CollegeAdmissionSystem.Entity;


import java.util.List;
/*

        create table Department (
        Id integer primary key AUTOINCREMENT ,
        Name varchar(40) not null
        );
        */
public class Department extends Entity {
    public String Name;
    List<Direction> Directions;
}

