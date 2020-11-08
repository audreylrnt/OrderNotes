package com.example.ordernotes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    RecyclerView rv;
    AdapterRV adapterRV;
    TextView justNotes;
    DBHelper dbHelper;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv = findViewById(R.id.recyclerView);
        dbHelper = new DBHelper(this);
        justNotes = findViewById(R.id.justNotes);
        fab = findViewById(R.id.fabAddNotes);
        NotesDataDB.notesData.clear();
        getData();

        if(NotesDataDB.notesData.size() > 0){
            justNotes.setText("");
        }
        rv.setLayoutManager(new LinearLayoutManager(this));
        adapterRV = new AdapterRV(this);
        rv.setAdapter(adapterRV);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toAddNotes = new Intent(MainActivity.this, AddNotes.class);
                startActivity(toAddNotes);
            }
        });
    }
    private void getData() {
        Cursor c = dbHelper.getAllData();
        while (c.moveToNext()){
            String tglOrder = c.getString(c.getColumnIndex(DBHelper.notesTglTrx));
            String invoiceNo = c.getString(c.getColumnIndex(DBHelper.notesInvoice));
            String platform = c.getString(c.getColumnIndex(DBHelper.notesPlatform));
            String namaPembeli = c.getString(c.getColumnIndex(DBHelper.notesNamaPembeli));
            String namaBarang = c.getString(c.getColumnIndex(DBHelper.notesBarang));
            String status = c.getString(c.getColumnIndex(DBHelper.notesStatus));
            NotesViewedAtRV notesViewedAtRV = new NotesViewedAtRV(invoiceNo, namaPembeli, namaBarang, platform, tglOrder, status);
            NotesDataDB.notesData.add(notesViewedAtRV);
        }
    }
}
