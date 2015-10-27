package windows.login;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import jdbc.DbMetaData;
import mysql.jdbc.Mysql;
import mysql.jdbc.MysqlDbConnectionInfo;
import windows.main.Main;

import javax.swing.DropMode;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

public class Connection {

	private JFrame frame;
	private JTextField txtLocalhost;
	private JTextField txtRoot;
	private JPasswordField passwordField;
	private JButton btnConnection;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Connection window = new Connection();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Connection() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Connection");
		frame.setBounds(100, 100, 280, 280);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblDbHost = new JLabel("DB Host");
		lblDbHost.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
		
		txtLocalhost = new JTextField();
		txtLocalhost.setText("localhost");
		txtLocalhost.setColumns(50);
		
		JLabel lblDbAccount = new JLabel("DB Account");
		lblDbAccount.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
		
		txtRoot = new JTextField();
		txtRoot.setText("root");
		txtRoot.setColumns(50);
		
		JLabel lblDbPassword = new JLabel("DB Password");
		lblDbPassword.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
		
		passwordField = new JPasswordField();
		passwordField.setColumns(50);
		
		btnConnection = new JButton("Connection");
		btnConnection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (btnConnection.equals(arg0.getSource())){
					btnConnection.setEnabled(false);
					
					String hosting  = txtLocalhost.getText();
					String account  = txtRoot.getText();
					String password = new String(passwordField.getPassword());
					
					MysqlDbConnectionInfo info = new MysqlDbConnectionInfo();
					
					info.setHosting(hosting);
					info.setAccount(account);
					info.setPassword(password);
					
					try {
						DbMetaData metaData = new DbMetaData(info);
						
						Main mailGUI = new Main(metaData);
						mailGUI.setVisible(true);
						//frame.setVisible(false);
						frame.dispose();
						//frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
					} catch (Exception e) {
						JOptionPane.showMessageDialog(frame, "Con't Connection To Database", "Error", JOptionPane.ERROR_MESSAGE);
					}
					
					btnConnection.setEnabled(true);
				}
			}
		});
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lblDbHost)
						.addComponent(lblDbPassword, GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
						.addComponent(txtRoot, 0, 0, Short.MAX_VALUE)
						.addComponent(txtLocalhost, 0, 0, Short.MAX_VALUE)
						.addComponent(lblDbAccount, GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
						.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 205, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(29, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(167, Short.MAX_VALUE)
					.addComponent(btnConnection)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblDbHost)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtLocalhost, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblDbAccount, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtRoot, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblDbPassword, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
					.addComponent(btnConnection)
					.addContainerGap())
		);
		
		frame.getContentPane().setLayout(groupLayout);
	}
}
