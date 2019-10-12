package com.Pedro.RandomAccessFile;
import java.io.*;
import java.util.Arrays;

public class Personal extends IOmethods implements DbObject  {
    protected final int nameLen = 10, cityLen = 10;
    protected String SSN, name, city;
    protected int year;
    protected long salary;
    protected final int size = 9*2 + nameLen*2 + cityLen*2 + 4 + 8;

    public Personal() {
    }

    public Personal(String SSN, String name, String city, int year, long salary) {
        this.SSN = SSN;
        this.name = name;
        this.city = city;
        this.year = year;
        this.salary = salary;
    }

    @Override
    public boolean equals(Object obj) {
        return SSN.equals(((Personal) obj).SSN);
    }

    @Override
    public void writeToFile(RandomAccessFile out) throws IOException {
        writeString(SSN, out);
        writeString(name, out);
        writeString(city, out);
        out.writeInt(year);
        out.writeLong(salary);
    }

    @Override
    public void readFromFile(RandomAccessFile in) throws IOException {
        SSN = readString(9, in);
        name = readString(nameLen, in);
        city = readString(cityLen, in);
        year = in.readInt();
        salary = in.readLong();
    }

    @Override
    public void writeLegibly() throws IOException {
        System.out.println("SSN = " + SSN + ", name = " + name.trim()
        + ", city = " + city.trim() + ", year = " + year);
    }

    @Override
    public void copy(DbObject[] db) {
        db[0] = new Personal(SSN, name, city, year, salary);
    }

    @Override
    public void readKey() throws IOException {
        System.out.println("Enter SSN: ");
        SSN = readLine();
    }

    @Override
    public void readFromconsole() throws IOException {
        System.out.print("Enter SSN: ");
        SSN = readLine();
        System.out.print("Name: ");
        name = readLine();

        for (int i = name.length(); i < nameLen; i++)
            name += ' ';

        System.out.print("City: ");
        city = readLine();
        for (int i = city.length(); i < cityLen; i++) {
            city += " ";
            System.out.print("Birthyear: ");
            year = Integer.valueOf(readLine().trim()).intValue();
            System.out.print("Salary: ");
            salary = Long.valueOf(readLine().trim()).longValue();
        }
    }

    @Override
    public int size() {
        return size;
    }


}

