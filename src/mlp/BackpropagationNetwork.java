/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mlp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

/**
 *
 * @author Ayu Lestari
 */
public class BackpropagationNetwork {
    
    private int INPUT_NEURONS;
    private int HIDDEN_NEURONS;
    private int OUTPUT_NEURONS;
    
    public double[][] wih = new double[INPUT_NEURONS + 1][HIDDEN_NEURONS];     // bobot Input ke Hidden 
    public double[][] who = new double[HIDDEN_NEURONS + 1][OUTPUT_NEURONS];    // bobot Hidden ke Output
    
    private final double[] inputs;
    private final double[] hidden;
    private final double[] outputs;
    private double[] targets;
    
    private final double[] erro; // variabel untuk error output
    private final double[] errh; // variabel untuk error hidden
    
    private final double LEARNING_RATE; 
    private double MSE = 0.0;
    private final double stopMSE; // variable inisialisasi Stopping Condition MSE
    
    private double[][] wihF = new double[INPUT_NEURONS + 1][HIDDEN_NEURONS];     // bobot Input ke Hidden 
    private double[][] whoF = new double[HIDDEN_NEURONS + 1][OUTPUT_NEURONS];    // bobot Hidden ke Output
    double v01,v11,v21,v31,v41,v51,v61,v71,v81,v91,v101,v111,v121,v131,w01,w11; //variabel untuk nyimpan bobot dari bobot lama
    double bv01,bv11,bv21,bv31,bv41,bv51,bv61,bv71,bv81,bv91,bv101,bv111,bv121,bv131,bw01,bw11; //variabel untuk nyimpan bobot dari bobot baru
    // Method ini berfugsi untuk inisialisasi BP dengan inputan/parameter -> input, output, hidden, lr, stopMSE
    // juga memanggil random bobot
    public BackpropagationNetwork(int input, int output, int hidden, double lr, double stopMSE){
        this.INPUT_NEURONS  = input;
        this.HIDDEN_NEURONS = hidden;
        this.OUTPUT_NEURONS = output;
        this.LEARNING_RATE  = lr;
        this.stopMSE        = stopMSE;
        
        this.inputs = new double[INPUT_NEURONS]; // diinisialisasi lagi untuk membuat objek sebanyak parameternya. Misal inputs[2]
        this.hidden = new double[HIDDEN_NEURONS];
        this.outputs = new double[OUTPUT_NEURONS];
        
        wih = new double[INPUT_NEURONS + 1][HIDDEN_NEURONS];
        who = new double[HIDDEN_NEURONS + 1][OUTPUT_NEURONS];
        
        wihF = new double[INPUT_NEURONS + 1][HIDDEN_NEURONS];     // bobot Input ke Hidden 
        whoF = new double[HIDDEN_NEURONS + 1][OUTPUT_NEURONS];    // bobot Hidden ke Output
        
        this.erro = new double[OUTPUT_NEURONS];
        this.errh = new double[HIDDEN_NEURONS];
        
        // method random bobot 
        bobotRandom();
    }
    
    // Method ini digunakan untuk melakukan random bobot 
   
    public double[][] get_wihF(){
        return wihF;
    }
    public double[][] get_whoF(){
        return whoF;
    }
    
    private void bobotRandom()
    {
        System.out.println("Bobot input layer dan bias dari ke hidden layer : ");
        // bobot random dari input ke hidden
        for(int i = 0; i < INPUT_NEURONS + 1; i++){
            for(int j = 0; j < HIDDEN_NEURONS; j++){
                Random random = new Random();
                wih[i][j] = (Math.random() * 2) - 1;
                System.out.println("data ke-v[:"+i+"]["+(j+1)+"] : "+wih[i][j]);
            }
        }
        
        System.out.println("Bobot hidden layer dan bias ke output layer : ");
        // bobot random dari hidden ke output
        for(int i = 0; i < HIDDEN_NEURONS + 1; i++){
            for(int j = 0; j < OUTPUT_NEURONS; j++){
                who[i][j] = (Math.random() * 2) - 1;
                System.out.println("data ke-w[:"+i+"]["+(j+1)+"] : "+who[i][j]);
            }
        }
    }

    /* Method untuk training/pelatihan dengan masukkan berupa data dan target
     * Inputan/data berupa array 2 dimensi. Seperti dari kasus penyakit jantung, data terdiri dari 303 baris dan 14 kolom termasuk target.
     * Untuk target dibuat dalam array sendiri sehingga menjadi 13 kolom input.
     * Pada data tersebut dilakukan looping per baris data, sehingga ada 303 kali looping
    */
    public void train(double[][] data, double[] targets)
    {
        
        int i = 0;
        int j = 0;
        while (true)
        {
            // hitung MSE
            calculateMSE(data, targets);
            System.out.print(j+" : ");
            System.out.println(MSE + " < " + stopMSE);
            
            // Jika MSE masih kurang dari stop MSE lakukan ...
            if (MSE < stopMSE) break;
            feedForward(data[i]);
            backPropagate(data[i], targets[i]);
            
//            // print perubahan bobot data ke-i
//            
////             misalkan ada 16 data 
//            System.out.println(wih[0][0]);
//            System.out.println(wih[1][0]);
//            System.out.println(wih[2][0]);
//            System.out.println(wih[3][0]);
//            System.out.println(wih[4][0]);
//            System.out.println(wih[5][0]);
//            System.out.println(wih[6][0]);
//            System.out.println(wih[7][0]);
//            System.out.println(wih[8][0]);
//            System.out.println(wih[9][0]);
//            System.out.println(wih[10][0]);
//            System.out.println(wih[11][0]);
//            System.out.println(wih[12][0]);
//            System.out.println(wih[13][0]);
//            
//            System.out.println(who[0][0]);
//            System.out.println(who[1][0]);
//            System.out.println("-------------------");
    
             i++;
             i = i % data.length; 
             j++;
        }
          
            System.out.println(wih[0][0]);
            System.out.println(wih[1][0]);
            System.out.println(wih[2][0]);
            System.out.println(wih[3][0]);
            System.out.println(wih[4][0]);
            System.out.println(wih[5][0]);
            System.out.println(wih[6][0]);
            System.out.println(wih[7][0]);
            System.out.println(wih[8][0]);
            System.out.println(wih[9][0]);
            System.out.println(wih[10][0]);
            System.out.println(wih[11][0]);
            System.out.println(wih[12][0]);
            System.out.println(wih[13][0]);
            
            System.out.println(who[0][0]);
            System.out.println(who[1][0]);
            System.out.println("-------------------");
    
        
        
//             System.out.println("");
//            System.out.println(wih[0][0]);
//            System.out.println(wih[1][0]);
//            System.out.println(wih[2][0]);
//            
//            System.out.println(who[0][0]);
//            System.out.println(who[1][0]);
//            System.out.println("-------------------");
            
        
    }
    
    public void simpanBobot() {
        for (int i = 0; i < INPUT_NEURONS + 1; i++) {
            for (int j = 0; j <HIDDEN_NEURONS; j++) {
                wihF[i][j] = wih[i][j];  
//                System.out.println(wihF[i][j]);
            }
        }

        for (int i = 0; i < HIDDEN_NEURONS + 1; i++) {
            for (int j = 0; j <OUTPUT_NEURONS; j++) {
                whoF[i][j] = who[i][j];   
//                System.out.println(whoF[i][j]);
            }
        }
    }
    public void simpanbobotlama(){
        v01 = wih[0][0];
        v11 = wih[1][0];
        v21 = wih[2][0];
        v31 = wih[3][0];
        v41 = wih[4][0];
        v51 = wih[5][0];
        v61 = wih[6][0];
        v71 = wih[7][0];
        v81 = wih[8][0];
        v91 = wih[9][0];
        v101 = wih[10][0];
        v111 = wih[11][0];
        v121 = wih[12][0];
        v131 = wih[13][0];
        w01 = who[0][0];
        w11 = who[1][0];        
        try{
            Connection c = KoneksiDatabase.getKoneksi();
            String sql = "INSERT INTO bobotl VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            
            System.out.println(sql);
            PreparedStatement p = c.prepareStatement(sql);
               
                p.setDouble(1,v01);
                p.setDouble(2,v11);
                p.setDouble(3,v21);
                p.setDouble(4,v31);
                p.setDouble(5,v41);
                p.setDouble(6,v51);
                p.setDouble(7,v61);
                p.setDouble(8,v71);
                p.setDouble(9,v81);
                p.setDouble(10,v91);
                p.setDouble(11,v101);
                p.setDouble(12,v111);
                p.setDouble(13,v121);
                p.setDouble(14,v131);
                p.setDouble(15,w01);
                p.setDouble(16,w11); 
                p.executeUpdate();
                p.close();   
        }
        catch (SQLException e){
            System.out.println("Terjadi error Saat Penambahan!");
            
        }
    }
    public void simpanbobotbaru(){
        bv01 = wihF[0][0];
        bv11 = wihF[1][0];
        bv21 = wihF[2][0];
        bv31 = wihF[3][0];
        bv41 = wihF[4][0];
        bv51 = wihF[5][0];
        bv61 = wihF[6][0];
        bv71 = wihF[7][0];
        bv81 = wihF[8][0];
        bv91 = wihF[9][0];
        bv101 = wihF[10][0];
        bv111 = wihF[11][0];
        bv121 = wihF[12][0];
        bv131 = wihF[13][0];
        bw01 = whoF[0][0];
        bw11 = whoF[1][0];
        
        try{
            Connection c = KoneksiDatabase.getKoneksi();
            String sql = "INSERT INTO bobotb VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            
            System.out.println(sql);
            PreparedStatement p = c.prepareStatement(sql);
               
                p.setDouble(1,bv01);
                p.setDouble(2,bv11);
                p.setDouble(3,bv21);
                p.setDouble(4,bv31);
                p.setDouble(5,bv41);
                p.setDouble(6,bv51);
                p.setDouble(7,bv61);
                p.setDouble(8,bv71);
                p.setDouble(9,bv81);
                p.setDouble(10,bv91);
                p.setDouble(11,bv101);
                p.setDouble(12,bv111);
                p.setDouble(13,bv121);
                p.setDouble(14,bv131);
                p.setDouble(15,bw01);
                p.setDouble(16,bw11); 
                p.executeUpdate();
                p.close();   
        }
        catch (SQLException e){
            System.out.println("Terjadi error Saat Penambahan!");
            
        }
    }
    
    public void simpanbobotlt(){ //prosedur testing untuk contoh xor
        v01 = wih[0][0];
        v11 = wih[1][0];
        v21 = wih[2][0];
        w01 = who[0][0];
        w11 = who[1][0];
        
        try{
            Connection c = KoneksiDatabase.getKoneksi();
            String sql = "INSERT INTO bobotlt VALUES (?, ?, ?, ?, ?)";
            
            System.out.println(sql);
            PreparedStatement p = c.prepareStatement(sql);
               
                p.setDouble(1,v01);
                p.setDouble(2,v11);
                p.setDouble(3,v21);
                p.setDouble(4,w01);
                p.setDouble(5,w11); 
                p.executeUpdate();
                p.close();   
        }
        catch (SQLException e){
            System.out.println("Terjadi error Saat Penambahan!");
            
        }
    }
    public void simpanbobotbt(){ //prosedur testing untuk contoh xor
        bv01 = wihF[0][0];
        bv11 = wihF[1][0];
        bv21 = wihF[2][0];
        bw01 = whoF[0][0];
        bw11 = whoF[1][0];
        
        try{
            Connection c = KoneksiDatabase.getKoneksi();
            String sql = "INSERT INTO bobotbt VALUES (?, ?, ?, ?, ?)";
            
            System.out.println(sql);
            PreparedStatement p = c.prepareStatement(sql);
               
                p.setDouble(1,bv01);
                p.setDouble(2,bv11);
                p.setDouble(3,bv21);
                p.setDouble(4,bw01);
                p.setDouble(5,bw11); 
                p.executeUpdate();
                p.close();   
        }
        catch (SQLException e){
            System.out.println("Terjadi error Saat Penambahan!");
            
        }
    }
    // Method ini digunakan untuk testing data.
    // Karena satu node output maka jika nilainya 1 berarti memiliki penyakit dan jika bernilai 0 maka tidak memiliki penyakit jantung.
    public int test(double[] data)
    {
        feedForward(data);
        if (outputs[0] > 0.5) // karena kemungkinan nya cuma 2 nilai maka rangenya 1/2 
        {
            return 1;
        }
        else
        {
            return 0;
        }
    }
    
    // Method ini digunakan untuk feedforward
    
    private void feedForward(double[] input)
    {
        double sum = 0.0; // net
        
        // Langkah 4 : Hitung semua keluaran di unit tersembunyi
        for (int hid = 0; hid < HIDDEN_NEURONS; hid++)
        {
            sum = 0.0;
            for (int inp = 0; inp < INPUT_NEURONS; inp++)
            {
                if (inp < input.length)
                {
                    sum += input[inp] * wih[inp][hid];
                }
            }
            
            sum += 1.0 * wih[INPUT_NEURONS][hid];
            hidden[hid] = sigmoid(sum); // hitung dengan fungsi aktivasi sigmoid
        }
        
        // Langkah 5: Hitung semua jaringan di unit keluaran
        for (int out = 0; out < OUTPUT_NEURONS; out++)
        {
            sum = 0.0;
            for (int hid = 0; hid < HIDDEN_NEURONS; hid++)
            {
                sum += hidden[hid] * who[hid][out]; 
            }
            
            sum += 1.0 * who[HIDDEN_NEURONS][out];
            outputs[out] = sigmoid(sum); // hitung dengan fungsi sigmoid
        }
    }
    
    // Method ini digunakan untuk melakukan backpropagasi mundur
    private void backPropagate(double[] input, double target)
    {
        // Langkah 6 : Hitung factor error unit keluaran berdasarkan kesalahan setiap unit keluaran yk
        for (int out = 0; out < OUTPUT_NEURONS; out++)
        {
            erro[out] = (target - outputs[out]) * sigmoidDerivative(outputs[out]);
            for (int hid = 0; hid < HIDDEN_NEURONS; hid++)
            {
                double delta_w = LEARNING_RATE * erro[out] * hidden[hid];
                who[hid][out] = who[hid][out] + delta_w;
            }
            who[HIDDEN_NEURONS][out] += LEARNING_RATE * erro[out];
        }
        
        // Langkah 7 : Hitung factor  unit tersembunyi berdasarkan kesalahan di setiap unit tersembunyi zj
        for (int hid = 0; hid < HIDDEN_NEURONS; hid++)
        {
            errh[hid] = 0.0;
            for (int out = 0; out < OUTPUT_NEURONS; out++)
            {
                errh[hid] += erro[out] * who[hid][out];
            }
            
            errh[hid] *= sigmoidDerivative(hidden[hid]);
        }
        
        // Langkah 8 : Update Bobot
        for (int hid = 0; hid < HIDDEN_NEURONS; hid++)
        {
            for (int inp = 0; inp < INPUT_NEURONS; inp++)
            {
                if (inp < input.length)
                {
                    double delta_w = LEARNING_RATE * errh[hid] * input[inp];
                    wih[inp][hid] = wih[inp][hid] + delta_w;
                }
            }
            wih[INPUT_NEURONS][hid] += LEARNING_RATE * errh[hid];
        }
    }
    
    // Method ini digunakan untuk menghitung nilai MSE untuk setiap data
    private void calculateMSE(double[][] inputs, double[] targets)
    {
        // lakukan feedforword khusus untuk mneghitung MSE
        this.MSE = 0.0;
        double[] y = new double[inputs.length];  // sama saja outputnya 1 node
        for (int i = 0; i < inputs.length; i++)
        {
            double sum = 0.0; // net
            
            double[] z = new double[HIDDEN_NEURONS]; //banyaknya hidden misalnya 1
        
            for (int hid = 0; hid < HIDDEN_NEURONS; hid++) 
            {
                sum = 0.0;
                for (int inp = 0; inp < INPUT_NEURONS; inp++) //input_neuron misalnya di contoh 13
                {
                    if (inp < inputs[i].length) //banyaknya data di contoh 303, selagi inputan <303 karena dimulai dari 0 maka jumlahkan
                    {
                        sum += inputs[i][inp] * wih[inp][hid];
                        /*sum = sum + inputs[0][0]*wih[0][0]
                        jadi bobot pada indeks wih[0][0] dianggap sebagai bias
                        dimana nantinya dia akan mengalikan setiap input baris pertama dari kolom 1 sampai jumlah input,dicontoh 13 input
                        dengan bobot setiap biasnya
                        */
                    }
                }

                sum += 1.0 * wih[INPUT_NEURONS][hid];
                z[hid] = sigmoid(sum);
            }

            for (int out = 0; out < OUTPUT_NEURONS; out++)
            {
                sum = 0.0;
                for (int hid = 0; hid < HIDDEN_NEURONS; hid++)
                {
                    sum += z[hid] * who[hid][out]; 
                }

                sum += 1.0 * who[HIDDEN_NEURONS][out];
                y[i] = sigmoid(sum);
            }
            
            
        }
        
        // menjumlahkan error MSE
        for (int i = 0; i < inputs.length; i++)
        {
            this.MSE += 0.5 * Math.pow((targets[i] - y[i]), 2);
        }
    }
    
    // Method ini digunakan untuk fungsi aktivasi
    private double sigmoid(double val)
    {
        return (1.0 / (1.0 + Math.exp(-val)));
    }
    
    // Method ini digunakan untuk fungsi aktivasi dalam backprop
    private double sigmoidDerivative(double val)
    {
        return (val * (1.0 - val));
    }
}
