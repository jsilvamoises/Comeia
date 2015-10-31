/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author note-moises
 */
public class Colmeia {
    public static void main(String[] args) {
        
        Mutex mutex = new Mutex();
        Deposito deposito = new Deposito();
        Rainha  rainha= new Rainha("Rainha R_", null, deposito, mutex);        
        rainha.start();
        
        Zangao zangao = new Zangao("Zangao Z_", rainha, deposito, mutex);
        
       // zangao.start();
        
    }
    
}
