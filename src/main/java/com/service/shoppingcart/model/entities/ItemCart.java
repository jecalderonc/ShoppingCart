package com.service.shoppingcart.model.entities;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;
/**
 * ItemCart Entity
 * 
 * @author je.calderon
 *
 */
@Getter
@Setter
@Entity
@Table(name = "itemcart")
public class ItemCart implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "itemid")
    private Integer itemid;
    @Column(name = "itemtitle")
    private String itemtitle;
    @Column(name = "itemprice")
    private Double itemprice;
    @Column(name = "quantity")
    private Integer quantity;
    @JsonIgnore
    @JoinColumn(name = "cartid", referencedColumnName = "id")
    @ManyToOne
    private Cart cartid;

    public ItemCart() {
    }

    public ItemCart(Integer id) {
        this.id = id;
    }

}

