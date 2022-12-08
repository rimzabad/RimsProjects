package Front;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import DatabaseManagement.BddCmm;
import Home.Home;
import Projects.AllProjects;
import Projects.NewProject;

public class HomePage extends JFrame implements MouseListener, ActionListener {

	private JButton home, cur, proj, his; 
	private BddCmm con;
	
	public HomePage(BddCmm con) {
		this.setTitle("Architectural Management System");
		this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		this.setLayout(null);
		// this.setUndecorated(true);
		this.con =con;

		BufferedImage img = null;
		try {
			img = ImageIO.read(new File("Decoration/homepage.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		Image dimg = img.getScaledInstance(1550, 850, Image.SCALE_SMOOTH);
		ImageIcon imageIcon = new ImageIcon(dimg);
		setContentPane(new JLabel(imageIcon));

		home = new JButton("HOME");
		home.setBounds(793, 50, 120, 40);
		// home.setBackground(Color.white);
		home.setForeground(Color.BLACK);
		this.add(home);
		home.setOpaque(false);
		home.setContentAreaFilled(false);
		home.setBorderPainted(false);

		home.addActionListener(this);
		home.addMouseListener(this);

		proj = new JButton("NEW PROJECT");
		proj.setBounds(902, 50, 150, 40);
		proj.setBackground(Color.white);
		proj.setForeground(Color.black);
		proj.setOpaque(false);
		proj.setContentAreaFilled(false);
		proj.setBorderPainted(false);
		this.add(proj);
		
		proj.addActionListener(this);
		proj.addMouseListener(this);

		
		cur = new JButton("CURRENT PROJECTS");
		cur.setBounds(1050, 50, 190, 40);
		// cur.setBackground(Color.white);
		cur.setForeground(Color.black);
		cur.setOpaque(false);
		cur.setContentAreaFilled(false);
		cur.setBorderPainted(false);
		this.add(cur);
		
		cur.addActionListener(this);
		cur.addMouseListener(this);

		his = new JButton("LOGOUT");
		his.setBounds(1230, 50, 120, 40);
		his.setBackground(Color.white);
		his.setForeground(Color.black);
		his.setOpaque(false);
		his.setContentAreaFilled(false);
		his.setBorderPainted(false);
		this.add(his);
		
		his.addActionListener(this);
		his.addMouseListener(this);


		this.setLocationRelativeTo(null);
		
		this.setVisible(true);
		this.setResizable(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String choice = String.valueOf(((JButton)e.getSource()).getText());
		System.out.print(choice);
		System.out.println(choice.equals(cur.getText()));
		if(choice.compareTo(home.getText())==0) {
			JFrame f= (JFrame) SwingUtilities.getRoot((JButton)e.getSource());
			new Home(con);
			f.dispose();
			
		}
		else if(choice.equals(proj.getText())) {
			JFrame p= (JFrame) SwingUtilities.getRoot((JButton)e.getSource());
			new NewProject(con);
			p.dispose();
		}
		else if(choice.compareTo(cur.getText())==0) {
			JFrame p= (JFrame) SwingUtilities.getRoot((JButton)e.getSource());
			new AllProjects(con);
			p.dispose();
		}
		else if(choice.compareTo(his.getText())==0) {
			con.disconnectBdd();
			JFrame p= (JFrame) SwingUtilities.getRoot((JButton)e.getSource());
			new Login();
			p.dispose();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {}
	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		JButton b = (JButton) e.getSource();
		b.setFont(new Font("Modern No. 20", Font.BOLD, 15));
		b.setForeground(Color.WHITE);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		JButton b = (JButton) e.getSource();
		b.setFont(new Font("Calisto MT", Font.BOLD, 12));
		b.setForeground(Color.black);
	}

}
