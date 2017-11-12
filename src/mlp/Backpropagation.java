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
    
    public int init_hidden = 4;
    public int init_output =2;
    public int init_input = 303;
    int init_maxloop = 1000;
    float input[][] = new float[init_input][13];
    float target[] = new float[init_input];
    
    float[][] set_bobot_input_to_hidden = new float[304][13];
    float[][] set_bobot_hidden_to_output = new float[init_hidden][init_output];
    
    float set_bobot_bias_to_hidden[] = new float[init_hidden];
    float set_bobot_bias_to_output[] = new float[init_output];
    
    /*
• Langkah 0 : Inisialisasi semua bobot dengan bilangan acak kecil.
• Langkah 1 : Jika kondisi penghentian belum terpenuhi, lakukan
langkah 2 sampai dengan 8.
• Langkah 2: Untuk setiap pasang data pelatihan, lakukan
langkah 3 sampai dengan 8
    */
    public Backpropagation(float inputlayer[][],float layertarget[]) {
        input = inputlayer;
        target = layertarget;
    }

    public float[][] getbih(){
        initializeweigth();
        return set_bobot_input_to_hidden;
    }
    public float[][] getbho(){
        initializeweigth();
        return set_bobot_input_to_hidden;
    }
    public float[] getbiash(){
        return set_bobot_bias_to_hidden;
    }
    public float[] getbiaso(){
        return set_bobot_bias_to_output;
    }
    
    public void initializeweigth(){
        int i,j;
        //mendapatkan nilai random dari input ke hidden
        for(i=0;i<init_input;i++)
        {
            for(j=0;j<13;j++)
            {
                set_bobot_input_to_hidden[i][j] = (float) (Math.random()*2);
            }
        }
        
        //mendapatkan nilai random dari hidden ke output
        for(i=0;i<init_hidden;i++)
        {
            for(j=0;j<init_output;j++)
            {
                set_bobot_hidden_to_output[i][j] = (float) (Math.random()*2);
            }       
        }
        
        for(i=0;i<init_hidden;i++)
        {
            set_bobot_bias_to_hidden[i] = (float) (Math.random()*2);
        }
        for(i=0;i<init_output;i++)
        {
            set_bobot_bias_to_output[i] = (float) (Math.random()*2);
        }
    }
    //step1
    public float hitung_MSE(float bias_to_hidden[],float input_to_hidden[][],float bobot_to_hidden[][]){
        float error = 0;
        int i,j,k,l,m;
        float z_in[][] = new float[init_hidden][303];
        
        for(i=0;i<init_hidden;i++){
            for(j=0;j<303;j++){
               for(k=0;k<init_input;k++){
                       z_in[i][j] = bias_to_hidden[i]+( 
                               (input_to_hidden[k][0]*bobot_to_hidden[k][0])+(input_to_hidden[k][1]*bobot_to_hidden[k][1])+
                               (input_to_hidden[k][2]*bobot_to_hidden[k][2])+(input_to_hidden[k][3]*bobot_to_hidden[k][3])+
                               (input_to_hidden[k][4]*bobot_to_hidden[k][4])+(input_to_hidden[k][5]*bobot_to_hidden[k][5])+
                               (input_to_hidden[k][6]*bobot_to_hidden[k][6])+(input_to_hidden[k][7]*bobot_to_hidden[k][7])+
                               (input_to_hidden[k][8]*bobot_to_hidden[k][8])+(input_to_hidden[k][9]*bobot_to_hidden[k][9])+
                               (input_to_hidden[k][10]*bobot_to_hidden[k][10])+(input_to_hidden[k][11]*bobot_to_hidden[k][11])+
                               (input_to_hidden[k][12]*bobot_to_hidden[k][12])+(input_to_hidden[k][13]*bobot_to_hidden[k][13])+
                               (input_to_hidden[k][14]*bobot_to_hidden[k][4])
                               );
                   }
                   
               } 
                
            }
        
        return error;
    }
    public void Hitung_Bp(){
        float E =0;
        do{
            E = hitung_MSE(set_bobot_bias_to_hidden,input,set_bobot_input_to_hidden);
        }while(E>0.01);
        
    }
            
    
    
}
