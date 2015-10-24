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
public class Zangao extends Abelha {
    private boolean acasalou=false;

    public Zangao(String nome, Rainha rainha, Deposito deposito, Mutex mutex) {
        super(nome, rainha, deposito, mutex);
        Log.msgBlack("Acaba de Nascer o Zangão "+getName());
        start();
    }

    private void tentarAcasalar(){
        acasalou = getRainha().acasalar();
    }

    

    @Override
    public void run() {
        while (isVivo()) {
             Log.msgBlack("Hoje o Zangao"+getName()+" completa "+getIdade()+" dias");
            comer();
            tentarAcasalar();
            dormir();
        }
        Log.msgRed("$$$$$$$$$$$$$$$MORREU O ZANGÃO "+getName());
    }
    //Verifica se ainda está vivo
    private boolean isVivo() {
        return getDiaSemComer() <= 30 && !acasalou && getIdade()<=40;
    }

    

}
