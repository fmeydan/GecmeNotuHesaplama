package com.fuatmeydan.nothesapla;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cursoradapter.widget.SimpleCursorAdapter;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Helper.DatabaseHelper;

public class MainActivity extends AppCompatActivity  {

    Spinner spinner_DersAdi;
    EditText vize1,vize2,vize3,odev1,odev2,odev3,odev4,quiz1,quiz2,quiz3,vize1_oran,vize2_oran,vize3_oran,odev1_oran,odev2_oran,odev3_oran,odev4_oran,quiz1_oran,quiz2_oran,quiz3_oran,gecme_not,final_oran;
    TextView gereken_not;
    Button btn_hesapla,btn_kaydet;
    SQLiteDatabase db;
    DatabaseHelper dbHelper;

    ArrayList<String> derslistesi=new ArrayList<>();
    ArrayAdapter<String> dersadapter;
    SharedPreferences sp;
    SharedPreferences.Editor spe;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        this.getSupportActionBar().hide();



        spinner_DersAdi=findViewById(R.id.spinner_dersAdi);
        vize1=findViewById(R.id.et_vize1);
        vize2=findViewById(R.id.et_vize2);
        vize3=findViewById(R.id.et_vize3);
        odev1=findViewById(R.id.et_odev1);
        odev2=findViewById(R.id.et_odev2);
        odev3=findViewById(R.id.et_odev3);
        odev4=findViewById(R.id.et_odev4);
        quiz1=findViewById(R.id.et_quiz1);
        quiz2=findViewById(R.id.et_quiz2);
        quiz3=findViewById(R.id.et_quiz3);
        vize1_oran=findViewById(R.id.et_vize1_oran);
        vize2_oran=findViewById(R.id.et_vize2_oran);
        vize3_oran=findViewById(R.id.et_vize3_oran);
        odev1_oran=findViewById(R.id.et_odev1_oran);
        odev2_oran=findViewById(R.id.et_odev2_oran);
        odev3_oran=findViewById(R.id.et_odev3_oran);
        odev4_oran=findViewById(R.id.et_odev4_oran);
        quiz1_oran=findViewById(R.id.et_quiz1_oran);
        quiz2_oran=findViewById(R.id.et_quiz2_oran);
        quiz3_oran=findViewById(R.id.et_quiz3_oran);
        gecme_not=findViewById(R.id.tv_gecme_notu);
        final_oran=findViewById(R.id.et_final_oran);
        gereken_not=findViewById(R.id.tv_gerekenNot);
        btn_hesapla=findViewById(R.id.button);
        btn_kaydet=findViewById(R.id.btn_kaydet);
        sp= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        spe=sp.edit();

        try {
            dbHelper=new DatabaseHelper(getApplicationContext());
            db=dbHelper.getReadableDatabase();
            Cursor c=db.rawQuery("select * from dersler2",null);
            while (c.moveToNext()){
                derslistesi.add(c.getString(c.getColumnIndex("ders_adi")));
            }
        }catch(IOException e){
            Toast.makeText(this, "ss", Toast.LENGTH_LONG).show();
        }


        spinner_DersAdi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "Ders Seçimi Yapım Aşamasında", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        btn_hesapla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    double gecmeNot=Double.parseDouble(gecme_not.getText().toString());
                    double vize1Not=Double.parseDouble(vize1.getText().toString());
                    double vize1Oran=Double.parseDouble(vize1_oran.getText().toString());
                    double vize2Not=Double.parseDouble(vize2.getText().toString());
                    double vize2Oran=Double.parseDouble(vize2_oran.getText().toString());
                    double vize3Not=Double.parseDouble(vize3.getText().toString());
                    double vize3Oran=Double.parseDouble(vize3_oran.getText().toString());
                    double quiz1Not=Double.parseDouble(quiz1.getText().toString());
                    double quiz1Oran=Double.parseDouble(quiz1_oran.getText().toString());
                    double quiz2Not=Double.parseDouble(quiz2.getText().toString());
                    double quiz2Oran=Double.parseDouble(quiz2_oran.getText().toString());
                    double quiz3Not=Double.parseDouble(quiz3.getText().toString());
                    double quiz3Oran=Double.parseDouble(quiz3_oran.getText().toString());
                    double odev1Not=Double.parseDouble(odev1.getText().toString());
                    double odev1Oran=Double.parseDouble(odev1_oran.getText().toString());
                    double odev2Not=Double.parseDouble(odev2.getText().toString());
                    double odev2Oran=Double.parseDouble(odev2_oran.getText().toString());
                    double odev3Not=Double.parseDouble(odev3.getText().toString());
                    double odev3Oran=Double.parseDouble(odev3_oran.getText().toString());
                    double odev4Not=Double.parseDouble(odev4.getText().toString());
                    double odev4Oran=Double.parseDouble(odev4_oran.getText().toString());
                    double finalOran=Double.parseDouble(final_oran.getText().toString());



                    if ((vize1Oran+vize2Oran+vize3Oran+odev1Oran+odev2Oran+odev3Oran+odev4Oran+quiz1Oran+quiz2Oran+quiz3Oran+finalOran)!=100){
                        Toast.makeText(getApplicationContext(),"Oranların Toplamı 100 olmalı",Toast.LENGTH_LONG).show();
                    }else{
                        double gerkenNot=(gecmeNot-(oranHesap(vize1Not,vize1Oran))-(oranHesap(vize2Not,vize2Oran))-(oranHesap(vize3Not,vize3Oran))-(oranHesap(odev1Not,odev1Oran))-(oranHesap(odev2Not,odev2Oran))-(oranHesap(odev3Not,odev3Oran))-(oranHesap(odev4Not,odev4Oran))-(oranHesap(quiz1Not,quiz1Oran))-(oranHesap(quiz2Not,quiz2Oran))-(oranHesap(quiz3Not,quiz3Oran)))/(finalOran/100);
                        String gerekenString=String.valueOf(gerkenNot);
                        gereken_not.setText(gerekenString);
                    }




                }catch (NumberFormatException e){
                    Toast.makeText(getApplicationContext(),"Alanları Boş Bırakmayın",Toast.LENGTH_LONG).show();
                }




            }
        });


        btn_kaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Henüz Kaydetme işlemi etkinleştirilmedi",Toast.LENGTH_LONG).show();

            }
        });
        dersadapter=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_dropdown_item_1line,derslistesi);
        spinner_DersAdi.setAdapter(dersadapter);

    }
    private double oranHesap(double not,double oran){
        return not*(oran/100);
    }




}
