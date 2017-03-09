package mealy;

import java.util.Scanner;

public class Mealy {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int c,sum,t;
        String ch;
        int [][] transition = new int[][]
        {{0,1,2,5,10},
         {1,2,3,6,11},
         {2,3,4,7,12},
         {3,4,5,8,13},
         {4,5,6,9,14},
         {5,6,7,10,15},
         {6,7,8,11,16},
         {7,8,9,12,17},
         {8,9,10,13,18},
         {9,10,11,14,19},
         {10,11,12,15,20},
         {11,12,13,16,-1},
         {12,13,14,17,-1},
         {13,14,15,18,-1},
         {14,15,16,19,-1},
         {15,16,17,20,-1},
         {16,17,18,-1,-1},
         {17,18,19,-1,-1},
         {18,19,20,-1,-1},
         {19,20,-1,-1,-1},
         {20,-1,-1,-1,-1},
        };
        sum =0;
        t = 0;
        
        System.out.println("******VENDING MACHINE******");
        System.out.println("Enter coins of denomination (1/2/5/10) to get: ");
        System.out.println("Tea    : 20 Rupees");
        System.out.println("Coffee : 20 Rupees");
        
        while(true){
            System.out.print("\nEnter next coin: ");
            c = in.nextInt();
            if(c==1) t=1; else if (c==2) t=2; else if(c==5) t=3; else if(c==10) t=4;
            else {System.out.println("Wrong Value, Try Again!");
            break;}
            
            if((sum+c)<20)
            {
                System.out.println("Status: You have entered a total of "+ transition[sum][t] +" Rupees.");
                /*Here, transaction[sum][t] represents the next state we arrive on after receiving
                the input of 'c' and present state being sum*/
                sum = sum +c;
            }
            else if((sum+c)>20){
                System.out.println( c + " Coin returned. ");
                System.out.print("Enter a valid coin again: ");
                continue;
            }
            else if((sum+c)==20)
            {
                System.out.print("Enter T/C for Tea/Coffee: ");
                ch = in.next();
                if(ch.equals("C")) 
                    System.out.println("Here's your Coffee!");
                else if(ch.equals("T"))
                    System.out.println("Here's your Tea!");
                break;
            }  
        }
    }
    
}
