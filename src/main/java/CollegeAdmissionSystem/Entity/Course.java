package CollegeAdmissionSystem.Entity;

/*
  create table Course(
 Id integer primary key AUTOINCREMENT ,
 Name varchar(256) not null,
 DirectionId INTEGER ,
 FOREIGN KEY (DirectionId)  REFERENCES Direction (id)
 );
* */
public class Course extends Entity {
     public Direction Direction;
    public Course(int id, String name) {
        super(id);
        Name = name;
        HoursPerWeek=2;
    }

    public Course(int id, String name, byte hoursPerWeek) {
        super(id);
        Name = name;
        HoursPerWeek = hoursPerWeek;
    }

    public String Name;
    public  int HoursPerWeek;
    public  int  DirectionId;

    public Course() {
        super(0);
        Name = null;
    }
}
