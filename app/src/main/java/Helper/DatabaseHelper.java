package Helper;
import java.util.ArrayList;
import java.util.HashMap;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
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
    public void onCreate(SQLiteDatabase db) {  // DatabaseHelper classından instance alınınca kendi çalışıyor bu kod.
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

    public void dersSil(int id){

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, ID + " = ?",
                new String[] { String.valueOf(id) });
        db.close();
    }

    public boolean dersEkle(String ders_adi, String vize1,String vize2,String vize3,String quiz1,String quiz2,String quiz3,String odev1,String odev2,String odev3,String odev4,String vize1_oran,String vize2_oran,String vize3_oran,String quiz1_oran,String quiz2_oran,String quiz3_oran,String odev1_oran,String odev2_oran,String odev3_oran,String odev4_oran,String final_oran,String gecme_not) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        try {
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

            db.insert(TABLE_NAME, null, values);
            db.close();
            return true;
        } catch (Exception e){
            return false;
        }

    }

    public HashMap<String, String> dersDetay(int id){


        HashMap<String,String> ders = new HashMap<String,String>();
        String selectQuery = "SELECT * FROM " + TABLE_NAME+ " WHERE id="+id;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        if(cursor.getCount() > 0){
            ders.put(DERS_ADI, cursor.getString(1));
            ders.put(VIZE1, cursor.getString(2));
            ders.put(VIZE2, cursor.getString(3));
            ders.put(VIZE3, cursor.getString(4));
            ders.put(QUIZ1, cursor.getString(5));
            ders.put(QUIZ2, cursor.getString(6));
            ders.put(QUIZ3, cursor.getString(7));
            ders.put(ODEV1, cursor.getString(8));
            ders.put(ODEV2, cursor.getString(9));
            ders.put(ODEV3, cursor.getString(10));
            ders.put(ODEV4, cursor.getString(11));
            ders.put(VIZE1_ORAN, cursor.getString(12));
            ders.put(VIZE2_ORAN, cursor.getString(13));
            ders.put(VIZE3_ORAN, cursor.getString(14));
            ders.put(QUIZ1_ORAN, cursor.getString(15));
            ders.put(QUIZ2_ORAN, cursor.getString(16));
            ders.put(QUIZ3, cursor.getString(17));
            ders.put(ODEV1_ORAN, cursor.getString(18));
            ders.put(ODEV2_ORAN, cursor.getString(19));
            ders.put(ODEV3_ORAN, cursor.getString(20));
            ders.put(ODEV4_ORAN, cursor.getString(21));
            ders.put(FINAL_ORAN, cursor.getString(22));
            ders.put(GECME_NOT, cursor.getString(23));

        }
        cursor.close();
        db.close();

        return ders;
    }

    public  ArrayList<String> dersler(){



        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(selectQuery, null);
        ArrayList<String> derslist = new ArrayList<String>();


       while(cursor.moveToNext()){
           derslist.add(cursor.getString(cursor.getColumnIndex("ders_adi")));
       }
        db.close();

        return derslist;
    }

    public void dersDuzenle(String ders_adi, String vize1,String vize2,String vize3,String quiz1,String quiz2,String quiz3,String odev1,String odev2,String odev3,String odev4,String vize1_oran,String vize2_oran,String vize3_oran,String quiz1_oran,String quiz2_oran,String quiz3_oran,String odev1_oran,String odev2_oran,String odev3_oran,String odev4_oran,String final_oran,String gecme_not)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
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


        db.update(TABLE_NAME, values, DERS_ADI + " = ?",
                new String[] { String.valueOf(ders_adi) });
    }

    public int getRowCount() {

        String countQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int rowCount = cursor.getCount();
        db.close();
        cursor.close();

        return rowCount;
    }

    public void databaseTemizle(){

        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_NAME, null, null);
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
        // TODO Auto-generated method stub

    }

}









