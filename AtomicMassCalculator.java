package java;

import java.util.HashMap;
import java.util.Map;

public class AtomicMassCalculator {

    public static final HashMap<String, Double> atomicMass = new HashMap<String, Double>();


    public void addElements() {
        atomicMass.put("H", 1.00794);
        atomicMass.put("S", 32.065);
        atomicMass.put("O", 15.9994);
        atomicMass.put("Cl", 35.0);
    }

    public void AtomicWeightPrint() {


        for (Map.Entry m : atomicMass.entrySet()) {
            System.out.println(m.getKey() + " " + m.getValue());
        }
    }


    static boolean isDigit(char ch) {
        return ch >= '2' && ch <= '9';
    }

    static boolean isLowerCase(char ch) {
        return ch >= 'a' && ch <= 'z';
    }

    static boolean isUpperCase(char ch) {
        return ch >= 'A' && ch <= 'Z';
    }


    public double calculateAtomicMass(String element) {

        if (element.length() == 1) {
            return atomicMass.get(element);

        }

        else if (element.length() > 1) {

            char[] c = element.toCharArray();
            double totalSum = 0;
            String ele = "", dig = "",inParenthesis="";

            if (isUpperCase(c[0])) {
                for (int i = 0; i < c.length; i++) {

                    if (((i + 1) == c.length) || isUpperCase(c[i + 1])) {

                        if (isUpperCase(c[i])) {
                            totalSum += atomicMass.get(Character.toString(c[i]));
                            continue;
                        }

                    }

                    else if (((i + 1) == c.length) || isLowerCase(c[i + 1])) {


                        if ((i + 1) != c.length) {
                            ele = Character.toString(c[i]) + Character.toString(c[i + 1]);

                        }

                        totalSum += atomicMass.get(ele);
                        ele = "";
                    }

                    else if (((i + 1) == c.length) || isDigit(c[i + 1])) {


                        System.out.println(c[i]);
                        int temp = i;
                         System.out.println(c[temp]);
                        if (isLowerCase(c[temp])) {

                            ele = Character.toString(c[temp - 1]) + Character.toString(c[temp]);

                            while ((i + 1) != c.length && isDigit(c[i + 1])) {

                                dig += Character.toString(c[i + 1]);
                                i++;
                            }

                            totalSum += atomicMass.get(ele) * (Integer.parseInt(dig) - 1) ;
                            System.out.println("sum:" + totalSum);
                            System.out.println("ele" + ele);
                            System.out.println("dig" + dig);
                            dig = "";
                            ele = "";
                            continue;
                        }
                        else {

                            while ((i + 1) != c.length && isDigit(c[i + 1])) {

                                dig += Character.toString(c[++i]);
                            }
                            totalSum += atomicMass.get(Character.toString(c[temp])) * Integer.parseInt(dig);
                            dig = "";
                            ele = "";
                            continue;
                        }
                    }


                    else if(((i + 1) == c.length) || c[i+1]=='('){

                        if(isUpperCase(c[i]))
                            totalSum += atomicMass.get(Character.toString(c[i]));

                        i++;

                        while(c[i+1] != ')'){

                            inParenthesis += Character.toString(c[++i]);
                        }

                        i++;
                        if(isDigit(c[i+1])){

                            while ((i + 1) != c.length && isDigit(c[i + 1])) {

                                dig += Character.toString(c[++i]);
                            }

                            totalSum += calculateAtomicMass(inParenthesis) * Integer.parseInt(dig);
                            dig="";
                            inParenthesis="";
                            continue;

                        }
                        else
                            totalSum +=  calculateAtomicMass(inParenthesis);
                        inParenthesis="";
                    }
                }
        } else {
            System.out.println("Invalid Input");
        }

        return totalSum;
    }
    return 0.0;
    }
}
