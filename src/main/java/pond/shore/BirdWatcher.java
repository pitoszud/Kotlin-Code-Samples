/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pond.shore; // same package as Bird

public class BirdWatcher {
    public void watchBird() {
        Bird bird = new Bird();
        bird.floatInWater(); // calling protected member
        System.out.println(bird.text); // calling protected member
        
        // this.floatInWater(); // DOES NOT COMPILE - BirdWatcher does not extends Bird
        
        
    }
}