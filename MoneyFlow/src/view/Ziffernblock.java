/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Christoph
 */
public class Ziffernblock extends JPanel {

    public Ziffernblock() {
        this.setBorder(new EmptyBorder(new Insets(0, 10, 10, 10)));
        initComponents();
    }

    private void initComponents() {
        GroupLayout ziffernBlockGroupLayout = new GroupLayout(this);
        ziffernBlockGroupLayout.setAutoCreateGaps(true);
        this.setLayout(ziffernBlockGroupLayout);

        JTextField preisField = new JTextField();
        preisField.setEditable(false);
        preisField.setPreferredSize(new Dimension(Short.MAX_VALUE, 70));
        preisField.setMaximumSize(new Dimension(Short.MAX_VALUE, 70));
        JButton num1 = new JButton("1");
        num1.setFont(new Font("Arial", 1, 30));
        JButton num2 = new JButton("2");
        num2.setFont(new Font("Arial", 1, 30));
        JButton num3 = new JButton("3");
        num3.setFont(new Font("Arial", 1, 30));
        JButton num4 = new JButton("4");
        num4.setFont(new Font("Arial", 1, 30));
        JButton num5 = new JButton("5");
        num5.setFont(new Font("Arial", 1, 30));
        JButton num6 = new JButton("6");
        num6.setFont(new Font("Arial", 1, 30));
        JButton num7 = new JButton("7");
        num7.setFont(new Font("Arial", 1, 30));
        JButton num8 = new JButton("8");
        num8.setFont(new Font("Arial", 1, 30));
        JButton num9 = new JButton("9");
        num9.setFont(new Font("Arial", 1, 30));
        JButton num0 = new JButton("0");
        num0.setFont(new Font("Arial", 1, 30));
        JButton num00 = new JButton("00");
        num00.setFont(new Font("Arial", 1, 30));
        JButton numKomma = new JButton(",");
        numKomma.setFont(new Font("Arial", 1, 30));
        JButton enter = new JButton("\u2713");
        enter.setForeground(new java.awt.Color(0, 204, 51));
        enter.setFont(enter.getFont().deriveFont(34f));
        JButton delete = new JButton("X");
        delete.setFont(new Font("Arial", 1, 30));
        delete.setForeground(new java.awt.Color(255, 51, 51));
        JButton checkout = new JButton("CHECKOUT");
        checkout.setFont(new Font("Arial", 1, 30));

        JPanel ziffernPanel = new JPanel();
        //ziffernPanel.setPreferredSize(new Dimension(, ));
        GridLayout ziffernGridLayout = new GridLayout(5, 3, 5, 5);
        ziffernPanel.setLayout(ziffernGridLayout);
        ziffernPanel.add(num1);
        ziffernPanel.add(num2);
        ziffernPanel.add(num3);
        ziffernPanel.add(num4);
        ziffernPanel.add(num5);
        ziffernPanel.add(num6);
        ziffernPanel.add(num7);
        ziffernPanel.add(num8);
        ziffernPanel.add(num9);
        ziffernPanel.add(num00);
        ziffernPanel.add(num0);
        ziffernPanel.add(numKomma);
        ziffernPanel.add(delete);
        ziffernPanel.add(Box.createRigidArea(new Dimension(0, delete.getMinimumSize().height * 2)));
        ziffernPanel.add(enter);
        
        JPanel checkoutPanel = new JPanel();
        checkoutPanel.setPreferredSize(new Dimension(Short.MAX_VALUE, 120));
        GridLayout checkoutGridLayout = new GridLayout(1, 1, 5, 5);
        checkoutPanel.setLayout(checkoutGridLayout);
        checkoutPanel.add(checkout);

        ziffernBlockGroupLayout.setHorizontalGroup(
                ziffernBlockGroupLayout.createParallelGroup()
                        .addGroup(ziffernBlockGroupLayout.createSequentialGroup()
                                .addComponent(preisField)
                        )
                        .addGroup(ziffernBlockGroupLayout.createSequentialGroup()
                                .addComponent(ziffernPanel)
                        )
                        .addGroup(ziffernBlockGroupLayout.createSequentialGroup()
                                .addComponent(checkoutPanel)
                        )
        );
        ziffernBlockGroupLayout.setVerticalGroup(
                ziffernBlockGroupLayout.createSequentialGroup()
                        .addGroup(ziffernBlockGroupLayout.createSequentialGroup()
                                .addGap(5)
                                .addComponent(preisField)
                        )
                        .addGroup(ziffernBlockGroupLayout.createSequentialGroup()
                                .addComponent(ziffernPanel)
                                .addGap(50)
                        )
                        .addGroup(ziffernBlockGroupLayout.createParallelGroup()
                                .addComponent(checkoutPanel)
                        )
        );
        //ziffernBlockGroupLayout.linkSize(SwingConstants.VERTICAL, preisField, num1);
        //        this.add(Box.createRigidArea(new Dimension(0, 5)));
        //        this.add(preisField);
        //        this.add(Box.createRigidArea(new Dimension(0, 5)));
        //        this.add(ziffernPanel);
    }

}
