package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class LoginScreen extends JPanel {
	
	private JLabel usernameLabel;
	private JLabel passwordLabel;
	private JTextField usernameField;
	private JTextField passwordField;
	private JButton loginBtn;

	public LoginScreen(){
		usernameLabel = new JLabel("Username: ");
		passwordLabel = new JLabel("Password: ");
		usernameField = new JTextField();
		passwordField = new JTextField();
		loginBtn = new JButton("Login");
		
		JPanel usernamePanel = new JPanel();
		usernamePanel.setLayout(new BorderLayout());
		usernamePanel.add(usernameLabel, BorderLayout.WEST);
		usernamePanel.add(usernameField, BorderLayout.CENTER);
		
		JPanel passwordPanel = new JPanel();
		passwordPanel.setLayout(new BorderLayout());
		passwordPanel.add(passwordLabel, BorderLayout.WEST);
		passwordPanel.add(passwordField, BorderLayout.CENTER);
		
		setLayout(new BorderLayout());
		add(usernamePanel, BorderLayout.NORTH);
		add(passwordPanel, BorderLayout.CENTER);
		add(loginBtn, BorderLayout.SOUTH);
		
		loginBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
			}
		});
		
		
		setVisible(true);
	}
}
