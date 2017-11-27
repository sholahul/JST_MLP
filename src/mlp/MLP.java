/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mlp;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Ayu Lestari
 */
public class MLP { //kelas utama

    public static Connection conn;
    
    public static void main(String[] args) throws ClassNotFoundException {
        DataPreprocessing preprocessor = new DataPreprocessing(); //create object preprocessor
//        preprocessor.Load("C:\\xampp\\htdocs\\JST_MLP\\src\\mlp\\dataset.txt");
         preprocessor.normalizedb();
//          preprocessor.opendatabase();
          
          double[][]input = new double[304][14];
          double[]target ;
//          input = preprocessor.get_input();
////          target = preprocessor.get_target();
//          
//          for(int i=0;i<304;i++){
//              for(int j=0;j<14;i++)
//              {
//                  System.out.println(input[i][j]);
//              }
//              System.out.println("");
//          }
          
//        System.out.println("\nBobot dari input ke hidden secara random");
//        PB.cetak_bobot_input_to_hidden();
//        
//        System.out.println("\nBobot hidden ke output secara random");
//        PB.cetak_bobot_hidden_to_output();
//        
//        System.out.println("\nBobot dari bias ke hidden secara random");
//        PB.cetak_bobot_bias_to_hidden();
//        
//        System.out.println("\nBobot dari bias ke output secara random");
//        PB.cetak_bobot_bias_to_output();
        
        

        //Contoh perhitungan tugas Backprop kemaren
//        double[][] input = { // coba inisialisasi
//            {1, 0},
//            {0, 1},
//            {1, 0},
//            {1, 1}
//        };
//        
//        double[] targets = {0, 1, 1, 0}; // target harus diinput untuk menghitung MSE dan error
//        
//        BackpropagationNetwork bp = new BackpropagationNetwork(2, 1, 1, 0.01, 0.41); // buat objek backprop
//        
//        bp.train(input, targets); // coba training
//        
//        // menghitung akurasi = (jumlah benar/jumlah data)*100%
//        int jumlah_benar = 0; 
//        for (int i = 0; i < input.length; i++)
//        {
//            System.out.println(bp.test(input[i]));
//            if (bp.test(input[i]) == targets[i]) jumlah_benar++;
//        }
//        
//        System.out.println("Akurasi: "  + ((float)jumlah_benar / (float)input.length) * 100 + "%");     

    }
    
}
