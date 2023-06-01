package sg.edu.rp.c346.id22000765.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etElement;
    Button btnAdd;
    Button btnDel;
    Button btnClear;
    Spinner spnAddRemove;
    ListView lvTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<String> alTasks = new ArrayList<String>();
        etElement=findViewById(R.id.editTextTask);
        btnAdd=findViewById(R.id.buttonAdd);
        btnDel=findViewById(R.id.buttonDel);
        btnClear=findViewById(R.id.buttonClear);
        spnAddRemove=findViewById(R.id.spinner);
        lvTask=findViewById(R.id.listViewTask);

        ArrayAdapter adapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,alTasks);

        lvTask.setAdapter(adapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Get the colour that user entered in the EditText
                String color = etElement.getText().toString();
                //Modify the data source – specifically, add the colour to the ArrayList
                alTasks.add(color);
                //Call notifyDataSetChanged() of the adapter
                adapter.notifyDataSetChanged();
                etElement.setText("");

            }
        });
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alTasks.clear();
                adapter.notifyDataSetChanged();
            }
        });
        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String position=etElement.getText().toString();
                if(!position.isEmpty()){
                    int index=Integer.parseInt(position);
                    if(index>=0 && index< alTasks.size()){
                        alTasks.remove(index);
                        adapter.notifyDataSetChanged();
                        etElement.setText("");
                    }if(alTasks.isEmpty()){
                        Toast.makeText(MainActivity.this,"You don't have any task to remove",Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(MainActivity.this,"Wrong index number",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        spnAddRemove.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();
                if (selectedItem.equals("Add a new task")) {
                    etElement.setHint("Type in a new task here");
                    btnDel.setEnabled(false);
                    btnAdd.setEnabled(true);
                } else if (selectedItem.equals("Remove a task")) {
                    etElement.setHint("Type in the index of the task to be removed");
                    btnDel.setEnabled(true);
                    btnAdd.setEnabled(false);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
}