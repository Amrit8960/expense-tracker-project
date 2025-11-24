PreparedStatement stmt = conn.prepareStatement("INSERT INTO expenses (title, amount) VALUES (?, ?)");
stmt.setString(1, "Lunch");
stmt.setDouble(2, 150.00);
stmt.executeUpdate();
