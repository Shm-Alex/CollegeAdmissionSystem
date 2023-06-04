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
    public String Name;
}
