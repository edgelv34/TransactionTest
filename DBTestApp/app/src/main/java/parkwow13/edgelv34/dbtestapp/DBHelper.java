package parkwow13.edgelv34.dbtestapp;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by com on 2017-12-11.
 */

public class DBHelper extends SQLiteOpenHelper {

    Context context;
    //    public static final String DB_NAME = "/mnt/sdcard/message.db";
    public static final String DB_NAME = "CheckDB.db";
    public static final String TABLE_NAME = "CHECK_DB";
    public static final int DB_VERSION = 1;

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, name, factory, version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + "(idx INTEGER PRIMARY KEY AUTOINCREMENT, type INTEGER, title TEXT);");
        Logger.i("db 생성 완료 : !!!!!!!!!!!!!!!!!!!!!!!!");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Logger.d("버전 업 : " + oldVersion + "  ->  " + newVersion);

        switch (newVersion) {
            case 2:

                break;

            default:
                String sql = "drop table CHECK_DB;"; // 테이블 드랍
                db.execSQL(sql);
                onCreate(db); // 다시 테이블 생성
                break;
        }

    }


    public void insert(SQLiteDatabase db, int type, String title){
        Logger.d("");

        StringBuffer sql = new StringBuffer();
        sql.append(" INSERT INTO " + TABLE_NAME + " ( ");
        sql.append(" type, title ) ");
        sql.append(" VALUES ( ?, ? )");

        db.execSQL(sql.toString(), new Object[]{
                type,
                title
        });
    }

    public void insertTransaction(SQLiteDatabase db, int type, String title) {
        Logger.d("");

        StringBuffer sql = new StringBuffer();
        sql.append(" INSERT INTO " + TABLE_NAME + " ( ");
        sql.append(" type, title ) ");
        sql.append(" VALUES ( ?, ? )");

        db.execSQL(sql.toString(), new Object[]{
                type,
                title
        });
    }

    public void updateTitle(SQLiteDatabase db, int idx, String title){
        Logger.d("");
        String sql = "UPDATE " + TABLE_NAME + " SET title = '"+ title + "' WHERE idx='" + idx + "';";
        db.execSQL(sql);
    }

    public void updateTitleTransaction(SQLiteDatabase db, int idx, String title){
        Logger.d("");
        String sql = "UPDATE " + TABLE_NAME + " SET title = '"+ title + "' WHERE idx='" + idx + "';";
        db.execSQL(sql);
    }

    public void delete(SQLiteDatabase db, int idx){
        Logger.d("");
        String sql = "DELETE FROM " + TABLE_NAME + " WHERE idx='" + idx + "';";
        db.execSQL(sql);
    }

    public void deleteTransaction(SQLiteDatabase db, int idx){
        Logger.d("");
        String sql = "DELETE FROM " + TABLE_NAME + " WHERE idx='" + idx + "';";
        db.execSQL(sql);
    }



}
