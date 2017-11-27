/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mlp;

/**
 *
 * @author Sholahul Fajri
 */
public class ProsesBP{ //class untuk memproses keluaran nilai
    public float input[][] = new float[304][14];
    public float target[]=new float[304];
    public float w_input_to_hidden[][] = new float[304][14];
    public float w_hidden_to_output[][];
    public int i_hidden;
    public int i_output;
    public float w_bias_to_hidden[];
    public float w_bias_to_output[];
    /*        
    public ProsesBP() {
       DataPreprocessing dp = new DataPreprocessing(); 
       input = dp.get_input(); //mendapatkan input dari dataprepocessing
       target = dp.get_target(); //mendapatkan target dari dataprepocessing
       Backpropagation bp = new Backpropagation(input,target); //mengoper nilai input dan target untuk di proses di bp.java
       i_hidden = bp.init_hidden;
       i_output = bp.init_output;
       w_hidden_to_output = new float[i_hidden][i_output];
       w_bias_to_hidden = new float[i_hidden];
       w_bias_to_output = new float[i_output];
       
       w_input_to_hidden = bp.getbih();
       w_hidden_to_output = bp.getbho();
       w_bias_to_hidden = bp.getbiash();
       w_bias_to_output = bp.getbiaso();
  
    }
    
    public void cetak_input(){
        int i,j;
        for(i=0;i<303;i++){
            for(j=0;j<14;j++){
                System.out.print(input[i][j]+",");
            }
            System.out.println();
        }
    }
    
    public void cetak_target(){
        int k;
        for(k=0;k<303;k++)
        {
            System.out.println(target[k]);       
        }
    }
    
    public void cetak_bobot_input_to_hidden(){
     
        int i,j;
        for(i=0;i<303;i++){
//            System.out.println(i);
            for(j=0;j<13;j++){
                System.out.print(w_input_to_hidden[i][j]+",");
            }
            System.out.println();
        }      
    }
    public void cetak_bobot_hidden_to_output(){
        int i,j;
        for(i=0;i<i_hidden;i++){
            for(j=0;j<i_output;j++){
                System.out.print(w_input_to_hidden[i][j]+",");
            }
            System.out.println();
        }      
    }
    public void cetak_bobot_bias_to_hidden(){
        int i;
        for(i=0;i<i_hidden;i++){
            System.out.println(w_bias_to_hidden[i]);
        }
    }
    public void cetak_bobot_bias_to_output(){
        int i;
         for(i=0;i<i_output;i++){
            System.out.println(w_bias_to_output[i]);
        }
    }
    
    
    */
}
