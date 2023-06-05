package CollegeAdmissionSystem.DepartmentCreateRepository;

public class CourseSaveDbException extends Exception {
    public CourseSaveDbException(String message) {
        super(message);
    }

    public static CourseSaveDbException DepartmentSaveException(String DeparmentName) {
        return new CourseSaveDbException("department " + DeparmentName + " NotAdded");
    }

    public static CourseSaveDbException DirectionSaveException(String Name) {
        return new CourseSaveDbException("Direction " + Name + " NotAdded");
    }

    public static CourseSaveDbException CourseSaveException(String Name) {
        return new CourseSaveDbException("Course " + Name + " NotAdded");
    }
}
