package com.pj.untapped.domain.enuns;

public enum MethodPayment {

    DINHEIRO(0, "DINHEIRO"),
	CARTAOCREDITO(1, "CARTAOCREDITO"),
	CARTAODEBITO(2, "CARTAODEBITO"),
	PIX(3, "PIX"),
	BOLETO(4, "BOLETO");
	
	private Integer cod;
    private String description;
    
    private MethodPayment(Integer cod, String description) {
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
    
    public static MethodPayment toEnum(Integer cod) {
        if(cod == null) return null;
        
        for(MethodPayment x : MethodPayment.values()) {
            if(cod.equals(x.getCod())) return x;
        }
        throw new IllegalArgumentException("Invalid status! " + cod);
    }
}
