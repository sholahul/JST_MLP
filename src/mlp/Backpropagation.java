/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mlp;

import java.util.Random;

/**
 *
 * @author Sholahul Fajri
 */
class Backpropagation {
    final float Learning_rate = 0.5f;
    final int randomWeightMultiplier = 1;
    float init_t[] = {0,1};
    int init_hidden = 4;
    int init_maxloop = 1000;
    double init_Emse = 0.01;
    float input[][] = new float[304][13];
    
    public Backpropagation(float inputlayer[][]) {
        input = inputlayer;
    }
    
    void propagation(){
        
    }
    
    public float getRandom(){
        Random rand = new Random();
        return randomWeightMultiplier * (rand.nextFloat()*2 - 1);
    }
    
    
}
