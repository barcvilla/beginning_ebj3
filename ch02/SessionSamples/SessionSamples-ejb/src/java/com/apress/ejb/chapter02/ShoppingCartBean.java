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
    
    /**
     * este evento callback ocurre despues que la instancia de un bean ha sido instanciado en el contendor EJB
     * Si el bean no usa ningun mecanismo de inyeccion de dependencia para adquirir referencias a recursos u otro
     * objetos en su ambiente, el evento @PostConstruct ocurre luego que injection es llevado a cabo y antes que
     * el primero evento de negocio en el bean es llamado.
     */
    @PostConstruct
    public void initialized()
    {
        cartItems = new ArrayList();
    }
    
    /**
     * El evento callback @PreDestroy ocurre luego que cualquier metodo donde la anotacion @Remove ha sido completado
     * En este caso ShoppingCart tenemos el metodo exit() que escribe la lista cartItems en una base de datos.
     */
    @PreDestroy
    public void exit()
    {
        System.out.println("Saved items listo into DataBase");
    }
    
    /**
     * El evento callback @Remove es un evento muy util para un session bean StateFul. Cuando el metodo con la 
     * anotacion @Remove es llamada, el contenedor removera la instancia del bean del object pool luego que el metodo
     * es ejecutado. 
     */
    @Remove
    public void stopSession()
    {
        System.out.println("From stopSession method with @Remove annotacion");
    }
}
