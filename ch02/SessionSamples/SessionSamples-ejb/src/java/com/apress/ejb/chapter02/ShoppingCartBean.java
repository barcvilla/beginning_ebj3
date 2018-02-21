/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apress.ejb.chapter02;

import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Remove;
import javax.ejb.Stateful;

/**
 *
 * @author barcvilla
 */
@Local({ShoppingCartLocal.class})
@Remote({ShoppingCart.class})
@Stateful(name="ShoppingCart")
public class ShoppingCartBean implements ShoppingCart, ShoppingCartLocal
{
    public ArrayList cartItems;
    
    public ShoppingCartBean(){}
    
    public void addWineItem(String wine)
    {
        cartItems.add(wine);
    }
    
    public void removeWineItem(String wine)
    {
        cartItems.remove(wine);
    }
    
    public void setCartItems(ArrayList cartItems)
    {
        this.cartItems = cartItems;
    }
    
    @PostConstruct
    public void initialized()
    {
        cartItems = new ArrayList();
    }
    
    @PreDestroy
    public void exit()
    {
        System.out.println("Saved items listo into DataBase");
    }
    
    @Remove
    public void stopSession()
    {
        System.out.println("From stopSession method with @Remove annotacion");
    }
}
