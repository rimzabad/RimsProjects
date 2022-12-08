package Projects;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import DatabaseManagement.BddCmm;

public class ForTender extends JPanel {
	private Graphics2D g2;
	private JScrollPane sp;
	private Image image;
	private JTable pet;
	private int rowAtPoint;
	private JButton add;
	Project project;
	BddCmm con;

	public ForTender(Project p, BddCmm con) {
		this.project = p;
		this.con = con;
		this.setLayout(null);
		pet = new JTable(con.getData(p.getId()));
		sp = new JScrollPane(pet);
		pet.setForeground(Color.black);
		pet.setBackground(Color.white);
		pet.setAutoCreateColumnsFromModel(false);
		pet.setRowHeight(30);

		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
//		pet.setDefaultRenderer(String.class, centerRenderer);
//		pet.setDefaultRenderer(Integer.class, centerRenderer);

//		pet.setBackground(this.getForeground());
		pet.setSelectionBackground(new Color(236, 240, 241, 250));
//		pet.setSelectionForeground(Color.black);

		pet.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		pet.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
		pet.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
		pet.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
		pet.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);

		sp.setBounds(0, 0, 1200, 475);
		System.out.println(project.getId());

		JButton add = new JButton("ADD");
		add.setBounds(920, 500, 150, 33);
		add.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent e) {
				// TODO Auto-generated method stub
				JFrame f = new JFrame();
				f.setSize(400, 380);
				f.setLayout(null);
				BufferedImage backg = null;
				try {
					backg = ImageIO.read(new File("Decoration/back.png"));
				} catch (IOException ex) {
					ex.printStackTrace();
				}

				Image dimg = backg.getScaledInstance(400, 380, Image.SCALE_SMOOTH);
				ImageIcon imageIconb = new ImageIcon(dimg);
				f.setContentPane(new JLabel(imageIconb));
				f.setUndecorated(true);
				String[] optionNames = con.getOptionalMaterial().split("\n");
				JCheckBox[] options = new JCheckBox[optionNames.length];
				JPanel containOptions = new JPanel();
				containOptions.setBounds(5, 5, 380, 310);
				containOptions.setLayout(new FlowLayout(FlowLayout.LEFT));
				containOptions.setOpaque(false);
				containOptions.setBackground(new Color(236, 240, 241, 180));
				for (int i = 0; i < optionNames.length; i++) {
					options[i] = new JCheckBox(optionNames[i].toUpperCase());
					options[i].setOpaque(false);
					options[i].setPreferredSize(new Dimension(350, 50));
					containOptions.add(options[i]);
				}
				JScrollPane container = new JScrollPane();
				container.setBounds(0, 5, 390, 320);
				container.add(containOptions);
				JPanel coloring = new JPanel();
				coloring.setBounds(5, 5, 380, 310);
				coloring.setLayout(null);
				coloring.setOpaque(true);
				coloring.setBackground(new Color(236, 240, 241, 180));

				JButton addFromOptions = new JButton("Add");
				// addFromOptions.setPreferredSize(new Dimension(100, 70));
				addFromOptions.setBounds(200, 340, 100, 40);
				addFromOptions.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(java.awt.event.MouseEvent e) {
						int count = 0;
						for (JCheckBox box : options) {
							if (box.isSelected()) {
								con.insertToFortender(project.getId(), box.getText());
								count++;
							}
						}
						if (count > 0) {
							String ObjButtons[] = { "Yes", "No" };
							int PromptResult = JOptionPane.showOptionDialog(null, "Confirm?", "ArchiveTect System",
									JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, ObjButtons,
									ObjButtons[1]);
							if (PromptResult == JOptionPane.YES_OPTION) {
								f.dispose();
							}
						} else
							f.dispose();
						pet.setModel(con.getData(p.getId()));
						repaint();
					}
				});
				addFromOptions.setBackground(new Color(236, 240, 241, 180));
				f.add(containOptions);
				f.add(addFromOptions);
				f.add(coloring);
				JButton newOption = new JButton("+");
				newOption.setFont(new Font("Calisto MT", Font.BOLD, 18));
				newOption.setBounds(130, 350, 40, 40);
				newOption.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						f.dispose();
						JFrame f2 = new JFrame();
						f2.setSize(400, 380);
						JTextField matName = new JTextField();
						f2.setLocationRelativeTo(null);
						f2.setLayout(null);
						f2.setVisible(true);
						matName.setBounds(77, 140, 250, 45);
						matName.setBackground(f.getBackground());
						matName.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),
								"Material Name"));
						f2.add(matName);

						JLabel stat = new JLabel("Status");
						stat.setBounds(260, 80, 100, 40);
						f2.add(stat);
						JCheckBox st = new JCheckBox();
						st.setBounds(305, 90, 20, 20);

						f2.add(st);

						String materials[] = con.matCombo();
						JComboBox<String> mat = new JComboBox<String>(materials);
						mat.setBounds(77, 220, 250, 40);
						f2.add(mat);
						matName.addKeyListener(new KeyListener() {
							@Override
							public void keyPressed(KeyEvent e) {
								if (e.getKeyCode() == KeyEvent.VK_ENTER) {
									String name = matName.getText();
									String type = String.valueOf(mat.getSelectedItem());

									String status;
									if (st.isSelected()) {
										status = "Mandatory";
									} else {
										status = "Optional";
									}

									if (!name.isEmpty() || !type.isEmpty()) {
										con.addMaterial(p.getId(), name, type, status);
										f2.dispose();
									}

									else {
										JOptionPane.showMessageDialog(null, "FILL MATERIAL NAME AND TYPE!!");
									}

								}

								if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
									f2.dispose();
								}

							}

							@Override
							public void keyTyped(KeyEvent e) {
								// TODO Auto-generated method stub

							}

							@Override
							public void keyReleased(KeyEvent e) {
								// TODO Auto-generated method stub

							}
						});

					}

				});
				f.add(newOption);
				f.setLocationRelativeTo(null);
				f.setLayout(null);
				f.setVisible(true);
				pet.setModel(con.getData(project.getId()));
			}
		});

		final JPopupMenu popup = new JPopupMenu();
		// New project menu item
		JMenuItem menuItem = new JMenuItem("|X| Delete");
		menuItem.setMnemonic(KeyEvent.VK_P);
		menuItem.getAccessibleContext().setAccessibleDescription("New Project");
		menuItem.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String ObjButtons[] = { "Yes", "No" };
				int PromptResult = JOptionPane.showOptionDialog(null, "Are you sure?", "", JOptionPane.DEFAULT_OPTION,
						JOptionPane.WARNING_MESSAGE, null, ObjButtons, ObjButtons[1]);
				if (PromptResult == JOptionPane.YES_OPTION) {
					int mID = Integer.parseInt(String.valueOf(pet.getModel().getValueAt(rowAtPoint, 0)));
					con.deleteRow(project.getId(),mID);
					pet.setModel(con.getData(project.getId()));
				}
			}
		});
		popup.add(menuItem);
        pet.setComponentPopupMenu(popup);
        popup.addPopupMenuListener(new PopupMenuListener() {

            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        rowAtPoint = pet.rowAtPoint(SwingUtilities.convertPoint(popup, new Point(0, 0), pet));
                        if (rowAtPoint > -1) {
                            pet.setRowSelectionInterval(rowAtPoint, rowAtPoint);
                        }
                    }
                });
            }

            @Override
            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void popupMenuCanceled(PopupMenuEvent e) {
                // TODO Auto-generated method stub

            }
        });
		pet.getModel().addTableModelListener(new TableModelListener() {
			@Override
			public void tableChanged(TableModelEvent e) {
				// TODO Auto-generated method stub
				int row = e.getFirstRow();
				int column = e.getColumn();
				TableModel model = (TableModel) e.getSource();
				String o = String.valueOf(model.getValueAt(row, column));
				con.connectBdd();
				System.out.println("HEYYYYY");
				con.updateForTender(Integer.parseInt(String.valueOf(model.getValueAt(row, 0))), p.getId(), row, o,
						column);
				repaint();
			}
		});

		pet.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 17));
		pet.getTableHeader().setMinimumSize(new Dimension(150, 90));

		JComboBox<String> filterCategory = new JComboBox<String>(con.matCombo());
		filterCategory.setBounds(520, 500, 150, 33);
		filterCategory.addItem("All");
		JButton filter = new JButton("Filter");
		filter.setBounds(720, 500, 150, 33);
		filter.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String choice = String.valueOf(filterCategory.getSelectedItem());
				if (choice.compareTo("All") == 0) {
					pet.setModel(con.getData(p.getId()));
				} else {
					pet.setModel(con.getDataFiltered(p.getId(), choice));
				}
			}

		});
		this.add(filter);
		this.add(filterCategory);

		JButton print = new JButton("Save");
		print.setBounds(20, 500, 150, 33);
		print.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				Document doc;
				try {
					doc = new Document();
					PdfWriter.getInstance(doc, new FileOutputStream(
							"archivetect\\projects\\" + p.getName() + "\\For Tender\\" + p.getName() + ".pdf"));
					doc.open();
					PdfPTable pdfTable = new PdfPTable(pet.getColumnCount());
					// adding table headers
					for (int i = 0; i < pet.getColumnCount(); i++) {
						pdfTable.addCell(pet.getColumnName(i));
					}
					// extracting data from the JTable and inserting it to PdfPTable
					for (int rows = 0; rows < pet.getRowCount() - 1; rows++) {
						for (int cols = 0; cols < pet.getColumnCount(); cols++) {
							pdfTable.addCell(pet.getModel().getValueAt(rows, cols).toString());

						}
					}
					doc.add(pdfTable);
					doc.close();
					System.out.println("done");
				} catch (DocumentException ex) {
				} catch (FileNotFoundException ex) {
				}

				JOptionPane.showMessageDialog(null, "Saved Successfully");
			}

		});
		this.add(print);

		this.add(add);
		this.add(sp);

	}

	@Override
	public void paintComponent(Graphics g) {
		Color background = this.getBackground();

		pet.setAutoCreateColumnsFromModel(false);

		pet.getModel().addTableModelListener(new TableModelListener() {
			@Override
			public void tableChanged(TableModelEvent e) {
				// TODO Auto-generated method stub
				int row = e.getFirstRow();
				int column = e.getColumn();
				TableModel model = (TableModel) e.getSource();
				String o = String.valueOf(model.getValueAt(row, column));
				con.connectBdd();
				con.updateForTender(Integer.parseInt(String.valueOf(model.getValueAt(row, 0))), project.getId(), row, o,
						column);
				pet.setModel(con.getData(project.getId()));
				repaint();
			}
		});
		if (image == null) {
			this.setBackground(Color.white);
			image = createImage(getSize().width, getSize().height);
			g2 = (Graphics2D) image.getGraphics();
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g2.setPaint(background);
			g2.fillRect(0, 0, getSize().width, getSize().height);
			repaint();
		}
		g.drawImage(image, 0, 0, null);

	}
}
