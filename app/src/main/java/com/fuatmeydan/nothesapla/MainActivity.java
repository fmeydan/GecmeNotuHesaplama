package com.fuatmeydan.nothesapla;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cursoradapter.widget.SimpleCursorAdapter;

import android.content.ContentValues;
import android.content.Intent;
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
import com.fuatmeydan.nothesapla.R;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Helper.DatabaseHelper;

public class MainActivity extends AppCompatActivity  {


    EditText vize1,vize2,vize3,odev1,odev2,odev3,odev4,quiz1,quiz2,quiz3,vize1_oran,vize2_oran,vize3_oran,odev1_oran,odev2_oran,odev3_oran,odev4_oran,quiz1_oran,quiz2_oran,quiz3_oran,gecme_not,final_oran,dersAdi;
    TextView gereken_not;
    Button btn_hesapla,btn_kaydet;
    SharedPreferences sp;
    SharedPreferences.Editor spe;
    ArrayList<String>ders_liste;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN); //full ekran olması için gerekli kod
        this.getSupportActionBar().hide(); //uygulamanın (activity nin) adını gizlemek için kod.
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
        gecme_not=findViewById(R.id.et_gecme_notu);
        final_oran=findViewById(R.id.et_final_oran);
        gereken_not=findViewById(R.id.tv_gerekenNot);
        btn_hesapla=findViewById(R.id.btn_hesapla);
        btn_kaydet=findViewById(R.id.btn_kaydet);
        dersAdi=findViewById(R.id.et_dersAdi);
        sp= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        spe=sp.edit();
        DatabaseHelper dbHelper=new DatabaseHelper(getApplicationContext());
        ders_liste=dbHelper.dersler();

        try {  //intentle gelen verileri boş olabilir diye try bloğu oluşturduk.
            Bundle veriler = getIntent().getExtras();  //intentle gelen verileri almak için BUndle oluşturduk.
            dersAdi.setText(veriler.getString("dersadi")); //veriler adında oluşturduğumuz Bundle daki verileri yerlerine yerleştirdik.
            vize1.setText(veriler.getString("Vize1"));
            vize2.setText(veriler.getString("Vize2"));
            vize3.setText(veriler.getString("Vize3"));
            quiz1.setText(veriler.getString("Quiz1"));
            quiz2.setText(veriler.getString("Quiz2"));
            quiz3.setText(veriler.getString("Quiz3"));
            odev1.setText(veriler.getString("Odev1"));
            odev2.setText(veriler.getString("Odev2"));
            odev3.setText(veriler.getString("Odev3"));
            odev4.setText(veriler.getString("Odev4"));
            vize1_oran.setText(veriler.getString("Vize1_Oran"));
            vize2_oran.setText(veriler.getString("Vize2_Oran"));
            vize3_oran.setText(veriler.getString("Vize3_Oran"));
            quiz1_oran.setText(veriler.getString("Quiz1_Oran"));
            quiz2_oran.setText(veriler.getString("Quiz2_Oran"));
            quiz3_oran.setText(veriler.getString("Quiz3_Oran"));
            odev1_oran.setText(veriler.getString("Odev1_Oran"));
            odev2_oran.setText(veriler.getString("Odev2_Oran"));
            odev3_oran.setText(veriler.getString("Odev3_Oran"));
            odev4_oran.setText(veriler.getString("Odev4_Oran"));
            gecme_not.setText((veriler.getString("Gecme_Not")));
            final_oran.setText(veriler.getString("Final_Oran"));
        }catch (Exception e){ //veriler boş gelirse uygulama birşey yapmadan ve çökmeden devam edecek.

        }










        btn_hesapla.setOnClickListener(new View.OnClickListener() {  //Hesapla butonuna basılınca çalışacak fonksiyon.
            @Override
            public void onClick(View v) {
                try { //önce alanlardaki tüm bilgileri double a çevirdik. sayı yerine harf girilebilmesi ihtimaline karşı try catch bloğu içinde çalışıyoruz.
                    double gecmeNot;
                    if (gecme_not.getText().toString().equals(""))
                    {
                        gecmeNot=0;
                    }else {
                        gecmeNot=Double.parseDouble(gecme_not.getText().toString());
                    }

                    double vize1Not;
                    if (vize1.getText().toString().equals("")){vize1Not=0;}else{vize1Not=Double.parseDouble(vize1.getText().toString());}

                    double vize1Oran;
                    if (vize1_oran.getText().toString().equals("")){vize1Oran=0;}else{vize1Oran=Double.parseDouble(vize1_oran.getText().toString());
                    }

                    double vize2Not;
                    if (vize2.getText().toString().equals("")){vize2Not=0;}else{vize2Not =Double.parseDouble(vize2.getText().toString());
                    }
                    double vize2Oran;
                    if (vize2_oran.getText().toString().equals("")){vize2Oran=0;}else{vize2Oran=Double.parseDouble(vize2_oran.getText().toString());
                    }

                    double vize3Not;
                    if (vize3.getText().toString().equals("")){vize3Not=0;}else{vize3Not=Double.parseDouble(vize3.getText().toString());
                    }

                    double vize3Oran;
                    if (vize3_oran.getText().toString().equals("")){vize3Oran=0;}else{vize3Oran=Double.parseDouble(vize3_oran.getText().toString());
                    }
                    double quiz1Not;
                    if (quiz1.getText().toString().equals("")){quiz1Not=0;}else{quiz1Not=Double.parseDouble(quiz1.getText().toString());
                    }
                    double quiz1Oran;
                    if (quiz1_oran.getText().toString().equals("")){quiz1Oran=0;}else{quiz1Oran=Double.parseDouble(quiz1_oran.getText().toString());}
                    double quiz2Not;
                    if (quiz2.getText().toString().equals("")){quiz2Not=0;}else{quiz2Not=Double.parseDouble(quiz2.getText().toString());}
                    double quiz2Oran;
                    if (quiz2_oran.getText().toString().equals("")){quiz2Oran=0;}else{quiz2Oran=Double.parseDouble(quiz2_oran.getText().toString());}
                    double quiz3Not;
                    if (quiz3.getText().toString().equals("")){quiz3Not=0;}else{quiz3Not=Double.parseDouble(quiz3.getText().toString());}
                    double quiz3Oran;
                    if (quiz3_oran.getText().toString().equals("")){quiz3Oran=0;}else{quiz3Oran=Double.parseDouble(quiz3_oran.getText().toString());}
                    double odev1Not;
                    if (odev1.getText().toString().equals("")){odev1Not=0;}else{odev1Not=Double.parseDouble(odev1.getText().toString());}
                    double odev1Oran;
                    if (odev1_oran.getText().toString().equals("")){odev1Oran=0;}else{odev1Oran=Double.parseDouble(odev1_oran.getText().toString());}
                    double odev2Not;
                    if (odev2.getText().toString().equals("")){odev2Not=0;}else{odev2Not=Double.parseDouble(odev2.getText().toString());}
                    double odev2Oran;
                    if (odev2_oran.getText().toString().equals("")){odev2Oran=0;}else{odev2Oran=Double.parseDouble(odev2_oran.getText().toString());}
                    double odev3Not;
                    if (odev3.getText().toString().equals("")){odev3Not=0;}else{odev3Not=Double.parseDouble(odev3.getText().toString());}
                    double odev3Oran;
                    if (odev3_oran.getText().toString().equals("")){odev3Oran=0;}else{odev3Oran=Double.parseDouble(odev3_oran.getText().toString());}
                    double odev4Not;
                    if (odev4.getText().toString().equals("")){odev4Not=0;}else{odev4Not=Double.parseDouble(odev4.getText().toString());}
                    double odev4Oran;
                    if (odev4_oran.getText().toString().equals("")){odev4Oran=0;}else{odev4Oran=Double.parseDouble(odev4_oran.getText().toString());}
                    double finalOran;
                    if (final_oran.getText().toString().equals("")){finalOran=0;}else{finalOran=Double.parseDouble(final_oran.getText().toString());}






                    if ((vize1Oran+vize2Oran+vize3Oran+odev1Oran+odev2Oran+odev3Oran+odev4Oran+quiz1Oran+quiz2Oran+quiz3Oran+finalOran)!=100){ //oranların toplamı 100 ü geçmemesi için koşul kontrolü yapıyoruz.
                        Toast.makeText(getApplicationContext(),"Oranların Toplamı 100 olmalı",Toast.LENGTH_SHORT).show();
                    }else{
                        //gelen verilerle geçmek için gerekli olan notu hesaplayan formül.
                        double gerkenNot=(gecmeNot-(oranHesap(vize1Not,vize1Oran))-(oranHesap(vize2Not,vize2Oran))-(oranHesap(vize3Not,vize3Oran))-(oranHesap(odev1Not,odev1Oran))-(oranHesap(odev2Not,odev2Oran))-(oranHesap(odev3Not,odev3Oran))-(oranHesap(odev4Not,odev4Oran))-(oranHesap(quiz1Not,quiz1Oran))-(oranHesap(quiz2Not,quiz2Oran))-(oranHesap(quiz3Not,quiz3Oran)))/(finalOran/100);
                        String gerekenString=String.valueOf(gerkenNot);
                        //Toast.makeText(getApplicationContext(),gerekenString,Toast.LENGTH_LONG).show();
                        if (gerkenNot<0){Toast.makeText(getApplicationContext(),"Geçme notu '0' ın altında.",Toast.LENGTH_SHORT).show();} //çıkan sonucun 0 ın altında kalması ihtimaline karşı kontrol yapıyoruz.
                        else{gereken_not.setText(gerekenString);} //çıkan sonucu gerekli alana yazdırıyoruz.
                    }




                }catch (NumberFormatException e){
                    Toast.makeText(getApplicationContext(),"Alanları Boş Bırakmayın",Toast.LENGTH_SHORT).show();
                }




            }
        });


        btn_kaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //verileri veritabanına kaydetmek için gereken fonksiyon.
               if (dersAdi.getText().toString().equals("")){
                   Toast.makeText(getApplicationContext(),"Ders Adı Boş",Toast.LENGTH_SHORT).show();
               }else {

                   DatabaseHelper dbHelper = new DatabaseHelper(getApplicationContext());

                   String kaydet_dersAdi = dersAdi.getText().toString();
                   String kaydet_vize1 = vize1.getText().toString();
                   String kaydet_vize1Oran = (vize1_oran.getText().toString());
                   String kaydet_vize2Oran = (vize2_oran.getText().toString());
                   String kaydet_vize3Oran = (vize3_oran.getText().toString());
                   String kaydet_odev1Oran = (odev1_oran.getText().toString());
                   String kaydet_odev2Oran = (odev2_oran.getText().toString());
                   String kaydet_odev3Oran = (odev3_oran.getText().toString());
                   String kaydet_odev4Oran = (odev4_oran.getText().toString());
                   String kaydet_quiz1Oran = (quiz1_oran.getText().toString());
                   String kaydet_quiz2Oran = (quiz2_oran.getText().toString());
                   String kaydet_quiz3Oran = (quiz3_oran.getText().toString());
                   String kaydet_vize2 = (vize2.getText().toString());
                   String kaydet_vize3 = (vize3.getText().toString());
                   String kaydet_quiz1 = (quiz1.getText().toString());
                   String kaydet_quiz2 = (quiz2.getText().toString());
                   String kaydet_quiz3 = (quiz3.getText().toString());
                   String kaydet_odev1 = (odev1.getText().toString());
                   String kaydet_odev2 = (odev2.getText().toString());
                   String kaydet_odev3 = (odev3.getText().toString());
                   String kaydet_odev4 = (odev4.getText().toString());
                   String kaydet_final_oran = (final_oran.getText().toString());
                   String kaydet_gecme_not = (gecme_not.getText().toString());

                   //DatabaseHelper daki dersEkle metoduna verileri yolluyoruz. String bir değişkene eşitliyoruz kli gelen mesajı ekrana gösterelim. (eşitlemeden direk Toastın içine de yazabilirdik ama açık olsun)
                   String sonuc = dbHelper.dersEkle(kaydet_dersAdi, kaydet_vize1, kaydet_vize2, kaydet_vize3, kaydet_quiz1, kaydet_quiz2, kaydet_quiz3, kaydet_odev1, kaydet_odev2, kaydet_odev3, kaydet_odev4, kaydet_vize1Oran, kaydet_vize2Oran, kaydet_vize3Oran, kaydet_quiz1Oran, kaydet_quiz2Oran, kaydet_quiz3Oran, kaydet_odev1Oran, kaydet_odev2Oran, kaydet_odev3Oran, kaydet_odev4Oran, kaydet_final_oran, kaydet_gecme_not);

                   //Toast ile gelen mesajı ekrana yazdırıyoruz.
                   Toast.makeText(getApplicationContext(), sonuc, Toast.LENGTH_LONG).show();
               }

            }
        });


    }
    private double oranHesap(double not,double oran){ //formülümüzde kalabalık oluşturmaması için oran bu fonksiyonu oluşturduk. girilen oranı yüzdelik kısma çevirmesi için.
        return not*(oran/100);
    }


    @Override
    public void onBackPressed() {  //geri tuşuna basınca ders listesini oluşturması için geri tuş fonksiyonu.
        Intent intent =new Intent(MainActivity.this,GirisActivity.class);
        startActivity(intent);
        finish();
    }



}
