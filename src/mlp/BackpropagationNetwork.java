/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mlp;

import java.util.Random;

/**
 *
 * @author Ayu Lestari
 */
public class BackpropagationNetwork {
    
    private int INPUT_NEURONS;
    private int HIDDEN_NEURONS;
    private int OUTPUT_NEURONS;
    
    private double[][] wih = new double[INPUT_NEURONS + 1][HIDDEN_NEURONS];     // bobot Input ke Hidden 
    private double[][] who = new double[HIDDEN_NEURONS + 1][OUTPUT_NEURONS];    // bobot Hidden ke Output
    
    private double[] inputs;
    private double[] hidden;
    private double[] outputs;
    private double[] targets;
    
    private double[] erro; // variabel untuk error output
    private double[] errh; // variabel untuk error hidden
    
    private double LEARNING_RATE; 
    private double MSE = 0.0;
    private double stopMSE; // variable inisialisasi Stopping Condition MSE
    
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
        
        this.erro = new double[OUTPUT_NEURONS];
        this.errh = new double[HIDDEN_NEURONS];
        
        // method random bobot 
        bobotRandom();
    }
    
    // Method ini digunakan untuk melakukan random bobot 
    private void bobotRandom()
    {
        // bobot random dari input ke hidden
        for(int i = 0; i < INPUT_NEURONS + 1; i++){
            for(int j = 0; j < HIDDEN_NEURONS; j++){
                Random random = new Random();
                wih[i][j] = (Math.random() * 2) - 1;
            }
        }
        
        // bobot random dari hidden ke output
        for(int i = 0; i < HIDDEN_NEURONS + 1; i++){
            for(int j = 0; j < OUTPUT_NEURONS; j++){
                who[i][j] = (Math.random() * 2) - 1;
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
        while (true)
        {
            // hitung MSE
            calculateMSE(data, targets);
            System.out.println(MSE + " < " + stopMSE);
            
            // Jika MSE masih kurang dari stop MSE lakukan ...
            if (MSE < stopMSE) break;
            feedForward(data[i]);
            backPropagate(data[i], targets[i]);
            
            // print perubahan bobot data ke-i
            System.out.println("DATA KE-" + i);
            System.out.println(wih[0][0]);
            System.out.println(wih[1][0]);
            System.out.println(wih[2][0]);
            
            System.out.println(who[0][0]);
            System.out.println(who[1][0]);
            System.out.println("-------------------");
            
            // untuk menghentikan looping
            i = (i + 1) % data.length;
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
            
            double[] z = new double[HIDDEN_NEURONS];
        
            for (int hid = 0; hid < HIDDEN_NEURONS; hid++)
            {
                sum = 0.0;
                for (int inp = 0; inp < INPUT_NEURONS; inp++)
                {
                    if (inp < inputs[i].length)
                    {
                        sum += inputs[i][inp] * wih[inp][hid];
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
