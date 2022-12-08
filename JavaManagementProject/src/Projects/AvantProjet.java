package Projects;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.io.File;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.commons.io.FileUtils;

import DatabaseManagement.BddCmm;

public class AvantProjet extends JPanel {
	private final String destinationAP;
	private ImageIcon a = new ImageIcon("Decoration/newfile.png");

	public AvantProjet(Project p, BddCmm con) {
		this.setLayout(new FlowLayout(FlowLayout.LEFT, 50, 25));
		destinationAP = "archivetect\\projects\\" + p.getName() + "\\Avant Projet\\";
		File folder = new File(destinationAP);
		File[] listOfFiles = folder.listFiles();
		JLabel filechoose = new JLabel();
		filechoose.setIcon(a);
		filechoose.setText("Select A File");
		filechoose.setHorizontalTextPosition(JLabel.CENTER);
		filechoose.setVerticalTextPosition(JLabel.BOTTOM);
		ImageIcon b = new ImageIcon("Decoration/project.png");
		
		this.add(filechoose);

		if (listOfFiles != null) {
			JLabel plans[] = new JLabel[listOfFiles.length];
			for (int i = 0; i < listOfFiles.length; i++) {
				if (listOfFiles[i].isFile()) {
					plans[i] = new JLabel();
					plans[i].setIcon(b);
					String type = "";
					plans[i].setBorder(
							BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), type));
					plans[i].setText(listOfFiles[i].getName());
					plans[i].setHorizontalTextPosition(JLabel.CENTER);
					plans[i].setVerticalTextPosition(JLabel.BOTTOM);
					add(plans[i]);
				}
			}
		}
		filechoose.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(java.awt.event.MouseEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				chooser.addChoosableFileFilter(new FileNameExtensionFilter("Image Files","jpeg", "jpg", "png", "tif"));

				add(chooser);
				int returnValue = chooser.showOpenDialog(null);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					File filePlan = chooser.getSelectedFile();
					String type = JOptionPane.showInputDialog("Which plan?");

					try {
						FileUtils.copyFile(filePlan, new File(destinationAP + chooser.getSelectedFile().getName()));
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					con.addPlan(p.getAVID(), destinationAP + chooser.getSelectedFile().getName(), type);

					Component[] components = getComponents();

					for (Component component : components) {
						System.out.print(components.length);
						remove(component);
					}
					add(filechoose);

					File folder = new File(destinationAP);
					File[] listOfFiles = folder.listFiles();
					JLabel plans[] = new JLabel[listOfFiles.length];
					if (listOfFiles != null) {
						for (int i = 0; i < listOfFiles.length; i++) {
							if (listOfFiles[i].isFile()) {
								plans[i] = new JLabel();
								plans[i].setIcon(b);
								String type1 = "";
								plans[i].setBorder(BorderFactory
										.createTitledBorder(BorderFactory.createLineBorder(Color.black), type1));
								plans[i].setText(listOfFiles[i].getName());
								plans[i].setHorizontalTextPosition(JLabel.CENTER);
								plans[i].setVerticalTextPosition(JLabel.BOTTOM);
								add(plans[i]);
							}
						}
					}

				}

			}
		});

	}
}
