package DatabaseManagement;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import Projects.Project;
import Utilities.AES;

public class BddCmm {
	private Connection cn;
	private String us, pw;
	private int architect_id = 1;
	final String secretKey = "ssshhhhhhhhhhh!!!!";

	public void connectBdd() {
		try {
			cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/oopii?useSSL=false", "root", "rim2002");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void disconnectBdd() {
		try {
			cn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Connection getCn() {
		return cn;
	}

	public boolean authenticate(String u, String p) {
		connectBdd();
		java.sql.Statement st;
		try {
			st = cn.createStatement();
			ResultSet rs = st.executeQuery("select * from Architect");
			if (rs.first() == false) {
				addArchitect("", "", u, p);
			}
			rs.beforeFirst();
			while (rs.next()) {
				us = rs.getString(4);
				pw = AES.decrypt(rs.getString(5), secretKey);
				if (us.compareTo(u) == 0 && pw.compareTo(p) == 0) {
					architect_id = rs.getInt(1);
					return true;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public ResultSet getInfo() {

		try {
			java.sql.Statement st = cn.createStatement();
			ResultSet sc = st.executeQuery("select * from architect where architect_id =" + architect_id + ";");
			sc.first();
			return sc;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public void updateInfo(String fn, String ln, String us, String pw) {
		// TODO Auto-generated method stub
		java.sql.Statement st;
		try {
			st = cn.createStatement();
			st.executeUpdate("update Architect set firstname = '" + fn + "', `lastname` = '" + ln + "', a_username = '"
					+ us + "', a_password='" + AES.encrypt(pw, secretKey) + "' where architect_id =" + architect_id
					+ ";");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getSecretKey() {
		return secretKey;
	}

	public void addArchitect(String newF, String newL, String newU, String newP) {
		// TODO Auto-generated method stub

		String sql = "INSERT INTO `oopii`.`architect`(`firstname`,`lastname`,`a_username`,`a_password`)VALUES (?,?,?,?);";
		PreparedStatement preparedStatement;
		try {
			preparedStatement = cn.prepareStatement(sql);

			preparedStatement.setString(1, newF);
			preparedStatement.setString(2, newL);
			preparedStatement.setString(3, newU);
			preparedStatement.setString(4, AES.encrypt(newP, secretKey));

			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String[] catCombo() {
		String categ = "";

		try {

			Statement st = null;
			st = cn.createStatement();
			ResultSet rs = st.executeQuery("SELECT p_category_name FROM Project_Category");

			while (rs.next()) {
				categ = categ + rs.getString("p_category_name") + "\n";
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return categ.split("\n");
	}

	public String[] matCombo() {
		String material = " ";

		try {

			Statement st = null;
			st = cn.createStatement();
			ResultSet rs = st.executeQuery("SELECT material_type_name FROM material_type");

			while (rs.next()) {
				material = material + rs.getString("material_type_name") + "\n";
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return material.split("\n");
	}

	/*
	 * For Projects
	 */

	public boolean addProject(String projectName, String clientName, float budget, String description, String location,
			String date1, String date2, String category) {

		int cat_id = 1;
		if (projectName.isEmpty() || clientName.isEmpty() || budget == 0 || description.isEmpty() || location.isEmpty()
				|| category.isEmpty() || date1.isEmpty() || date2.isEmpty())
			return false;

		try {

			Statement st = null;
			st = cn.createStatement();
			System.out.println(category);
			ResultSet rs = st.executeQuery(
					"SELECT p_category_id FROM Project_Category where p_category_name = '" + category + "'");

			rs.first();
			cat_id = rs.getInt(1);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String sql = "INSERT INTO `oopii`.`project`(`architect_id`,`p_category_id`,`p_name`,`p_description`,`client_name`,`client_budget`,`start_date`, `submission_date`,`cost`,`location`,`p_status`) VALUES (?,?,?,?,?,?,?,?,?,?,?);";

		float cost = budget;
		PreparedStatement preparedStatement;
		try {
			preparedStatement = cn.prepareStatement(sql);
			preparedStatement.setInt(1, this.architect_id);
			preparedStatement.setInt(2, cat_id);
			preparedStatement.setString(3, projectName);
			preparedStatement.setString(4, description);
			preparedStatement.setString(5, clientName);
			preparedStatement.setFloat(6, budget);
			preparedStatement.setString(7, date1);
			preparedStatement.setString(8, date2);
			preparedStatement.setFloat(9, 20);
			preparedStatement.setString(10, location);
			preparedStatement.setString(11, "Not Finished");
			preparedStatement.executeUpdate();

			Statement st = cn.createStatement();

			ResultSet rs = st.executeQuery("Select project_id from project order by project_id desc limit 1");
			rs.first();
			String sql_av = "insert into avantprojet(project_id) values(?);";
			String sql_ft = "insert into fortender(project_id,material_id) values(?,?);";
			String sql_fc = "insert into forconstruction(project_id) values(?);";
			String sql_materials = "Select material_id from material where status='mandatory'";
			String sql_materialsBB = "Select material_id from material where status='Big Building'";

			PreparedStatement pst = cn.prepareStatement(sql_av);
			pst.setInt(1, rs.getInt(1));
			pst.executeUpdate();

			Statement st1 = cn.createStatement();
			ResultSet rs1 = st1.executeQuery(sql_materials);
			rs1.beforeFirst();
			while (rs1.next()) {
				PreparedStatement pst1 = cn.prepareStatement(sql_ft);
				pst1.setInt(1, rs.getInt(1));
				pst1.setInt(2, rs1.getInt(1));
				pst1.executeUpdate();
			}
			if (category.compareTo("Big Building") == 0) {
				Statement st2 = cn.createStatement();
				ResultSet rs2 = st2.executeQuery(sql_materialsBB);
				rs2.beforeFirst();
				while (rs2.next()) {
					PreparedStatement pst1 = cn.prepareStatement(sql_ft);
					pst1.setInt(1, rs.getInt(1));
					pst1.setInt(2, rs2.getInt(1));
					pst1.executeUpdate();
				}
			}

			PreparedStatement pst2 = cn.prepareStatement(sql_fc);
			pst2.setInt(1, rs.getInt(1));
			pst2.executeUpdate();

			new File("archivetect\\projects\\" + projectName).mkdirs();
			new File("archivetect\\projects\\" + projectName + "\\Avant Projet").mkdirs();
			new File("archivetect\\projects\\" + projectName + "\\For Tender").mkdirs();
			new File("archivetect\\projects\\" + projectName + "\\For Construction").mkdirs();
			new File("archivetect\\projects\\" + projectName + "\\For Construction\\Corrections").mkdirs();

			return true;

		} catch (SQLIntegrityConstraintViolationException e) {
			JOptionPane.showMessageDialog(null, "Project name already exists");
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public void deleteProject(int project_id) {
		Statement st;
		try {
			st = cn.createStatement();
			st.executeUpdate("delete from project where project_id = " + project_id + ";");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public int getProjectsCount() {
		String sql = "Select Count(project_id),p_name from Project where architect_id =" + this.architect_id + ";";

		PreparedStatement preparedStatement;
		try {
			preparedStatement = cn.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			rs.first();
			return rs.getInt(1);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}

	/*
	 * public getProjectName() {
	 * 
	 * }
	 */

	public Project getProjectInfo(int id) {
		String sql = "Select * from Project,avantprojet where project.project_id=avantprojet.project_id and where project_id=?;";

		PreparedStatement pst;
		try {
			pst = cn.prepareStatement(sql);
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			rs.first();

			Project project = new Project(id, rs.getInt(2), rs.getInt("p_category_id"), rs.getString("p_name"),
					rs.getString("p_description"), rs.getString("client_name"), rs.getFloat("cost"),
					rs.getFloat("client_budget"), rs.getString("location"), rs.getDate("start_date"),
					rs.getDate("submission_date"), rs.getString("p_status"), rs.getInt("ap_id"));
			return project;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList<Project> getProjects() {
		String sql = "Select * from Project,avantprojet where project.project_id=avantprojet.project_id and architect_id=?;";

		PreparedStatement pst;
		try {
			pst = cn.prepareStatement(sql);
			pst.setInt(1, this.architect_id);
			ResultSet rs = pst.executeQuery();
			ArrayList<Project> projects = new ArrayList<Project>();
			while (rs.next()) {
				Project project = new Project(rs.getInt(1), rs.getInt(2), rs.getInt("p_category_id"),
						rs.getString("p_name"), rs.getString("p_description"), rs.getString("client_name"),
						rs.getFloat("cost"), rs.getFloat("client_budget"), rs.getString("location"),
						rs.getDate("start_date"), rs.getDate("submission_date"), rs.getString("p_status"),
						rs.getInt("ap_id"));
				projects.add(project);
				System.out.print("fdproject");
			}
			return projects;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	/*
	 * For Approval
	 */
	public ResultSet getAvantProjet(int id) {

		String sql = "Select * from avantproject where project_id = ?";
		PreparedStatement pst;
		try {
			pst = cn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;

		}
	}

	public void addPlan(int id, String path, String type) {
		String sql = "Insert into plan(idAV, path, type_name) values (?,?,?)";
		PreparedStatement pst;
		try {
			pst = cn.prepareStatement(sql);
			pst.setInt(1, id);
			pst.setString(2, path);
			pst.setString(3, type);
			pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void updateProject(int id, String new1, float new3, String new2, String new4, String new5) {
		// TODO Auto-generated method stub
		String sql = "Update project set p_name = ? , client_name = ?, cost = ?, location = ?, submission_date = ? where project_id ="
				+ id + ";";
		PreparedStatement pst;
		try {
			pst = cn.prepareStatement(sql);
			pst.setString(1, new1);
			pst.setString(2, new2);
			pst.setFloat(3, new3);
			pst.setString(4, new4);
			pst.setString(5, new5);
			pst.executeUpdate();
		} catch (SQLIntegrityConstraintViolationException e) {
			JOptionPane.showMessageDialog(null, "Project name already exists");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public DefaultTableModel getData(int id) {
		String col[] = { "Material ID", "Material", "Price", "Quantity", "Total Price" };
		DefaultTableModel dm = new DefaultTableModel(col, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return column == 0 || column == 4 ? false : true;
			}

		};

		String sql = "SELECT * FROM forTender t, material m where t.material_id = m.material_id and project_id = " + id;

		try {

			Statement s = cn.prepareStatement(sql);
			ResultSet rs = s.executeQuery(sql);

			while (rs.next()) {
				int mID = rs.getInt("material_id");
				String mat = rs.getString("material_name");
				float price = rs.getFloat("price");
				int quant = rs.getInt("quantity");
				float total = rs.getFloat("totalprice");

				dm.addRow(new Object[] { mID, mat, price, quant, total });
			}

			return dm;

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return null;

	}

	public boolean tender(String proj, String mat, String price, String quant, String total) {

		try {
			Statement st = cn.createStatement();

			String sql = "INSERT INTO `oopii`.`forTender` (`project_id`,`material_id`,`quantity`,`price`,`totalprice`)	VALUES('"
					+ proj + "','" + mat + "', '" + price + "', '" + quant + "', '" + total + "');";
			ResultSet rs = st.executeQuery("Select * from forTender where material_id='" + mat + "';");
			if (rs.first() == false) {
				st.executeUpdate(sql);
				return true;
			} else {
				return false;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public void addMaterial(int id, String name, String type, String status) {
		// TODO Auto-generated method stub
		String sql1 = " Select material_type_id from material_type where material_type_name= '" + type + "';";
		String sql2 = "INSERT INTO `oopii`.`material`(`material_type_id`,`material_name`,`status`)VALUES(?,?,?)";
		String sql3 = " Select material_id from material order by material_id desc limit 1";
		String sql4 = "INSERT INTO `oopii`.`forTender` (`project_id`,`material_id`)	VALUES(?,?);";

		try {

			Statement s = cn.prepareStatement(sql1);
			ResultSet rs = s.executeQuery(sql1);
			rs.first();
			int categid = rs.getInt(1);
			PreparedStatement pst = cn.prepareStatement(sql2);
			pst.setInt(1, categid);
			pst.setString(2, name);
			pst.setString(3, status);
			pst.executeUpdate();

			Statement s1 = cn.prepareStatement(sql3);
			ResultSet rs1 = s.executeQuery(sql3);
			rs1.first();
			int matid = rs1.getInt(1);
			PreparedStatement pst1 = cn.prepareStatement(sql4);
			pst1.setInt(1, id);
			pst1.setInt(2, matid);
			pst1.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void updateForTender(int mID, int id, int row, String o, int col) {
		// TODO Auto-generated method stub
		String column = null;
		if (col == 1) {
			column = "material_id";
			String sql1 = "Update material set material_name ='" + o + "' where material_id = " + mID;
			try {
				Statement s = cn.prepareStatement(sql1);
				s.executeUpdate(sql1);
				return;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (col == 2) {
			column = "price";
		} else if (col == 3) {
			column = "quantity";
		}

		String regex = "[+-]?[0-9]+";

		// compiling regex
		Pattern p = Pattern.compile(regex);

		// Creates a matcher that will match input1 against regex
		Matcher m = p.matcher(o);

		String regex1 = "[+-]?[0-9]+(\\.[0-9]+)?([Ee][+-]?[0-9]+)?";

		// compiling regex
		Pattern p1 = Pattern.compile(regex1);

		// Creates a matcher that will match input1 against regex
		Matcher m1 = p1.matcher(o);
		if ((m1.find() && m1.group().equals(o)) || m.find() && m.group().equals(o)) {
			String sql = "Update fortender set " + column + "= " + o + " where project_id = " + id
					+ " and material_id = " + mID;
			String sql2 = "Select quantity, price from fortender where material_id =" + mID + " and project_id = " + id;
			String sql3 = "Update fortender set totalprice = ? where project_id = " + id + " and material_id = " + mID;
			try {
				PreparedStatement st = cn.prepareStatement(sql);

				st.executeUpdate();

				PreparedStatement s1 = cn.prepareStatement(sql2);
				ResultSet rs1 = s1.executeQuery();
				rs1.first();
				int quantity = rs1.getInt(1);
				float price = rs1.getFloat(2);
				PreparedStatement st1 = cn.prepareStatement(sql3);
				double newTotalPrice = quantity * price;

				st1.setDouble(1, newTotalPrice);
				st1.executeUpdate();

				return;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			JOptionPane.showMessageDialog(null, "Invalid Value");
		}

	}

	public TableModel getDataFiltered(int id, String choice) {
		// TODO Auto-generated method stub
		String col[] = { "Material ID", "Material", "Price", "Quantity", "Total Price" };
		DefaultTableModel dm = new DefaultTableModel(col, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return column == 0 || column == 4 ? false : true;
			}

		};

		String sql = "SELECT * FROM forTender t, material m, material_type mt where t.material_id = m.material_id and m.material_type_id = mt.material_type_id and project_id = "
				+ id + " and mt.material_type_name ='" + choice + "';";

		try {

			Statement s = cn.prepareStatement(sql);
			ResultSet rs = s.executeQuery(sql);

			while (rs.next()) {
				int mID = rs.getInt(2);
				String mat = rs.getString(8);
				int price = rs.getInt(3);
				int quant = rs.getInt(4);
				int total = rs.getInt(5);

				dm.addRow(new Object[] { mID, mat, price, quant, total });
			}

			return dm;

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return null;

	}

	public void editFC(int id, String pathFC, String type) {
		// TODO Auto-generated method stub
		if (type.compareTo("Corrections") == 0) {
			String sql1 = "Select fc_id from forconstruction where project_id = " + id;
			String sql2 = "Insert into correction(fc_id, image) values(?,?)";
			try {
				Statement st = cn.prepareStatement(sql1);
				ResultSet rs = st.executeQuery(sql1);
				rs.first();
				int fcID = rs.getInt(1);

				PreparedStatement pst = cn.prepareStatement(sql2);
				pst.setInt(1, fcID);
				pst.setString(2, pathFC);

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (type.compareTo("Final Drawing") == 0) {
			String sql = "Update forconstruction set final_drawing ='" + pathFC + "' where project_id = " + id;

			try {
				Statement st = cn.prepareStatement(sql);
				st.executeUpdate(sql);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (type.compareTo("Electrical") == 0) {
			String sql = "Update forconstruction set elect_map ='" + pathFC + "' where project_id = " + id;

			try {
				Statement st = cn.prepareStatement(sql);
				st.executeUpdate(sql);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (type.compareTo("Mechanical") == 0) {
			String sql = "Update forconstruction set mech_map ='" + pathFC + "' where project_id = " + id;

			try {
				Statement st = cn.prepareStatement(sql);
				st.executeUpdate(sql);
			} catch (SQLException e) {
				// TODO Auto-generated catch block 
				e.printStackTrace();
			}

		} else if (type.compareTo("Civil") == 0) {
			String sql = "Update forconstruction set civil_map ='" + pathFC + "' where project_id = " + id;

			try {
				Statement st = cn.prepareStatement(sql);
				st.executeUpdate(sql);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (type.compareTo("Revision 1") == 0) {
			String sql = "Update forconstruction set revision1 ='" + pathFC + "' where project_id = " + id;
			try {
				Statement st = cn.prepareStatement(sql);
				st.executeUpdate(sql);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (type.compareTo("Revision 2") == 0) {
			String sql = "Update forconstruction set revision2 ='" + pathFC + "' where project_id = " + id;

			try {
				Statement st = cn.prepareStatement(sql);
				st.executeUpdate(sql);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	public void deleteArchitect() {
		// TODO Auto-generated method stub

		String sql = "delete from architect where architect_id=" + architect_id;
		try {
			Statement st = cn.prepareStatement(sql);
			st.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void submitProject(int id) {
		// TODO Auto-generated method stub
		String sql1 = "Select Sum(totalPrice) from fortender where project_id=" + id;

		try {
			Statement st = cn.prepareStatement(sql1);
			ResultSet rs = st.executeQuery(sql1);
			rs.first();
			int total = rs.getInt(1);
			String sql2 = "Update Project set cost=" + total + ", p_status='Finished' where project_id=" + id;
			Statement st1 = cn.prepareStatement(sql2);
			st1.executeUpdate(sql2);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getOptionalMaterial() {
		// TODO Auto-generated method stub
		String sql1 = "Select material_name from material where status='optional';";
		String names="";
		try {
			Statement s = cn.prepareStatement(sql1);
			ResultSet rs = s.executeQuery(sql1);
			rs.beforeFirst();
			while(rs.next()) {
				names+= rs.getString(1)+"\n";
			}
			return names;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return null;
	}

	public void insertToFortender(int id, String name) {
		// TODO Auto-generated method stub
		String sql1 = " Select material_id from material where material_name= '" + name + "';";
		try {
			Statement s = cn.prepareStatement(sql1);
			ResultSet rs = s.executeQuery(sql1);
			rs.first();
			int matID= rs.getInt(1);
			System.out.println(id);
			System.out.println(matID);
			String sql2 = "INSERT INTO `oopii`.`fortender` (`project_id`,`material_id`)	VALUES("+id+","+matID+");";
			Statement s1 = cn.prepareStatement(sql2);
			s1.executeUpdate(sql2);
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void deleteRow(int id, int mID) {
		// TODO Auto-generated method stub
		
		String sql1 = " Delete from fortender where material_id= " + mID + " and project_id ="+id+";";
		try {
			Statement s = cn.prepareStatement(sql1);
			s.executeUpdate(sql1);
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
