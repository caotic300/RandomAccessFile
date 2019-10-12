package com.Pedro.RandomAccessFile;

import java.io.EOFException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Database {
    private RandomAccessFile database;
    private String fName;
    private IOmethods io = new IOmethods();

    Database() throws IOException {
        System.out.print("File name: ");
        fName = io.readLine();
    }

    private void add(DbObject d) throws IOException {
        database = new RandomAccessFile(fName, "rw");
        database.seek(database.length());
        d.writeToFile(database);
        database.close();
    }

    private void modify(DbObject d) throws IOException {
        DbObject[] tmp = new DbObject[1];
        d.copy(tmp);
        database = new RandomAccessFile(fName, "rw");

        while (database.getFilePointer() < database.length()) {
            tmp[0].readFromFile(database);
            if (tmp[0].equals(d)) {
                tmp[0].readFromconsole();
                database.seek(database.getFilePointer() - d.size());
                tmp[0].writeToFile(database);
                database.close();
            }
        }
        database.close();
        System.out.println("The record to be modified is not in the database");
    }

    private boolean find(DbObject d) throws IOException {
        DbObject[] tmp = new DbObject[1];
        d.copy(tmp);
        database = new RandomAccessFile(fName, "r");
        while (database.getFilePointer() < database.length()) {
            tmp[0].readFromFile(database);
            if (tmp[0].equals(d)) {
                database.close();
                return true;
            }
        }
        database.close();
        return false;
    }

    private void printDb(DbObject d) throws IOException {
        try {
            database = new RandomAccessFile(fName, "r");
            while (database.getFilePointer() < database.length()) {
                d.readFromFile(database);
                d.writeLegibly();
                System.out.println();
            }
        } catch (EOFException e){
            e.printStackTrace();
        } finally {
            database.close();
        }

    }

    public void run(DbObject rec) throws IOException {
        String option;
        System.out.println("1. Add 2. Find 3. Modify a record; 4. Exit");
        System.out.print("Enter an option: ");
        option = io.readLine();

        while (true) {

            if (option.charAt(0) == '1') {
                rec.readFromconsole();
                add(rec);
            } else if (option.charAt(0) == '2') {
                rec.readKey();
                System.out.print("The record is: ");
                if (find(rec))
                    System.out.print("not ");
                System.out.println("in the database");
            } else if (option.charAt(0) == '3') {
                rec.readKey();
                modify(rec);
            } else if (option.charAt(0) != 4) {
                System.out.println("Wrong option");
            } else {

            }

            printDb(rec);
            System.out.print("Enter an option: ");
            option = io.readLine();
        }
    }
}
