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
    private double input[][] = new double[303][13];
    
    public Connection conn;
    
    public DatabaseManager(String host, String username, String password, String database) {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://" + host + ":3306/" + database, username, password);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void open(String table)
    {    
        try{
            String sql = "SELECT * FROM "+table+";";
            System.out.println(sql);
            Statement st = conn.createStatement();
            ResultSet res = st.executeQuery(sql);
            System.out.println("id,age,sex,cp,trestbps,chol,fbs,restecg,thalach,exang,oldpeak,slope,ca,thal,num");
            while(res.next())
            {
                //retrieve by coloumn name
                
                int id = res.getInt("id_patient");
                float age,sex,cp,trestbps,chol,fbs,restecg,thalach,exang,oldpeak,slope,ca,thal,num;
                
                age = res.getFloat("age");
                sex = res.getFloat("sex");
                cp = res.getFloat("cp");
                trestbps = res.getFloat("trestbps");
                chol = res.getFloat("chol");
                fbs = res.getFloat("fbs");
                restecg = res.getFloat("restecg");
                thalach = res.getFloat("thalach");
                exang = res.getFloat("exang");
                oldpeak = res.getFloat("oldpeak");
                slope = res.getFloat("slope");
                ca = res.getFloat("ca");
                thal = res.getFloat("thal");
                num = res.getFloat("num");
                
          
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
            System.out.println(sql);
            Statement st = conn.createStatement();
            ResultSet res = st.executeQuery(sql);
            
            System.out.println("\nHasil Normalisasi");
            System.out.println("id,age,sex,cp,trestbps,chol,fbs,restecg,thalach,exang,oldpeak,slope,ca,thal,num");
            
      
            while(res.next())
            {
                //retrieve by coloumn name
                int id = res.getInt("id_patient");
                double age,sex,cp,trestbps,chol,fbs,restecg,thalach,exang,oldpeak,slope,ca,thal,num;
                
                age = res.getFloat("age")/(double)77;
                sex = res.getFloat("sex")/(double)1;
                cp = res.getFloat("cp")/(double)4;
                trestbps = res.getFloat("trestbps")/(double)200;
                chol = res.getFloat("chol")/(double)564;
                fbs = res.getFloat("fbs")/(double)1;
                restecg = res.getFloat("restecg")/(double)2;
                thalach = res.getFloat("thalach")/(double)202;
                exang = res.getFloat("exang")/(double)1;
                oldpeak = res.getFloat("oldpeak")/(double)6.2;
                slope = res.getFloat("slope")/(double)3;
                ca = res.getFloat("ca")/(double)3;
                thal = res.getFloat("thal")/(double)7;
                num = res.getFloat("num")*2 / (double)2;
                if(num>0){
                    num = 1;
                }
               
//                System.out.println(i+1);
//                input[i][1]= age;
//                input[i][2]= sex;
//                input[i][3]= cp;
//                input[i][4]= trestbps;
//                input[i][5]= chol;
//                input[i][6]= fbs;
//                input[i][7]= thalach;
//                input[i][8]= exang;
//                input[i][9]= oldpeak;
//                input[i][10]= slope;
//                input[i][11]= ca;
//                input[i][12]= thal;
//                i++;
                
                System.out.println(id+","+age+","+sex+","+cp+","+trestbps+","+chol+","+fbs+","+restecg+","+thalach+","+exang+","+oldpeak+","+slope+","+ca+","+thal+","+num);
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
