package com.pj.untapped.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pj.untapped.domain.enuns.MethodPayment;
import com.pj.untapped.domain.enuns.StatusPayment;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private MethodPayment methodPayment;
    private StatusPayment statusPayment;
    private LocalDateTime datePayment;
    
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "reserveId")
    private Reserves reserve;
}
