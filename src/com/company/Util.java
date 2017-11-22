//Author: Meera Murali
package com.company;

import java.util.Scanner;

public class Util
{
    protected Scanner input;

    public Util()
    {
        input = new Scanner(System.in);
    }



    public boolean confirm()
    {
        System.out.print("Confirm changes? yes/no: ");
        String response = input.nextLine();

        if (response.toUpperCase().equals("YES"))
            return true;

        return false;
    }



    public boolean tryAgain()
    {
        System.out.print("Try again? yes/no: ");
        String response = input.nextLine();

        if (response.toUpperCase().equals("YES"))
            return true;
        return false;
    }
}
