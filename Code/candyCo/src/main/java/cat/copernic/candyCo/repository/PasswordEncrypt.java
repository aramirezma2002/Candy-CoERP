/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.candyCo.repository;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author marco
 */
public class PasswordEncrypt {

    public static String passwordEncrypter(String pass) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(pass);
    }
    
    public static Boolean passwordDecrypter(String pass, String encrytedPass){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(pass, encrytedPass);
    }

    public static void main(String[] args) {
        String pass = "pepe";
        System.out.println(passwordEncrypter(pass));
    }
}
