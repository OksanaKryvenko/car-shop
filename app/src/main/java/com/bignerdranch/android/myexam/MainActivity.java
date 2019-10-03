package com.bignerdranch.android.myexam;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
    private static final String DB_NAME = "MyDataBase";
    public static final String TABLE_NAME = "MyTable";

    public static final String KEY_MODEL = "model";
    public static final String KEY_VOLUME = "volume";
    public static final String KEY_YEAR = "year";
    public static final String KEY_SALON = "salon";
    public static final String KEY_CONDITIONER = "conditioner";
    public static final String KEY_ABS = "abs";

    public static SQLiteDatabase mDataBase;
    private DBHelper mDBHelper;

//    private SearchFragment mSearchFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("log_tag", "MainActivity onCreate");
        setContentView(R.layout.activity_main);

//        if (mDataBase == null) {
        mDBHelper = new DBHelper(this);
        mDataBase = mDBHelper.getWritableDatabase();
        //       } else {
        //           clearDatabase();
//        }

        SearchFragment searchFragment = (SearchFragment) SearchFragment.newInstance();

        FragmentManager manager = getSupportFragmentManager();
        // запуск транзакции
        manager.beginTransaction()
                .add(R.id.fl_container, searchFragment)
                .commit();
    }

    public void clearDatabase() {
        int deleteCount = mDataBase.delete(TABLE_NAME, null, null);
        Log.d("logTag", "raws deleted: " + deleteCount);
    }

    class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context) {
            // контекст, имя базы данных, особый класс, версия
            super(context, "MyDataBase", null, 1);
        }

        @Override
        // вызывается только 1 раз в момент создания базы данных
        public void onCreate(SQLiteDatabase db) {
            Log.d("logTag", "database created");

            db.execSQL("CREATE TABLE " + TABLE_NAME + " ("            //строка на языке SQL (команда), имя, перечисление колонок таблицы
                    + "id_ INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "model TEXT, "
                    + "year INTEGER, "
                    + "volume REAL, "
                    + "salon INTEGER, "
                    + "conditioner INTEGER, "
                    + "abs INTEGER)");

            createDatabase(db);
        }

        public void createDatabase(SQLiteDatabase db) {
            ContentValues cv = new ContentValues();
            cv.put(KEY_MODEL, "Kia");
            cv.put(KEY_YEAR, 2016);
            cv.put(KEY_VOLUME, 2.0);
            cv.put(KEY_SALON, 1);
            cv.put(KEY_CONDITIONER, 1);
            cv.put(KEY_ABS, 1);
            long rowId = db.insert(TABLE_NAME, null, cv);
            Log.d("logTag", "row inserted: " + rowId);
            cv.put(KEY_MODEL, "Skoda");
            cv.put(KEY_YEAR, 2019);
            cv.put(KEY_VOLUME, 2.0);
            cv.put(KEY_SALON, 1);
            cv.put(KEY_CONDITIONER, 1);
            cv.put(KEY_ABS, 1);
            rowId = db.insert(TABLE_NAME, null, cv);
            Log.d("logTag", "row inserted: " + rowId);
            cv.put(KEY_MODEL, "Hyundai");
            cv.put(KEY_YEAR, 2015);
            cv.put(KEY_VOLUME, 2.0);
            cv.put(KEY_SALON, 1);
            cv.put(KEY_CONDITIONER, 1);
            cv.put(KEY_ABS, 1);
            rowId = db.insert(TABLE_NAME, null, cv);
            Log.d("logTag", "row inserted: " + rowId);
            cv.put(KEY_MODEL, "BMW");
            cv.put(KEY_YEAR, 2016);
            cv.put(KEY_VOLUME, 2.0);
            cv.put(KEY_SALON, 1);
            cv.put(KEY_CONDITIONER, 1);
            cv.put(KEY_ABS, 1);
            rowId = db.insert(TABLE_NAME, null, cv);
            Log.d("logTag", "row inserted: " + rowId);
            cv.put(KEY_MODEL, "Skoda");
            cv.put(KEY_YEAR, 2012);
            cv.put(KEY_VOLUME, 1.8);
            cv.put(KEY_SALON, 0);
            cv.put(KEY_CONDITIONER, 0);
            cv.put(KEY_ABS, 0);
            rowId = db.insert(TABLE_NAME, null, cv);
            Log.d("logTag", "row inserted: " + rowId);
            cv.put(KEY_MODEL, "BMW");
            cv.put(KEY_YEAR, 2016);
            cv.put(KEY_VOLUME, 2.0);
            cv.put(KEY_SALON, 1);
            cv.put(KEY_CONDITIONER, 1);
            cv.put(KEY_ABS, 0);
            rowId = db.insert(TABLE_NAME, null, cv);
            Log.d("logTag", "row inserted: " + rowId);
            cv.put(KEY_MODEL, "Skoda");
            cv.put(KEY_YEAR, 2016);
            cv.put(KEY_VOLUME, 1.5);
            cv.put(KEY_SALON, 0);
            cv.put(KEY_CONDITIONER, 0);
            cv.put(KEY_ABS, 1);
            rowId = db.insert(TABLE_NAME, null, cv);
            Log.d("logTag", "row inserted: " + rowId);
            cv.put(KEY_MODEL, "BMW");
            cv.put(KEY_YEAR, 2004);
            cv.put(KEY_VOLUME, 2.0);
            cv.put(KEY_SALON, 0);
            cv.put(KEY_CONDITIONER, 0);
            cv.put(KEY_ABS, 0);
            rowId = db.insert(TABLE_NAME, null, cv);
            Log.d("logTag", "row inserted: " + rowId);
            cv.put(KEY_MODEL, "Kia");
            cv.put(KEY_YEAR, 2014);
            cv.put(KEY_VOLUME, 2.4);
            cv.put(KEY_SALON, 0);
            cv.put(KEY_CONDITIONER, 1);
            cv.put(KEY_ABS, 0);
            rowId = db.insert(TABLE_NAME, null, cv);
            Log.d("logTag", "row inserted: " + rowId);
            cv.put(KEY_MODEL, "BMW");
            cv.put(KEY_YEAR, 1991);
            cv.put(KEY_VOLUME, 3.0);
            cv.put(KEY_SALON, 0);
            cv.put(KEY_CONDITIONER, 0);
            cv.put(KEY_ABS, 1);
            rowId = db.insert(TABLE_NAME, null, cv);
            Log.d("logTag", "row inserted: " + rowId);
        }

        @Override
        // редактирование базы данных (изменение версии), содержит последовательные изменения (миграция)
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            switch (newVersion) {
                case 2:
                    break;
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
//        FragmentManager manager = getSupportFragmentManager();
//        SavedRecyclerFragment savedRecyclerFragment;
        if (id == R.id.action_saved_cars) {
            FragmentManager manager = getSupportFragmentManager();

            SavedRecyclerFragment recyclerFragment;
            recyclerFragment = (SavedRecyclerFragment) SavedRecyclerFragment.newInstance();
            manager.beginTransaction()
                    .addToBackStack(null)
                    .replace(R.id.fl_container, recyclerFragment)
                    .commit();
        }
        return true;
    }
}
