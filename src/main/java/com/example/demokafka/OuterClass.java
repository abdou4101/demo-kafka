package com.example.demokafka;

public class OuterClass {
     static int staticVar = 10;
    private int instanceVar = 20;

    // Static Nested Class
    static class StaticNestedClass {
        void display() {
            // Direct access to static member
            System.out.println("Static Variable: " + staticVar);

            // Attempting direct access to instance variable (this won't work directly)
            // System.out.println(instanceVar); // ERROR

            // Indirect access via an instance of OuterClass
            OuterClass outer = new OuterClass();
            System.out.println("Instance Variable: " + outer.instanceVar);
        }
    }

    public static void main(String[] args) {
        StaticNestedClass nested = new StaticNestedClass();
        nested.display();
    }
}