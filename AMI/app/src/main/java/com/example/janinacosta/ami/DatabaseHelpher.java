package com.example.janinacosta.ami;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Janina Costa on 3/1/2017.
 */

public class DatabaseHelpher extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="mediAlarma";
    private static final int DATABASE_VERSION = 2;
    private static final String MEDICAMENTOS_TABLE = "medicamentos";
    private static final String MED_TABLE = "create table "+MEDICAMENTOS_TABLE +
            "(name TEXT primary key, num_dias INT, dosis TEXT, indicaciones TEXT)";
    Context context;

    public DatabaseHelpher(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(MED_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + MEDICAMENTOS_TABLE);
        onCreate(db);
    }

    /***METODO PARA INSERTAR EN LA BDD*/
    public void insertIntoDB(String nombre, int num_dias , int dosis, String indicaciones){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", nombre);
        values.put("num_dias", num_dias);
        values.put("dosis", dosis);
        values.put("indicaciones", indicaciones);

        db.insert(MEDICAMENTOS_TABLE, null, values);
        db.close();
        Toast.makeText(context, "Medicament Creado", Toast.LENGTH_LONG);

    }
    /* Obtener todos los medicamentos de la base para el recycler */

    public List<MedicamentoModel> getDataFromDB(){
        List<MedicamentoModel> modelList = new ArrayList<MedicamentoModel>();
        String query = "select * from "+MEDICAMENTOS_TABLE;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        if (cursor.moveToFirst()){
            do {
                MedicamentoModel model = new MedicamentoModel();
                model.setName(cursor.getString(0));
                model.setNum_dias(cursor.getInt(1));
                model.setDosis(cursor.getInt(2));
                model.setIndicaciones(cursor.getString(3));

                modelList.add(model);
            }while (cursor.moveToNext());
        }

        return modelList;
    }



    /*eliminar una fila */

    /*public void deleteARow(String nombreMedicamento){
        SQLiteDatabase db= this.getWritableDatabase();
        db.delete(MEDICAMENTOS_TABLE, "name" + " = ?", new String[] { nombreMedicamento });
        db.close();
    }*/

}
