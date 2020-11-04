package com.example.excelcols;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.excelcols.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());

    }
    // Excel sheet column title
    private String getColName(int col) {
        StringBuilder CV = new StringBuilder();
        while (col > 0) {
            int rem = col % 26;
            if (rem == 0) {
                CV.append('Z');
                col = (col / 26) - 1;
            } else {
                CV.append((char) (64 + rem));
                col /= 26;
            }
        }
        return CV.reverse().toString();
    }
    // Excel sheet column number
    public int titleToNumber(String s) {

        int col = 0, power = 0;
        for(int i = s.length()-1; i >= 0; i--) {
            char ch = s.charAt(i);
            col += (ch - 'A' + 1) * Math.pow(26, power++);
        }
        return col;
    }

    public void getTittle(View view) {
        Editable text = b.editTextNumber.getText();
        if (text == null){
            Toast.makeText(MainActivity.this, "Please Enter Your Input!!", Toast.LENGTH_SHORT).show();
        }
        int num = Integer.parseInt(String.valueOf(text));
        b.editTextNumber.setText(getColName(num));
    }

    public void getNumber(View view) {
        String text = b.editTextTitle.getText().toString().trim();
        if (text == null){
            Toast.makeText(MainActivity.this, "Please Enter Your Input!!", Toast.LENGTH_SHORT).show();
        }
        int num = titleToNumber(text);
        b.editTextTitle.setText(String.valueOf(num));
    }
}