package Projects;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.toedter.calendar.JDateChooser;

import DatabaseManagement.BddCmm;
import Front.HomePage;

public class NewProject extends JFrame {
	private BddCmm con;
	private int height;
	private int width;

	public NewProject(BddCmm con) {
		// TODO Auto-generated constructor stub
		this.con = con;

		this.setTitle("");
		height = Toolkit.getDefaultToolkit().getScreenSize().height	;
		width = Toolkit.getDefaultToolkit().getScreenSize().width	;

		this.setSize(width, height);
		this.setLayout(null);

		BufferedImage backg = null;
		try {
			backg = ImageIO.read(new File("Decoration/h2.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		Image dimg = backg.getScaledInstance(1350, 750, Image.SCALE_SMOOTH);
		ImageIcon imageIconb = new ImageIcon(dimg);
		setContentPane(new JLabel(imageIconb));

		JLabel p = new JLabel("Project Name: ");
		p.setBounds(415, 200, 150, 40);
		p.setFont(new Font("Modern No", Font.BOLD, 12));
		p.setForeground(Color.black);
		this.add(p);
		JTextField pt = new JTextField();
		pt.setBounds(535, 200, 210, 35);
		this.add(pt);

		JLabel c = new JLabel("Client Name: ");
		c.setBounds(415, 250, 150, 40);
		c.setFont(new Font("Modern No", Font.BOLD, 12));
		c.setForeground(Color.black);
		this.add(c);
		JTextField ct = new JTextField();
		ct.setBounds(535, 255, 210, 35);
		this.add(ct);

		JLabel b = new JLabel("Client Budget: ");
		b.setBounds(770, 250, 150, 40);
		b.setFont(new Font("Modern No", Font.BOLD, 12));
		b.setForeground(Color.black);
		this.add(b);
		JTextField bt = new JTextField();
		bt.setBounds(885, 255, 210, 35);
		this.add(bt);

		JLabel l = new JLabel("Location: ");
		l.setBounds(770, 197, 150, 40);
		l.setFont(new Font("Modern No", Font.BOLD, 12));
		l.setForeground(Color.black);
		this.add(l);
		JTextField lt = new JTextField();
		lt.setBounds(885, 200, 210, 35);
		this.add(lt);

		JLabel cat = new JLabel("Category: ");
		cat.setBounds(415, 385, 150, 40);
		cat.setFont(new Font("Modern No", Font.BOLD, 12));
		cat.setForeground(Color.black);
		this.add(cat);

		String categories[] = con.catCombo();
		JComboBox<String> tcat = new JComboBox<String>(categories);
		tcat.setBounds(535, 385, 560, 42);
		this.add(tcat);

		JLabel d = new JLabel("Description: ");
		d.setBounds(415, 450, 150, 40);
		d.setFont(new Font("Modern No", Font.BOLD, 12));
		d.setForeground(Color.black);
		this.add(d);
		JTextArea area = new JTextArea();
		area.setBounds(535, 460, 560, 130);
		this.add(area);

		JLabel start = new JLabel("Start Date: ");
		start.setBounds(415, 300, 150, 40);
		start.setFont(new Font("Modern No", Font.BOLD, 12));
		start.setForeground(Color.black);
		this.add(start);

		JLabel sub = new JLabel("Submission date: ");
		sub.setBounds(770, 300, 150, 40);
		sub.setFont(new Font("Modern No", Font.BOLD, 12));
		sub.setForeground(Color.black);
		this.add(sub);

		JDateChooser startdate = new JDateChooser();
		startdate.setBounds(535, 310, 210, 35);
		this.add(startdate);

		JDateChooser subdate = new JDateChooser();
		subdate.setBounds(885, 310, 210, 35);
		this.add(subdate);

		JButton back = new JButton("Go Back");
		back.setBounds(535, 610, 270, 40);
		back.setBackground(Color.white);
		back.setForeground(Color.black);
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
		this.add(back);

		JButton Save = new JButton("Save");
		Save.setBounds(820, 610, 270, 40);
		Save.setBackground(Color.white);
		Save.setForeground(Color.black);
		Save.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (bt.getText().isEmpty()) { 
					JOptionPane.showMessageDialog(null, "Failed WOOO!");
					return;
				}
				String projectName = pt.getText();
				String clienttName = ct.getText();
				float budget = (Float.parseFloat(bt.getText()));
				String description = area.getText();
				String location = lt.getText();
				java.util.Date startD = startdate.getDate();
				java.util.Date subD = subdate.getDate();
				String category = (String) tcat.getSelectedItem();

				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				String date1 = format.format(startD);
				String date2 = format.format(subD);

				boolean b = con.addProject(projectName, clienttName, budget, description, location, date1, date2,category);
				if (b) {
					JOptionPane.showMessageDialog(null, "Success");
				} else {
					JOptionPane.showMessageDialog(null, "Failed WOOO!");
				}
			}

		});
		this.add(Save);

		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowListener() {
			@Override
			public void windowOpened(WindowEvent e) {}
			 @Override
			    public void windowClosing(WindowEvent we)
			    { 
			        String ObjButtons[] = {"Yes","No"};
			        int PromptResult = JOptionPane.showOptionDialog(null,"Are you sure you want to exit?","ArchiveTect System",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE,null,ObjButtons,ObjButtons[1]);
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

	}

}
