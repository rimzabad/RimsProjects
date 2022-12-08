package Home;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.RenderingHints;
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
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import DatabaseManagement.BddCmm;
import Front.HomePage;
import Front.Login;
import Utilities.AES;
import Utilities.TextPrompt;

public class Home extends JFrame {

	private JPanel profile, edit_profile;
	private int id;
	private JTextField fn, ln, us, pw;
	private JLabel welcome, avatar, firstname, lastname, username, password;
	private ResultSet rs;
	private JButton edit, back, addUser, delete;
	private BddCmm con;
	private Graphics2D g2;
	private Image image;

	public Home(BddCmm con) {
		this.con = con; 
		this.setTitle("Home");
		this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		this.setLayout(null);
		
		
		BufferedImage backg = null;
		try {
			backg = ImageIO.read(new File("Decoration/h2.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Color background = this.getBackground();

		Image dimg = backg.getScaledInstance(1550, 850, Image.SCALE_SMOOTH);
		ImageIcon imageIconb = new ImageIcon(dimg);
		setContentPane(new JLabel(imageIconb));

		try {
			rs = con.getInfo();
			if (rs != null) {
				firstname = new JLabel("First Name:	\t" + rs.getString("firstname"));
				lastname = new JLabel("Last Name:	\t" + rs.getString("lastname"));
				id = rs.getInt("architect_id");
				username = new JLabel("Username:	\t" + rs.getString("a_username"));
				password = new JLabel("Password:	\t" + rs.getString("a_password"));
			}

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		profile = new JPanel() {
			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);

				try {
					rs = con.getInfo();
					if (rs != null) {
						firstname.setText("First Name:	\t" + rs.getString("firstname"));
						lastname.setText("Last Name:	\t" + rs.getString("lastname"));
						id = rs.getInt("architect_id");
						username.setText("Username:	\t" + rs.getString("a_username"));
						password.setText("Password:	\t" + rs.getString("a_password"));
					}

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if (image == null) {
					profile.setBackground(Color.white);
					image = createImage(getSize().width, getSize().height);
					g2 = (Graphics2D) image.getGraphics();
					g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
					g2.setPaint(Color.white);
					g2.fillRect(0, 0, getSize().width, getSize().height);
					repaint();
				}
				g.drawImage(image, 0, 0, null);
			}
		};

		profile.setBackground(Color.white);
		profile.setOpaque(true);
		profile.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Profile"));
		profile.setBounds(200, 150, 750, 480);
		profile.setLayout(new GridLayout(1, 2, 25, 35));
		profile.setPreferredSize(new Dimension(480, 400));
		profile.setMinimumSize(new Dimension(480, 400));
		this.add(profile);

		try {
			welcome = new JLabel("Welcome " + rs.getString("firstname").toUpperCase());
			welcome.setFont(new Font("Mongolian Baiti", Font.BOLD, 35));
			//welcome.setForeground(new Color(176,139,123));
			welcome.setForeground(new Color(134,56,46));
			welcome.setBounds(250, 60, 400, 100);
			this.add(welcome);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		BoxLayout layout1 = new BoxLayout(profile, BoxLayout.X_AXIS);
		profile.setLayout(layout1);
		
		ImageIcon imageIcon = new ImageIcon("Decoration/icon7.jpg");
		avatar = new JLabel(imageIcon);
		avatar.setBounds(2, 25, 100, 80);
		profile.add(avatar);

		JPanel c_profile = new JPanel();
		c_profile.setLayout(null);
		c_profile.setBackground(Color.white);
		firstname.setFont(new Font("Mongolian Baiti", Font.BOLD, 15));
		firstname.setBounds(20, 80, 300, 80);
		c_profile.add(firstname);
		lastname.setFont(new Font("Mongolian Baiti", Font.BOLD, 15));
		lastname.setBounds(20, 160, 300, 80);
		c_profile.add(lastname);
		username.setFont(new Font("Mongolian Baiti", Font.BOLD, 15));
		username.setBounds(20, 240, 300, 80);
		c_profile.add(username);
		password.setFont(new Font("Mongolian Baiti", Font.BOLD, 15));
		password.setBounds(20, 320, 300, 80);
		c_profile.add(password);

	
		profile.add(c_profile);

		edit = new JButton("Edit Profile");
		edit.setBounds(1050, 240, 170, 50);
		edit.setFont(new Font("Calisto MT", Font.BOLD, 13));
		edit.setForeground(new Color(134,56,46));
		edit.setBackground(Color.white);
		this.add(edit);
		edit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JButton Save = new JButton("Save");
				Save.setBounds(20, 38, 299, 40);
				Save.setBackground(Color.white);
				Save.setForeground(Color.black);
				profile.remove(c_profile);

				edit_profile = new JPanel();
				edit_profile.setBackground(Color.white);
				edit_profile.setVisible(true);
				edit_profile.setLayout(null);
				profile.add(edit_profile);

				edit.hide();
				try {
					fn = new JTextField("");
					fn.setBounds(20, 80, 300, 80);
					TextPrompt f = new TextPrompt(rs.getString("firstname"), fn);
					f.setForeground(Color.DARK_GRAY);
					f.changeAlpha(0.5f);
					f.changeStyle(Font.BOLD);
					f.setIcon(null);
					edit_profile.add(fn);
					ln = new JTextField("");
					ln.setBounds(20, 160, 300, 80);
					TextPrompt l = new TextPrompt(rs.getString("lastname"), ln);
					l.setForeground(Color.DARK_GRAY);
					l.changeAlpha(0.5f);
					l.changeStyle(Font.BOLD);
					l.setIcon(null);
					edit_profile.add(ln);
					us = new JTextField("");
					us.setBounds(20, 240, 300, 80);
					TextPrompt u = new TextPrompt(rs.getString("a_username"), us);
					u.setForeground(Color.DARK_GRAY);
					u.changeAlpha(0.5f);
					u.changeStyle(Font.BOLD);
					u.setIcon(null);
					edit_profile.add(us);
					pw = new JTextField("");
					pw.setBounds(20, 320, 300, 80);
					TextPrompt p = new TextPrompt(rs.getString("a_password"), pw);
					p.setForeground(Color.DARK_GRAY);
					p.changeAlpha(0.5f);
					p.changeStyle(Font.BOLD);
					p.setIcon(null);
					edit_profile.add(pw);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				edit_profile.add(Save);
				Save.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						String newF = fn.getText(), newL = ln.getText(), newU = us.getText(), newP = pw.getText();
						profile.remove(edit_profile);
						try {
							if (newF.compareTo("") == 0) {
								newF = rs.getString("firstname");
							}
							if (newL.compareTo("") == 0) {
								newL = rs.getString("lastname");
							}
						 	if (newU.compareTo("") == 0) {
								newU = rs.getString("a_username");
							}
							if (newP.compareTo("") == 0) {
								newP =AES.decrypt(rs.getString("a_password"), con.getSecretKey());
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						con.updateInfo(newF, newL, newU, newP);
						rs = con.getInfo();
						profile.revalidate();

						profile.repaint();
						((JFrame)SwingUtilities.getRoot(profile)).repaint();
						profile.add(c_profile);
						edit.show();
					}
				});

			}
		});

		addUser = new JButton("Add An Architect");
        
		addUser.setBounds(1050, 310, 170, 50);
		addUser.setBackground(Color.white);
		//addUser.setFont(new Font("Mongolian Baiti", Font.PLAIN, 15));
		addUser.setFont(new Font("Calisto MT", Font.BOLD, 13));
		addUser.setForeground(new Color(134,56,46));
		this.add(addUser);
		addUser.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFrame f = new JFrame();
				f.setBounds(635, 230, 320, 380);
				f.setLayout(null);
				f.setVisible(true);
				edit_profile = new JPanel();
				edit_profile.setBounds(30, 20, 250, 250);
				edit_profile.setBackground(Color.white);
				edit_profile.setVisible(true);
				edit_profile.setLayout(null);
				fn = new JTextField("");
				fn.setBounds(25, 20, 200, 40);
				TextPrompt f1 = new TextPrompt("First Name", fn);
				f1.setForeground(Color.DARK_GRAY);
				f1.changeAlpha(0.5f);
				f1.changeStyle(Font.BOLD);
				f1.setIcon(null);
				edit_profile.add(fn);
				ln = new JTextField("");
				ln.setBounds(25, 70, 200, 40);
				TextPrompt l = new TextPrompt("Last Name", ln);
				l.setForeground(Color.DARK_GRAY);
				l.changeAlpha(0.5f);
				l.changeStyle(Font.BOLD);
				l.setIcon(null);
				edit_profile.add(ln);
				us = new JTextField("");
				us.setBounds(25, 120, 200, 40);
				TextPrompt u = new TextPrompt("Username", us);
				u.setForeground(Color.DARK_GRAY);
				u.changeAlpha(0.5f);
				u.changeStyle(Font.BOLD);
				u.setIcon(null);
				edit_profile.add(us);
				pw = new JTextField("");
				pw.setBounds(25, 170, 200, 40);
				TextPrompt p = new TextPrompt("Password", pw);
				p.setForeground(Color.DARK_GRAY);
				p.changeAlpha(0.5f);
				p.changeStyle(Font.BOLD);
				p.setIcon(null);
				edit_profile.add(pw);

				JButton add = new JButton("ADD");
				add.setForeground(Color.white);
				add.setBackground(new Color(134,56,46));
				add.setFont(new Font("Calisto MT", Font.BOLD, 13));
				add.setBounds(30, 270, 250, 50);
				add.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						String newF = fn.getText(), newL = ln.getText(), newU = us.getText(), newP = pw.getText();
						if (newF.compareTo("") == 0 || newL.compareTo("") == 0 || newU.compareTo("") == 0
								|| newP.compareTo("") == 0) {
							JOptionPane.showMessageDialog(profile, "Wrong Input(s)");
						} else {
							con.addArchitect(newF, newL, newU, newP);
							JOptionPane.showMessageDialog(profile, "Success!");
							f.dispose();
						}
					}

				});
				
				f.add(edit_profile);
				f.add(add);
				f.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			}
		});
		delete = new JButton("Delete");
		delete.setBounds(1050, 380, 170, 50);
		delete.setBackground(Color.white);
		delete.setFont(new Font("Calisto MT", Font.BOLD, 13));
		delete.setForeground(new Color(134,56,46));
		this.add(delete);
		delete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				con.deleteArchitect();
				JFrame f = (JFrame) SwingUtilities.getRoot((JButton) e.getSource());
				new Login();
				f.dispose();
			}
			
		});

		back = new JButton("Go Back");
		back.setBounds(1050, 450, 170, 50);
		back.setBackground(Color.white);
		back.setForeground(new Color(134,56,46));
		back.setFont(new Font("Calisto MT", Font.BOLD, 13));
		this.add(back);
		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFrame f = (JFrame) SwingUtilities.getRoot((JButton) e.getSource());
				new HomePage(con);
				f.dispose();
			}
		});

		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowListener() {
			@Override
			public void windowOpened(WindowEvent e) {
			}

			@Override
			public void windowClosing(WindowEvent we) {
				String ObjButtons[] = { "Yes", "No" };
				int PromptResult = JOptionPane.showOptionDialog(null, "Are you sure you want to exit?", "",
						JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, ObjButtons, ObjButtons[1]);
				if (PromptResult == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}

			@Override
			public void windowClosed(WindowEvent e) {
			}

			@Override
			public void windowIconified(WindowEvent e) {
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
			}

			@Override
			public void windowActivated(WindowEvent e) {
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
			}
		});
		this.setResizable(false);
		this.setVisible(true);

	}

}
