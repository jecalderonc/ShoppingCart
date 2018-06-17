package com.service.shoppingcart.model.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * Cart Entity
 * 
 * @author je.calderon
 *
 */
@Getter
@Setter
@Entity
@Table(name = "cart")
public class Cart implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "description")
    private String description;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "cartid")
    private List<ItemCart> itemcartList;

    public Cart() {
    }

    public Cart(Integer id) {
        this.id = id;
    }
    
    public Double getTotalAmount() {
    	double a = 0;
    	a = (this.itemcartList != null && itemcartList.size() >0)? itemcartList.stream().mapToDouble(s -> s.getQuantity() * s.getItemprice()).sum() : 0;
    	return a;
    }
}
