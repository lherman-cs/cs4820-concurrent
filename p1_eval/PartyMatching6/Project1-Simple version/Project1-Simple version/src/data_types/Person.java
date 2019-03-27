package data_types;

public class Person {

	private String name;
	private boolean type; // true for superhero
	private String partnerName;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isType() {
		return type;
	}

	public void setType(boolean type) {
		this.type = type;
	}

	public String getPartnerName() {
		return partnerName;
	}

	public void setPartnerName(String partnerName) {
		this.partnerName = partnerName;
	}

	public Person(String name, boolean type, String partnerName) {
		this.name = name;
		this.type = type;
		this.partnerName = partnerName;
	}

}
