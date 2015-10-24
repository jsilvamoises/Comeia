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
public class Operaria extends Abelha {

    private int quantidadeNectar;
    private int quantidadeGeleiaReal;
    private int tempoProduzir = 1000;

    public Operaria(String nome, Rainha rainha, Deposito deposito, Mutex mutex) {
        super(nome, rainha, deposito, mutex);
        Log.msgBlack("Acaba de Nascer a Opararia " + getName());
        start();
    }

    public void produzirNectar() {
        getMutex().entrarNaRegiaoCritica();
        getDeposito().setNectar(getDeposito().getNectar() + 1);
        getMutex().sairDaRegiaoCritica();
        Log.msgBlack(getNome()+"Produziu Nectar :: Estoque atual = " + getDeposito().getNectar());
    }

    public void produzirGeleiaReal() {
        if(getIdade()<10){
            getMutex().entrarNaRegiaoCritica();
            getDeposito().setGeleiaReal(getDeposito().getGeleiaReal()+1);
            getMutex().sairDaRegiaoCritica();
            Log.msgBlack(getNome()+" Produziu Geléia Real :: Estoque atual = " + getDeposito().getGeleiaReal());
        }

    }

    @Override
    public void run() {
        while (estaViva()) {
            comer();
            
            if(getIdade()<5){
                for(int i =1; i<=3;i++){
                    produzirGeleiaReal();
                }
            }
            
            if(getIdade()>=5){
                for(int i=1;i<=10;i++){
                    int tempo =-1;
                    while(tempo++ <= 10000){};
                    produzirNectar();
                }
            }
            
            dormir();
            
        }
        Log.msgRed("$$$$$$$$$$$$$$$MORREU A OPERARIA "+getName());
    }

    private boolean estaViva() {
        return getDiaSemComer() <= 20 && getIdade() <= 20;
    }

}
