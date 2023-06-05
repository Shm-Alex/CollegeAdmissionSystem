package CollegeAdmissionSystem.DepartmentCreateRepository;

import CollegeAdmissionSystem.Entity.Course;
import CollegeAdmissionSystem.Entity.Department;
import CollegeAdmissionSystem.Entity.Direction;
import CollegeAdmissionSystem.Repository.AbstractSqlLightRepository;
import CollegeAdmissionSystem.Repository.IDepartmentCreateRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class DepartmentCreateRepository  extends AbstractSqlLightRepository implements IDepartmentCreateRepository {
    public DepartmentCreateRepository(  Connection conn) {
        super( conn);
    }

    @Override
    public void CleanCourseTables() {

    }

    @Override
    public List<Department> SaveDepartments(List<Department> departments) throws SQLException, CourseSaveDbException {

        try
        {
            conn.setAutoCommit(false);
            for (Department department : departments) {
                var ps= conn.prepareStatement("insert into Department(Name) values(?)");
                ps.setString(1,department.Name);
                int affectedRows = ps.executeUpdate();
                if (affectedRows==0) throw CourseSaveDbException.DepartmentSaveException(department.Name);
                ps.getGeneratedKeys().next();
                department.Id= ps.getGeneratedKeys().getInt(1);

                for (Direction direction : department.Directions) {
                    var directionPs= conn.prepareStatement("insert into Direction(Name,DepartmentId) values(?,?)");
                    directionPs.setString(1,direction.Name);
                    directionPs.setInt(2,department.Id);
                    if (directionPs.executeUpdate()==0) throw CourseSaveDbException.DirectionSaveException(direction.Name);
                    direction.Id=directionPs.getGeneratedKeys().getInt(1);

                    for (Course course : direction.Courses)
                    {
                        var coursePs= conn.prepareStatement("insert into Course(Name,HoursPerWeek,DirectionId) values(?,?,?)");
                        coursePs.setString(1,course.Name);
                        coursePs.setInt(2,course.HoursPerWeek);
                        coursePs.setInt(3,direction.Id);
                        if (coursePs.executeUpdate()==0) throw CourseSaveDbException.CourseSaveException(course.Name);
                        course.Id=coursePs.getGeneratedKeys().getInt(1);
                    }
                }




            }


            conn.commit();
        }
        catch(Exception e)
        {
            conn.rollback();
            throw e;
        }
        finally
        {
            conn.close();
        }
        return departments;
    }
}