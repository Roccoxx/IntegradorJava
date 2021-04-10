package ar.com.gugler.dao;

public abstract class BaseModelo {
	protected Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
