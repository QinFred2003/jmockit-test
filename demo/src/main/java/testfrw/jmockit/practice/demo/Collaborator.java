package testfrw.jmockit.practice.demo;

import java.util.Date;

public class Collaborator {
	final int value;

    Collaborator() { value = -1; }
    Collaborator(int value) { this.value = value; }

    int getValue() { return value; }
    final boolean simpleOperation(int a, String b, Date c) { return true; }
    static void doSomething(boolean b, String s) { throw new IllegalStateException(); }
    public int doSomething(int i) {return i;}
}
