/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apress.ejb.chapter02;

import java.util.List;
import javax.ejb.Local;

/**
 * Interface de negocio @Local se aplica cuando la app cliente web o de escritorio debe ejecutarse en la misma JVM 
 * dond se ejecutan los session bean en un contenerdor EJB. En otras palabras, cuando la app cliente y server se 
 * ejecutan en la misma JVM las interfaces de negocios llevan a anotacion @Local
 * @author barcvilla
 */
@Local
public interface SearchFacadeLocal {
    // Business Methods
    public List wineSearch(String wineType);
}
