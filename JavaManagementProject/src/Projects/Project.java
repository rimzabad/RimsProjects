package Projects;

import java.awt.HeadlessException;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Project {
	private int id, architectID, categoryID, av_id;
	private String name, desc, clientName, location, status;
	private float budget, cost;
	private java.util.Date startD, subD;
	private String startDate, subDate;

	public Project(int id, int architectID, int categoryID, String name, String desc, String clientName, float cost,
			float budget, String location, Date startD, Date subD, String status, int av_id) throws HeadlessException {
		this.id = id;
		this.architectID = architectID;
		this.categoryID = categoryID;
		this.name = name;
		this.desc = desc;
		this.clientName = clientName;
		this.location = location;
		this.budget = budget;
		this.startD = startD;
		this.subD = subD;
		this.cost = cost;
		this.status = status;
		this.av_id = av_id;

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		startDate = format.format(startD);
		subDate = format.format(subD);
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setArchitectID(int architectID) {
		this.architectID = architectID;
	}

	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID;
	}

	public void setAv_id(int av_id) {
		this.av_id = av_id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public void setStatus(String status) {
		this.status = status;
	} 

	public void setBudget(float budget) {
		this.budget = budget;
	}

	public void setCost(float cost) {
		this.cost = cost;
	}

	public void setStartD(java.util.Date startD) {
		this.startD = startD;
	}

	public void setSubD(java.util.Date subD) {
		this.subD = subD;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public void setSubDate(String subDate) {
		this.subDate = subDate;
	}

	public int getId() {
		return id;
	}

	public int getArchitectID() {
		return architectID;
	}

	public int getCategoryID() {
		return categoryID;
	}

	public String getName() {
		return name;
	}

	public String getDesc() {
		return desc;
	}

	public String getClientName() {
		return clientName;
	}

	public int getAVID() {
		return av_id;
	}
	public String getlocation() {
		return location;
	}

	public String getStatus() {
		return status;
	}

	public float getBudget() {
		return budget;
	}

	public float getCost() {
		return cost;
	}

	public java.util.Date getStartD() {
		return startD;
	}

	public java.util.Date getSubD() {
		return subD;
	}

	public String getStartDate() {
		return startDate;
	}

	public String getSubDate() {
		return subDate;
	}

}
