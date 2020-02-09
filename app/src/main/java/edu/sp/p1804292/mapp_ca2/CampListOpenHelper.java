package edu.sp.p1804292.mapp_ca2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CampListOpenHelper extends SQLiteOpenHelper {

    // It's a good idea to always define a log tag like this.
    private static final String TAG = CampListOpenHelper.class.getSimpleName();

    // has to be 1 first time or app will crash
    private static final int DATABASE_VERSION = 1;
    private static final String CAMP_LIST_TABLE = "camp_entries";
    private static final String DATABASE_NAME = "camplist";
    // Column names...
    public static final String KEY_ID = "id";
    public static final String KEY_NAME = "name";
    public static final String KEY_DESC = "descr";
    public static final String KEY_CDATE = "cdate";
    public static final String KEY_REGDATE = "regdate";
    public static final String KEY_LOCATION = "location";
    public static final String KEY_PRICE = "price";
    public static final String KEY_CONTACT = "price";
    public static final String KEY_LINK = "link";


    // ... and a string array of columns.
    private static final String[] COLUMNS = { KEY_ID, KEY_NAME, KEY_DESC, KEY_CDATE, KEY_REGDATE, KEY_LOCATION, KEY_PRICE, KEY_CONTACT, KEY_LINK };

    // Build the SQL query that creates the table.

    //SYNTAX
    //" + KEY_ID +" INTEGER PRIMARY KEY,

    private static final String CAMP_LIST_TABLE_CREATE =
            "CREATE TABLE " + CAMP_LIST_TABLE + " (" +
                    KEY_ID + " INTEGER PRIMARY KEY, " +
                    // id will auto-increment if no value passed
                    KEY_NAME + " TEXT, " +
                    KEY_DESC + " TEXT, " +
                    KEY_CDATE + " TEXT, " +
                    KEY_REGDATE +" TEXT, " +
                    KEY_PRICE +" TEXT, " +
                    KEY_LOCATION +" TEXT, " +
                    KEY_LINK +" TEXT);";


    private SQLiteDatabase mWritableDB;
    private SQLiteDatabase mReadableDB;

    //ADD ARRAYLSIT FOR CAMP ITEM ARRAYLIST <CAMP> CAMPITEMS = NEW CAMPITEM()



    public CampListOpenHelper (Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CAMP_LIST_TABLE_CREATE);
        //fillDatabaseWithData(db);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) { ;
        try{

            db.execSQL("DROP TABLE IF EXISTS " + CAMP_LIST_TABLE);
            onCreate(db);

        } catch (SQLException e){

        }

    }

    public void fillDatabaseWithData(JSONObject jsonOBJ) throws JSONException {

        // Create a container for the data.
        ContentValues values = new ContentValues();


        //taking of names so that amt of obj is known
        JSONArray names = jsonOBJ.names();

        for(int n=0 ; n < names.length(); n++){

            int count = count();

            int m = n +1;
            JSONObject u = jsonOBJ.getJSONObject("c" + m);

            if (mWritableDB == null) {
                mWritableDB = getWritableDatabase();
            }

            values.put(KEY_ID, count+1);

            values.put(KEY_NAME, u.getString("name"));
            values.put(KEY_DESC, u.getString("desc"));
            values.put(KEY_CDATE, u.getString("date"));
            values.put(KEY_REGDATE, u.getString("regDate"));
            values.put(KEY_PRICE, u.getString("price"));
            values.put(KEY_LOCATION, u.getString("location"));
            values.put(KEY_LINK, u.getString("link"));


            mWritableDB.insert(CAMP_LIST_TABLE, null, values);

        }

        mWritableDB.close();

    }

    public ArrayList<CampItem> query() {

        String query = "SELECT * FROM " + CAMP_LIST_TABLE;

        Cursor cursor = null;

        ArrayList <CampItem> cItem = new ArrayList<CampItem>();



            if (mReadableDB == null) {
                mReadableDB = getReadableDatabase();
            }
            cursor = mReadableDB.rawQuery(query, null);

            cursor.moveToFirst();

            CampItem entry = new CampItem();

            //A FOR OR WHILE LOOP ---> WHILE !ISAFTERTHELAST
            //THEN SET TIME BLAH BLAH
            //ADD INTO ARRAYLIST
            //MOVETONEXT

            while (!cursor.isAfterLast()) {
                entry.setcId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
                entry.setCname(cursor.getString(cursor.getColumnIndex(KEY_NAME)));
                entry.setCdesc(cursor.getString(cursor.getColumnIndex(KEY_DESC)));
                entry.setCdate(cursor.getString(cursor.getColumnIndex(KEY_CDATE)));
                entry.setCregDate(cursor.getString(cursor.getColumnIndex(KEY_REGDATE)));
                entry.setCprice(cursor.getString(cursor.getColumnIndex(KEY_PRICE)));
                entry.setCloc(cursor.getString(cursor.getColumnIndex(KEY_LOCATION)));
                entry.setClink(cursor.getString(cursor.getColumnIndex(KEY_LINK)));
                cItem.add(entry);
                cursor.moveToNext();
            }
            return cItem;

        }


    public int count(){
        if (mReadableDB == null) {
            mReadableDB = getReadableDatabase();
        }
        return (int)DatabaseUtils.queryNumEntries(mReadableDB, CAMP_LIST_TABLE);
    }
}