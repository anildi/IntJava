package ttl.larku.domain;

public class StudentWithBuilder {

	public enum Status {
		FULL_TIME,
		PART_TIME,
		HIBERNATING
	};

	private int id;
	private String name;
	private String phoneNumber;

	private Status status = Status.FULL_TIME;

	public StudentWithBuilder() {
		this("Unknown", "", Status.FULL_TIME);
	}

	public StudentWithBuilder(String name, String phoneNumber, Status status) {
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.status = status;
	}

	private StudentWithBuilder(int id, String name, String phoneNumber, Status status) {
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.status = status;
	}

	public int getId() {
		return id;
	}


	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", phoneNumber=" + phoneNumber + ", status=" + status + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		StudentWithBuilder other = (StudentWithBuilder) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (phoneNumber == null) {
			if (other.phoneNumber != null)
				return false;
		} else if (!phoneNumber.equals(other.phoneNumber))
			return false;
		if (status != other.status)
			return false;
		return true;
	}


	public static class Builder
	{
		private int id;
		private String name;
		private String phoneNumber;

		private Status status = Status.FULL_TIME;

		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public Builder phoneNumber(String name) {
			this.name = name;
			return this;
		}

		public Builder id(int id) {
			this.id = id;
			return this;
		}

		public Builder status(Status status) {
			this.status = status;
			return this;
		}

		public StudentWithBuilder build() {
			StudentWithBuilder swb = new StudentWithBuilder(id, name, phoneNumber, status);
			return swb;
		}

	}
}