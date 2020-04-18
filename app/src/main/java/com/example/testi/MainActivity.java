package com.example.testi;

import android.content.*;
import android.graphics.Color;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
    MyService servMe;
    boolean isBound = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //MyIntentService.startActionBaz(this, "param1", "param2");

        Intent i = new Intent(this, MyService.class);
        bindService(i, conn, Context.BIND_AUTO_CREATE);
    }

    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MyService.MyBinder bindMe = (MyService.MyBinder) service;
            servMe = bindMe.getService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;
        }
    };

    public void show(View view) {
        Toast.makeText(this, ((Integer) servMe.compute()).toString(), Toast.LENGTH_SHORT).show();
    }

    public void myonClick2(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Ganze Menschheit auslöschen?");
        String[] elements = {"Gelb", "Grün"};
        builder.setMultiChoiceItems(elements, null, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                switch (which) {
                    case 0:
                        if(isChecked){
                            findViewById(R.id.rel).setBackgroundColor(Color.YELLOW);
                        }
                        break;
                    case 1:
                        if(isChecked){
                            findViewById(R.id.rel).setBackgroundColor(Color.GREEN);
                        } else {
                            findViewById(R.id.rel).setBackgroundColor(Color.RED);
                        }
                        break;
                    default:
                        break;
                }
            }
        });

        builder.setPositiveButton("Ja, alles töten!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "Kill everybody!", Toast.LENGTH_LONG).show();
            }
        });
        builder.setNegativeButton("Nein, ich will leben!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "Du hast überlebt!", Toast.LENGTH_SHORT).show();
            }
        });
        builder.create().show();
    }

    public void myonClick3(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Hintergrundfarbe wählen");
        String[] elements = {"Gelb", "Grün"};
        builder.setSingleChoiceItems(elements, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        findViewById(R.id.rel).setBackgroundColor(Color.YELLOW);
                        break;
                    case 1:
                        findViewById(R.id.rel).setBackgroundColor(Color.GREEN);
                        break;
                    default:
                        break;
                }
            }
        });
        builder.create().show();
    }

    public void myonClick(View view) {
        Log.d("myonClick", "created Intent");
        Intent i = new Intent(this, Activity2.class);

        final EditText editIt = (EditText) findViewById(R.id.editText);
        String msg = editIt.getText().toString();
        i.putExtra("msg", msg);
        startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true; //
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Toast.makeText(this, "Settings2", Toast.LENGTH_LONG).show();
            return true;
        }

        if (id == R.id.action_refresh) {
            Toast.makeText(this, "Refresh", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
