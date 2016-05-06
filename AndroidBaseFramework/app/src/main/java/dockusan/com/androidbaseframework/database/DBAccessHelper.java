/*
 * Copyright (C) 2014 Saravan Pantham
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package dockusan.com.androidbaseframework.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * SQLite database implementation. Note that this class
 * only contains methods that access Jams' private
 * database. For methods that access Android's
 * MediaStore database, see MediaStoreAccessHelper.
 *
 * @author Saravan Pantham
 */
public class DBAccessHelper extends SQLiteOpenHelper {

    //Database instance. Will last for the lifetime of the application.
    private static DBAccessHelper sInstance;

    //Writable database instance.
    private SQLiteDatabase mDatabase;
    //Databse path url
    public static String DB_PATH = "";
    private static final String DB_NAME = "AndroidBaseFramework.sqlite";
    //Database Version.
    private static final int DATABASE_VERSION = 1;

    //Database Name.
    private static final String DATABASE_NAME = "AndroidBaseFramework.db";

    //Common fields.
    public static final String _ID = "_id";

    //Music folders table.
    public static final String MUSIC_FOLDERS_TABLE = "MusicFoldersTable";
    public static final String FOLDER_PATH = "folder_path";
    public static final String INCLUDE = "include";
    private final Context mContext;


    public DBAccessHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.mContext = context;
        DB_PATH = "/data/data/" + mContext.getPackageName() + "/databases/" + DB_NAME;
    }

    /**
     * Returns a singleton instance for the database.
     *
     * @param context
     * @return
     */
    public static synchronized DBAccessHelper getInstance(Context context) {
        if (sInstance == null)
            sInstance = new DBAccessHelper(context.getApplicationContext());

        return sInstance;
    }

    /**
     * copy database from assets to the device if not existed
     *
     * @return true if not exist and create database success
     * @throws IOException
     */
    public boolean isCreatedDatabase() throws IOException {
        // Default lÃ  Ä‘Ã£ cÃ³ DB
        boolean result = true;
        // Náº¿u chÆ°a tá»“n táº¡i DB thÃ¬ copy tá»« Asses vÃ o Data
        if (!checkExistDataBase()) {
            this.getReadableDatabase();
            try {
                copyDataBase();
                result = false;
            } catch (Exception e) {
                throw new Error("Error copying database");
            }
        }
        return result;
    }

    /**
     * check whether database exist on the device?
     *
     * @return true if existed
     */

    private boolean checkExistDataBase() {

        try {
            String myPath = DB_PATH;
            File fileDB = new File(myPath);

            if (fileDB.exists()) {
                return true;
            } else
                return false;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * copy database from assets folder to the device
     *
     * @throws IOException
     */
    private void copyDataBase() throws IOException {
        InputStream myInput = mContext.getAssets().open(DB_NAME);
        OutputStream myOutput = new FileOutputStream(DB_PATH);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }

        myOutput.flush();
        myOutput.close();
        myInput.close();
    }

    /**
     * Returns a writable instance of the database. Provides an additional
     * null check for additional stability.
     */
    private synchronized SQLiteDatabase getDatabase() {
        if (mDatabase == null)
            mDatabase = getWritableDatabase();

        return mDatabase;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //Music folders table.
        String[] musicFoldersTableCols = {FOLDER_PATH, INCLUDE};
        String[] musicFoldersTableColTypes = {"TEXT", "TEXT"};
        String createMusicFoldersTable = buildCreateStatement(MUSIC_FOLDERS_TABLE,
                musicFoldersTableCols,
                musicFoldersTableColTypes);

        //Execute the CREATE statements.
        db.execSQL(createMusicFoldersTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub

    }

    @Override
    protected void finalize() {
        try {
            getDatabase().close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Constructs a fully formed CREATE statement using the input
     * parameters.
     */
    private String buildCreateStatement(String tableName, String[] columnNames, String[] columnTypes) {
        String createStatement = "";
        if (columnNames.length == columnTypes.length) {
            createStatement += "CREATE TABLE IF NOT EXISTS " + tableName + "("
                    + _ID + " INTEGER PRIMARY KEY, ";

            for (int i = 0; i < columnNames.length; i++) {

                if (i == columnNames.length - 1) {
                    createStatement += columnNames[i]
                            + " "
                            + columnTypes[i]
                            + ")";
                } else {
                    createStatement += columnNames[i]
                            + " "
                            + columnTypes[i]
                            + ", ";
                }

            }

        }

        return createStatement;
    }


}
