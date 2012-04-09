/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;

/**
 *
 * @author vlada
 */
@Stateless
public class EncryptorBean implements EncryptorBeanLocal {

    public static final String staticSeed = "VrrrrrXrrrrr";

    @Override
    public String encryptPassword(String password, String dynamicSeed) {
        try {

            MessageDigest md;
            md = MessageDigest.getInstance("MD5");

            String passwd = password + dynamicSeed + staticSeed;

            md.update(passwd.getBytes("utf-8"), 0, passwd.length());
            byte[] md5hash = md.digest();


            return convertToHex(md5hash);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(EncryptorBean.class.getName()).log(Level.SEVERE, "Problem with algorithm", ex);
        } catch(UnsupportedEncodingException ex) {
            Logger.getLogger(EncryptorBean.class.getName()).log(Level.SEVERE, "Problem with encoding", ex);
        }

        return null;

    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    private static String convertToHex(byte[] data) {
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < data.length; i++) {
            int halfbyte = (data[i] >>> 4) & 0x0F;
            int two_halfs = 0;
            do {
                if ((0 <= halfbyte) && (halfbyte <= 9)) {
                    buf.append((char) ('0' + halfbyte));
                } else {
                    buf.append((char) ('a' + (halfbyte - 10)));
                }
                halfbyte = data[i] & 0x0F;
            } while (two_halfs++ < 1);
        }
        return buf.toString();
    }
}
