package com.tecmm.tala.listviewproductos;

import android.content.Intent;
import android.os.Parcel;
import android.renderscript.Sampler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import static java.lang.System.*;

public class Addproduct extends AppCompatActivity {

    private Spinner SpinnerCategories;
    private TextView Txtname;
    private Button Btnsave;
    private int pos = 0, Result =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addproduct);
        SpinnerCategories =(Spinner) findViewById(R.id.SpinnerProducts);
        Txtname = (TextView) findViewById(R.id.Txtname);
        String Options[] = {"Bebe","Auto","Electronicos","Peliculas","Ropa","Zapatos","Deportes","Hogar","Industria","Juegos","Libros","Mascotas","Musica"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, Options);
        SpinnerCategories.setAdapter(adapter );
        Intent intent = getIntent();
        if ( intent.getStringExtra("Name")!= null){
          //  Btnsave.add
            Txtname.setText(intent.getStringExtra("Name"));
            out.println("ITEM"+intent.getStringExtra("Category"));
            out.println("NAme"+intent.getStringExtra("Name"));
            SpinnerCategories.setSelection( adapter.getPosition(intent.getStringExtra("Category")));
            pos = intent.getIntExtra("cPosition",0);
            Result = 1;
        }
        Btnsave = (Button) findViewById(R.id.Btnsave);
        Btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Add();
                ShowTSuccess();
            }
        });
    }

    public void Add(){
        Intent i = new Intent();
        i.putExtra("Name",Txtname.getText().toString());
        i.putExtra("Category",SpinnerCategories.getSelectedItem().toString());
        i.putExtra("cPosition",pos);
        setResult(RESULT_OK,i);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode,data);
        Txtname.setText(data.getStringExtra("Name"));
     //   System.out.println("Result add");
    }

    public void ShowTSuccess(){
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.toast_success_layout, (ViewGroup) findViewById(R.id.toast_root));
        TextView text = (TextView) view.findViewById(R.id.toast_text);
        text.setText("Producto "+Txtname.getText().toString()+" agregado correctamente");
        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL, 0,0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(view);
        toast.show();
    }

}
