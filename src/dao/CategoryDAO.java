package src.dao;

import src.db.DBConnection;
import src.model.Category;
import java.sql.*;
import java.util.*;

public class CategoryDAO {
    public List<Category> getAllCategories(String type) {
        List<Category> list = new ArrayList<>();
        String sql = "SELECT * FROM categories WHERE type = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, type);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Category c = new Category();
                c.setId(rs.getInt("id"));
                c.setName(rs.getString("name"));
                c.setType(rs.getString("type"));
                list.add(c);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching categories: " + e.getMessage(), e);
        }
        return list;
    }

    public List<String> getAllCategories() {
        List<String> categories = new ArrayList<>();
        String sql = "SELECT name FROM categories";
        try (Connection conn = DBConnection.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                categories.add(rs.getString("name"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching categories: " + e.getMessage(), e);
        }
        return categories;
    }

    public List<String> getCategoriesByType(String type) {
        List<String> categories = new ArrayList<>();
        String sql = "SELECT name FROM categories WHERE type = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, type);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                categories.add(rs.getString("name"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching categories by type: " + e.getMessage(), e);
        }
        return categories;
    }

    public boolean isValidCategory(String name) {
        String sql = "SELECT COUNT(*) FROM categories WHERE name = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error checking category: " + e.getMessage(), e);
        }
        return false;
    }
}
