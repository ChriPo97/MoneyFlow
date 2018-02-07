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
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Christoph
 */
public class Ziffernblock extends JPanel {

    JTextField preisField = new JTextField();
    JLabel produktNummerLabel = new JLabel("Produktnummer");
    JLabel mengeLabel = new JLabel("Menge");
    JLabel rabattLabel = new JLabel("Rabatt");
    ButtonGroup numBlock = new ButtonGroup();
    JButton num1 = new JButton("1");
    JButton num2 = new JButton("2");
    JButton num3 = new JButton("3");
    JButton num4 = new JButton("4");
    JButton num5 = new JButton("5");
    JButton num6 = new JButton("6");
    JButton num7 = new JButton("7");
    JButton num8 = new JButton("8");
    JButton num9 = new JButton("9");
    JButton num0 = new JButton("0");
    JButton num00 = new JButton("00");
    JButton numKomma = new JButton(",");
    JButton enter = new JButton("\u2713");
    JButton delete = new JButton("X");
    JButton checkout = new JButton("CHECKOUT");
    JPanel ziffernPanel = new JPanel();
    GridLayout ziffernGridLayout = new GridLayout(5, 3, 5, 5);
    GroupLayout ziffernBlockGroupLayout = new GroupLayout(this);
    JPanel checkoutPanel = new JPanel();
    GridLayout checkoutGridLayout = new GridLayout(1, 1, 5, 5);

    public Ziffernblock() {
        this.setBorder(new EmptyBorder(new Insets(0, 10, 10, 10)));
        initComponents();
    }

    private void initComponents() {
        ziffernBlockGroupLayout.setAutoCreateGaps(true);
        this.setLayout(ziffernBlockGroupLayout);

        preisField.setEditable(false);
        preisField.setPreferredSize(new Dimension(Short.MAX_VALUE, 70));
        preisField.setMaximumSize(new Dimension(Short.MAX_VALUE, 70));
        
        produktNummerLabel.setFont(new Font("Arial", 0, produktNummerLabel.getFont().getSize()));
        mengeLabel.setFont(new Font("Arial", 1, mengeLabel.getFont().getSize()));
        rabattLabel.setFont(new Font("Arial", 0, rabattLabel.getFont().getSize()));

        num1.setFont(new Font("Arial", 1, 30));
        num2.setFont(new Font("Arial", 1, 30));
        num3.setFont(new Font("Arial", 1, 30));
        num4.setFont(new Font("Arial", 1, 30));
        num5.setFont(new Font("Arial", 1, 30));
        num6.setFont(new Font("Arial", 1, 30));
        num7.setFont(new Font("Arial", 1, 30));
        num8.setFont(new Font("Arial", 1, 30));
        num9.setFont(new Font("Arial", 1, 30));
        num0.setFont(new Font("Arial", 1, 30));
        num00.setFont(new Font("Arial", 1, 30));
        numKomma.setFont(new Font("Arial", 1, 30));
        enter.setForeground(new java.awt.Color(0, 204, 51));
        enter.setFont(enter.getFont().deriveFont(34f));
        delete.setFont(new Font("Arial", 1, 30));
        delete.setForeground(new java.awt.Color(255, 51, 51));
        checkout.setFont(new Font("Arial", 1, 30));

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

        checkoutPanel.setPreferredSize(new Dimension(Short.MAX_VALUE, 120));
        checkoutPanel.setLayout(checkoutGridLayout);
        checkoutPanel.add(checkout);

        ziffernBlockGroupLayout.setHorizontalGroup(
                ziffernBlockGroupLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addGroup(ziffernBlockGroupLayout.createSequentialGroup()
                        .addComponent(preisField)
                )
                .addGroup(ziffernBlockGroupLayout.createSequentialGroup()
                        .addComponent(produktNummerLabel)
                        .addComponent(mengeLabel)
                        .addComponent(rabattLabel)
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
                .addGroup(ziffernBlockGroupLayout.createParallelGroup()
                        .addComponent(produktNummerLabel)
                        .addComponent(mengeLabel)
                        .addComponent(rabattLabel)
                )
                .addGroup(ziffernBlockGroupLayout.createSequentialGroup()
                        .addComponent(ziffernPanel)
                        .addGap(50)
                )
                .addGroup(ziffernBlockGroupLayout.createParallelGroup()
                        .addComponent(checkoutPanel)
                )
        );
    }
    
    public void setModeProduktnummer() {
        produktNummerLabel.setFont(new Font("Arial", 1, produktNummerLabel.getFont().getSize()));
        mengeLabel.setFont(new Font("Arial", 0, mengeLabel.getFont().getSize()));
        rabattLabel.setFont(new Font("Arial", 0, rabattLabel.getFont().getSize()));
    }
    
    public void setModeMenge() {
        produktNummerLabel.setFont(new Font("Arial", 0, produktNummerLabel.getFont().getSize()));
        mengeLabel.setFont(new Font("Arial", 1, mengeLabel.getFont().getSize()));
        rabattLabel.setFont(new Font("Arial", 0, rabattLabel.getFont().getSize()));
    }
    
    public void setModeRabatt() {
        produktNummerLabel.setFont(new Font("Arial", 0, produktNummerLabel.getFont().getSize()));
        mengeLabel.setFont(new Font("Arial", 0, mengeLabel.getFont().getSize()));
        rabattLabel.setFont(new Font("Arial", 1, rabattLabel.getFont().getSize()));
    }
    
}