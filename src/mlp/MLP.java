/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mlp;

import java.sql.Connection;

/**
 *
 * @author Ayu Lestari
 */
public class MLP { //kelas utama

    public static Connection conn;
    
    public static void main(String[] args) throws ClassNotFoundException {
        DataPreprocessing preprocessor = new DataPreprocessing(); //create object preprocessor
        //preprocessor.Load("C:\\xampp\\htdocs\\JST_MLP\\src\\mlp\\dataset.txt");
        preprocessor.opendatabase();
        preprocessor.normalizedb();
        
        ProsesBP PB = new ProsesBP();
        System.out.println("\nData proses");
        PB.cetak_input();
        System.out.println("\nData Target");
        PB.cetak_target();
        
    }
    
}
