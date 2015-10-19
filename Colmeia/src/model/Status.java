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
public class Status {
    private boolean choosing;
    private int ticket;

    public Status() {
    }

    public boolean isChoosing() {
        return choosing;
    }

    public void setChoosing(boolean choosing) {
        this.choosing = choosing;
    }

    public int getTicket() {
        return ticket;
    }

    public void setTicket(int ticket) {
        this.ticket = ticket;
    }
    
    
}
