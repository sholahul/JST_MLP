/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mlp;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ayu Lestari
 */
public class MLP {

    public static Connection conn;
    
    public static void main(String[] args) {
//        try {
//            // TODO code application logic here
//            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/heart_disease", "root", "");
//            String data = "SELECT * FROM bobot";
//            Statement st = conn.createStatement();
//            ResultSet rs = st.executeQuery(data);
//            while (rs.next())
//            {
//                System.out.println(rs.getString("fuzzy"));
//            }
//
////            String[] kata2 = new String[] {"Azhary", "Arliansyah", "Ganteng"};
////            for (String kata : kata2)
////            {
////                System.out.println(kata);
////            }
//        } catch (SQLException ex) {
//            Logger.getLogger(MLP.class.getName()).log(Level.SEVERE, null, ex);
//        }

//        FileManager fileManager = new FileManager();
//        try {
//            ArrayList<String> data = fileManager.read("D:\\MLP\\src\\mlp\\dataset.txt");
//            for (String d : data) {
//                String[] x = d.split(",");
//                int usia = (int)Float.parseFloat(x[0]);
//                System.out.println(usia);
//            }
//        } catch (IOException ex) {
//            Logger.getLogger(MLP.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
//        DatabaseManager db = new DatabaseManager("localhost", "root", "", "heart_disease");
//        Map<String, String> data = new HashMap<>();
//        data.put("usia", "20");
//        data.put("jenis_kelamin", "0");
//        db.Insert("table1", data);

        DataPreprocessing preprocessor = new DataPreprocessing();
        preprocessor.Load("D:\\MLP\\src\\mlp\\dataset.txt");
    }
    
}
