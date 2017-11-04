/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mlp;

/**
 *
 * @author Ayu Lestari
 */
public class BackpropagationNetwork {
    
    private int INPUT_NEURONS;
    private int HIDDEN_NEURONS;
    private int OUTPUT_NEURONS;
    
    private double[][] wih = new double[INPUT_NEURONS + 1][HIDDEN_NEURONS];
    private double[][] who = new double[HIDDEN_NEURONS + 1][OUTPUT_NEURONS];
    
    private double[] inputs;
    private double[] hidden;
    private double[] outputs;
    private double[] targets;
    
    private double[] erro;
    private double[] errh;
    
    private double LEARNING_RATE;
    private double MSE = 0.0;
    private double stopMSE;
    
    
    
    private void train(double[][] data)
    {
        int i = 0;
        do
        {
            feedForward(data[i]);
            backPropagate(data[i]);
            
            i = (i + 1) % data.length;
        }
        while (MSE > stopMSE);
    }
    
    private int test(double[] data)
    {
        feedForward(data);
        if (outputs[0] > 0.5)
        {
            return 1;
        }
        else
        {
            return 0;
        }
    }
    
    private void feedForward(double[] input)
    {
        double sum = 0.0; // net
        
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
            hidden[hid] = sigmoid(sum);
        }
        
        for (int out = 0; out < OUTPUT_NEURONS; out++)
        {
            sum = 0.0;
            for (int hid = 0; hid < HIDDEN_NEURONS; hid++)
            {
                sum += hidden[hid] * who[hid][out]; 
            }
            
            sum += 1.0 * who[HIDDEN_NEURONS][out];
            outputs[out] = sigmoid(sum);
        }
    }
    
    private void backPropagate(double[] input)
    {
        // menghitung error output dan update bobot hidden ke output
        for (int out = 0; out < OUTPUT_NEURONS; out++)
        {
            MSE += calculateMSE(targets[out], outputs[out]);
            erro[out] = (targets[out] - outputs[out]) * sigmoidDerivative(outputs[out]);
            for (int hid = 0; hid < HIDDEN_NEURONS; hid++)
            {
                double delta_w = LEARNING_RATE * erro[out] * hidden[hid];
                who[hid][out] = who[hid][out] + delta_w;
            }
            who[HIDDEN_NEURONS][out] += LEARNING_RATE * erro[out];
        }
        
        // menghitung error hidden
        for (int hid = 0; hid < HIDDEN_NEURONS; hid++)
        {
            errh[hid] = 0.0;
            for (int out = 0; out < OUTPUT_NEURONS; out++)
            {
                errh[hid] += erro[out] * who[hid][out];
            }
            
            errh[hid] *= sigmoidDerivative(hidden[hid]);
        }
        
        // update bobot input ke hidden
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
    
    private double calculateMSE(double t, double y)
    {
        return 0.5 * Math.pow((t - y), 2);
    }
    
    private double sigmoid(double val)
    {
        return (1.0 / (1.0 + Math.exp(-val)));
    }
    
    private double sigmoidDerivative(double val)
    {
        return (val * (1.0 - val));
    }
}
