package CollegeAdmissionSystem.RepositoryImplentation;

import CollegeAdmissionSystem.Entity.Course;
import CollegeAdmissionSystem.Entity.Department;
import CollegeAdmissionSystem.Entity.Direction;
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
                department.Directions= preparedQuery("select * from Direction where  DepartmentId=?",department.Id, Direction.class);
                for (Direction direction : department.Directions) {
                    direction.Courses= preparedQuery("select * from course where DirectionId=?",direction.Id, Course.class);
                }
            }
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Department getDepartmentById(int id) {
        try {
            List<Department>   result= preparedQuery("select *  from Department where id=?",id,Department.class);
            if(result.isEmpty()) return null;
            Department department=result.get(0);
                department.Directions= preparedQuery("select * from Direction where  DepartmentId=?",department.Id, Direction.class);
                for (Direction direction : department.Directions) {
                    direction.Courses= preparedQuery("select * from course where DirectionId=?",direction.Id, Course.class);

            }
            return department;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
