package CollegeAdmissionSystem.RepositoryImplentation;

import CollegeAdmissionSystem.Entity.Course;
import CollegeAdmissionSystem.Entity.Department;
import CollegeAdmissionSystem.Entity.Direction;
import CollegeAdmissionSystem.Entity.Entity;
import CollegeAdmissionSystem.Repository.AbstractSqlLightRepository;
import CollegeAdmissionSystem.Repository.IDepartmentReadonlyRepository;

import java.sql.Connection;
import java.util.List;

public class DepartmentReadonlyRepository extends AbstractSqlLightRepository implements IDepartmentReadonlyRepository {
    public DepartmentReadonlyRepository(Connection conn) {
        super(conn);
    }

    @Override
    public List<Department> getDepartments() {
        try {
            List<Department>   result= query("select *  from Department",Department.class);
            for (Department department : result) {
                department.Directions=parametrasedQuery("select * from Direction where  DepartmentId=?",department.Id, Direction.class);
                for (Direction direction : department.Directions) {
                    direction.Courses=parametrasedQuery("select * from course where DirectionId=?",direction.Id, Course.class);
                }
            }
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
