package com.project;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Ussama Iftikhar on 21-Jan-2021.
 * Email iusama46@gmail.com
 * Email iusama466@gmail.com
 * Github https://github.com/iusama46
 */

public class MyJTable {
    JFrame jFrame;
    JTable jTable;

    MyJTable(ArrayList<Order> orderArrayList) {
        jFrame = new JFrame();
        jFrame.setTitle("All Orders");

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - jFrame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - jFrame.getHeight()) /2 );
        jFrame.setLocation(x, y);
        String[][] dataList = new String[orderArrayList.size()][7];

        for (int i = 0; i < orderArrayList.size(); i++) {
            dataList[i][0] = String.valueOf(orderArrayList.get(i).getOrderId());
            dataList[i][1] = orderArrayList.get(i).getDate();
            dataList[i][2] = orderArrayList.get(i).getTime();

            dataList[i][3] = String.valueOf(orderArrayList.get(i).getColdDrinkQuantity());
            dataList[i][4] = String.valueOf(orderArrayList.get(i).getMuffinsQuantity());
            dataList[i][5] = String.valueOf(orderArrayList.get(i).getBurgerQuantity());
            dataList[i][6] = String.valueOf(orderArrayList.get(i).getCoffeeQuantity());
        }

        String[] columnNames = {"OrderId", "Date", "Time","Burgers", "Coffee", "Drinks","Muffins" };

        jTable = new JTable(dataList, columnNames);
        jTable.setBounds(30, 40, 300, 400);


        JScrollPane sp = new JScrollPane(jTable);
        jFrame.add(sp);

        jFrame.setSize(500, 400);
        jFrame.setVisible(true);
    }
}