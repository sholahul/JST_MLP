/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mlp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
    static double[][] input;
    static double[]targets; 
    
    static double[][]wih;
    static double[][]who;
    

    public static void main(String[] args){
           
        DataPreprocessing preprocessor = new DataPreprocessing(); //create object preprocessor
//        //preprocessor.Load("C:\\xampp\\htdocs\\JST_MLP\\src\\mlp\\dataset.txt");
////        preprocessor.opendatabase();
            preprocessor.normalizedb();
            input = preprocessor.get_input();
            targets = preprocessor.get_target();
            
            for(int i=0;i<303;i++){
                System.out.println("Data ke : "+(i+1));
                for(int j=0;j<13;j++){
                    System.out.print(input[i][j]+",");
                }
                System.out.println();
            }
            for(int i=0;i<303;i++){
                System.out.println("Target ke : "+(i+1));
                System.out.println(targets[i]+",");
            }
          
          //input layer,ouput,hidden,learningrate,stop mse
//            BackpropagationNetwork bp = new BackpropagationNetwork(13, 1, 1, 0.01, 36);  
//            bp.simpanbobotlama();
//            bp.train(input,targets);
//            bp.simpanBobot();
//            bp.simpanbobotbaru();
//              
//          
//        Contoh perhitungan tugas Backprop kemaren
//        double[][] input = { // coba inisialisasi
//            {1, 0},
//            {0, 1},
//            {1, 0},
//            {1, 1}
//        };
//        
//        double[] targets = {0, 1, 1, 0}; // target harus diinput untuk menghitung MSE dan error
        
//        BackpropagationNetwork bp = new BackpropagationNetwork(2, 1, 1, 0.01, 0.41); // buat objek backprop
//        bp.simpanbobotlt();
//        bp.train(input, targets); // coba training
//        bp.simpanBobot();
//        bp.simpanbobotbt();
    }
    
}
