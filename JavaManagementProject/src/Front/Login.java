package Front;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import DatabaseManagement.BddCmm;
import Utilities.TextPrompt;

public class Login extends JFrame {
	JLabel error;

	public Login() {

		this.setTitle("Login");
		
		this.setVisible(true);
		this.setMinimumSize(new Dimension(1200,700)); 
		this.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 0, 60));
		panel.setBounds(435, 140, 350, 400);
		panel.setLayout(null);
		this.add(panel);

		JTextField nom = new JTextField("");
		nom.setBounds(55, 50, 240, 40);
		panel.add(nom);

		TextPrompt tp = new TextPrompt("Username", nom);
		tp.setForeground(Color.DARK_GRAY);
		tp.changeAlpha(0.5f);
		tp.changeStyle(Font.BOLD);
		tp.setIcon(null);

		/*
		 * JLabel icon1 = new JLabel(); icon1.setBounds(617, 350, 45, 40);
		 * icon1.setIcon(new ImageIcon("icon.png")); this.add(icon1);
		 */

		JPasswordField pass = new JPasswordField();
		pass.setBounds(55, 120, 240, 40);
		TextPrompt tpp = new TextPrompt("Password", pass);
		tpp.setForeground(Color.DARK_GRAY);
		tpp.changeAlpha(0.5f);
		tpp.changeStyle(Font.BOLD);
		tpp.setIcon(null);
		panel.add(pass);

		JCheckBox checkpass = new JCheckBox("Show Password");
		checkpass.setBounds(607, 320, 200, 45);
	//	checkpass.setBounds(590, 310, 200, 45);
		checkpass.setForeground(Color.white);
		checkpass.setOpaque(false);
		checkpass.setContentAreaFilled(false);
		checkpass.setHorizontalTextPosition(SwingConstants.LEFT);
		checkpass.setBorderPainted(false);
		this.add(checkpass);
		char def = pass.getEchoChar();
		checkpass.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				if (checkpass.isSelected()) {
					pass.setEchoChar((char) 0);
				} else {
					pass.setEchoChar(def);
				}
			}
		});

		JButton button = new JButton("LOGIN");
		button.setBounds(490, 390, 240, 45);
		button.setBackground(Color.white);
		//panel.add(button);
		this.add(button);
		
		

		error = new JLabel("Wrong Credentials!");
		error.setForeground(Color.red);
		error.setBounds(490, 350, 200, 45);
		add(error);
		error.hide();
	

		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				BddCmm con = new BddCmm();
				con.connectBdd();
				String password = String.valueOf(pass.getPassword());
				if(password == null || nom.getText()== "") {
					error.show();
				}
				if (con.authenticate(nom.getText(), password)) {
					error.hide();
					JFrame f= (JFrame) SwingUtilities.getRoot(panel);
					new HomePage(con);
					f.dispose();
				} else {
					error.show();
				}
			}
		});

		setLayout(new BorderLayout());
		JLabel background = new JLabel(new ImageIcon("Decoration/login.png"));
		add(background);
		background.setLayout(new FlowLayout());

		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowListener() {
			@Override
			public void windowOpened(WindowEvent e) {}

			 @Override
			    public void windowClosing(WindowEvent we)
			    { 
			        String ObjButtons[] = {"Yes","No"};
			        int PromptResult = JOptionPane.showOptionDialog(null,"Are you sure you want to exit?","",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE,null,ObjButtons,ObjButtons[1]);
			        if(PromptResult==JOptionPane.YES_OPTION)
			        {
			            System.exit(0);
			        }
			    }
			@Override
			public void windowClosed(WindowEvent e) {}

			@Override
			public void windowIconified(WindowEvent e) {}
			
			@Override
			public void windowDeiconified(WindowEvent e) {}

			@Override
			public void windowActivated(WindowEvent e) {}

			@Override
			public void windowDeactivated(WindowEvent e) {}
		});
		this.setVisible(true);

	}
}