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
public class Abelha extends Thread{
    private Rainha rainha;
    private Deposito deposito;
    private int idade=0;
    private int diaSemComer=0;
    private Mutex mutex;
    private String nome;

    public Abelha(String nome, Rainha rainha, Deposito deposito, Mutex mutex ) {
        super(nome);        
        this.rainha = rainha;
        this.deposito = deposito;
        this.mutex = mutex;
        this.nome = nome;
        mutex.registrarThread(this.getId());
    }
    
    
    
    protected boolean consumirGeleiaReal(int quantidade){
        mutex.entrarNaRegiaoCritica();
        
        if(deposito.getGeleiaReal()>=quantidade){            
            deposito.setGeleiaReal(deposito.getNectar()+(quantidade*-1));
            Log.msgBlack("Abelha "+getName()+" consumiu geléia real, saldo = "+deposito.getGeleiaReal());
            mutex.sairDaRegiaoCritica();
            return true;
        }else{
            mutex.sairDaRegiaoCritica();
            Log.msgRed("Abelha "+getName()+" não consumiu geléia real, saldo inferior ao necessãrio: saldo = "+deposito.getGeleiaReal());
            return false;
        }
        
    }
    
    protected boolean consumirNectar(int quantidade){
        mutex.entrarNaRegiaoCritica();
        if(deposito.getNectar()>=quantidade){            
            deposito.setNectar(deposito.getNectar()+(quantidade*-1));
            Log.msgBlack("Abelha "+getName()+" consumiu néctar, saldo = "+deposito.getNectar());
            mutex.sairDaRegiaoCritica();
            return true;
        }else{
            mutex.sairDaRegiaoCritica();
            Log.msgRed("Abelha "+getName()+" não néctar, saldo inferior ao necessãrio: saldo = "+deposito.getNectar());            
            return false;
        }
    }
    
    public void comer(){        
        //Caso a abelha seja operaria ou zamgão se alimenta de acordo
        if(this instanceof Operaria || this instanceof Zangao){
            //Até o terceiro dia de vida
            if(this.idade<=3){
                consumirGeleiaReal(1);
                consumirNectar(8);
            }else{//Acima do terceiro dia de vida
                consumirNectar(8);
            }
        }else if(this instanceof Rainha){
            //Caso seja rainha se alimenta de acordo
            consumirGeleiaReal(10);
        }
    }
    
    protected void dormir(){
        Log.msgRed("Abelha "+getName()+" dormindo");
        try {
            Thread.sleep(100);
        } catch (Exception e) {
        }
        this.idade++;
        Log.msgBlack("Abelha "+getName()+" acordou");
    }

    public Rainha getRainha() {
        return rainha;
    }

    public Deposito getDeposito() {
        return deposito;
    }

    public int getIdade() {
        return idade;
    }

    public int getDiaSemComer() {
        return diaSemComer;
    }

    public Mutex getMutex() {
        return mutex;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    
    
    
    
}
