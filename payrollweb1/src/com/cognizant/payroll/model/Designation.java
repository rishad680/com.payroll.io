package com.cognizant.payroll.model;

public class Designation {

	public Designation() {
		// TODO Auto-generated constructor stub
	}

	private int designationId;
	private String designationName;

	public Designation(int designationId, String designationName) {
		super();
		this.designationId = designationId;
		this.designationName = designationName;
	}

	@Override
	public String toString() {
		return "Designation [designationId=" + designationId + ", designationName=" + designationName + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + designationId;
		result = prime * result + ((designationName == null) ? 0 : designationName.hashCode());
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
		Designation other = (Designation) obj;
		if (designationId != other.designationId)
			return false;
		if (designationName == null) {
			if (other.designationName != null)
				return false;
		} else if (!designationName.equals(other.designationName))
			return false;
		return true;
	}

	public int getDesignationId() {
		return designationId;
	}

	public void setDesignationId(int designationId) {
		this.designationId = designationId;
	}

	public String getDesignationName() {
		return designationName;
	}

	public void setDesignationName(String designationName) {
		this.designationName = designationName;
	}
}
