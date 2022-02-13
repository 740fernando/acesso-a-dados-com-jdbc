package model.entities;

import java.io.Serializable;

public class Department implements Serializable {

	/**
	 * implements Serializable - Permite que os objetos possam ser transformados em
	 * sequencia de byte. Na linguagem Java, é necessário implements Serializable
	 * para que nosso objeto seja gravado em arquivo, redes e etc
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String name;

	public Department() {

	}

	public Department(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	// Método criado para que os objetos sejam comparados pelo conteúdo e não pela
	// refêrencia de ponteiros
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Department other = (Department) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Department [id=" + id + ", name=" + name + "]";
	}
}
