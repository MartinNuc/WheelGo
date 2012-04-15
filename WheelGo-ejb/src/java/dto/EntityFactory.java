/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import anotations.DtoConnection;
import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author mist
 */
@Stateless
public class EntityFactory {
    @PersistenceContext(unitName = "WheelGo-ejbPU")
    protected EntityManager em;
     /**
     * Vraci entitu vytvorenou na zaklade DTO
     * @param DTO z ktereho chceme tvorit Entitu
     * @return objekt Entity
     */
    public Object convertToEntity(Object dto) {
        Object o;
        Object value = null;
        String getMethodName = null;
        try {
            // ziskame tridu entity
            if (dto.getClass().getSimpleName().endsWith("DTO") == false)
                throw new Exception("DTO class doesnt end with DTO postfix.");
            String dtoClassName = dto.getClass().getSimpleName();
            String entityClassName = dtoClassName.substring(0, dtoClassName.length()-3);
            Class entityClass = Class.forName("model." + entityClassName);
            // vytvorime objekt tridy entity
            o = entityClass.newInstance();
            // proiterujeme pole tridy dto
            Field[] fields = dto.getClass().getDeclaredFields();
            for (Field field : fields) {
                try {
                    // zjistime nazev metody getteru
                    getMethodName = Character.toUpperCase(field.getName().charAt(0)) + field.getName().substring(1);
                    // zkusime, jestli ma field getter
                    System.out.println("wokring on " + "get" + getMethodName);
                    if (entityClass.getMethod("get" + getMethodName) != null) {
                        //System.out.println(field.getName());
                        // precteme hodnotu z dto
                        value = new PropertyDescriptor(field.getName(), dto.getClass()).getReadMethod().invoke(dto);
                        // nastavime hodnotu objektu o hodnotou, ktere ma pole
                        Method wr = o.getClass().getMethod("set" + getMethodName, field.getType());
                        wr.invoke(o, value);
                    }
                } catch (NoSuchMethodException e)
                {
                    try
                    {
                        // pokud neexistuje setter, zkusime jestli to neni jen ID
                        Annotation a = field.getAnnotation(DtoConnection.class);
                        if(a instanceof DtoConnection)
                        {
                            DtoConnection dtoConnection = (DtoConnection) a;
                            Class anotClass = Class.forName(dtoConnection.entity());
                            Object foundValue = em.find(anotClass, value);
                            // zkusime, zda ma metoda setter na ID
                            Method wr = o.getClass().getMethod("set" + getMethodName, foundValue.getClass());
                            // nasetujem
                            wr.invoke(o, foundValue);
                        }
                    }
                    catch (NoSuchMethodException e2)
                    {
                        System.err.println(e2);

                    }
                }
            }
        } catch (Throwable e) {
            System.err.println(e);
            return null;
        }

        return o;
    }
    
    /**
     * Slouzi k zpracovani kolekce entit
     * @param entities kolekce entit
     * @return objekt typu List obsahujici DTO objekty
     */
    public List convertArrayToEntities(List dtos) {
        ArrayList list = new ArrayList();
        if (dtos.size() < 1)
            return null;
        try {
            Method add = List.class.getDeclaredMethod("add", Object.class);
            // naplnime list
            for (Object dto : dtos)
                add.invoke(list, convertToEntity(dto));
            
        } catch (Exception e) {
            Logger.getLogger(DtoFactory.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
       
        return list;
    }
}
