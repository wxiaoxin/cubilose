package com.cubilose;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.*;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {

    @Test
    public void test1() {

        List<Character> characters = new ArrayList<>();

        for (int i = 48; i < 58; i++) {
            characters.add((char) i);
        }

        for (int i = 65; i < 91; i++) {
            characters.add((char) i);
        }

        characters.forEach(c -> {
            System.out.print("'" + c + "',");
        });

        System.out.println();

        Set<String> code = new HashSet<>();
        StringBuffer sbf = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            int next = 0;
            for (int j = 0; j < 10; j++) {
                next = random.nextInt(36);
                sbf.append(characters.get(next));
            }
            code.add(sbf.toString());
            sbf.delete(0, sbf.length());
        }

        System.out.println(code.size());
        code.forEach(s -> {
            System.out.print(s + " ");
        });
    }

}
