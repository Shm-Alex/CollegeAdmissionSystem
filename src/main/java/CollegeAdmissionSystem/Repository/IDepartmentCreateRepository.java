package CollegeAdmissionSystem.Repository;

import CollegeAdmissionSystem.DepartmentCreateRepository.CourseSaveDbException;
import CollegeAdmissionSystem.Entity.Department;

import java.sql.SQLException;
import java.util.List;

public interface IDepartmentCreateRepository {
    public void CleanCourseTables();
    public List<Department> SaveDepartments(List<Department> departments) throws SQLException, CourseSaveDbException;

}
