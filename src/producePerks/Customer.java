/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package producePerks;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * @author Katie Timmerman
 * @author < your name >
 * 
 * Course: Data Structures and Algorithms
 * Semester: 
 */

public class Customer {
    private int id;
    private ArrayList<Transaction> distributed;
    private ArrayList<Transaction> redeemed;
   
   
    public Customer(int id) {
        this.id = id;
        distributed = new ArrayList();
        redeemed = new ArrayList();
    }
    
    public void addDistributed(Transaction t){
        distributed.add(t);
    }
    
    public void addRedeemed(Transaction t){
        redeemed.add(t);
    }
    
    public double getTotals(Transaction t) {
    	return t.getTotalSpentWithSNAP();
    }
    
    public void dates() {
    	for (Transaction i : redeemed) {
    		System.out.println(i.getDate());
    	}
    }
    
    
    public String toString(){
        return "ID " + id + " - Num Dist Trans " + distributed.size() 
                + " - Num Rede Trans " + redeemed.size();
    }
}
