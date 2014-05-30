package gojava;

import com.google.common.io.ByteStreams;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        InputStream main = Main.class.getResourceAsStream("/goprogram");
        if (main == null) {
            System.exit(1);
        }
        File tmpMainFile = null;
        try {
            tmpMainFile = File.createTempFile("goprogram", null);
            tmpMainFile.deleteOnExit();
            ByteStreams.copy(main, new FileOutputStream(tmpMainFile));
            boolean ok = tmpMainFile.setExecutable(true, true);
            if (!ok) {
                throw new IOException("could not set file to be executable");
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        System.out.println(tmpMainFile);

        try {
            InputStream stdout = new ProcessBuilder().command(tmpMainFile.getPath()).start().getInputStream();
            String addr = new BufferedReader(new InputStreamReader(stdout)).readLine();

            doHttpRequest(addr);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    private static void doHttpRequest(String addr) throws IOException {
        String url = "http://" + addr + "/hello";
        Response resp = new OkHttpClient().newCall(new Request.Builder().get().url(url).build()).execute();
        String respString = resp.body().string();
        System.out.format("from golang: %s\n", respString);
    }
}
