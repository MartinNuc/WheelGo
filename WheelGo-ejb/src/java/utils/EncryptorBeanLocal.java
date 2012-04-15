/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import javax.ejb.Local;

/**
 *
 * @author vlada
 */
@Local
public interface EncryptorBeanLocal {

    String encryptPassword(String password, String dynamicSeed);
}
