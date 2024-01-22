package com.example.duckilngs;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class userLoginDB {
    Connection conn;

    public userLoginDB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/ducklings", "root", "");
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Map<String, String> getAllUsers() {
        Map<String, String> db = new HashMap<>();
        String sql = "SELECT username, password FROM userinfo";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                db.put(rs.getString("username")
                        , rs.getString("password"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return db;
    }

    public List<payments> getAllPayments(String username) {
        List<payments> paymentList = new ArrayList<>();
        String sql = "SELECT * From payment WHERE username=?";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                payments payment = new payments();

                payment.setNameOfUser(rs.getString("username"));
                payment.setTitle(rs.getString("title"));
                payment.setDateOfPost(rs.getString("date"));
                payment.setDescription(rs.getString("description"));
                payment.setCategory(rs.getString("category"));
                payment.setPrice(rs.getInt("price"));

                paymentList.add(payment);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return paymentList;
    }
    public void singUp(String name, String password) {
        String sql = "INSERT INTO userinfo (username, password)" +
                "VALUE (?, ?)";
        //you can make your username/password " "
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, password);
            pstmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void createPayment(String name, String title, String description, String category, int price){
        String sql = "INSERT INTO payment (username, title, date, description, category, price) " +
                "VALUE (?, ?, ?, ?, ?, ?)";

        LocalDate curentDate = LocalDate.now();
        try{
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, title);
            pstmt.setDate(3, Date.valueOf(curentDate));
            pstmt.setString(4, description);
            pstmt.setString(5, category);
            pstmt.setInt(6, price);

            pstmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void deletePayment(String name, String title){
        String sql = "DELETE FROM payment WHERE username=? AND title=?";
        //WHERE username=? AND title=? there is a risk that you delete multiple objects
        try{
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, title);
            pstmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void editPayment(String name, String title, String newTitle, String newDescription, String newCategory, int newPrice){
        String sql = "UPDATE payment SET title=?, date=?, description=?, category=?, price=? " +
                "WHERE username=? AND title=?";
        //WHERE username=? AND title=? there is a risk that you change multiple objects
        LocalDate curentDate = LocalDate.now();
        try{
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, newTitle);
            pstmt.setDate(2, Date.valueOf(curentDate));
            pstmt.setString(3, newDescription);
            pstmt.setString(4, newCategory);
            pstmt.setInt(5, newPrice);

            pstmt.setString(6, name);
            pstmt.setString(7, title);
            pstmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
