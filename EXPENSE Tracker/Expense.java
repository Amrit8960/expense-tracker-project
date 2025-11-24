package model;

/**
 * Model class representing an expense record.
 * Demonstrates Encapsulation and Inheritance.
 */
public class Expense {
    private int id;
    private String title;
    private double amount;
    private String category;
    private String date;

    // Constructor without ID (for new records)
    public Expense(String title, double amount, String category, String date) {
        this.title = title;
        this.amount = amount;
        this.category = category;
        this.date = date;
    }

    // Constructor with ID (for reading from DB)
    public Expense(int id, String title, double amount, String category, String date) {
        this.id = id;
        this.title = title;
        this.amount = amount;
        this.category = category;
        this.date = date;
    }

    // Getters and Setters (Encapsulation)
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    @Override
    public String toString() {
        return String.format("%-4d | %-15s | ₹%-8.2f | %-10s | %s",
                id, title, amount, category, date);
    }
}
