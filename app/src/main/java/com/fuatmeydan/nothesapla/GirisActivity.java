package com.fuatmeydan.nothesapla;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import Helper.DatabaseHelper;

public class GirisActivity extends AppCompatActivity {
ListView lv;
ArrayList<String> ders_liste;
ArrayAdapter<String> dersadapter;
Button btn_ekle;
String dersadi;
int derspos;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giris);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        this.getSupportActionBar().hide();
        lv=findViewById(R.id.listView_dersler);

        btn_ekle=findViewById(R.id.btn_ekle);
        registerForContextMenu(lv);
    }





    public void onResume(){
        super.onResume();
        final DatabaseHelper dbHelper=new DatabaseHelper(getApplicationContext());




        ders_liste=dbHelper.dersler();
        if (ders_liste.size()==0){
            Intent intent = new Intent(GirisActivity.this,MainActivity.class);
            startActivity(intent);

        }else{
            dersadapter=new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line,ders_liste);
            lv.setAdapter(dersadapter);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    dersadi=lv.getItemAtPosition(position).toString();
                    derspos=position;
                }
            });




        }
        btn_ekle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GirisActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        if (v.getId()==R.id.listView_dersler) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.menu_list, menu);
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
       AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

       derspos=info.position;
       dersadi=lv.getItemAtPosition(derspos).toString();
        final DatabaseHelper dbHelper=new DatabaseHelper(getApplicationContext());


        switch(item.getItemId()) {


            case R.id.Duzenle:
                // edit stuff here
                Dialog dialog = new Dialog(GirisActivity.this);
                dialog.setTitle("Düzenle");
                dialog.setContentView(R.layout.duzenle_layout);
                EditText editTextYeniAd=(EditText)dialog.findViewById(R.id.editText_yeniAd);
                Button buttonYeniAd=(Button)dialog.findViewById(R.id.button_yeniAd_tamam);
                final String yeniAd=editTextYeniAd.getText().toString();

                buttonYeniAd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ders_liste.set(derspos,yeniAd);
                        dersadapter.notifyDataSetChanged();
                    }
                });


                return true;
            case R.id.Sil:


                if (dbHelper.dersSil(dersadi)){
                    Toast.makeText(getApplicationContext(),"Silme İşlemi Başarılı",Toast.LENGTH_SHORT).show();
                    dersadapter.remove(ders_liste.get(derspos));
                    dersadapter.notifyDataSetChanged();
                }else{
                    Toast.makeText(getApplicationContext(),"Silme İşlemi Başarısız",Toast.LENGTH_SHORT).show();
                }

                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }







}


