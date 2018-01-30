/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apress.ejb.chapter02;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

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
    
    HashMap countryMap = new HashMap();

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
    
    /**
     * Este CallBack ocurre despues que una instancia de un bean se crea. Por tanto, se inicializara una lista
     * de vinos del pais cade vez que el bean sea instanciado.
     */
    @PostConstruct
    public void initializeCountryWineList()
    {
        countryMap.put("Australia", "Sauvignon Blanc");
        countryMap.put("Austrilia", "Grenache");
        countryMap.put("France","Gewurztraminer");
        countryMap.put("France","Burdeos");
    }
    
    /**
     * El Callback PreDestroy ocurre antes que el contenedor destruya un bean no usado o expirado en su pool de objetos
     * Este Callback puede ser usado para cerrar  cualquier conexion pool que ha sido creado con dependency injection
     */
    @PreDestroy
    public void destroyWineList()
    {
        countryMap.clear();
    }
    
    // se declara un metodo interceptor
    @AroundInvoke
    public Object TimerLog(InvocationContext ctx) throws Exception
    {
        String beanClassName = ctx.getClass().getName();
        String businessMethodName = ctx.getMethod().getName();
        String target = beanClassName + "." + businessMethodName;
        long startTime = System.currentTimeMillis();
        System.out.println("Invoking: "+ target);
        try
        {
            return ctx.proceed();
        }
        finally
        {
            System.out.println("Exiting: " + target);
            long totalTime = System.currentTimeMillis() - startTime;
            System.out.println("Business method: " + businessMethodName + "in " + beanClassName + "takes " + 
                    totalTime + "ms to execute");
        }
    }
}
