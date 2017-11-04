/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mlp;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ayu Lestari
 */
public class FileManager {
    public ArrayList<String> read(String filename) throws IOException{
        try {
            FileReader file = new FileReader(filename);
            BufferedReader br = new BufferedReader(file);
            ArrayList<String> data = new ArrayList<>();
            String line;
            
            while((line = br.readLine()) != null){
                data.add(line);
            }
            return data;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return null;
        
    }
}
