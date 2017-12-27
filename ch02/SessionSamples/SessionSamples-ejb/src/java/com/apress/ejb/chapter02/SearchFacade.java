/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apress.ejb.chapter02;

import java.util.List;
import javax.ejb.Remote;

/**
 * Interface de negocio @Remote se aplica cuando la app cliente web o de escritorio debe ejecutarse en una JVM diferente
 * de la que se  utiliza para ejecutar los session bean en un contenerdor EJB
 * @author barcvilla
 */
@Remote
public interface SearchFacade 
{
    // Business Methods
    public List wineSearch(String wineType); 
}
