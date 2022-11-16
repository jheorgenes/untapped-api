package com.pj.untapped.dtos;

import java.time.LocalDateTime;

import com.pj.untapped.domain.Order;
import com.pj.untapped.domain.Payment;
import com.pj.untapped.domain.enuns.MethodPayment;
import com.pj.untapped.domain.enuns.StatusPayment;

import lombok.NoArgsConstructor;

//@Getter
//@Setter
@NoArgsConstructor
public class PaymentDTO {

    private Integer id;
    private MethodPayment methodPayment;
    private StatusPayment statusPayment;
    private LocalDateTime datePayment;
    private Order order;
    
    public PaymentDTO(Payment obj) {
        this.id = obj.getId();
        this.methodPayment = obj.getMethodPayment();
        this.statusPayment = obj.getStatusPayment();
        this.datePayment = obj.getDatePayment();
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
