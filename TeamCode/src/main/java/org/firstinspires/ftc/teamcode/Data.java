package org.firstinspires.ftc.teamcode;

import android.os.Environment;
import java.io.File;
import java.io.IOException;
import java.io.Writer;

public class Data {

    private static final String BASE_FOLDER_NAME = "FIRST";
    private Writer fileWriter;
    Data(String filename) {
        String directoryPath = Environment.getExternalStorageDirectory().getPath()+"/"+BASE_FOLDER_NAME;
        File directory = new File(directoryPath);
        //noinspection ResultOfMethodCallIgnored
        directory.mkdir();
        try {
            fileWriter = new java.io.FileWriter(directoryPath + "/" + filename + ".csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void close() {
        try {
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void addData(String data) {
        try {
            fileWriter.write(data + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void addData(Object data) {
        addData(data.toString());
    }
    public void addData(boolean data) {
        addData(String.valueOf(data));
    }
    public void addData(byte data) {
        addData(String.valueOf(data));
    }
    public void addData(char data) {
        addData(String.valueOf(data));
    }
    public void addData(short data) {
        addData(String.valueOf(data));
    }
    public void addData(int data) {
        addData(String.valueOf(data));
    }
    public void addData(long data) {
        addData(String.valueOf(data));
    }
    public void addData(float data) {
        addData(String.valueOf(data));
    }
    public void addData(double data) {
        addData(String.valueOf(data));
    }
}
