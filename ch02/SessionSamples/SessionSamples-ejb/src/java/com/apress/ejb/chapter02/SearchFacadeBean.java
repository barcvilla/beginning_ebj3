/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apress.ejb.chapter02;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;

/**
 * Sample Stateless Session Bean
 * Clase que proporciona varios recursos de busqueda a las app clientes.
 * 
 * FILTRO DE TRABAJO:
 * =================
 * 1. Los usuarios escribiran o elegiran uno o mas criterios de busqueda que se enviaran al bean SearchFacade
 * 2. El bean SearchFacade accede a la BD para recuperar la informacion
 * 3. El bean SearchFacade devuelve a la app cliente la informacion correspondiente a la busqueda.
 * @author barcvilla
 */
@Stateless(name="SearchFacade")
public class SearchFacadeBean implements SearchFacade, SearchFacadeLocal
{
    public SearchFacadeBean()
    {
    }

    @Override
    public List wineSearch(String wineType) 
    {
        List wineList = new ArrayList();
        if(wineType.equals("Red"))
        {
            wineList.add("Bordeaux");
            wineList.add("Merlot");
            wineList.add("Pinot Noir");
        }
        else if(wineType.equals("White"))
        {
            wineList.add("Chardonay");
        }
        
        return wineList;
    }
    
    
}
