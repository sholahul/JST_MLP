/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mlp;

import java.sql.*;
import java.io.IOException;
import static java.lang.Class.forName;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.swing.text.html.HTML.Tag.SELECT;

/**
 *
 * @author Ayu Lestari
 */
public class DataPreprocessing {
 
    private final FileManager mFileManager; //membuat file manager library java dan menciptakan variable mfilemanager
    private final DatabaseManager mDatabaseManager; //membuat database manager
    
    //konstruktor
    public DataPreprocessing() {
        mDatabaseManager = new DatabaseManager("localhost", "root", "", "heart_disease"); //(namadatabase server,password,,namadatabase
        mFileManager = new FileManager();
    }

    public void Load(String filename) { //prosedur meload dengan parameter namafile direktori
        try {
            /*
            ArrayList merupakan collection yang menjadi bagian dari Java Util. 
            Seperti biasa, ArrayList dapat menambah data baru secara dinamis tanpa harus menentukan ukurannya di awal.
            */
            ArrayList<String> data = mFileManager.read(filename); 
            
            for (String line : data) {
                String[] attr = line.split(",");
                Map<String, String> row = new HashMap<>();
                for (int i = 0; i < attr.length; i++) {
                    if (attr[i].equals("?")) {
                        attr[i] = "0.0"; 
                    }
                }
                row.put("age", "'" + attr[0] + "'");
                row.put("sex", "'" + attr[1] + "'");
                row.put("cp", "'" + attr[2] + "'");
                row.put("trestbps", "'" + attr[3] + "'");
                row.put("chol", "'" + attr[4] + "'");
                row.put("fbs", "'" + attr[5] + "'");
                row.put("restecg", "'" + attr[6] + "'");
                row.put("thalach", "'" + attr[7] + "'");
                row.put("exang", "'" + attr[8] + "'");
                row.put("oldpeak", "'" + attr[9] + "'");
                row.put("slope", "'" + attr[10] + "'");
                row.put("ca", "'" + attr[11] + "'");
                row.put("thal", "'" + attr[12] + "'");
                row.put("num", "'" + attr[13] + "'");
                mDatabaseManager.Insert("raw_patient", row);
            }
        } catch (IOException ex) {
            Logger.getLogger(DataPreprocessing.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void opendatabase() 
    {
       mDatabaseManager.open("raw_patient");
    }
    
    public void normalizedb()
    {
        mDatabaseManager.normalizedatabase("raw_patient");
        mDatabaseManager.cetaki();
    }
    
}
