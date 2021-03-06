package io.loli.box.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileUtil {
    /*
     * Upload a file to tmp dir waiting for upload. <br> File name is
     */
    public static void uploadToTemp(File file) throws FileNotFoundException, IOException {
        Path target;
        target = new File(System.getProperty("java.io.tmpdir"), getFileName()).toPath();
        Files.copy(new BufferedInputStream(new FileInputStream(file)), target);
    }

    public synchronized static String getFileName() {
        return String.valueOf(System.nanoTime());
    }

    /**
     * Get file suffix by its name<br>
     * Example:
     * 
     * <pre>
     * "test.jpg" =&gt; "jpg"
     * "test" =&gt; ""
     * </pre>
     * 
     * @param filename name of file
     * @return Suffix of this file
     */
    public static String getSuffix(String filename) {
        if (filename.lastIndexOf(".") >= 0) {
            return filename.substring(filename.lastIndexOf(".") + 1).toLowerCase();
        } else {
            return "";
        }
    }

    /**
     * Get the http status of url
     * 
     * @param url will be checked
     * @return the http status of url, if any exception throwed, it will return
     *         0
     */
    public static int getUrlStatus(String url) {
        HttpURLConnection conn = null;

        try {
            URL u = new URL(url);
            conn = (HttpURLConnection) u.openConnection();
            conn.connect();
            return conn.getResponseCode();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        } finally {
            conn.disconnect();
        }
        return 0;

    }
}
