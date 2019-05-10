/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calcularhash2;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.DatatypeConverter;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author fabian.giraldo
 */
public class CalcularHash2 {

    public void calcularHash(String url){
        String name = url.substring(0,url.indexOf("?"));
        System.out.println(name);
        name = name.substring(name.lastIndexOf("/")+1,name.length());
        System.out.println(name);
        try{
            
            FileUtils.copyURLToFile( new URL(url),  new File(name), 1000,  1000);
            File file = new File(name);
            System.out.println("MD5    : " + toHex(Hash.MD5.checksum(file)));
            System.out.println("SHA1   : " + toHex(Hash.SHA1.checksum(file)));
            System.out.println("SHA256 : " + toHex(Hash.SHA256.checksum(file)));
            System.out.println("SHA512 : " + toHex(Hash.SHA512.checksum(file)));
            
        } catch (MalformedURLException ex) {
            Logger.getLogger(CalcularHash2.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CalcularHash2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     private static String toHex(byte[] bytes) {
        return DatatypeConverter.printHexBinary(bytes);
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       CalcularHash2 hash = new CalcularHash2();
       hash.calcularHash("https://scontent.xx.fbcdn.net/v/t1.15752-9/59924244_1081744018689913_8673932972509888512_n.jpg?_nc_cat=103&_nc_ad=z-m&_nc_cid=0&_nc_zor=9&_nc_ht=scontent.xx&oh=ab7fb27dad50e75db692909165cc5242&oe=5D755C63");
               
        
    }
    
}
