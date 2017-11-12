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
public class ProsesBP extends DataPreprocessing{ //class untuk memproses keluaran nilai
    public float input[][] = new float[304][14];
    public float target[]=new float[304];
    public ProsesBP() {
       DataPreprocessing dp = new DataPreprocessing();
       input = dp.get_input();
       target = dp.get_target();
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
    
}
