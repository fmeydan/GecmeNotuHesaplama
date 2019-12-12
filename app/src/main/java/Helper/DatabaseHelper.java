package Helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;


public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Versiyonumuz.
    private static final int DATABASE_VERSION = 1;


    private static final String DATABASE_NAME = "nothesap.db";//database adı

    private static final String TABLE_NAME = "dersler";
    private static String ID = "ID";
    private static String DERS_ADI = "ders_adi";
    private static String VIZE1 = "vize1";
    private static String VIZE1_ORAN = "vize1_oran";
    private static String VIZE2 = "vize2";
    private static String VIZE3 = "vize3";
    private static String ODEV1 = "odev1";
    private static String ODEV2 = "odev2";
    private static String ODEV3 = "odev3";
    private static String ODEV4 = "odev4";
    private static String QUIZ1 = "quiz1";
    private static String QUIZ2 = "quiz2";
    private static String QUIZ3 = "quiz3";
    private static String VIZE2_ORAN = "vize2_oran";
    private static String VIZE3_ORAN = "vize3_oran";
    private static String ODEV1_ORAN = "odev1_oran";
    private static String ODEV2_ORAN = "odev2_oran";
    private static String ODEV3_ORAN = "odev3_oran";
    private static String ODEV4_ORAN = "odev4_oran";
    private static String QUIZ1_ORAN = "quiz1_oran";
    private static String QUIZ2_ORAN = "quiz2_oran";
    private static String QUIZ3_ORAN = "quiz3_oran";
    private static String FINAL_ORAN = "final_oran";
    private static String GECME_NOT = "gecme_not";



    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {  // DatabaseHelper classından instance alınınca kendi çalışıyor bu kod. static constant olarak oluşturduğumuz değerler ile yoksa veritabanı oluşturuyor.
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + DERS_ADI + " TEXT,"
                + VIZE1 + " TEXT,"
                + VIZE2 + " TEXT,"
                + VIZE3 + " TEXT,"
                + QUIZ1+" TEXT,"
                + QUIZ2+" TEXT,"
                + QUIZ3+" TEXT,"
                + ODEV1+" TEXT,"
                + ODEV2+" TEXT,"
                + ODEV3+" TEXT,"
                + ODEV4+" TEXT,"
                + VIZE1_ORAN+" TEXT,"
                + VIZE2_ORAN+" TEXT,"
                + VIZE3_ORAN+" TEXT,"
                + QUIZ1_ORAN+" TEXT,"
                + QUIZ2_ORAN+" TEXT,"
                + QUIZ3_ORAN+" TEXT,"
                + ODEV1_ORAN+" TEXT,"
                + ODEV2_ORAN+" TEXT,"
                + ODEV3_ORAN+" TEXT,"
                + ODEV4_ORAN+" TEXT,"
                + GECME_NOT+" TEXT,"
                + FINAL_ORAN+" TEXT"+")";
        db.execSQL(CREATE_TABLE);
    }

    public boolean dersSil(String ad){ //Veritabanından ders silmek için oluşturduğumuz fonksiyon.

        SQLiteDatabase db = this.getWritableDatabase(); //veritabanını yazılabilir şekilde açtık.
        try{

            String sql=String.format("delete from dersler where ders_adi = '%s'",ad); //ders adını göndereceğimiz şekilde sql stringimizi oluşturduk.

            db.execSQL(sql);
            db.close();
            return true;}

        catch (Exception e){

            return false;
        }

    }


    //Ders ekle metodu ilk önce ders database de aynı isimde ders var mı yok mu onu kontrol eder
    //Ders database de mevcutsa girilen değerler ile databesi günceller.
    //Mevcut değilse yeni ders olarak dersi ekler.
    public String dersEkle(String ders_adi, String vize1,String vize2,String vize3,String quiz1,String quiz2,String quiz3,String odev1,String odev2,String odev3,String odev4,String vize1_oran,String vize2_oran,String vize3_oran,String quiz1_oran,String quiz2_oran,String quiz3_oran,String odev1_oran,String odev2_oran,String odev3_oran,String odev4_oran,String final_oran,String gecme_not) {

        SQLiteDatabase dbr = this.getReadableDatabase(); //Database de ders varmı yokmu sorgulayabilmek için instance aldık(getReadableDatabase)
        String selectQuery = String.format("select * from dersler where ders_adi='%s'",ders_adi); //Ders adını selectquery isminde bir değişkene atadık (String formatlama kullandık %s olan yere virgülden sonra ders_adi olan değişken gelecek.)
        Cursor cursor = dbr.rawQuery(selectQuery, null); //Cursor tanımlayıp select sorgusunu gönderdik.
        if (!cursor.moveToNext()){    //Eğer cursor sıradakine ilerlemesse bu veritabanında bu isimde bir ders olmadığını gösterir. Yani Ekleme yapabiliriz.
        SQLiteDatabase db = this.getWritableDatabase(); //Veritabanını yazılabilir bir şekilde aldık.
        ContentValues values = new ContentValues(); //Content values oluşturduk böylece sorguya hangi değeri neyle kaydedeceğini gönderebileceğiz.
        try {  //hata olasılığını düşürmek için try catch bloğu ile verileri values in içine gönderiyoruz.
            //Daha önce static değişmez (Static Constant) olarak veritabanı kolonlarını tanımladık. böylece kolon isimleri konusunda hata ihtimalini ortadan kaldırdık.
            values.put(DERS_ADI, ders_adi);
            values.put(VIZE1, vize1);
            values.put(VIZE2, vize2);
            values.put(VIZE3, vize3);
            values.put(QUIZ1,quiz1);
            values.put(QUIZ2,quiz2);
            values.put(QUIZ3,quiz3);
            values.put(ODEV1,odev1);
            values.put(ODEV2,odev2);
            values.put(ODEV3,odev3);
            values.put(ODEV4,odev4);
            values.put(VIZE1_ORAN,vize1_oran);
            values.put(VIZE2_ORAN,vize2_oran);
            values.put(VIZE3_ORAN,vize3_oran);
            values.put(QUIZ1_ORAN,quiz1_oran);
            values.put(QUIZ2_ORAN,quiz2_oran);
            values.put(QUIZ3_ORAN,quiz3_oran);
            values.put(ODEV1_ORAN,odev1_oran);
            values.put(ODEV2_ORAN,odev2_oran);
            values.put(ODEV3_ORAN,odev3_oran);
            values.put(ODEV4_ORAN,odev4_oran);
            values.put(FINAL_ORAN,final_oran);
            values.put(GECME_NOT,gecme_not);

            db.insert(TABLE_NAME, null, values);  //database e insert komutu ile verilerimizi ekledik.
            db.close();
            return "Ders Ekleme Başarılı"; //Toast mesaj ile verilerin eklendiğini ekrana yazdırdık.
        } catch (Exception e){ //Veri eklenirken bir hata oluşursa çalışacak blok.

            return "Ders Eklenirken Bir Hata Oluştu"; //Bir hata meydana gelirse Toast mesajı ile ekrana yazdıracağımız hata mesajı.
        }
        }else {  //Cursor eğer bir sonraki satıra geçerse bu veritabanında o isimde bir ders olduğunu gösterir. Ozaman yukarıdaki if bloğu çalışmayıp buraya gelir ve else bloğumuz çalışır.


            try { //Aşağıda tanımladığımız dersGuncelle fonksiyonunu çağırdık ve değerleri gönderdik.
                //try catch bloğu içinde yaptıkki herhangi bir hatada uygulamamız çökmesin.
              dersDuzenle(ders_adi,vize1,vize2,vize3,quiz1,quiz2,quiz3,odev1,odev2,odev3,odev4,vize1_oran,vize2_oran,vize3_oran,quiz1_oran,quiz2_oran,quiz3_oran,odev1_oran,odev2_oran,odev3_oran,odev4_oran,final_oran,gecme_not);
                return "Ders Güncellendi";


            } catch (Exception e) {

                return "Ders Güncellemede Hata Oluştu";
            }
        }
        }

    public ArrayList<String> dersDetay(String dersad){  //Derslerin notlarını alabilmek için dersin adını değer olarak alan bu metodu oluşturduk.
        SQLiteDatabase db = this.getReadableDatabase(); //veritabanını okuma modunda alıyoruz.

        ArrayList<String> ders = new ArrayList<>(); //Gelen notları tutmak için ders adında ArrayList tanımladık.


        String selectQuery=("SELECT * FROM " + TABLE_NAME+ " WHERE ders_adi=?"); //metoda gelen dersin adını yerleştirebileceğimiz ve o derse ait bilgilerin gelmesi için sorgu oluşturuyoruz.


        Cursor cursor = db.rawQuery(selectQuery,new String[] { String.valueOf(dersad) }); //Metoda gelen ders adini yerleştirip sorguyu çalıpştıracak ver verileri tek tek okuyacak bir cursor oluşturuyoruz.
        cursor.moveToFirst(); //İlk Sıraya gitmesini sağlıyoruz böylece ders atlama ihtimalini ortadan kaldırıyoruz.
        if(cursor.getCount() > 0){ //Cursorumuz eğer ilerleyebiliyorsa bu dersin olduğu anlamına gelir ve verileri önceden oluşturduğumuz ders adlı listeye eklemeye başlar.
            ders.add(cursor.getString(1)); //dikkat edilmesi gereken konu 0.index den başlamadık. çünkü 0.index bize dersin id sini veriyor. Aslında 1.indexi de almayabilirdik dersin adı zaten elimizde mevcut.
            ders.add(cursor.getString(2));
            ders.add(cursor.getString(3));
            ders.add( cursor.getString(4));
            ders.add( cursor.getString(5));
            ders.add( cursor.getString(6));
            ders.add( cursor.getString(7));
            ders.add( cursor.getString(8));
            ders.add( cursor.getString(9));
            ders.add( cursor.getString(10));
            ders.add( cursor.getString(11));
            ders.add( cursor.getString(12));
            ders.add( cursor.getString(13));
            ders.add( cursor.getString(14));
            ders.add( cursor.getString(15));
            ders.add( cursor.getString(16));
            ders.add( cursor.getString(17));
            ders.add( cursor.getString(18));
            ders.add( cursor.getString(19));
            ders.add( cursor.getString(20));
            ders.add(cursor.getString(21));
            ders.add( cursor.getString(22));
            ders.add( cursor.getString(23));

        }
        cursor.close();
        db.close();

        return ders;
    }

    public  ArrayList<String> dersler(){ //Derslerin isimlerini almamızı sağlayan metot.



        SQLiteDatabase db = this.getReadableDatabase(); //veritabanını okunabilir şekilde aldık.
        String selectQuery = "SELECT * FROM " + TABLE_NAME; //veritabanındaki tüm bilgileri almak için sorgu stringimizi yazdık.
        Cursor cursor = db.rawQuery(selectQuery, null); //Kolonları tek tek gezmesi için cursor oluşturup sorgu stringimizle çalıştırdık.
        ArrayList<String> derslist = new ArrayList<>(); //Gelen ders adlarını tutmak için bir array list tanımladık.


        while(cursor.moveToNext()){
            derslist.add(cursor.getString(cursor.getColumnIndex(DERS_ADI))); //cursorun ilerlediki her satırdaki ders_adi isimli kolondaki veriyi alıp oluşturduğumuz listeye attık.

        }
        db.close();

        return derslist; //ders listesini return ettik.
    }

    public void dersDuzenle(String ders_adi , String vize1,String vize2,String vize3,String quiz1,String quiz2,String quiz3,String odev1,String odev2,String odev3,String odev4,String vize1_oran,String vize2_oran,String vize3_oran,String quiz1_oran,String quiz2_oran,String quiz3_oran,String odev1_oran,String odev2_oran,String odev3_oran,String odev4_oran,String final_oran,String gecme_not)
    {
        SQLiteDatabase db = this.getWritableDatabase(); //Veritabanını yine yazılabilir bir şekilde alıyoruz.

        // Burada gelen verileri yine aynı şekilde values e atıp bu defa update komutuyla güncelleme yapacağız.
        ContentValues values = new ContentValues();  //Values olarak değerlerimizi tanımlıyoruz.
        values.put(VIZE1, vize1);
        values.put(VIZE2, vize2);
        values.put(VIZE3, vize3);
        values.put(QUIZ1,quiz1);
        values.put(QUIZ2,quiz2);
        values.put(QUIZ3,quiz3);
        values.put(ODEV1,odev1);
        values.put(ODEV2,odev2);
        values.put(ODEV3,odev3);
        values.put(ODEV4,odev4);
        values.put(VIZE1_ORAN,vize1_oran);
        values.put(VIZE2_ORAN,vize2_oran);
        values.put(VIZE3_ORAN,vize3_oran);
        values.put(QUIZ1_ORAN,quiz1_oran);
        values.put(QUIZ2_ORAN,quiz2_oran);
        values.put(QUIZ3_ORAN,quiz3_oran);
        values.put(ODEV1_ORAN,odev1_oran);
        values.put(ODEV2_ORAN,odev2_oran);
        values.put(ODEV3_ORAN,odev3_oran);
        values.put(ODEV4_ORAN,odev4_oran);
        values.put(FINAL_ORAN,final_oran);
        values.put(GECME_NOT,gecme_not);

        //Bu defa insert değil update komutumuzu çalıştırıyoruz.
        db.update(TABLE_NAME, values, DERS_ADI + " = ?",
                new String[] { String.valueOf(ders_adi) });
        db.close();

    }

    public void sadeceDersAdiDuzenle(String yeniDers,String eskiDers){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DERS_ADI,yeniDers);
        db.update(TABLE_NAME, values, DERS_ADI + " = ?",
                new String[] { String.valueOf(eskiDers) });
        db.close();
    }

    public int satirSayisi() {

        //Şu an için bu fonksiyona ihtiyacımız yok. fakat ilerde kaç ders oluğunu bulmak için lazım olabilir.
        String countQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int rowCount = cursor.getCount();
        db.close();
        cursor.close();

        return rowCount;
    }

    public void databaseTemizle(){
        //Databasei temizlemek için gerekli metot henüz kullanmamıza gerek yok.
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_NAME, null, null);
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
        // TODO Auto-generated method stub

    }

}









