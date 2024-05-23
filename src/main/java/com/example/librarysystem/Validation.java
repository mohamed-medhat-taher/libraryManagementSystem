package com.example.librarysystem;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {
   public boolean isNumber(String s)
    {
        for (int i = 0; i < s.length(); i++)
            if (Character.isDigit(s.charAt(i)) == false)
                return false;

        return true;
    }

    public boolean validEmail(String email)
    {
        String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);

        if(matcher.matches() == true){
            return true;
        }else {
            return false;
        }
    }
}
