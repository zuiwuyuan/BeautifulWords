package com.otb.designerassist.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class StringUtil {

    public static String inToString(InputStream inputStream) {

        String result = "";
        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        try {
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
