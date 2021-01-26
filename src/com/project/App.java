package com.project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Created by Ussama Iftikhar on 21-Jan-2021.
 * Email iusama46@gmail.com
 * Email iusama466@gmail.com
 * Github https://github.com/iusama46
 */

public class App {
    private final MySQL mySQL;
    private JTextField burgerTextField1;
    private JTextField coldDrinkTextField2;
    private JTextField coffeeTextField3;
    private JTextField muffinsTextField4;
    private JPanel panel1;
    private JButton addButton;
    private JButton resetButton;
    private JButton updateOrderButton;
    private JButton deleteOrderButton;
    private JButton exitButton;
    private JButton viewAllOrdersButton;
    private int burgerQty, coldDrinkQty, muffinsQty, coffeeQty = 0;

    public App() {
        panel1.setSize(520, 420);

        mySQL = new MySQL();
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addData();
            }
        });
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetScreen();
            }
        });
        deleteOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteOrderDialog();
            }
        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int a = JOptionPane.showConfirmDialog(new Frame(), "Are you sure you want to quit?");
                if (a == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });

        updateOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateDialog();
            }
        });
        viewAllOrdersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MyJTable(mySQL.getAllOrders());
            }
        });
    }

    private void resetScreen() {
        burgerTextField1.setText("");
        coffeeTextField3.setText("");
        coldDrinkTextField2.setText("");
        muffinsTextField4.setText("");
        muffinsQty = 0;
        coldDrinkQty = 0;
        coffeeQty = 0;
        burgerQty = 0;
    }

    public static void main(String[] args) {

        JFrame jFrame = new JFrame("Cafe Management System");
        jFrame.setVisible(true);
        jFrame.setSize(520, 460);

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - jFrame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - jFrame.getHeight()) / 2);
        jFrame.setLocation(x, y);

        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setContentPane(new App().panel1);

    }

    private void updateDialog() {

        JTextField textField1 = new JTextField();
        final JTextField textField2 = new JTextField();
        final JTextField textField3 = new JTextField();
        final JTextField textField4 = new JTextField();
        final JTextField textField5 = new JTextField();

        Object[] inputFields = {"Enter OrderId", textField1,
                "Burgers (Qty)", textField2,
                "Coffee (Qty)", textField3,
                "ColdDrinks (Qty)", textField4,
                "Muffins (Qty)", textField5};

        int option = JOptionPane.showConfirmDialog(new Frame(), inputFields, "Multiple Inputs", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (option == JOptionPane.OK_OPTION) {
            try {
                Order order = new Order();
                order.setOrderId(Integer.parseInt(textField1.getText()));
                order.setCoffeeQuantity(Integer.parseInt(textField3.getText()));
                order.setColdDrinkQuantity(Integer.parseInt(textField4.getText()));
                order.setMuffinsQuantity(Integer.parseInt(textField5.getText()));
                order.setBurgerQuantity(Integer.parseInt(textField2.getText()));
                if (mySQL.updateOrder(order)) {
                    JOptionPane.showMessageDialog(new JFrame(), "Record updated against OrderId #" + order.getOrderId(), "Operation Successful", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(new JFrame(), "Failed to update. Order Id isn't found", "OrderId Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception d) {
                JOptionPane.showMessageDialog(new JFrame(), "Error encountered " + d.getMessage(), " Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void deleteOrderDialog() {
        String id = JOptionPane.showInputDialog(new Frame(), "Enter Order Id");
        if (id != null)
            mySQL.deleteOrder(Integer.parseInt(id));
    }

    private void addData() {
        if (!burgerTextField1.getText().isEmpty()) {
            burgerQty = Integer.parseInt(burgerTextField1.getText());
        }
        if (!coffeeTextField3.getText().isEmpty()) {
            coffeeQty = Integer.parseInt(coffeeTextField3.getText());
        }
        if (!coldDrinkTextField2.getText().isEmpty()) {
            coldDrinkQty = Integer.parseInt(coldDrinkTextField2.getText());
        }
        if (!muffinsTextField4.getText().isEmpty()) {
            muffinsQty = Integer.parseInt(muffinsTextField4.getText());
        }

        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        System.out.println(time.toString().substring(0, 8));
        Order order = new Order(burgerQty, coffeeQty, coldDrinkQty, muffinsQty, date.toString(), time.toString().substring(0, 8));
        if (mySQL.insertOrder(order)) {
            JOptionPane.showMessageDialog(new JFrame(), "Record Added to database", "Operation Successful", JOptionPane.INFORMATION_MESSAGE);
            resetScreen();
        } else {
            JOptionPane.showMessageDialog(new JFrame(), "Insertion Failed.. ", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}


