package com.example.filetest;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.*;

public class MainActivity extends AppCompatActivity {


    TextView textView;
    EditText editText;
    final String FILE_NAME = "test.bin";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textView);
        editText = (EditText) findViewById(R.id.editText);

    }

    public void write(View view) {

        try {
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                File sdCradDir = Environment.getExternalStorageDirectory();

                File targetFile =new File(sdCradDir.getCanonicalFile()+FILE_NAME);

                RandomAccessFile raf=new RandomAccessFile(targetFile,"rw");

                String content = editText.getText().toString();
                raf.seek(targetFile.length());
                raf.write(content.getBytes());

                raf.close();


                editText.setText("");
                FileOutputStream fos = openFileOutput(FILE_NAME, MODE_APPEND);
                PrintStream ps = new PrintStream(fos);
                ps.println(content);
                ps.close();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void read(View view) {
        textView.setText("");
        try {
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                File sdCradDir = Environment.getExternalStorageDirectory();

                FileInputStream fis1 = new FileInputStream(sdCradDir.getCanonicalPath() + FILE_NAME);

                BufferedReader br = new BufferedReader(new InputStreamReader(fis1));


                FileInputStream fis = openFileInput(FILE_NAME);

                byte buff[] = new byte[1024];
                int hasRead = 0;
                StringBuffer sb = new StringBuffer("");

                String line = "";
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
               /* while ((hasRead = fis.read(buff)) > 0) {
                    sb.append(new String(buff, 0, hasRead));
                }*/

                fis.close();
                br.close();

                textView.setText(sb.toString());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
