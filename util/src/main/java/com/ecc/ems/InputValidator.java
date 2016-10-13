package com.ecc.ems;

import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;
import java.lang.IllegalArgumentException;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

public final class InputValidator{
    
    public static int getInputInt(String msg, boolean nonZero){
        Scanner sc = new Scanner(System.in);
        boolean isAlpha = true;
        int floor = nonZero?1:0;
        String limit = nonZero?"one":"zero";
        int row = 0;
        
        while(isAlpha){
            try{
                System.out.print(msg);
                row = Integer.parseInt(sc.nextLine());
                if(row < floor){
                    System.out.println("Integer too low, it should be greater than "+limit);    
                }
                else isAlpha = false;
            }catch(NumberFormatException e){
                isAlpha = true;
                System.out.println("Input not an integer");
            }
        }    
        

        return row;    
    }

    public static int getInputMenu(String msg, int size){
        Scanner sc = new Scanner(System.in);
        boolean isAlpha = true;
        int floor = 1;
        String limit = "one";
        int row = 0;
        
        while(isAlpha){
            try{
                System.out.print(msg);
                row = Integer.parseInt(sc.nextLine());
                if(row < floor){
                    System.out.println("Integer too low, it should be greater than "+limit);    
                }
                else if(row > size){
                    System.out.println("Integer too high, it should be less than "+size);    
                }
                else isAlpha = false;
            }catch(NumberFormatException e){
                isAlpha = true;
                System.out.println("Input not an integer");
            }
        }    
        

        return row;    
    }
    
    public static String getInputStr(String msg, int maxLen){
        Scanner sc = new Scanner(System.in);
        boolean isValid = false;
        String input = "";

        while(!isValid){
            System.out.print(msg);
            if(sc.hasNextLine()){
                input = sc.nextLine();
                if(input.length() > maxLen){
                    System.out.println("Input too long. Max char of " + maxLen);               
                }
                else if (!StringUtils.isAsciiPrintable(input)) {
                    System.out.println("Contains invalid character.");
                }
                else{
                    return input;     
                }
            }           
        }
        
        return null;
    } 
    
    public static Date getInputDate(String msg){
        Scanner sc = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date inputDate;
        String input = "";
        boolean isValid = false;
        
        while(!isValid){
            System.out.print(msg);
            if(sc.hasNextLine()){
                input = sc.nextLine();
                try{
                    inputDate = sdf.parse(input);
                }
                catch(ParseException e){
                    System.out.println("Invalid input, must be in yyyy-mm-dd format.");
                    continue;
                }
                if(input.length() > 10 || inputDate == null){
                    System.out.println("Invalid input, must be in yyyy-mm-dd format.");               
                }
                else{
                    return inputDate;     
                }
            }           
        }
        
        return null;
    }
    
    public static Double getInputDouble(String msg){
        Scanner sc = new Scanner(System.in);
        boolean isAlpha = true;
        Double row = 0.0;
        
        while(isAlpha){
            try{
                System.out.print(msg);
                row = Double.parseDouble(sc.nextLine());
                if(row < 1.0){
                    System.out.println("Integer too low, it should be greater than 1.0");    
                }
                else if(row > 5.0){
                    System.out.println("Integer too high, it should be less than 5.0");
                }
                else isAlpha = false;
            }catch(NumberFormatException e){
                isAlpha = true;
                System.out.println("Input not an integer");
            }
        }    
        

        return row;    
    }
    
    /* int choice: 1 - title, 2 - firstname, 3 - middlename, 4 - lastname,  5 - suffix */
    public static String getInputName(int choice){
        Scanner sc = new Scanner(System.in);    
        String input = null;
        boolean inEnum = false;
        
        switch(choice){
            case 1:
                while(!inEnum){
                    input = InputValidator.getInputStr("Enter title: ", 5);
                    //inEnum = TitleEnum.isValidEnum(input);
                    inEnum = true;
                    if (!inEnum){
                        System.out.println("Invalid title. Title should be Mr., Mrs., Ms., Mx., Engr., Prof., or Hon.");
                    }
                }
                break;
            case 2:
                do{
                    input = InputValidator.getInputStr("Enter First name: ", 30);
                    if(input.length() < 1){
                        System.out.println("First name should not be empty");
                    }
                }while(input.length() < 1);            
                break;
            case 3:
                do{
                    input = InputValidator.getInputStr("Enter Middle name: ", 30);
                    if(input.length() < 1){
                        System.out.println("Middle name should not be empty");
                    }
                }while(input.length() < 1);                
                break;
            case 4:
                do{
                    input = InputValidator.getInputStr("Enter Last name: ", 30);
                    if(input.length() < 1){
                        System.out.println("Last name should not be empty");
                    }
                }while(input.length() < 1);                
                break;
            case 5:
                while(!inEnum){
                    input = InputValidator.getInputStr("Enter suffix: ", 5);
                    //inEnum = SuffixEnum.isValidEnum(input);
                    inEnum = true;
                    if (!inEnum){
                        System.out.println("Invalid suffix. Suffix should be Sr., Jr., I, II, III, IV, or IV");
                    }
                }
                break;              
        }
        
        return input;
    }
    
    public static String getInputMobile(){
        Scanner sc = new Scanner(System.in);
        boolean isValid = false;
        String input = "";

        while(!isValid){
            System.out.print("Enter mobile no : ");
            if(sc.hasNextLine()){
                input = sc.nextLine();
                if(input.length() > 11){
                    System.out.println("Input too long. String size should be of size 11.");               
                }
                else if(input.length() < 11){
                    System.out.println("Input too short. String size should be of size 11.");               
                }
                else if (!StringUtils.isAsciiPrintable(input)) {
                    System.out.println("Contains invalid character.");
                }
                
                try {
                    Validate.matchesPattern(input, "09\\d{9}", "%s is not a valid phone number. Format should be 09xxxxxxxxx.", input);   
                } catch (IllegalArgumentException e) {
                    continue;
                }
                
                return input;
            }           
        }
        
        return null;    
    }
    
    public static String getInputEmail(){
        Scanner sc = new Scanner(System.in);
        boolean isValid = false;
        String input = "";

        while(!isValid){
            System.out.print("Enter email: ");
            if(sc.hasNextLine()){
                input = sc.nextLine();
                try{
                    Validate.matchesPattern(input, "[\\w\\.\\-]+\\@\\w+\\.\\w", " is not a valid email", input);
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid pattern.");
                    continue;
                }
                
                if (!StringUtils.isAsciiPrintable(input)) {
                    System.out.println("Contains invalid character.");
                }
                else{
                    return input;     
                }
            }           
        }
        
        return null;    
    }
    
    public static String getInputPhone(){
        Scanner sc = new Scanner(System.in);
        boolean isValid = false;
        String input = "";

        while(!isValid){
            System.out.print("Enter phone: ");
            if(sc.hasNextLine()){
                input = sc.nextLine();
                try{
                    Validate.matchesPattern(input, "\\d{3}\\-\\d{4}", " is not a valid phone. Format should be 111-1111.", input);
                } catch (IllegalArgumentException e) {
                    continue;
                }
                
                if (!StringUtils.isAsciiPrintable(input)) {
                    System.out.println("Contains invalid character.");
                }
                else{
                    return input;     
                }
            }           
        }
        
        return null;    
    }
    
    public static boolean isEqual(String str1, String str2){
        return StringUtils.equalsIgnoreCase(str1, str2);    
    }
    
    public enum TitleEnum { 
        MR("Mr."), 
        MRS("Mrs."), 
        MS("Ms."), 
        MX("Mx."),  
        ENGR("Engr."),
        PROF("Prof."),
        HON("Hon."),
        EMPTY("");
        
        private String value;
        private TitleEnum (String value){
            this.value = value;
        }
        public static boolean inEnum (String input) {
            for(TitleEnum enm: TitleEnum.values()){
                if(enm.value.equals(input)){
                    return true;
                }
            }
            return false;
        }
    }
    
    public enum ContactEnum { 
        EMAIL("Email"), 
        MOBILE("Mobile"), 
        PHONE("Phone");
        
        private String value;
        private ContactEnum (String value){
            this.value = value;
        }
        public static boolean inEnum (String input) {
            for(ContactEnum enm: ContactEnum.values()){
                if(enm.value.equals(input)){
                    return true;
                }
            }
            return false;
        }
    }
    
    public enum SuffixEnum { 
        SR("Sr."), 
        JR("Jr."), 
        I("I"), 
        II("II"), 
        III("III"), 
        IV("IV"), 
        V("V"),
        EMPTY("");
        
        private String value;
        private SuffixEnum (String value){
            this.value = value;
        }
        public static boolean inEnum (String input) {
            for(SuffixEnum enm: SuffixEnum.values()){
                if(enm.value.equals(input)){
                    return true;
                }
            }
            return false;
        }
    }
}
