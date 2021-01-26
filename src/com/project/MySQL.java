package com.project;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Ussama Iftikhar on 21-Jan-2021.
 * Email iusama46@gmail.com
 * Email iusama466@gmail.com
 * Github https://github.com/iusama46
 */

public class MySQL {
    private Connection connection;
    private String url = "jdbc:mysql://remotemysql.com:3306/";
    private String user = "ylO5Spv5hX", pass = "G1ur9pS6pu";
    private String db = "ylO5Spv5hX", dbTable = "Orders";

    public MySQL() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url + db, user, pass);
            if (connection != null) {
                System.out.println("Connected.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(new JFrame(), "Database Connection Error " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }

    }

/*    CREATE TABLE `Orders` (
            `OrderId` int(11) NOT NULL AUTO_INCREMENT,
    `BurgerQuantity` int NOT NULL,
    `CoffeeQuantity` int NOT NULL,
    `ColdDrinkQuantity` int NOT NULL,
    `MuffinsQuantity` int NOT NULL,
    `Date` varchar(15) NOT NULL,
    `Time` varchar(15) NOT NULL,
    PRIMARY KEY (`OrderId`)
);*/

    public ArrayList<Order> getAllOrders() {
        ArrayList<Order> list = new ArrayList<>();
        try {
            System.out.println("Getting orders...");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "select * from " + dbTable);
            while (resultSet.next()) {
                //    resultSet.getString(1),
                Order order = new Order();
                order.setOrderId(resultSet.getInt(1));
                order.setBurgerQuantity(resultSet.getInt(2));
                order.setCoffeeQuantity(resultSet.getInt(3));
                order.setColdDrinkQuantity(resultSet.getInt(4));
                order.setMuffinsQuantity(resultSet.getInt(5));
                order.setDate(resultSet.getString(6));
                order.setTime(resultSet.getString(7));
                list.add(order);
                System.out.println("Order id " + resultSet.getString(5));
            }
            resultSet.close();
            statement.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(new JFrame(), "Error in getting data " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }

        return list;
    }


    public boolean insertOrder(Order order) {
        System.out.println("adding...");
        String sqlInsert = "INSERT INTO " + dbTable + "(BurgerQuantity, CoffeeQuantity, ColdDrinkQuantity,MuffinsQuantity, Date, Time) ";
        sqlInsert = sqlInsert + "VALUES (?, ?, ?, ?, ?, ?)";
        int rowsInserted = 0;
        try {
            PreparedStatement statement = connection.prepareStatement(sqlInsert);
            statement.setInt(1, order.getBurgerQuantity());
            statement.setInt(2, order.getCoffeeQuantity());
            statement.setInt(3, order.getColdDrinkQuantity());
            statement.setInt(4, order.getMuffinsQuantity());
            statement.setString(5, order.date);
            statement.setString(6, order.getTime());
            rowsInserted = statement.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(new JFrame(), "Error in inserting data " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (rowsInserted > 0) {
            System.out.println("A new order is inserted successfully!");
            return true;
        } else {
            System.out.println("Insertion Failed!");
            return false;
        }
    }

    public boolean isExist(int id) {
        try {
            System.out.println("Checking Order id...");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "select * from " + dbTable + " where OrderId=" + id);
            while (resultSet.next()) {

                Order order = new Order();
                order.setOrderId(resultSet.getInt(1));
                order.setBurgerQuantity(resultSet.getInt(2));
                order.setCoffeeQuantity(resultSet.getInt(3));
                order.setColdDrinkQuantity(resultSet.getInt(4));
                order.setDate(resultSet.getString(5));
                order.setTime(resultSet.getString(6));
                order.setTotalPrice(resultSet.getDouble(7));

                System.out.println("Order id " + resultSet.getInt(1) + " Exists");
            }
            resultSet.close();
            statement.close();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(new JFrame(), "Error in getting data " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

    }

    public boolean updateOrder(Order order) {
        String sql = "UPDATE " + dbTable + " SET BurgerQuantity=?, CoffeeQuantity=?, ColdDrinkQuantity=?, MuffinsQuantity=?  WHERE OrderId=?";
        int rowsUpdated = 0;
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, order.getBurgerQuantity());
            statement.setInt(2, order.getCoffeeQuantity());
            statement.setInt(3, order.getColdDrinkQuantity());
            statement.setInt(4, order.getMuffinsQuantity());
            statement.setInt(5, order.getOrderId());
            rowsUpdated = statement.executeUpdate();

            statement.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(new JFrame(), "Error in updating data " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (rowsUpdated > 0) {
            System.out.println("Record is updated successfully!");
            return true;
        } else {
            System.out.println("Record  is not not updated!");
            return false;
        }
    }


    public boolean deleteOrder(int id) {
        String sql = "DELETE FROM " + dbTable + " WHERE OrderId=?";
        int rowsDeleted = 0;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            rowsDeleted = statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(new JFrame(), "Error in deleting data " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }

        if (rowsDeleted > 0) {
            System.out.println("Order is deleted successfully!");
            JOptionPane.showMessageDialog(new JFrame(), "Order is deleted successfully!", "Operation Successful", JOptionPane.INFORMATION_MESSAGE);
            return true;
        } else {
            System.out.println("Order is not deleted!");
            JOptionPane.showMessageDialog(new JFrame(), "Error in deleting data ", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

}
