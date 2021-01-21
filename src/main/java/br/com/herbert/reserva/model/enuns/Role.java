package br.com.herbert.reserva.model.enuns;


public enum Role {

	ADMIN(1, "ROLE_ADMIN"),
	CLIENTE(2, "ROLE_CLIENTE");
	
	private int codigo;
	private String descricao;
	
	private Role(int codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}
	
	public int getCodigo() {
		return codigo;
	}
	
	public String getDescricao () {
		return descricao;
	}
	
	public static Role toEnum(Integer cod) {
		
		if (cod == null) {
			return null;
		}
		
		for (Role role : Role.values()) {
			if (cod.equals(role.getCodigo())) {
				return role;
			}
		}
		
		throw new IllegalArgumentException("Código inválido: " + cod);
	}
}

