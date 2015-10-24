/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author note-moises
 */
public class Rainha extends Abelha{
    private boolean gravida= false;
    private int crias=0;
    private List<Operaria> operarias = new ArrayList<>();
    private List<Zangao> zamgoes = new ArrayList<>();
    

    public Rainha(String nome, Rainha rainha, Deposito deposito, Mutex mutex) {
        super(nome+activeCount(), rainha, deposito, mutex);
        Log.msgBlack("Acaba de Nascer a Rainha "+getName());
    }
    
    protected boolean acasalar(){
        if(!gravida && getIdade()<=1000){
            Log.msgRed("#RAINHA -> Rainha fazendo Sacanagem");
            gravida = true;
            return true;
        }else{
            return false;
        }
        
    }
    
    private void reproduzir(){
        if(gravida){
            for(int i = 1; i<=2; i++){               
                operarias.add(new Operaria("Operaria O_"+activeCount(), null, getDeposito(), getMutex()));
                
            }
            
            for(int i = 1; i<=1; i++){               
               zamgoes.add(new Zangao("Zangao Z_"+activeCount(), this, getDeposito(), getMutex()));
               
            }
            
            crias++;
            gravida=false;
            
            Log.msgRed("Abelha rainha deu crias");
        }
    }

    @Override
    public void run() {
        while(estaViva()){
            Log.msgBlack(">>>>>>>>########## Hoje a rainha completa "+getIdade()+" dias");
            comer();
            
            if(getIdade() % 5 == 0){
                reproduzir();
            }
            
            dormir();
        }
        
        Log.msgRed("$$$$$$$$$$$$$$$MORREU A Rainha "+getName());
    }
    
    private boolean estaViva(){
        return getIdade()<=1825 && getDiaSemComer()<=100;
    }
    
}
