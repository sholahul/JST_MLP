/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mlp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Ayu Lestari
 */
public class DatabaseManager {
    public double input[][] = new double[304][13];
    public double target[]=new double[304];
    
    public Connection conn;
    
    public DatabaseManager(String host, String username, String password, String database) {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://" + host + ":3306/" + database, username, password);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public double[][] get_input(){
        return input;
    }
    public double[] get_target(){
        return target;
    }
//    public void cetaki()
//    {
//        int i;
//        int j;
//        System.out.println("\nData yang disimpan di variabel yang telah dinormalisasi");
//        for(i=0;i<303;i++){
//            for(j=0;j<=13;j++){
//                System.out.print(input[i][j]+",");
//            
//            }
//            System.out.println();
//        }
//    }
    public void open(String table)
    {    
        try{
            String sql = "SELECT * FROM "+table+";";
            System.out.println(sql);
            Statement st = conn.createStatement();
            ResultSet res = st.executeQuery(sql);
            System.out.println("id,age,sex,cp,trestbps,chol,fbs,restecg,thalach,exang,oldpeak,slope,ca,thal,num");
            
            int i=0;
            
            while(res.next())
            {
                //retrieve by coloumn name
                
                int id = res.getInt("id_patient");
                double age,sex,cp,trestbps,chol,fbs,restecg,thalach,exang,oldpeak,slope,ca,thal,num;
                
                age = res.getDouble("age");
                sex = res.getDouble("sex");
                cp = res.getDouble("cp");
                trestbps = res.getDouble("trestbps");
                chol = res.getDouble("chol");
                fbs = res.getDouble("fbs");
                restecg = res.getDouble("restecg");
                thalach = res.getDouble("thalach");
                exang = res.getDouble("exang");
                oldpeak = res.getDouble("oldpeak");
                slope = res.getDouble("slope");
                ca = res.getDouble("ca");
                thal = res.getDouble("thal");
                num = res.getDouble("num");
                
                System.out.println(id+","+age+","+sex+","+cp+","+trestbps+","+chol+","+fbs+","+restecg+","+thalach+","+exang+","+oldpeak+","+slope+","+ca+","+thal+","+num);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void normalizedatabase(String table)
    {
   
        try{
            String sql = "SELECT * FROM "+table+";";
//            System.out.println(sql);
            Statement st = conn.createStatement();
            ResultSet res = st.executeQuery(sql);
            
//            System.out.println("\nHasil Normalisasi");
//            System.out.println("id,age,sex,cp,trestbps,chol,fbs,restecg,thalach,exang,oldpeak,slope,ca,thal,num");
            
            int i=0;
            while(res.next())
            {
                //retrieve by coloumn name
                int id = res.getInt("id_patient");
                double age,sex,cp,trestbps,chol,fbs,restecg,thalach,exang,oldpeak,slope,ca,thal,num;
                
                age = res.getDouble("age")/77;
                sex = res.getDouble("sex")/1;
                cp = res.getDouble("cp")/4;
                trestbps = res.getDouble("trestbps")/200;
                chol = res.getDouble("chol")/564;
                fbs = res.getDouble("fbs")/1;
                restecg = res.getDouble("restecg")/2;
                thalach = res.getDouble("thalach")/202;
                exang = res.getDouble("exang")/1;
                oldpeak = res.getDouble("oldpeak")/6.2;
                slope = res.getDouble("slope")/3;
                ca = res.getDouble("ca")/3;
                thal = res.getDouble("thal")/7;
                num = res.getDouble("num");
                
                input[i][0]= age;
                input[i][1]= sex;
                input[i][3]= cp;
                input[i][4]= trestbps;
                input[i][5]= chol;
                input[i][6]= fbs;
                input[i][7]= thalach;
                input[i][8]= exang;
                input[i][9]= oldpeak;
                input[i][10]= slope;
                input[i][11]= ca;
                input[i][12]= thal;
               
                // Normalisasi Target, jika target lebih besar dari 0 maka target 1 dan jika kurang dari atau sama dengan 0 maka target 0
                i++;
                if(num>0){
                    num = 1;
                }
                target[i]=num;
               
//                System.out.println(id+","+age+","+sex+","+cp+","+trestbps+","+chol+","+fbs+","+restecg+","+thalach+","+exang+","+oldpeak+","+slope+","+ca+","+thal+","+num);
            }        
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void Insert(String table, Map<String, String> data) {
        Set<String> fields = data.keySet();
        Collection<String> values = data.values();
        
        StringBuilder fBuilder = new StringBuilder();
        StringBuilder vBuilder = new StringBuilder();
        
        int i = 0;
        for (String field : fields) {
            fBuilder.append(field);
            i++;
            if (i < fields.size()) {
                fBuilder.append(",");
            }
        }
        
        i = 0;
        for (String value : values) {
            vBuilder.append(value);
            i++;
            if (i < values.size()) {
                vBuilder.append(",");
            }
        }
        
        String sql = "INSERT INTO " + table + "(" + fBuilder.toString() + ") VALUES(" + vBuilder.toString() + ")";
        System.out.println(sql);
        try {
            Statement st = conn.createStatement();
            st.execute(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void Update() {
        
    }
    
    public void Delete() {
        
    }
    
}
