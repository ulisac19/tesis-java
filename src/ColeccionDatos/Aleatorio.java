/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ColeccionDatos;

import java.util.Random;

/**
 *
 * @author Isaac
 */
public class Aleatorio {
    
    
Random rnd;
    public Aleatorio() {
    rnd = new Random();
    }
    
    public static int getAleatorio(int min, int max)
    {
        Random rnd = new Random();
        int f;
        f= (int)(rnd.nextDouble() * max + min);
        return f;
    }
    
}
