/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mist
 */
public class DtoFactory {

    /**
     * Vraci objekt DTO vytvoreny na zaklade entity
     * @param entity entita z ktere chceme vytvorit DTO
     * @return objekt DTO
     */
    static public Object convertToDto(Object entity) {
        Object o;
        try {
            // ziskame tridu dto
            Class dtoClass = Class.forName("dto." + entity.getClass().getSimpleName() + "DTO");
            // vytvorime objekt tridy dto
            o = dtoClass.newInstance();
            // proiterujeme public pole tridy dto
            Field[] fields = entity.getClass().getDeclaredFields();
            for (Field field : fields) {
                try {
                    // zjistime nazev metody getteru
                    String getMethodName = Character.toUpperCase(field.getName().charAt(0)) + field.getName().substring(1);
                    // zkusime, jestli ma field getter
                    if (dtoClass.getMethod("get" + getMethodName) != null) {
                        //System.out.println(field.getName());
                        // precteme hodnotu z entity
                        Object value = new PropertyDescriptor(field.getName(), entity.getClass()).getReadMethod().invoke(entity);
                        // nastavime hodnotu objektu o hodnotou, ktere ma pole
                        Method wr = o.getClass().getMethod("set" + getMethodName, field.getType());
                        Object invoke = wr.invoke(o, value);
                    }
                } catch (NoSuchMethodException e) {
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
    static public List convertArrayToDto(List entities) {
        ArrayList list = new ArrayList();
        if (entities.size() < 1)
            return null;
        try {
            Method add = List.class.getDeclaredMethod("add", Object.class);
            // naplnime list
            for (Object entity : entities)
                add.invoke(list, convertToDto(entity));
            
        } catch (Exception e) {
            Logger.getLogger(DtoFactory.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
       
        return list;
    }
    
}
