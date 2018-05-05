package com.honger.expo.fileUri;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by chenjian on 2018/5/5.
 */
public class FileURI {
    @Test
    public void getFileFromNet() throws IOException {
        URL url = new URL("http://47.97.201.63:8081/visa/aa.txt");
        URLConnection urlConnection = url.openConnection();
        InputStream inputStream = urlConnection.getInputStream();
        String s = IOUtils.toString(inputStream);
        System.out.println(s);
    }
}
