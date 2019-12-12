package com.fuatmeydan.nothesapla;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import Helper.DatabaseHelper;

public class GirisActivity extends AppCompatActivity implements DuzenleDialog.duzenleDialogListener {
ListView lv;  //Dersleri listeli şekilde göstermek için listview tanımladık.
ArrayList<String> ders_liste;  //ders listesini adapter ile listview a iletmek için arraylist tanımladık.
ArrayAdapter<String> dersadapter; //ders listesini listview e iletmek için gerekli olan arrayadapter i tanımladık.
Button btn_ekle;
String dersadi;
int derspos;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giris);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN); //Tam ekran gelmesi için gereken kod.

        this.getSupportActionBar().hide(); //uygulama isminin yazdığı barı kaldırmak için gereken kod.
        lv=findViewById(R.id.listView_dersler);
        btn_ekle=findViewById(R.id.btn_ekle);
        registerForContextMenu(lv);  //oluşturduğumuz menüyü listview a bağladık.
    }





    public void onResume(){ //Uygulama kodlarını on resume içinde tanımladıkki geri gidip gelmelerde listview yenilensin.
        super.onResume();
        final DatabaseHelper dbHelper=new DatabaseHelper(getApplicationContext());

        ders_liste=dbHelper.dersler(); //ders listemizi veritabanından aldık.
        if (ders_liste.size()==0){ //eğer ders sayısı sıfır sa direk ders ekleme ve hesaplama ekranına yönlendirmesi için ders listesinin eleman sayısına baktık.
            Intent intent = new Intent(GirisActivity.this,MainActivity.class);
            startActivity(intent);

        }else{ //eğer ders listesi sıfırdan büyükse demekki veritabanında ders var. ozaman ders seçim ekranına otomatik yönlenecektir.
            dersadapter=new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line,ders_liste);
            //array adapterimizi androidin içindeki dropdown item ile ders listesine bağladık.
            lv.setAdapter(dersadapter); //listview e arrayadapterimizi bağladık. böylece adapterin içindeki bilgiler listview e iletilcek.
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() { //listview den herhangi bir ders tıklandığında çalışacak komutlar.
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    dersadi=lv.getItemAtPosition(position).toString();  //seçilen dersin listedeki pozisyonundam dersin adını aldık.
                    derspos=position; //dersin listedeki pozisyonunu aldık.
                    ArrayList<String> liste = dbHelper.dersDetay(dersadi);  //dbhelperdaki dersDetay metoduyla ders bilgilerini listeye atadık.
                    Intent intent = new Intent(GirisActivity.this,MainActivity.class); //gelen bilgilerle ders hesap ekranını açmak için intent oluşturduk.
                    intent.putExtra("dersadi",dersadi);  //dersin adına daha önceden sahip olduğumuz için veritabanından gelen değil elimizdeki ders adını intentle diğer sayfaya iletmek üzere ekledik.
                    intent.putExtra("Vize1",liste.get(1)); //Vize 1 notu gelen listenin 1.indisinde olduğu için 1.indisi intent e ekledik. (dikkat: 0. indisde dersin adı olduğu için 1.indisle başladık. 0.indisde ID yok çünkü dbhelperdan gelen listeyi oluştururken de ID nin olduğu 0.indisi atlamıştık. )
                    intent.putExtra("Vize2",liste.get(2)); //Aynı şekilde devam ettik.
                    intent.putExtra("Vize3",liste.get(3));
                    intent.putExtra("Quiz1",liste.get(4));
                    intent.putExtra("Quiz2",liste.get(5));
                    intent.putExtra("Quiz3",liste.get(6));
                    intent.putExtra("Odev1",liste.get(7));
                    intent.putExtra("Odev2",liste.get(8));
                    intent.putExtra("Odev3",liste.get(9));
                    intent.putExtra("Odev4",liste.get(10));
                    intent.putExtra("Vize1_Oran",liste.get(11));
                    intent.putExtra("Vize2_Oran",liste.get(12));
                    intent.putExtra("Vize3_Oran",liste.get(13));
                    intent.putExtra("Quiz1_Oran",liste.get(14));
                    intent.putExtra("Quiz2_Oran",liste.get(15));
                    intent.putExtra("Quiz3_Oran",liste.get(16));
                    intent.putExtra("Odev1_Oran",liste.get(17));
                    intent.putExtra("Odev2_Oran",liste.get(18));
                    intent.putExtra("Odev3_Oran",liste.get(19));
                    intent.putExtra("Odev4_Oran",liste.get(20));
                    intent.putExtra("Gecme_Not",liste.get(21));
                    intent.putExtra("Final_Oran",liste.get(22));
                    startActivity(intent); //intentdeki veriler ile hesaplama ve güncelleme işlemini yapacağımız mainactivity sayfasını açıyoruz.


                }
            });




        }
        btn_ekle.setOnClickListener(new View.OnClickListener() { //olur ki ders eklemeden geri geliriz veya listedeki dersleri sileriz diye main activity e yönlendirmesi için eklediğimiz buton.
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

       derspos=info.position; //uzun basılan dersin listedeki yerini almak için info.position kullandık. (info onCOntextItemSelected metodunun içiinden geliyor.)
       dersadi=lv.getItemAtPosition(derspos).toString(); //Dersin adını alabilmek için listviev in getItemAtPosition metoduna dersin position ını göndererek aldık.
        final DatabaseHelper dbHelper=new DatabaseHelper(getApplicationContext()); //database işlemleri için databasehelperdan instance aldık.


        switch(item.getItemId()) { //uzun basılınca açılan menüdeki elemanların işlemlerini tanımlamak için switch case yapısı kullandık. Menü için


            case R.id.Duzenle:
                openDialog(); //duzenleye basılınca oluşturduğumuz dialog u açacak fonksiyonu çağırdık.
                                // Açılır menüdeki itemleri oluşturmak için res içinde menu_list.xml oluşturduk. İçinde menü elemanları oluşturuldu.
                return true;
            case R.id.Sil:
                if (dbHelper.dersSil(dersadi)){ //dbHelperdaki ders sil methodu başarılı olursa çalışacak kodlar.
                    Toast.makeText(getApplicationContext(),"Silme İşlemi Başarılı",Toast.LENGTH_SHORT).show();
                    dersadapter.remove(ders_liste.get(derspos)); //listeden dersi sildik.
                    dersadapter.notifyDataSetChanged();//listenin yenilenmesini sağladık.
                }else{
                    Toast.makeText(getApplicationContext(),"Silme İşlemi Başarısız",Toast.LENGTH_SHORT).show();
                }

                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    private void openDialog() { //Uzun basılı tutulunca açılan menuden duzenleyi seçince açılcak olan fragment dialog penceresini açaçak method.
        DuzenleDialog duzenleDialog = new DuzenleDialog(); //DuzenleDiyalog interface inden instance aldık.
        duzenleDialog.show(getSupportFragmentManager(),"Duzenle Dialog");

    }


    @Override  //Açılan dialog penceresinde tamam a tıklanınca çalışacak method.
    public void dersDuzenle(String yeniAd) {
         DatabaseHelper dbHelper = new DatabaseHelper(getApplicationContext()); //yeni yazılan veriyi veritabanındakiyle değiştirmek için databasehelper dan instance aldık.
         String eskiad=dersadi; //eski ders adi lazım olduğu için eskiad olarak bir değişkene atadık.
         ders_liste.remove(derspos); //eski ders i listeden sildik.
         dersadi=yeniAd; //yeni ders adini dersadi olarak globalde tanımladığımız değişkene atadık.
         ders_liste.add(derspos,yeniAd); // eski sildiğimizin yerine listeye ekledik.
         dersadapter.notifyDataSetChanged(); //liste yeni ekleneni göstersin diye notify set changed çalıştırdık.
         dbHelper.sadeceDersAdiDuzenle(yeniAd,eskiad); //dbhelperdaki dersin sadece adını düzenleyen methoda eski ad ve yeni adı gönderdik ve veritabanında ismi değiştirdik.


    }
}


