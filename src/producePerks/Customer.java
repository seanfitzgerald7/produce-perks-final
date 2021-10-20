/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package producePerks;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Katie Timmerman
 * @author Sean Fitzgerald
 * 
 * Course: Data Structures and Algorithms
 * Semester: 
 */

public class Customer {
    private int id;
    private ArrayList<Transaction> distributed;
    private ArrayList<Transaction> redeemed;
    private double beg, mid, end, zero; 
    private double p1, p2, p3, p4; // percentage variables
   
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
    
    // Methods used for debugging (Not used for Question)
    public double getRedeemedTotals() {
    	double redeemedCount = 0;
    	for (int i = 0; i < redeemed.size() - 1; i++) {
    		if (redeemed.get(i).getTotalSpentWithSNAP() < 3) continue;
    		else {
    			redeemedCount += redeemed.get(i).getTotalSpentWithSNAP();
    		}
    	}
    	System.out.println(redeemedCount);
    	return redeemedCount;
    }
    
    public double getDistTotals() {
    	double count = 0;
    	for (int i = 0; i < distributed.size() - 1; i++) {
    		if (distributed.get(i).getTotalSpentWithSNAP() < 3) continue;
    		else {
    			count += distributed.get(i).getTotalSpentWithSNAP();
    		}
    	}
    	System.out.println(count);
    	return count;
    }
    
    /**
     * TotalsByDate
     * Function to collect total spending amounts with snap for each part of the month using DateTime
     * Print functions at the end of this code are for debugging purposes
     * This is called in ProducePerksDriver to write to "transactions.csv"
     * @throws FileNotFoundException
     */
    
    public void totalsByDate() throws FileNotFoundException {
    	beg = mid = end = 0;
    	zero = 0;
     	for (int i = 0; i < redeemed.size(); i++) {
     		double temp = redeemed.get(i).getTotalSpentWithSNAP();
     		if (temp < 3) {
     			zero++;
     		}
     		else {
     			if (redeemed.get(i).getDate().getDayOfMonth() <= 10) {
     				beg += temp;
     			} else if (redeemed.get(i).getDate().getDayOfMonth() > 10 && redeemed.get(i).getDate().getDayOfMonth() < 21) {
     				mid += temp;
     			} else {
     				end += temp;
     			}
     		}
     	}
     	
    	
		if (beg > 0) System.out.println("Beginning of Month: " + beg);
		if (mid > 0) System.out.println("Middle of Month: " + mid);
		if (end > 0) System.out.println("End of Month: " + end);
    	if (zero != 0) System.out.println("Zero Dollar Transactions: " + zero);
    	
    	// Calculating percentages
    	p1 = p2 = p3 = p4 = 0;
    	p1 = ((beg) / (beg + mid + end)) * 100;
    	p2 = ((mid) / (beg + mid + end)) * 100;
    	p3 = ((end) / (beg + mid + end)) * 100;
    	p4 = (zero) / (beg + mid + end) * 100;
    	
    	if (!Double.isNaN(p1)) System.out.println("Beginning of Month Percentage: " + p1);
    	if (!Double.isNaN(p2)) System.out.println("Middle of Month Percentage: " + p2);
    	if (!Double.isNaN(p3)) System.out.println("End of Month Percentage: " + p3);
    	if (!Double.isNaN(p4) && Double.isFinite(p4)) System.out.println("Zero Dollar Transaction Percentage: " + p4);

    	
    	
    }
    
    public int getID() {
    	return this.id;
    }
    
    public double getBeg() {
    	return this.beg;
    }
    
    public double getMid() {
    	return this.mid;
    }
    public double getEnd() {
    	return this.end;
    }
    public double getBegPercentage() {
    	return this.p1;
    }
    public double getMidPercentage() {
    	return this.p2;
    }
    public double getEndPercentage() {
    	return this.p3;
    }
    
    public double getZeros() {
    	return this.zero;
    }
    
    public String toString(){
        return "ID " + id + " - Num Dist Trans " + distributed.size() 
                + " - Num Rede Trans " + redeemed.size();
    }

	public double getZeroPercentage() {
		return this.p4;
	}
}
