/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.util.Random;

/**
 *
 * @author phamh
 */
public class DatabaseUtils {
    public static int randomNumber(int min, int max){
        Random rn = new Random();
        int range = max - min + 1;
        int randomNum = min + rn.nextInt(range);
        return randomNum;
    }
    
    
    
}
