/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

    private static JTextField preisField = new JTextField();
    private static JLabel produktNummerLabel = new JLabel("Produktnummer");
    private static JLabel mengeLabel = new JLabel("Menge");
    private static JLabel rabattLabel = new JLabel("Rabatt");
    private ButtonGroup numBlock = new ButtonGroup();
    private JButton num1 = new JButton("1");
    private JButton num2 = new JButton("2");
    private JButton num3 = new JButton("3");
    private JButton num4 = new JButton("4");
    private JButton num5 = new JButton("5");
    private JButton num6 = new JButton("6");
    private JButton num7 = new JButton("7");
    private JButton num8 = new JButton("8");
    private JButton num9 = new JButton("9");
    private JButton num0 = new JButton("0");
    private JButton num00 = new JButton("00");
    private JButton numKomma = new JButton(",");
    private JButton enter = new JButton("\u2713");
    private JButton delete = new JButton("X");
    private JButton checkout = new JButton("CHECKOUT");
    private JPanel ziffernPanel = new JPanel();
    private GridLayout ziffernGridLayout = new GridLayout(5, 3, 5, 5);
    private GroupLayout ziffernBlockGroupLayout = new GroupLayout(this);
    private JPanel checkoutPanel = new JPanel();
    private GridLayout checkoutGridLayout = new GridLayout(1, 1, 5, 5);

    private static enum Mode {
        PRODUKTNUMMER, MENGE, RABATT
    };
    private static Mode mode = Mode.MENGE;

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
        preisField.setFont(new Font("Arial", 0, 24));
        preisField.setBackground(Color.WHITE);

        produktNummerLabel.setFont(new Font("Arial", 0, produktNummerLabel.getFont().getSize()));
        mengeLabel.setFont(new Font("Arial", 1, mengeLabel.getFont().getSize()));
        rabattLabel.setFont(new Font("Arial", 0, rabattLabel.getFont().getSize()));

        num1.setFont(new Font("Arial", 1, 30));
        num1.addActionListener(new ZiffernblockActionListener());
        num2.setFont(new Font("Arial", 1, 30));
        num2.addActionListener(new ZiffernblockActionListener());
        num3.setFont(new Font("Arial", 1, 30));
        num3.addActionListener(new ZiffernblockActionListener());
        num4.setFont(new Font("Arial", 1, 30));
        num4.addActionListener(new ZiffernblockActionListener());
        num5.setFont(new Font("Arial", 1, 30));
        num5.addActionListener(new ZiffernblockActionListener());
        num6.setFont(new Font("Arial", 1, 30));
        num6.addActionListener(new ZiffernblockActionListener());
        num7.setFont(new Font("Arial", 1, 30));
        num7.addActionListener(new ZiffernblockActionListener());
        num8.setFont(new Font("Arial", 1, 30));
        num8.addActionListener(new ZiffernblockActionListener());
        num9.setFont(new Font("Arial", 1, 30));
        num9.addActionListener(new ZiffernblockActionListener());
        num0.setFont(new Font("Arial", 1, 30));
        num0.addActionListener(new ZiffernblockActionListener());
        num00.setFont(new Font("Arial", 1, 30));
        num00.addActionListener(new ZiffernblockActionListener());
        numKomma.setFont(new Font("Arial", 1, 30));
        numKomma.addActionListener(new ZiffernblockActionListener());
        
        enter.setForeground(new java.awt.Color(0, 204, 51));
        enter.setFont(enter.getFont().deriveFont(34f));
        enter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                preisField.setText("");
            }
        });
        
        delete.setFont(new Font("Arial", 1, 30));
        delete.setForeground(new java.awt.Color(255, 51, 51));
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                preisField.setText("");
            }
        });
        
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

    public static void setModeProduktnummer() {
        mode = Mode.PRODUKTNUMMER;
        produktNummerLabel.setFont(new Font("Arial", 1, produktNummerLabel.getFont().getSize()));
        mengeLabel.setFont(new Font("Arial", 0, mengeLabel.getFont().getSize()));
        rabattLabel.setFont(new Font("Arial", 0, rabattLabel.getFont().getSize()));
    }

    public static void setModeMenge() {
        mode = Mode.MENGE;
        produktNummerLabel.setFont(new Font("Arial", 0, produktNummerLabel.getFont().getSize()));
        mengeLabel.setFont(new Font("Arial", 1, mengeLabel.getFont().getSize()));
        rabattLabel.setFont(new Font("Arial", 0, rabattLabel.getFont().getSize()));
    }

    public static void setModeRabatt() {
        mode = Mode.RABATT;
        produktNummerLabel.setFont(new Font("Arial", 0, produktNummerLabel.getFont().getSize()));
        mengeLabel.setFont(new Font("Arial", 0, mengeLabel.getFont().getSize()));
        rabattLabel.setFont(new Font("Arial", 1, rabattLabel.getFont().getSize()));
    }

    public static void addToPreisField(String newText) {
        if (mode == Mode.MENGE) {
            //if(artikel.getEinheit() == Artikel.Einheit.GEWICHT)
            if (!preisField.getText().contains(",")) {
                preisField.setText(preisField.getText() + newText);
                return;
            }
            if (preisField.getText().contains(",")) {
                if (!newText.contains(",")) {
                    String[] subStrings = preisField.getText().split(",");
                    if (subStrings.length > 1) {
                        if (!((subStrings[1].length() + newText.length()) > 3)) {
                            preisField.setText(preisField.getText() + newText);
                            return;
                        }
                        return;
                    }
                    preisField.setText(preisField.getText() + newText);
                    return;
                }
            }
        }
        if (mode == Mode.PRODUKTNUMMER) {
            if (!preisField.getText().contains(",") && !newText.contains(",")) {
                preisField.setText(preisField.getText() + newText);
            }
        }
        if (mode == Mode.RABATT) {
            if (!preisField.getText().contains(",") && !newText.contains(",") && (preisField.getText().length() < 2)) {
                preisField.setText(preisField.getText() + newText);
            }
        }
    }

}
