package com.cognizant.payroll.model;

public class EmployeeSkillSet {

	public EmployeeSkillSet() {
		// TODO Auto-generated constructor stub
	}

	private int skillSetID;
	private Skill skill;
	private Employee employee;

	public EmployeeSkillSet(int skillSetID, Skill skill, Employee employee) {
		super();
		this.skillSetID = skillSetID;
		this.skill = skill;
		this.employee = employee;
	}

	@Override
	public String toString() {
		return "EmployeeSkillSet [skillSetID=" + skillSetID + ", skill=" + skill + ", employee=" + employee + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((employee == null) ? 0 : employee.hashCode());
		result = prime * result + ((skill == null) ? 0 : skill.hashCode());
		result = prime * result + skillSetID;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmployeeSkillSet other = (EmployeeSkillSet) obj;
		if (employee == null) {
			if (other.employee != null)
				return false;
		} else if (!employee.equals(other.employee))
			return false;
		if (skill == null) {
			if (other.skill != null)
				return false;
		} else if (!skill.equals(other.skill))
			return false;
		if (skillSetID != other.skillSetID)
			return false;
		return true;
	}

	public int getSkillSetID() {
		return skillSetID;
	}

	public void setSkillSetID(int skillSetID) {
		this.skillSetID = skillSetID;
	}

	public Skill getSkill() {
		return skill;
	}

	public void setSkill(Skill skill) {
		this.skill = skill;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
}
