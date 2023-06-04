package CollegeAdmissionSystem.Repository;

import CollegeAdmissionSystem.Entity.Department;

import java.util.List;

public interface IDepartmentReadonlyRepository {
      public List<Department> getDepartments();

}
