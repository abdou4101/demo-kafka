package com.example.demokafka;

public class PersonComparator implements java.util.Comparator<Test2.Person> {
    @Override
    public int compare(Test2.Person o1, Test2.Person o2) {

        return o1.age - o2.age;
    }
}
