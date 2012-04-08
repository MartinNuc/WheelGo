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
public class EntityFactory {
        /**
     * Vraci objekt DTO vytvoreny na zaklade entity
     * @param entity entita z ktere chceme vytvorit DTO
     * @return objekt DTO
     */
    static public Object convertToEntity(Object dto) {
        Object o = null;
        try {
            // ziskame tridu entity
            String test = dto.getClass().getSimpleName();
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
                    String getMethodName = Character.toUpperCase(field.getName().charAt(0)) + field.getName().substring(1);
                    // zkusime, jestli ma field getter
                    if (entityClass.getMethod("get" + getMethodName) != null) {
                        //System.out.println(field.getName());
                        // precteme hodnotu z dto
                        Object value = new PropertyDescriptor(field.getName(), dto.getClass()).getReadMethod().invoke(dto);
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
    static public List convertArrayToEntities(List dtos) {
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
