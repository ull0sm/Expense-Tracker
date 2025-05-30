package src.model;

public class Category {
    private int id;
    private String name;
    private String type;

    // Default constructor
    public Category() {}

    // Parameterized constructor
    public Category(int id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public String getType() { return type; }

    // Setters
    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setType(String type) {
        if (type != null && (type.equals("income") || type.equals("expense"))) {
            this.type = type;
        } else {
            throw new IllegalArgumentException("Type must be 'income' or 'expense'");
        }
    }
}
