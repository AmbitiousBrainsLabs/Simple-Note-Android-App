package ke.co.ablabs.simplenote;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "com_example_simple_note.db";
    private static final String TAG = "MyActivity";

    String selectedItem;
    MyNotesDatabaseManager myNotesDatabaseManager;
    ArrayAdapter<String> listViewArrayAdapter;
    ListView listViewAllNotes;
    EditText enterNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewAllNotes = findViewById(R.id.mainListView);
        enterNote = findViewById(R.id.editTextTextPersonName);

        myNotesDatabaseManager = new MyNotesDatabaseManager(this, DATABASE_NAME, null, DATABASE_VERSION);

        refreshListView();

        //......................ALERT DIALOG..................................
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Confirm");
        alertDialog.setMessage("Delete Note?");
        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                myNotesDatabaseManager.deleteNote(selectedItem);
                Toast.makeText(getApplicationContext(), "Note Deleted", Toast.LENGTH_SHORT).show();
                refreshListView();
            }
        });
        alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        //......................ALERT DIALOG..................................

        //......................LIST VIEW..................................
        listViewAllNotes.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                selectedItem = listViewAllNotes.getItemAtPosition(position).toString();
                alertDialog.show();
                return true;
            }
        });
        //......................LIST VIEW..................................

    }

    private void refreshListView() {
        listViewArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, myNotesDatabaseManager.viewAllNotes());
        listViewAllNotes.setAdapter(listViewArrayAdapter);
        enterNote.setText("");
    }

    public void addNote(View view) {
        MyNotesDatabaseModel myNotesDatabaseModel = new MyNotesDatabaseModel(enterNote.getText().toString());
        myNotesDatabaseManager.addNote(myNotesDatabaseModel);
        Toast.makeText(getApplicationContext(), "Note Added", Toast.LENGTH_SHORT).show();
        refreshListView();

    }
}