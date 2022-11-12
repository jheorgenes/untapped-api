package com.pj.untapped.domain.enuns;

public enum StatusPayment {

	DISPONIVEL(0, "DISPONIVEL"),
	PAGO(1, "PAGO"),
	CANCELADO(2, "CANCELADO"),
	INDISPONIVEL(3, "INDISPONIVEL");
	
	private Integer cod;
    private String description;
    
    private StatusPayment(Integer cod, String description) {
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
    
    public static StatusPayment toEnum(Integer cod) {
        if(cod == null) return null;
        
        for(StatusPayment x : StatusPayment.values()) {
            if(cod.equals(x.getCod())) return x;
        }
        throw new IllegalArgumentException("Invalid status! " + cod);
    }
}
