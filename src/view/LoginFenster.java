/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import Controller.Authentication;
import Controller.DBVerbindung;
import Controller.Languagemanager;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author ChriPo97 Klasse zur Erstellung eines Dialog zum Login
 */
public class LoginFenster extends JFrame {

    private JPanel dialogPanel;
    private JPanel loginPanel;
    private BoxLayout dialogLayout;
    private GroupLayout loginLayout;
    private JTextField userNameField = new JTextField();
    private JPasswordField passwordField = new JPasswordField();
    private JLabel userNameLabel = new JLabel(Languagemanager.getProperty("LoginDialog.userNameLabel"));
    private JLabel passwordLabel = new JLabel(Languagemanager.getProperty("LoginDialog.passwordLabel"));
    private JButton loginButton = new JButton(Languagemanager.getProperty("LoginDialog.loginButton"));

    public LoginFenster() {
        initComponents();
        dialogPanel.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));
        this.setTitle(Languagemanager.getProperty("LoginDialog.titel"));
        this.setMinimumSize(new Dimension(300, 125));
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void initComponents() {
        dialogPanel = new JPanel();
        dialogLayout = new BoxLayout(dialogPanel, BoxLayout.Y_AXIS);
        dialogPanel.setLayout(dialogLayout);
        
        loginPanel = new JPanel();
        loginLayout = new GroupLayout(loginPanel);
        loginLayout.setAutoCreateGaps(true);
        loginPanel.setLayout(loginLayout);
        loginLayout.setHorizontalGroup(
                loginLayout.createParallelGroup()
                        .addGroup(loginLayout.createSequentialGroup()
                                .addComponent(userNameLabel)
                                .addComponent(userNameField)
                        )
                        .addGroup(loginLayout.createSequentialGroup()
                                .addComponent(passwordLabel)
                                .addComponent(passwordField)
                        )
        );
        loginLayout.setVerticalGroup(
                loginLayout.createSequentialGroup()
                        .addGroup(loginLayout.createParallelGroup()
                                .addComponent(userNameLabel)
                                .addComponent(userNameField)
                        )
                        .addGroup(loginLayout.createParallelGroup()
                                .addComponent(passwordLabel)
                                .addComponent(passwordField)
                        )
        );
        loginLayout.linkSize(SwingConstants.HORIZONTAL, userNameLabel, passwordLabel);
        loginLayout.linkSize(SwingConstants.VERTICAL, userNameField, passwordField);

        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Authentication.login(userNameField.getText(), String.valueOf(passwordField.getPassword()))) {
                    DBVerbindung.verbinden();
                    Hauptfenster hf = new Hauptfenster();
                    hf.setVisible(true);
                    setVisible(false);
                }
                else {
                    JOptionPane.showMessageDialog(null, Languagemanager.getProperty("LoginDialog.error"));
                    userNameField.setText("");
                    passwordField.setText("");
                }
            }
        });
        dialogPanel.add(loginPanel);
        dialogPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        dialogPanel.add(loginButton);
        this.add(dialogPanel);
    }

}
