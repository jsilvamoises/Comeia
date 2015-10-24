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
    private boolean choosing= false;
    long ticket=0;

    public Status() {
    }

    public boolean isChoosing() {
        return choosing;
    }

    public void setChoosing(boolean choosing) {
        this.choosing = choosing;
    }

    public long getTicket() {
        return ticket;
    }

    public void setTicket(long ticket) {
        this.ticket = ticket;
    }
    
    
}
