package com.example.demokafka;

import org.apache.commons.lang3.time.StopWatch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class Test2 {

    public class Person {

        String name;
        int age;
        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }

    public static void main(String[] args) throws IOException {

        Person[] persons = new Person[10];// {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        for (int i = 0; i < persons.length; i++) {
            Test2 test2 = new Test2();
            persons[i] = test2.new Person("randomName" + i, i);
        }

        for(Person person : persons) {
            System.out.println(person.name + " " + person.age);

        }

        Person person = new Test2().new Person("randomName", 5);

        int result = Arrays.binarySearch(persons, person, (p1, p2) -> p1.age - p2.age);
        System.out.println("Index: " + result);

        long[] array = {1, 2, 3, 4, 5};
        List<Long> list = Arrays.stream(array).boxed().collect(Collectors.toList());

        list.remove(Long.valueOf(2));
        list.forEach(System.out::println);



//        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
//        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
//        String input;
//        while (!(input = bufferedReader.readLine()).isBlank()) {
//            System.out.println("Input: " + input);
//        }
//
//        inputStreamReader.close();
//        bufferedReader.close();



//        System.out.println("Hello");
//        Scanner scanner = new Scanner(System.in);
//        StringBuilder sb = new StringBuilder();
//        String line1 = scanner.nextLine();
//        String line2 = scanner.nextLine();
//        System.out.println(line1.concat(" ").concat(line2));
//        System.out.println(sb.toString());
//        scanner.close();

    }
}
