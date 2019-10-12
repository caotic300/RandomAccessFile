package com.Pedro.RandomAccessFile;

import java.io.IOException;

public class UseDatabase {

    public static void main(String[] args) throws IOException {
	// write your code here
        (new Database()).run(new Personal());
        (new Database()).run(new Student());

    }


}
