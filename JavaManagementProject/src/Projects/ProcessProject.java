package Projects;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;

import org.apache.commons.io.FileUtils;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfDocument;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfTemplate;
import com.lowagie.text.pdf.PdfWriter;
import com.toedter.calendar.JDateChooser;

import DatabaseManagement.BddCmm;
import Front.HomePage;

public class ProcessProject extends JFrame {
	private BddCmm con;
	private Project project;
	private JPanel cards;
	private JPanel details;
	private Graphics2D g2;
	private Image image;
	private JLabel name, cost, status, client, dueDate, location, matName, price, quantity, total;
	final static JLabel detailPANEL = new JLabel("Details");
	final static JLabel apPANEL = new JLabel("Avant-Projet");
	final static JLabel tablePANEL = new JLabel("For Tender");
	final static JLabel fcPANEL = new JLabel("For Construction");
	private JButton delButton;

	final static int extraWindowWidth = 100;

	public ProcessProject(BddCmm con, Project p) throws HeadlessException {
		this.con = con;
		this.project = p;

		this.setTitle(""); 
		this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		this.setLayout(null);

		status = new JLabel("Status: " + p.getStatus());
		if (p.getStatus().compareTo("Finished") == 0) {
			status.setForeground(Color.green);
			status.setFont(new Font("Calisto MT", Font.BOLD, 30));
		} else {
			status.setForeground(Color.red);
			status.setFont(new Font("Calisto MT", Font.BOLD, 30));
		}
		status.setBounds(100, 25, 200, 30);

		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.setBounds(100, 100, 1200, 600);
		tabbedPane.setVisible(true);

		BufferedImage backg = null;
		try {
			backg = ImageIO.read(new File("Decoration/back.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		Image dimg = backg.getScaledInstance(1500, 900, Image.SCALE_SMOOTH);
		ImageIcon imageIconb = new ImageIcon(dimg);
		setContentPane(new JLabel(imageIconb));

		Color background = this.getBackground();

		details = new JPanel() {
			@Override
			public void paintComponent(Graphics g) {
				name.setText("Name: " + p.getName());
				cost.setText("Cost: " + p.getCost());
				client.setText("Client: " + p.getClientName());
				dueDate.setText("Due Date: " + p.getSubDate());
				location.setText("Location: " + p.getlocation());
				if (image == null) {
					details.setBackground(Color.white);
					image = createImage(getSize().width, getSize().height);
					g2 = (Graphics2D) image.getGraphics();
					g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
					g2.setPaint(background);
					g2.fillRect(0, 0, getSize().width, getSize().height);
					repaint();
				}
				g.drawImage(image, 0, 0, null);
			}
		};

		details.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		JLabel edit = new JLabel(new ImageIcon("decoration/edit.png"));

		name = new JLabel("Name: " + p.getName());
		name.setFont(new Font("Calisto MT", Font.BOLD, 27));
		cost = new JLabel("Cost: " + p.getCost());
		cost.setFont(new Font("Calisto MT", Font.BOLD, 27));
		client = new JLabel("Client: " + p.getClientName());
		client.setFont(new Font("Calisto MT", Font.BOLD, 27));
		dueDate = new JLabel("Due Date: " + p.getSubDate());
		dueDate.setFont(new Font("Calisto MT", Font.BOLD, 27));
		location = new JLabel("Location: " + p.getlocation());
		location.setFont(new Font("Calisto MT", Font.BOLD, 27));
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(5, 5, 0, 0);
		gbc.weightx = 1;
		gbc.weighty = 0.5;
		gbc.anchor = GridBagConstraints.CENTER;
		details.add(name, gbc);

		gbc.gridx = 0;
		gbc.gridy = 5;
		details.add(dueDate, gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		details.add(edit, gbc);

		gbc.gridx = 0;
		gbc.gridy = 2;

		details.add(client, gbc);

		gbc.gridx = 0;
		gbc.gridy = 3;

		details.add(cost, gbc);

		gbc.gridx = 0;
		gbc.gridy = 4;
		details.add(location, gbc);

		AvantProjet avantProjet = new AvantProjet(p, con);
		ForTender forTender = new ForTender(p, con);
		ForConstruction forConstruction = new ForConstruction(p,con);

		tabbedPane.add(details);
		tabbedPane.add(avantProjet);
		tabbedPane.add(forTender);
		tabbedPane.add(forConstruction);

		edit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent e) {
				// TODO Auto-generated method stub

				tabbedPane.removeTabAt(0);

				JPanel temp = new JPanel();

				tabbedPane.add(temp, 0);
				tabbedPane.setTabComponentAt(0, detailPANEL);
				
				tabbedPane.setSelectedIndex(0);

				JTextField fName = new JTextField();
				JTextField fCost = new JTextField();
				JTextField fClient = new JTextField();
				JTextField fLocation = new JTextField();
				JDateChooser subdate = new JDateChooser();

				JLabel edit2 = new JLabel(new ImageIcon("decoration/edit.png"));
				temp.setLayout(null);

				temp.add(fName);
				temp.add(fCost);
				temp.add(edit2);
				temp.add(fClient);
				temp.add(fLocation);
				temp.add(subdate);

				fName.setBackground(background);
				fCost.setBackground(background);
				fClient.setBackground(background);
				fLocation.setBackground(background);
				subdate.setBackground(background);

				fName.setBounds(460, 50, 300, 50);
				edit2.setBounds(460, 120, 300, 50);
				fCost.setBounds(460, 200, 300, 50);
				fClient.setBounds(460, 270, 300, 50);
				fLocation.setBounds(460, 340, 300, 50);
				subdate.setBounds(460, 410, 300, 50);

				fName.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Name"));
				fCost.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Cost"));
				fClient.setBorder(
						BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Client"));
				fLocation.setBorder(
						BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Location"));
				subdate.setBorder(
						BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Date"));

				edit2.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(java.awt.event.MouseEvent e) {
						// TODO Auto-generated method stub
						java.util.Date subD;
						String date;
						if (subdate.getDate() != null) {
							subD = subdate.getDate();
							SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							date = format.format(subD);
						} else {
							date = "";
						}

						String new1 = fName.getText(), new2 = fCost.getText(), new3 = fClient.getText(),
								new4 = fLocation.getText(), new5 = date;
						if (new1.compareTo("") == 0) {
							new1 = p.getName();
						}
						if (new2.compareTo("") == 0) {
							new2 = String.valueOf(p.getCost());
						}
						if (new3.compareTo("") == 0) {
							new3 = p.getClientName();
						}
						if (new4.compareTo("") == 0) {
							new4 = p.getlocation();
						}
						if (new5.compareTo("") == 0) {
							new5 = p.getSubDate();
						}
						con.updateProject(p.getId(), new1, Float.parseFloat(new2), new3, new4, new5);
						p.setName(new1);
						p.setCost(Float.parseFloat(new2));
						p.setClientName(new3);
						p.setLocation(new4);
						p.setSubDate(new5);
						tabbedPane.removeTabAt(0);
						tabbedPane.add(details, 0);
						tabbedPane.setTabComponentAt(0, detailPANEL);
						tabbedPane.setSelectedIndex(0);

					}

				});
			}
		});

		detailPANEL.setPreferredSize(new Dimension(200, 30));
		apPANEL.setPreferredSize(new Dimension(200, 30));
		tablePANEL.setPreferredSize(new Dimension(200, 30));
		fcPANEL.setPreferredSize(new Dimension(200, 30));
		tabbedPane.setTabComponentAt(0, detailPANEL);
		tabbedPane.setTabComponentAt(1, apPANEL);
		tabbedPane.setTabComponentAt(2, tablePANEL);
		tabbedPane.setTabComponentAt(3, fcPANEL);

		delButton = new JButton("delete");
		delButton.setBounds(1153, 98, 150, 30);
		delButton.setBackground(Color.LIGHT_GRAY);
		delButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent e) {
				// TODO Auto-generated method stub
				int id = p.getId();
//				// First, remove files from into the folder 
//				FileUtils.cleanDirectory();
				try {
					FileUtils.deleteDirectory(new File("archivetect\\projects\\" + p.getName()));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				con.deleteProject(id);
				JFrame f = (JFrame) SwingUtilities.getRoot((JButton) e.getSource());
				new HomePage(con);
				f.dispose();
			}

		});
		delButton.setOpaque(false);

		

		JLabel back = new JLabel();
		back.setBounds(1090, 88, 50, 50);
		ImageIcon c = new ImageIcon("Decoration/left.png");
		back.setIcon(c);
		back.setText("");
		back.setFont(new Font("Calisto MT", Font.BOLD, 18));
		back.setHorizontalTextPosition(JLabel.RIGHT);
		back.setVerticalTextPosition(JLabel.CENTER);
		back.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(java.awt.event.MouseEvent e) {
				// TODO Auto-generated method stub
				JFrame p = (JFrame) SwingUtilities.getRoot((JLabel) e.getSource());
				new AllProjects(con);
				p.dispose();
			}
		});

		this.add(back);
		this.add(delButton);
		this.add(status);
		this.add(tabbedPane);
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowListener() {
			@Override
			public void windowOpened(WindowEvent e) {
			}

			@Override
			public void windowClosing(WindowEvent we) {
				String ObjButtons[] = { "Yes", "No" };
				int PromptResult = JOptionPane.showOptionDialog(null, "Are you sure you want to exit?",
						"ArchiveTect System", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, ObjButtons,
						ObjButtons[1]);
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

	}

}
