/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author note-moises
 */
public class Mutex {

    private ConcurrentHashMap<Long, Status> statuses = new ConcurrentHashMap<>();

    public void registrarThread(Long key) {
        statuses.put(key, new Status());
    }

    private boolean getChoosing(Long key) {
        return statuses.get(key).isChoosing();
    }

    public void setChoosing(long key, boolean value) {
        try {
            statuses.get(key).setChoosing(value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getNewTicket() {
        int maxTicket = -1;
        Set<Long> keys = statuses.keySet();
        for (Long key : keys) {
            if (statuses.get(key).ticket > maxTicket) {
                maxTicket = (int) statuses.get(key).ticket;
            }
        }
        setTicket((int) Thread.currentThread().getId(), maxTicket + 1);
    }

    public void setTicket(long key, int value) {
        try {
            statuses.get(key).ticket = value;
        } catch (Exception e) {
            System.err.println(String.format("Não existe thread registrada com o id %s.", key));
        }
    }

    public long getTicket(long key) {
        return statuses.get(key).getTicket();
    }

    public void entrarNaRegiaoCritica() {
        String nomeThreadCandidata = Thread.currentThread().getName();
        Long idThreadCandidata = Thread.currentThread().getId();
        setChoosing(idThreadCandidata, true);
        System.out.println(String.format("%s está tentando obter uma senha para acessar uma seção crítica.", nomeThreadCandidata));
        getNewTicket();
        System.out.println(String.format("%s conseguiu a senha nº %s.", nomeThreadCandidata, getTicket(idThreadCandidata)));
        setChoosing(idThreadCandidata, false);
        System.out.println(String.format("%s vai verificar se mais alguma thread pretende acessar a região crítica.", nomeThreadCandidata));
        Set<Long> keys = statuses.keySet();
        for (Long idThreadConcorrente : keys) {
            if (idThreadConcorrente.equals(idThreadCandidata)) {
                continue;
            }
            while (getChoosing(idThreadConcorrente));
            while (getTicket(idThreadConcorrente) != 0 && getTicket(idThreadConcorrente) < getTicket(idThreadCandidata));
            if (getTicket(idThreadConcorrente) == getTicket(idThreadCandidata) && idThreadConcorrente < idThreadCandidata) {
                while (getTicket(idThreadConcorrente) != 0) {
                };
            }
        }
        System.out.println(String.format("%s conseguiu acessar a região crítica.", nomeThreadCandidata));
    }

    private void stop(int milles) {
        try {
            Thread.sleep(milles);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sairDaRegiaoCritica() {
        setTicket(Thread.currentThread().getId(), 0);
        System.out.println(String.format("%s descartou sua senha.", Thread.currentThread().getName()));
        
        System.gc();
    }
}
