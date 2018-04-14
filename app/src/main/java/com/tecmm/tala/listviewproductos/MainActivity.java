package com.tecmm.tala.listviewproductos;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.lang.reflect.Array;

public class MainActivity extends AppCompatActivity {
    private TextView txt;
    private ListView list;
    private FloatingActionButton Btnadd;
    private String productos[] = {"Computadora", "Mouse", "Dulces", "Hojas",
            "Lapices", "Lentes","Reloj", "Cuchara", "Celular", "Mesa", "Refrigerador",
            "Horno", "Audifonos"};
    private String categoria[] =
            {"Electronica","Electronica","Dulceria","Papeleria","Papeleria","Moda","Perfumeria",
                    "Hogar", "Electronicos", "Hogar", "Electrodomesticos", "Electrodomesticos",
                    "Electronica"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt = (TextView) findViewById(R.id.txt);
        list = (ListView) findViewById(R.id.list);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,productos);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                txt.setText("Categoria elegido: " + categoria[i]);
            }
        });
        Btnadd = (FloatingActionButton) findViewById(R.id.Btnadd);
        Btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenAddproduct();
            }
        });
    }
    public void OpenAddproduct(){
        Intent intent = new Intent(this, Addproduct.class);
        startActivity(intent);

    }
}
