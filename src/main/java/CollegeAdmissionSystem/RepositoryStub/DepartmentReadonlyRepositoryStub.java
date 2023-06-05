package CollegeAdmissionSystem.RepositoryStub;

import CollegeAdmissionSystem.Entity.Course;
import CollegeAdmissionSystem.Entity.Department;
import CollegeAdmissionSystem.Entity.Direction;
import CollegeAdmissionSystem.Repository.IDepartmentReadonlyRepository;

import java.util.Arrays;
import java.util.List;


public class DepartmentReadonlyRepositoryStub implements IDepartmentReadonlyRepository {
    @Override
    public List<Department> getDepartments() {
        List<Department> ret;
        ret = Arrays.asList(
                new Department(
                        1,
                        "Cybernetics",
                        Arrays.asList(
                                new Direction(1,"Kaf22",
                                        Arrays.asList(
                                                new Course(1,"Data analysys SQL "),
                                                new Course(2,"Object oriented programming in java"),
                                                new Course(3,"Design patterns .Net "),
                                                new Course(4,"Artificial intelligence . "),
                                                new Course(5,"Parallel programming. ")
                                        )

                                ),
                                new Direction(2,"Kaf 10",
                                        Arrays.asList(
                                                new Course(6,"Microservices patterns ", (byte) 1),
                                                new Course(7,"Distributed  calculations",(byte) 1)
                                        )),
                                new Direction(3,"Kaf 5",
                                        Arrays.asList(
                                                new Course(8,"Modern microprocessor systems ", (byte) 3),
                                                new Course(9,"Operational System ",(byte) 4),
                                                new Course(9,"Compillers ",(byte) 4)
                                        )),
                            new Direction(4,"Common Physics",
                                    Arrays.asList(
                                            new Course(8,"Dynamics, Kinematics, Electricity ", (byte) 3),
                                            new Course(9,"Experimental Phisics "),
                                            new Course(10,"Atomic phisics ",(byte) 4),
                                            new Course(11,"Optics",(byte) 1)
                                    )),
                                new Direction(5,"Electrotechnics",
                                        Arrays.asList(
                                                new Course(12,"Schemetechnics ", (byte) 3),
                                                new Course(14," Electrotechnics basics")
                                        )),
                                new Direction(5,"Networks Organisation ",
                                        Arrays.asList(
                                                new Course(15,"Linux Networks ", (byte) 3),
                                                new Course(16,"Novel Netware")
                                                ))

                                )

                ),
                new Department(2,"Computer design ", Arrays.asList()),//new Department,
                new Department(4,"Mathematics ", Arrays.asList())//new Department,


        );
        return ret;
    }
}

