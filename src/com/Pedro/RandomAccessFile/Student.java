package com.Pedro.RandomAccessFile;

import java.io.IOException;
import java.io.RandomAccessFile;

public class Student extends Personal {
    protected String major;
    protected final int majorLen = 10;

    Student() {
        super();
    }

    public Student(String SSN, String name, String city, int year, long salary, String major) {
        super(SSN, name, city, year, salary);
        this.major = major;
    }

    @Override
    public void writeToFile(RandomAccessFile out) throws IOException {
        super.writeToFile(out);
        writeString(major, out);
    }

    @Override
    public void readFromFile(RandomAccessFile in) throws IOException {
        super.readFromFile(in);
        major = readString(majorLen, in);
    }

    @Override
    public void readFromconsole() throws IOException {
        super.readFromconsole();
        System.out.print("Enter major: ");
        major = readLine();

        for (int i = major.length(); i < majorLen; i++)
            major += ' ';

    }

    @Override
    public void writeLegibly() throws IOException {
        super.writeLegibly();
        System.out.print(", major = " + major.trim());
    }

    @Override
    public void copy(DbObject[] db) {
        db[0] = new Student(SSN, name, city, year, salary, major);
    }

}
