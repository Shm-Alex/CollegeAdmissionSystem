package CollegeAdmissionSystem.RepositoryImplentation;

import CollegeAdmissionSystem.Entity.Course;
import CollegeAdmissionSystem.Entity.Department;
import CollegeAdmissionSystem.Entity.Direction;
import CollegeAdmissionSystem.Entity.Student;
import CollegeAdmissionSystem.Repository.AbstractSqlLightRepository;
import CollegeAdmissionSystem.Repository.IStudentRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
class StudentCourse
{
    public int StudentId;
    public int CourseId;
}
public class StudentRepository extends AbstractSqlLightRepository implements IStudentRepository
{

    public StudentRepository(Connection conn) {
        super(conn);
    }

    @Override
    public Student getStudentByEmail(String Email) {
        try {
            List<Student>   result= preparedQuery(
                    "Select * from student where Email=?",
                    new IPrepareStatement() {
                        @Override
                        public PreparedStatement AdditionalPrepareStatement(PreparedStatement ps) throws SQLException {
                           ps.setString(1,Email);
                           return ps;
                        }
                    },
                    Student.class);
            if (result.isEmpty()) return null;
            for (Student student : result) {
                student.Courses= preparedQuery("select c.* from StudentCourses sc " +
                        "join Course c on c.Id=sc.CourseId " +
                        " where sc.StudentId=?",student.Id, Course.class);
  /*
   var departmentIds= preparedQuery("select d.id from  Department d " +
                        "join Direction dir on dir.DepartmentId=d.Id " +
                        "join Course c on c.DirectionId=c.Id " +
                        "join StudentCourses sc on c.Id=sc.CourseId " +
                        "join Student s on s.Id=sc.StudentId " +
                        " where sc.StudentId=?",student.Id, int.class);
                if(!departmentIds.isEmpty())
                {
                    IDepartmentReadonlyRepository
                }*/
                for (Course course :  student.Courses)
                {
                   var directions= preparedQuery("select * from Direction where id=? ",
                            course.DirectionId,
                            Direction.class
                    );
                    course.Direction=directions.isEmpty()?null:directions.get(0);
                    if(course.Direction!=null)
                    {
                       var departments= preparedQuery("select * from Department where id=? ",
                                course.Direction.DepartmentId,
                                Department.class);
                        course.Direction.Department=departments.isEmpty()?null:departments.get(0);
                    }
                }

            }
            return result.get(0);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Student CreateStudent(String LastName, String Name, String SurName, String Email, String Phone, Date Birthday) {
        if (getStudentByEmail(Email)!=null) return  Update(LastName,Name,SurName,Email,Phone,Birthday);
        try {

            List<Student>   result= preparedQuery(
                    "insert into Student(LastName, Name, SurName,  Email,  Phone,  Birthday) values(?,?,?,?,?,?) RETURNING *;",
                    new IPrepareStatement() {
                        @Override
                        public PreparedStatement AdditionalPrepareStatement(PreparedStatement ps) throws SQLException {
                            ps.setString(1,LastName);
                            ps.setString(2,Name);
                            ps.setString(3,SurName);
                            ps.setString(4,Email);
                            ps.setString(5,Phone);
                            ps.setString(6,Birthday.toString());
                            return ps;
                        }
                    },
                    Student.class);
            if(result.isEmpty())return  null;
            return result.get(0);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /*
  create table StudentCourses(
  StudentId INTEGER ,
  CourseId INTEGER ,
 FOREIGN KEY (StudentId)  REFERENCES Student (id)
 FOREIGN KEY (CourseId)  REFERENCES Course (id)
 CONSTRAINT con_primary_StudentCourses PRIMARY KEY(StudentId,CourseId)
 );
    * */
    @Override
    public Student AddCourseToStudent(int StudentId, int CourseId) {
        try {

            List<StudentCourse>   result= preparedQuery(
                    "insert into StudentCourses(StudentId,CourseId) values(?,?) RETURNING *;",
                    new IPrepareStatement() {
                        @Override
                        public PreparedStatement AdditionalPrepareStatement(PreparedStatement ps) throws SQLException {
                            ps.setInt(1,StudentId);
                            ps.setInt(2,CourseId);
                            return ps;
                        }
                    },
                    StudentCourse.class);
            if(result.isEmpty())return  null;
            return GetStudentById(StudentId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Student GetStudentById(int studentId)
    {
    try {
        List<Student>   result= preparedQuery(
                "Select * from student where id=?",
                new IPrepareStatement() {
                    @Override
                    public PreparedStatement AdditionalPrepareStatement(PreparedStatement ps) throws SQLException {
                        ps.setInt(1,studentId);
                        return ps;
                    }
                },
                Student.class);
        if (result.isEmpty()) return null;
        for (Student student : result) {
            student.Courses= preparedQuery("select c.* from StudentCourses sc " +
                    "join Course c on c.Id=sc.CourseId " +
                    " where sc.StudentId=?",student.Id, Course.class);
  /*
   var departmentIds= preparedQuery("select d.id from  Department d " +
                        "join Direction dir on dir.DepartmentId=d.Id " +
                        "join Course c on c.DirectionId=c.Id " +
                        "join StudentCourses sc on c.Id=sc.CourseId " +
                        "join Student s on s.Id=sc.StudentId " +
                        " where sc.StudentId=?",student.Id, int.class);
                if(!departmentIds.isEmpty())
                {
                    IDepartmentReadonlyRepository
                }*/
            for (Course course :  student.Courses)
            {
                var directions= preparedQuery("select * from Direction where id=? ",
                        course.DirectionId,
                        Direction.class
                );
                course.Direction=directions.isEmpty()?null:directions.get(0);
                if(course.Direction!=null)
                {
                    var departments= preparedQuery("select * from Department where id=? ",
                            course.Direction.DepartmentId,
                            Department.class);
                    course.Direction.Department=departments.isEmpty()?null:departments.get(0);
                }
            }

        }
        return result.get(0);
    } catch (Exception e) {
        throw new RuntimeException(e);
    }
}

    @Override
    public Student RemoveCourseFromStudent(int StudentId, int CourseId) {
        try {

            List<StudentCourse>   result= preparedQuery(
                    "delete from  StudentCourses where StudentId=? and CourseId=? RETURNING *;",
                    new IPrepareStatement() {
                        @Override
                        public PreparedStatement AdditionalPrepareStatement(PreparedStatement ps) throws SQLException {
                            ps.setInt(1,StudentId);
                            ps.setInt(2,CourseId);
                            return ps;
                        }
                    },
                    StudentCourse.class);
            if(result.isEmpty())return  null;
            return GetStudentById(StudentId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Student Update(String LastName, String Name, String SurName, String Email, String Phone, Date Birthday) {
        return null;
    }
}
