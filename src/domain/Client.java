package domain;

import java.util.List;

public class Client extends Entity{
	private String name;
	private List<String> adreses;
	private List<String> phones;
	public List<String> getAdreses() {
		return adreses;
	}

	public void setAdreses(List<String> adreses) {
		this.adreses = adreses;
	}

	public List<String> getPhones() {
		return phones;
	}

	public void setPhones(List<String> phones) {
		this.phones = phones;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
