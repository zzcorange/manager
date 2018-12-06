package com.chengzi.redis;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class read {
    public static String[] answer = new String[]{"A","B","C","D"};
    public static   Random random = new Random();
    public static ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(new String[]{"A","B","C","D"}));
    @Test
    public void getAnswer(){
        for(int i=0;i<3;i++){
            for(int j=0;j<13;j++)
                System.out.print(answer[random.nextInt(4)]);
            System.out.print("；多选题--");
            int count = random.nextInt(5);
            count = count>4?4:count;
            ArrayList<String>  arrayListtemp = new ArrayList<>(Arrays.asList(new String[]{"A","B","C","D"}));
         for(int j=0;j<=count;j++){
             System.out.print(arrayListtemp.remove(random.nextInt(arrayListtemp.size())));
         }
            System.out.println();
        }
    }
}
