package CollegeAdmissionSystem.Entity;

/*
базовая сущьность чтобы мапить объекты базы  в память с интовым идентификатором
* /
 */
public class Entity {
    public Entity(int id) {
        Id = id;
    }

    public int Id;
}
