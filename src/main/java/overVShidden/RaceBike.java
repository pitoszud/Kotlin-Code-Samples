/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package overVShidden;

/**
 *
 * @author UPatryk
 */
public class RaceBike {
    public boolean isFast(){
        return true;
    }
    
    public void getRaceBikeDescription(){
        System.out.println("race bike is fast: " + isFast());
    }
}
