package com.pj.untapped.domain.enuns;

public enum StatusTicket {

	DISPONIVEL(0, "DISPONIVEL"),
	RESERVADO(1, "RESERVADO"),
	CANCELADO(2, "CANCELADO"),
	INDISPONIVEL(3, "INDISPONIVEL"),
	VENDIDO(4, "VENDIDO");
	
	private Integer cod;
    private String description;
    
    private StatusTicket(Integer cod, String description) {
        this.cod = cod;
        this.description = description;
    }

    public Integer getCod() {
        return cod;
    }

    public void setCod(Integer cod) {
        this.cod = cod;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public static StatusTicket toEnum(Integer cod) {
        if(cod == null) return null;
        
        for(StatusTicket x : StatusTicket.values()) {
            if(cod.equals(x.getCod())) return x;
        }
        throw new IllegalArgumentException("Invalid status! " + cod);
    }
}
