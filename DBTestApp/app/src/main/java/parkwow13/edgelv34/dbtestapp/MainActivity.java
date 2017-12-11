package parkwow13.edgelv34.dbtestapp;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBHelper dbHelper = new DBHelper(this, DBHelper.DB_NAME, null, DBHelper.DB_VERSION);

        Long beforeTime = System.currentTimeMillis();

        SQLiteDatabase dbinsert = dbHelper.getWritableDatabase();

        for (int i = 0 ; i < 10000 ; i ++) {
            String dbInsertTitle = "TEST" + i;
            dbHelper.insert(dbinsert, i, dbInsertTitle);
        }

        dbinsert.close();
        Long afterTime = System.currentTimeMillis();

        Logger.d("INSERT beforeTime : " + beforeTime + " afterTime : " + afterTime);



        Long beforeTime1 = System.currentTimeMillis();

        SQLiteDatabase dbupdate = dbHelper.getWritableDatabase();

        for (int i = 0 ; i < 10000 ; i ++) {
            String dbInsertTitle = "TESTED" + i;
            dbHelper.updateTitle(dbupdate, i, dbInsertTitle);
        }

        dbupdate.close();

        Long afterTime1 = System.currentTimeMillis();

        Logger.d("UPDATE beforeTime : " + beforeTime1 + " afterTime : " + afterTime1);


        Long beforeTime2 = System.currentTimeMillis();

        SQLiteDatabase dbdelete = dbHelper.getWritableDatabase();

        for (int i = 0 ; i < 10000 ; i ++) {
            dbHelper.delete(dbdelete, i);
        }

        dbdelete.close();

        Long afterTime2 = System.currentTimeMillis();

        Logger.d("DELETE beforeTime : " + beforeTime2 + " afterTime : " + afterTime2);


        Long beforeTime3 = System.currentTimeMillis();

        SQLiteDatabase dbInsert = dbHelper.getWritableDatabase();

        try {
            dbInsert.beginTransaction();

            for (int i = 0 ; i < 10000 ; i ++) {
                String dbInsertTitle = "TEST" + i;
                dbHelper.insertTransaction(dbInsert, i, dbInsertTitle);
            }

            dbInsert.setTransactionSuccessful();
        } catch (IllegalStateException ise) {
            ise.printStackTrace();
        } finally {
            dbInsert.endTransaction();
            dbInsert.close();
        }

        Long afterTime3 = System.currentTimeMillis();

        Logger.d("INSERT T beforeTime : " + beforeTime3 + " afterTime : " + afterTime3);



        Long beforeTime4 = System.currentTimeMillis();

        SQLiteDatabase dbUpdate = dbHelper.getWritableDatabase();

        try {
            dbUpdate.beginTransaction();

            for (int i = 0 ; i < 10000 ; i ++) {
                String dbInsertTitle = "TESTED" + i;
                dbHelper.updateTitleTransaction(dbUpdate, i, dbInsertTitle);
            }

            dbUpdate.setTransactionSuccessful();
        } catch (IllegalStateException ise) {
            ise.printStackTrace();
        } finally {
            dbUpdate.endTransaction();
            dbUpdate.close();
        }

        Long afterTime4 = System.currentTimeMillis();

        Logger.d("UPDATE T beforeTime : " + beforeTime4 + " afterTime : " + afterTime4);



        Long beforeTime5 = System.currentTimeMillis();

        SQLiteDatabase dbDelete = dbHelper.getWritableDatabase();

        try {
            dbDelete.beginTransaction();

            for (int i = 0 ; i < 10000 ; i ++) {
                dbHelper.deleteTransaction(dbDelete, i);
            }

            dbDelete.setTransactionSuccessful();
        } catch (IllegalStateException ise) {
            ise.printStackTrace();
        } finally {
            dbDelete.endTransaction();
            dbDelete.close();
        }

        Long afterTime5 = System.currentTimeMillis();

        Logger.d("DELETE T beforeTime : " + beforeTime5 + " afterTime : " + afterTime5);

    }
}
