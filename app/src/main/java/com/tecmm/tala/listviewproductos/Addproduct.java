package com.tecmm.tala.listviewproductos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class Addproduct extends AppCompatActivity {

    private Spinner SpinnerProduct;
    private TextView Txtname;
    private Button Btnsave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addproduct);
        SpinnerProduct =(Spinner) findViewById(R.id.SpinnerProducts);
        Txtname = (TextView) findViewById(R.id.Txtname);
        String Options[] = {"Bebe","Auto","Electronicos","Peliculas","Ropa","Zapatos","Deportes","Hogar","Industria","Juegos","Libros","Mascotas","Musica"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, Options);
        SpinnerProduct.setAdapter(adapter );
        Btnsave = (Button) findViewById(R.id.Btnsave);
        Btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Add();
            }
        });
    }
    public void Add(){
        Intent i = new Intent();
        i.putExtra("Name",Txtname.getText().toString());
        i.putExtra("Category",SpinnerProduct.getSelectedItem().toString());
        setResult(RESULT_OK,i);
        finish();
    }
}
