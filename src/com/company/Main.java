package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String str = reader.readLine();
            if (str == null ||
                    !Pattern.compile("[\\[ | [\\w] | []]]*").matcher(str.replaceAll("\\s+", str)).matches()) {
                throw new IllegalArgumentException("Invalid String");
            }
            char open = '[';
            char close = ']';
            List<Integer> list = new ArrayList<>();
            StringBuilder word = new StringBuilder();
            StringBuilder result = new StringBuilder();
            StringBuilder builder = new StringBuilder();
            int num = 0;
            char[] c = str.toCharArray();
            for (int i = 0; i < c.length; i++) {
                if (Character.isDigit(c[i])) {
                    num = num * 10 + (c[i] - '0');
                } else if (c[i] == open) {
                    if (Character.isDigit(c[i + 1])) {
                        list.add(0, num);
                        num = 0;
                    }
                } else if (Character.isUnicodeIdentifierStart(c[i])) {
                    word.append(c[i]);
                    if (num == 0)
                        result.append(word);
                } else if (c[i] == close) {
                    while (num != 0) {
                        result.append(word);
                        num--;
                    }
                    num = 0;
                    word = new StringBuilder();
                }
            }
            if (list.isEmpty()) {
                System.out.println(result);
            }
            for (Integer i : list) {
                while (i != 0) {
                    builder.append(result);
                    i--;
                }
            }
            System.out.println(builder);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}