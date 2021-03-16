package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        unpack("3[xyz]4[xy]l");
        unpack("4[2[3[x]y]]z.");

    }

    public static String unpack(String str) {
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
                if (c[i] != close && num == 0)
                    result.append(word);
            } else if (c[i] == close) {
                for (int j = 0; j < num; j++) {
                    result.append(word);
                }
                num = 0;
                word = new StringBuilder();
            }
        }
        if (list.isEmpty()) {
            System.out.println(result);
            return result.toString();
        }
        for (Integer i : list) {
            for (int j = 0; j < i; j++) {
                builder.insert(0, result);
            }
        }
        return builder.toString();
    }
}