/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mkyong.rest;

import calcularhash2.CalcularHash2;
import calcularhash2.Hash;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.o7planning.restfulcrud.model.Archivo;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.DatatypeConverter;
import org.apache.commons.io.FileUtils;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.ManagedTransaction;
import org.web3j.tx.Contract;

/**
 *
 * @author fabian.giraldo
 * 
 * http://localhost:8090/myApp/rest/archivo
 * 
 * Peticion POST
 * {
   "url" : "https://scontent.xx.fbcdn.net/v/t1.15752-9/59924244_1081744018689913_8673932972509888512_n.jpg?_nc_cat=103&_nc_ad=z-m&_nc_cid=0&_nc_zor=9&_nc_ht=scontent.xx&oh=ab7fb27dad50e75db692909165cc5242&oe=5D755C63",
   "hash" : "",
   "autor": ""
   
}
 */
@Path("archivo")
public class ArchivoServicio {
   
   
 
    // URI:
    // /contextPath/servletPath/employees
    @POST
    @Produces({ MediaType.APPLICATION_JSON })
    public Archivo certificarArchivo(Archivo archivo) throws IOException, CipherException, Exception {
               
        String name = archivo.getUrl().substring(0,archivo.getUrl().indexOf("?"));
        System.out.println(name);
        name = name.substring(name.lastIndexOf("/")+1,name.length());
        System.out.println(name);
         String sha=  null;
        try{
            
            FileUtils.copyURLToFile( new URL(archivo.getUrl()),  new File(name), 1000,  1000);
            File file = new File(name);
           
            System.out.println("MD5    : " + toHex(Hash.MD5.checksum(file)));
            System.out.println("SHA1   : " + toHex(Hash.SHA1.checksum(file)));
            sha = toHex(Hash.SHA256.checksum(file));
            System.out.println("SHA256 : " + toHex(Hash.SHA256.checksum(file)));
            System.out.println("SHA512 : " + toHex(Hash.SHA512.checksum(file)));
            
        } catch (MalformedURLException ex) {
            Logger.getLogger(CalcularHash2.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CalcularHash2.class.getName()).log(Level.SEVERE, null, ex);
        }
   
      archivo.setHash(sha);
        return archivo;
    }
      private static String toHex(byte[] bytes) {
        return DatatypeConverter.printHexBinary(bytes);
    }
}
