package com.example.demokafka;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Test {

    public static void main(String[] args) {


        Arrays.stream(args).forEach(System.out::println);

        Collections.binarySearch(new ArrayList<String>(), "hello", (s1, s2) -> s1.length() - s2.length());



        String phoneNumber = "+1-800-123-4527";
        String regex = "^//+?[0-9]{1,3}-\\d{3}-\\d{3}-\\d{4}$";  // Phone number regex pattern

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phoneNumber);

        if (matcher.matches()) {
            System.out.println("The phone number is valid.");
        } else {
            System.out.println("The phone number is invalid.");
        }
    }
}
