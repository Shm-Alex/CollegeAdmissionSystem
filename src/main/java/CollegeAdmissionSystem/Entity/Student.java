package CollegeAdmissionSystem.Entity;
import java.util.Date;
import java.util.List;

/*
 create table  Student(id integer primary key AUTOINCREMENT ,
 LastName varchar(40) not null,
 Name varchar(40) not null ,
 SurName varchar(40) ,
 Email varchar(40) not null ,
 Phone varchar(11) not null ,
 Birthday text not null
 );
* */
public class Student extends  Entity {

    public String LastName;
    public String Name;
    public String SurName;
    public String Email;
    public String Phone;
    public  Date Birthday;
    List<Course> Courses;
}

