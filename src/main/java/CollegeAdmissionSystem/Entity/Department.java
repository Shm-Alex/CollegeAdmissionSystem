package CollegeAdmissionSystem.Entity;


import java.util.List;
/*

        create table Department (
        Id integer primary key AUTOINCREMENT ,
        Name varchar(40) not null
        );
        */
public class Department extends Entity {

    public Department(int id, String name, List<Direction> directions) {
        super(id);
        Name = name;
        Directions = directions;
    }

    public String Name;
    public  List<Direction> Directions;
}

