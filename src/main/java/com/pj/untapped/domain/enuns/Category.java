package com.pj.untapped.domain.enuns;

public enum Category {

	ENTRETERIMENTO(0, "ENTRETERIMENTO"),
	SERTANEJO(1, "SERTANEJO"),
	PAGODE(2, "PAGODE"),
	SAMBA(3, "SAMBA"),
	RAP(4, "RAP"),
	FUNK(5, "FUNK"),
	HIPHOP(6, "HIPHOP"),
	MPB(7, "MPB"),
	POP(8, "POP"),
	ELETRONICA(9, "ELETRONICA"),
	ROCK(10, "ROCK"),
	GOSPEL(11, "GOSPEL"),
	STANDUP(12, "COMEDIA"),
	CINEMA(13, "CINEMA"),
	TEATRO(14, "TEATRO");
	
	private Integer cod;
    private String description;
    
    private Category(Integer cod, String description) {
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
    
    public static Category toEnum(Integer cod) {
        if(cod == null) return null;
        
        for(Category x : Category.values()) {
            if(cod.equals(x.getCod())) return x;
        }
        throw new IllegalArgumentException("Invalid category! " + cod);
    }
}
