/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseprojectcinema;

/**
 *
 * @author fcbar
 */
public class mostsold {
    
    int fid;
    double rev;

    public mostsold(int fid, double rev) {
        this.fid = fid;
        this.rev = rev;
    }

    public int getFid() {
        return fid;
    }

    public void setFid(int fid) {
        this.fid = fid;
    }

   

    public double getRev() {
        return rev;
    }

    public void setRev(double rev) {
        this.rev = rev;
    }
    
    
    
}
