/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import Controller.Einkaufsmanager;
import Controller.Languagemanager;
import Controller.Propertymanager;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.PrintException;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import model.Artikel;
import model.Kassenbon;

/**
 *
 * @author ChriPo97 Klasse für das Panel des rechten Teils des Hauptfensters mit
 * dem Ziffernblock und einigen Buttons.
 */
public class Ziffernblock extends JPanel {

    private static JTextField preisField = new JTextField();
    private static JLabel produktNummerLabel = new JLabel(Languagemanager.getProperty("Ziffernblock.produktNummerLabel"));
    private static JLabel mengeLabel = new JLabel(Languagemanager.getProperty("Ziffernblock.mengeLabel"));
    private static JLabel rabattLabel = new JLabel(Languagemanager.getProperty("Ziffernblock.rabattLabel"));
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
    private JButton enter = new JButton("\u2713");
    private JButton delete = new JButton("X");
    private JButton checkout = new JButton(Languagemanager.getProperty("Ziffernblock.checkout"));
    private JPanel ziffernPanel = new JPanel();
    private GridLayout ziffernGridLayout = new GridLayout(5, 3, 5, 5);
    private GroupLayout ziffernBlockGroupLayout = new GroupLayout(this);
    private JPanel checkoutPanel = new JPanel();
    private GridLayout checkoutGridLayout = new GridLayout(1, 1, 5, 5);

    //Enum Feld für den Modus der Eingabe in den Ziffernblock
    private static enum Mode {
        PRODUKTNUMMER, MENGE, RABATT
    };

    //Start-Wert für den Modus ist MENGE
    private static Mode mode = Mode.MENGE;

    public Ziffernblock() {
        this.setBorder(new EmptyBorder(new Insets(0, 10, 10, 10)));
        initComponents();
    }

    private void initComponents() {
        ziffernBlockGroupLayout.setAutoCreateGaps(true);
        this.setLayout(ziffernBlockGroupLayout);

        //Formatieren des preisFields
        preisField.setEditable(false);
        preisField.setPreferredSize(new Dimension(Short.MAX_VALUE, 70));
        preisField.setMaximumSize(new Dimension(Short.MAX_VALUE, 70));
        preisField.setFont(new Font("Arial", 0, 24));
        preisField.setBackground(Color.WHITE);

        //Formatieren der Modus Label
        produktNummerLabel.setFont(new Font("Arial", 0, produktNummerLabel.getFont().getSize()));
        mengeLabel.setFont(new Font("Arial", 1, mengeLabel.getFont().getSize()));
        rabattLabel.setFont(new Font("Arial", 0, rabattLabel.getFont().getSize()));

        //Formatieren der Ziffernfelder
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

        //Belegen der Buttons mit ActionListenern
        enter.setForeground(new java.awt.Color(0, 204, 51));
        enter.setFont(enter.getFont().deriveFont(34f));
        enter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!preisField.getText().equals("")) {
                    if (mode == Mode.PRODUKTNUMMER) {
                        Warenliste.addArtikel(Integer.valueOf(preisField.getText()));
                    }
                    if (mode == Mode.RABATT) {
                        Warenliste.discountArtikel(Integer.valueOf(preisField.getText()));
                    }
                    if (mode == Mode.MENGE) {
                        Warenliste.changeMenge(Integer.valueOf(preisField.getText()));
                    }
                    preisField.setText("");
                }
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
        checkout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!Einkaufsmanager.getEinkaufskorb().isEmpty()) {
                    Object[] optionsCheckout = {Languagemanager.getProperty("Ziffernblock.optionsCheckout.Ja"),
                        Languagemanager.getProperty("Ziffernblock.optionsCheckout.Nein")};
                    int selectedOptionCheckout = JOptionPane.showOptionDialog(null,
                            Languagemanager.getProperty("Ziffernblock.selectedOptionCheckout.text"),
                            Languagemanager.getProperty("Ziffernblock.selectedOptionCheckout.titel"),
                            JOptionPane.DEFAULT_OPTION,
                            JOptionPane.INFORMATION_MESSAGE,
                            null, optionsCheckout, optionsCheckout[0]);

                    if (selectedOptionCheckout == 0) {
                        Kassenbon bon = new Kassenbon(Einkaufsmanager.getEinkaufskorb());
                        try {
                            bon.saveBon();
                        } catch (IOException ex) {
                            JOptionPane.showMessageDialog(null, Languagemanager.getProperty("Ziffernblock.selectedOptionCheckout.error.text"),
                                    Languagemanager.getProperty("Ziffernblock.selectedOptionCheckout.error.titel"), JOptionPane.ERROR_MESSAGE);
                        }

                        Object[] optionsBon = {Languagemanager.getProperty("Ziffernblock.optionsBon.Ja"),
                            Languagemanager.getProperty("Ziffernblock.optionsBon.Nein")};
                        int selectedOptionBon = JOptionPane.showOptionDialog(null,
                                Languagemanager.getProperty("Ziffernblock.selectedOptionBon.text"),
                                Languagemanager.getProperty("Ziffernblock.selectedOptionBon.titel"),
                                JOptionPane.DEFAULT_OPTION,
                                JOptionPane.INFORMATION_MESSAGE,
                                null, optionsBon, optionsBon[0]);
                        if (selectedOptionBon == 0) {
                            try {
                                bon.printBon();
                            } catch (FileNotFoundException | PrintException ex) {
                                Logger.getLogger(Ziffernblock.class.getName()).log(Level.SEVERE, null, ex);
                                JOptionPane.showMessageDialog(null, Languagemanager.getProperty("Ziffernblock.selectedOptionBon.error.text"),
                                        Languagemanager.getProperty("Ziffernblock.selectedOptionBon.error.titel"), JOptionPane.ERROR_MESSAGE);
                            }
                        }
                        Einkaufsmanager.stornierenEinkauf();
                        Warenliste.clearTable();
                        Ziffernblock.setModeMenge();
                    }
                }
            }
        }
        );

        //Hinzufügen der Komponenten
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

        ziffernPanel.add(Box.createRigidArea(new Dimension(0, delete.getMinimumSize().height * 2)));
        ziffernPanel.add(delete);

        ziffernPanel.add(Box.createRigidArea(new Dimension(0, delete.getMinimumSize().height * 2)));
        ziffernPanel.add(enter);

        checkoutPanel.setPreferredSize(
                new Dimension(Short.MAX_VALUE, 120));
        checkoutPanel.setLayout(checkoutGridLayout);

        checkoutPanel.add(checkout);

        //Erstellen des GroupLayouts
        ziffernBlockGroupLayout.setHorizontalGroup(
                ziffernBlockGroupLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addGroup(ziffernBlockGroupLayout.createSequentialGroup()
                                .addComponent(preisField)
                        )
                        .addGroup(ziffernBlockGroupLayout.createSequentialGroup()
                                .addComponent(produktNummerLabel)
                                .addGap(20)
                                .addComponent(mengeLabel)
                                .addGap(20)
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

    //Funktion zum Setzen des Modus auf PRODUKTNUMMER
    public static void setModeProduktnummer() {
        preisField.setText("");
        mode = Mode.PRODUKTNUMMER;
        produktNummerLabel.setFont(new Font("Arial", 1, produktNummerLabel.getFont().getSize()));
        mengeLabel.setFont(new Font("Arial", 0, mengeLabel.getFont().getSize()));
        rabattLabel.setFont(new Font("Arial", 0, rabattLabel.getFont().getSize()));
    }

    //Funktion zum Setzen des Modus auf MENGE
    public static void setModeMenge() {
        preisField.setText("");
        mode = Mode.MENGE;
        produktNummerLabel.setFont(new Font("Arial", 0, produktNummerLabel.getFont().getSize()));
        mengeLabel.setFont(new Font("Arial", 1, mengeLabel.getFont().getSize()));
        rabattLabel.setFont(new Font("Arial", 0, rabattLabel.getFont().getSize()));
    }

    //Funktion zum Setzen des Modus auf RABATT
    public static void setModeRabatt() {
        preisField.setText("");
        mode = Mode.RABATT;
        produktNummerLabel.setFont(new Font("Arial", 0, produktNummerLabel.getFont().getSize()));
        mengeLabel.setFont(new Font("Arial", 0, mengeLabel.getFont().getSize()));
        rabattLabel.setFont(new Font("Arial", 1, rabattLabel.getFont().getSize()));
    }

    //Funktion zum Hinzufügen eines Zahlenwertes zum preisField
    public static void addToPreisField(String newText) {
        if (mode == Mode.MENGE || mode == Mode.PRODUKTNUMMER) {
            preisField.setText(preisField.getText() + newText);
        }
        if (mode == Mode.RABATT) {
            if (Integer.valueOf(preisField.getText() + newText) <= 100) {
                preisField.setText(preisField.getText() + newText);
            }
        }
    }

}
