/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mlp;

import java.sql.Connection;
import java.sql.DriverManager;
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
    
    public Connection conn;
    
    public DatabaseManager(String host, String username, String password, String database) {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://" + host + ":3306/" + database, username, password);
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
