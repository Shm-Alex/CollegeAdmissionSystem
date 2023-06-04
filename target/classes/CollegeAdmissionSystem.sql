 .open CollegeAdmissionSystem
 create table  Student(id integer primary key AUTOINCREMENT ,
 LastName varchar(40) not null,
 Name varchar(40) not null ,
 SurName varchar(40) ,
 Email varchar(40) not null ,
 Phone varchar(11) not null ,
 Birthday text not null
 );
 create table Department(
 Id integer primary key AUTOINCREMENT ,
 Name varchar(40) not null
 );
 create table Direction(
 Id integer primary key AUTOINCREMENT ,
 Name varchar(40) not null,
 DepartmentId INTEGER ,
 FOREIGN KEY (DepartmentId)  REFERENCES department (id)
 );
  create table Course(
 Id integer primary key AUTOINCREMENT ,
 Name varchar(256) not null,
 DirectionId INTEGER ,
 FOREIGN KEY (DirectionId)  REFERENCES Direction (id)
 );

  create table StudentCourses(
  StudentId INTEGER ,
  CourseId INTEGER ,
 FOREIGN KEY (StudentId)  REFERENCES Student (id)
 FOREIGN KEY (CourseId)  REFERENCES Course (id)
 CONSTRAINT con_primary_StudentCourses PRIMARY KEY(StudentId,CourseId)
 );


