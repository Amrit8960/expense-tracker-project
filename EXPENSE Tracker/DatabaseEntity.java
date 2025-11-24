public abstract class DatabaseEntity {
    protected int id;

    public DatabaseEntity(int id) {
        this.id = id;
    }

    public DatabaseEntity() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public abstract String getEntityName();
}

