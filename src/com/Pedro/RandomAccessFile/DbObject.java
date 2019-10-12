package com.Pedro.RandomAccessFile;

import java.io.IOException;
import java.io.RandomAccessFile;

public interface DbObject {

    void writeToFile(RandomAccessFile out) throws IOException;
    void readFromFile(RandomAccessFile in) throws IOException;
    void readFromconsole() throws IOException;
    void writeLegibly() throws IOException;
    void readKey() throws IOException;
    void copy(DbObject[] db);
    int size();
}

