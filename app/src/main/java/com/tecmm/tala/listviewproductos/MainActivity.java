package com.tecmm.tala.listviewproductos;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TextView txt;
    private ListView list;
    private FloatingActionButton Btnadd;
    private List<String> lProducts = new ArrayList<>();
    private List<String> lCategories = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt = (TextView) findViewById(R.id.txt);
        list = (ListView) findViewById(R.id.list);
        registerForContextMenu(list);
       // list.setOnItemClickListener(list);
        Btnadd = (FloatingActionButton) findViewById(R.id.Btnadd);
        Btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenAddproduct();
            }
        });
        UpdateTable();
    }
    private void UpdateTable(){
        String products[] = new String[lProducts.size()];
        lProducts.toArray(products);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,products);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                txt.setText("Categoria elegida: " + lCategories.get(position));
            }
        });
    }
    public  void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode,data);
        lProducts.add(data.getStringExtra("Name"));
        lCategories.add(data.getStringExtra("Category"));
        UpdateTable();
        System.out.println("On Activity Result!!!");
    }
    public void OpenAddproduct(){
        Intent intent = new Intent(this, Addproduct.class);
        startActivityForResult(intent,123);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_layout, menu);
        return true;
    }
    public void ItemSelected(MenuItem item){
        switch (item.getItemId()){
            case  R.id.M_exit:
                finish();
                System.exit(0);
        }
    }
    /*--Contextmenu--*/
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu,v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.popup_menu, menu);

    }
    public boolean onContextItemSelected(MenuItem item){
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item
                .getMenuInfo();
        switch (item.getItemId()){
            case R.id.item_Update:
                System.out.println("U");
                return  true;
            case R.id.item_Delete:
                lProducts.remove(info.position);
                UpdateTable();
                return  true;
            default:
                return super.onContextItemSelected(item);
        }
    }
}






