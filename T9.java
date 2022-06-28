// Zhang Jingyi
// A0239855M

import java.util.*;

public class T9 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int total = Integer.parseInt(sc.nextLine());
        Map<Character, String> dict = new HashMap<Character, String>();
        dict.put('a', "2"); 
        dict.put('b', "22"); 
        dict.put('c', "222"); 
        dict.put('d', "3"); 
        dict.put('e', "33"); 
        dict.put('f', "333"); 
        dict.put('g', "4"); 
        dict.put('h', "44"); 
        dict.put('i', "444"); 
        dict.put('j', "5"); 
        dict.put('k', "55"); 
        dict.put('l', "555"); 
        dict.put('m', "6"); 
        dict.put('n', "66"); 
        dict.put('o', "666"); 
        dict.put('p', "7");
        dict.put('q', "77");  
        dict.put('r', "777"); 
        dict.put('s', "7777"); 
        dict.put('t', "8"); 
        dict.put('u', "88"); 
        dict.put('v', "888"); 
        dict.put('w', "9"); 
        dict.put('x', "99"); 
        dict.put('y', "999"); 
        dict.put('z', "9999"); 
        dict.put(' ', "0"); 

        for (int i = 1; i <= total; i++) {
            StringBuilder sb = new StringBuilder();
            sb.append("Case #");
            sb.append(i);
            sb.append(": ");
            String sentenceRaw = sc.nextLine();
            char [] sentence = sentenceRaw.toCharArray();

            for (int j = 0; j < sentence.length; j++) {
                String corrValue = dict.get(sentence[j]);
                
                Character char1 = corrValue.charAt(0);
                Character char2 = sb.charAt(sb.length()-1);

                if (j > 0 && char1.equals(char2)) {
                    sb.append(" ");
                }

                sb.append(corrValue);
            }

            System.out.println(sb);
        }
    }
}
