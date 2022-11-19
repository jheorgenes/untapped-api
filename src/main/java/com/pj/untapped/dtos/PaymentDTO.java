package com.pj.untapped.dtos;

import java.time.LocalDateTime;

import com.pj.untapped.domain.Order;
import com.pj.untapped.domain.Payment;
import com.pj.untapped.domain.enuns.MethodPayment;

import lombok.NoArgsConstructor;

//@Getter
//@Setter
@NoArgsConstructor
public class PaymentDTO {

    private Integer id;
    private Integer methodPayment;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Boolean status;
    private Order order;
    
    public PaymentDTO(Payment obj) {
        this.id = obj.getId();
        this.methodPayment = obj.getMethodPayment().getCod();
        this.createdAt = obj.getCreatedAt();
        this.updatedAt = obj.getUpdatedAt();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public MethodPayment getMethodPayment() {
        return MethodPayment.toEnum(this.methodPayment);
    }

    public void setMethodPayment(MethodPayment methodPayment) {
        this.methodPayment = methodPayment.getCod();
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
    
    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
