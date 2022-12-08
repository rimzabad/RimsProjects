package Projects;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.io.File;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.commons.io.FileUtils;

import DatabaseManagement.BddCmm;

public class ForConstruction extends JPanel {
	private ImageIcon a = new ImageIcon("Decoration/newfile.png");
	private ImageIcon b = new ImageIcon("Decoration/project.png");

	public ForConstruction(Project p, BddCmm con) {
		this.setLayout(null);

		JPanel filesFC = new JPanel();
		filesFC.setLayout(new FlowLayout(FlowLayout.LEFT, 50, 25));

		filesFC.setBounds(0, 0, 1100, 400);
		this.add(filesFC);
		JButton submit = new JButton("submit");
		submit.setBounds(1000, 500, 100, 50);
		submit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				con.submitProject(p.getId());
			}

		});
		this.add(submit);
		final String destinationFC = "archivetect\\projects\\" + p.getName() + "\\For Construction\\";
		File folder1 = new File(destinationFC);
		File[] listOfFiles1 = folder1.listFiles();
		JLabel filechoose1 = new JLabel();
		filechoose1.setIcon(a);
		filechoose1.setText("Select A File");
		filechoose1.setHorizontalTextPosition(JLabel.CENTER);
		filechoose1.setVerticalTextPosition(JLabel.BOTTOM);
		if (listOfFiles1 != null) {
			JLabel plans[] = new JLabel[listOfFiles1.length];
			for (int i = 0; i < listOfFiles1.length; i++) {
				if (listOfFiles1[i].isFile()) {
					plans[i] = new JLabel();
					plans[i].setIcon(b);
					String type = "";
					plans[i].setBorder(
							BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), type));
					plans[i].setText(listOfFiles1[i].getName());
					plans[i].setHorizontalTextPosition(JLabel.CENTER);
					plans[i].setVerticalTextPosition(JLabel.BOTTOM);
					filesFC.add(plans[i]);
				}
			}
		}
		filechoose1.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(java.awt.event.MouseEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				chooser.addChoosableFileFilter(new FileNameExtensionFilter("Files","txt","jpeg", "jpg", "png", "tif"));
				filesFC.add(chooser);
				int returnValue = chooser.showOpenDialog(null);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					File filePlan = chooser.getSelectedFile();
					String[] maps = { "Corrections", "Revision 1", "Revision 2", "Final Drawing", "Civil", "Mechanical",
							"Electrical" };
					JComboBox comboBox = new JComboBox<String>(maps);

					JOptionPane.showMessageDialog(null, comboBox, "Which map?", JOptionPane.QUESTION_MESSAGE);

					String type = String.valueOf(comboBox.getSelectedItem());
					String pathFC = "";
					if (type.compareTo("Corrections") == 0) {
						pathFC = destinationFC + type + "\\" + chooser.getSelectedFile().getName();
					} else {
						pathFC = destinationFC + type + "-" + chooser.getSelectedFile().getName();
					}

					try {
						FileUtils.copyFile(filePlan, new File(pathFC));
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					con.editFC(p.getId(), pathFC, type + "-" + chooser.getSelectedFile().getName());

					Component[] components = filesFC.getComponents();

					for (Component component : components) {
						System.out.print(components.length);
						filesFC.remove(component);
					}
					filesFC.add(filechoose1);

					if (listOfFiles1 != null) {
						JLabel plans[] = new JLabel[listOfFiles1.length];
						for (int i = 0; i < listOfFiles1.length; i++) {
							if (listOfFiles1[i].isFile()) {
								plans[i] = new JLabel();
								plans[i].setIcon(b);
								String type1 = "";
								plans[i].setBorder(BorderFactory
										.createTitledBorder(BorderFactory.createLineBorder(Color.black), type1));
								plans[i].setText(listOfFiles1[i].getName());
								plans[i].setHorizontalTextPosition(JLabel.CENTER);
								plans[i].setVerticalTextPosition(JLabel.BOTTOM);
								filesFC.add(plans[i]);
							}
						}
					}

					filesFC.revalidate();
				}

			}
		});
		filesFC.add(filechoose1);

	}

}
