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
import lombok.NoArgsConstructor;

@Entity
//@Getter
//@Setter
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
    @JoinColumn(name = "order_id")
    private Order order;
    
    public Payment(Integer id, MethodPayment methodPayment, StatusPayment statusPayment, LocalDateTime datePayment) {
        this.id = id;
        this.methodPayment = methodPayment;
        this.statusPayment = statusPayment;
        this.datePayment = datePayment;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public MethodPayment getMethodPayment() {
        return methodPayment;
    }

    public void setMethodPayment(MethodPayment methodPayment) {
        this.methodPayment = methodPayment;
    }

    public StatusPayment getStatusPayment() {
        return statusPayment;
    }

    public void setStatusPayment(StatusPayment statusPayment) {
        this.statusPayment = statusPayment;
    }

    public LocalDateTime getDatePayment() {
        return datePayment;
    }

    public void setDatePayment(LocalDateTime datePayment) {
        this.datePayment = datePayment;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
