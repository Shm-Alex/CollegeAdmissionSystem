package CollegeAdmissionSystem.Entity;

/*
базовая сущьность чтобы мапить объекты базы  в память с интовым идентификатором
* /
 */
public class Entity {
    public Entity(){};// нужен чтобы в мапере инстанцировать пустую сущьность без Id
    public Entity(int id) {
        Id = id;
    }

    public int Id;
}
