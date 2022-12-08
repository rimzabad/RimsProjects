package Projects;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import DatabaseManagement.BddCmm;
import Front.HomePage;

public class AllProjects extends JFrame {
	private BddCmm con;
	private JLabel back, title, newProj;
	private JPanel allFiles;
	private int counter;
	private ArrayList<Project> projects =new ArrayList<Project>();

	private int height;
	private int width;

	public AllProjects(BddCmm con) {
		this.con = con;
		this.setTitle("Projects");
 
		height = Toolkit.getDefaultToolkit().getScreenSize().height	;
		width = Toolkit.getDefaultToolkit().getScreenSize().width	;

		this.setSize(width, height);
		this.setLayout(null);
		
		BufferedImage backg = null;
		try {
			backg = ImageIO.read(new File("Decoration/back.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		Image dimg = backg.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		ImageIcon imageIconb = new ImageIcon(dimg);
		setContentPane(new JLabel(imageIconb));
		
		counter = con.getProjectsCount();
		JLabel[] projs = new JLabel[counter];
		
		allFiles = new JPanel();
		allFiles.setBounds(120, 150, width-250, height-300);
		allFiles.setBackground(new Color(236,240,241,180));
		allFiles.setLayout(new FlowLayout(FlowLayout.LEFT, 50, 25));
		allFiles.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		
		ImageIcon a = new ImageIcon("Decoration/newfile.png");
		ImageIcon b = new ImageIcon("Decoration/project.png");
		newProj = new JLabel();
		newProj.setIcon(a);
		newProj.setText("New Project");
		newProj.setHorizontalTextPosition(JLabel.CENTER);
		newProj.setVerticalTextPosition(JLabel.BOTTOM);
		newProj.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(java.awt.event.MouseEvent e) {
				// TODO Auto-generated method stub
                JFrame p= (JFrame) SwingUtilities.getRoot((JLabel) e.getSource());
    			new NewProject(con);
    			p.dispose();
			}
        });
		
		projects = con.getProjects();
		title = new JLabel("File Archive");
		title.setBounds(1040, 70, 300,50);
		title.setForeground(new Color(85,101,115,230));
		title.setFont(new Font("Calisto MT", Font.BOLD, 38));
		
		allFiles.add(newProj);
		
		for (int i = 0; i < projects.size(); i++) {
			projs[i] = new JLabel();
			projs[i].setIcon(b);
			projs[i].setText(projects.get(i).getName());
			projs[i].setHorizontalTextPosition(JLabel.CENTER);
			projs[i].setVerticalTextPosition(JLabel.BOTTOM);
		
			Project pro = projects.get(i);
			projs[i].addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(java.awt.event.MouseEvent e) {
					// TODO Auto-generated method stub
	                JFrame p= (JFrame) SwingUtilities.getRoot((JLabel) e.getSource());
	    			new ProcessProject(con, pro);
	    			p.dispose();
				}
	        });
			
			allFiles.add(projs[i]);
		}
		
		back = new JLabel();
		back.setBounds(1000, 65, 150,50);
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
                JFrame p= (JFrame) SwingUtilities.getRoot((JLabel) e.getSource());
    			new HomePage(con);
    			p.dispose();
			}
        });
		
		this.add(back);
		this.add(allFiles);
		this.add(title);
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
