package com.siuyifypcaptcha.captcha;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private GridView gridView;
    private View btnGo;
    private ArrayList<String> selectedStrings;
    private static String[] arrayRnd;
    private TextView textrand;
    private String LOG_TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = (GridView) findViewById(R.id.grid);
        btnGo = findViewById(R.id.button);
        textrand = findViewById(R.id.textView1);

        selectedStrings = new ArrayList<>();

        //to check which random generated array was returned. 
        List valid = Arrays.asList("m_1","m_2","m_3","m_4","m_5","m_6","m_7","m_8","m_9");
        List valid1 = Arrays.asList("n1", "n2", "n3", "n4", "n5", "n6", "n7", "n8", "n9");

        //ArrayList to store an array of images.
        ArrayList<String[]> randGen = new ArrayList<String[]>();
        randGen.add(new String[]{"m_1","m_2","m_3","m_4","m_5","m_6","m_7","m_8","m_9"});
        randGen.add(new String[]{"n1", "n2", "n3", "n4", "n5", "n6", "n7", "n8", "n9"});
        randGen.add(new String[]{"o1","o2","o3","o4","o5","o6","o7","o8","o9"});

        // random generator for array of images from ArrayList.
        Random rnd = new Random();
        arrayRnd = randGen.get(rnd.nextInt(randGen.size()));

        // checking which array is generated from arrayRnd in order to print the approriate text.
        if(Arrays.asList(arrayRnd).containsAll(valid)){
            textrand.setText("Select all images with both swan(s) and flamingo(s) as well as parrot(s) in a single image.");
        }

        else if (Arrays.asList(arrayRnd).containsAll(valid1)){
            textrand.setText("Select all images with both apple(s) and orange(s) in a single image.");
        }

        else{
            textrand.setText("Select all images with avocado(s), pineapple(s) and pumpkin(s) in a single image.");
        }

        // start of gridview adapter
        final GridViewAdapter adapter = new GridViewAdapter(arrayRnd, this);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                // to detect click events of the grids and changable background colours when selected and unselected by the user.
                // as well as storing and removing them from an ArrayList called selectedStrings during selection and deselection.
                @Override
                public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                    int selectedIndex = adapter.selectedPositions.indexOf(position);
                    if (selectedIndex > -1) {
                        adapter.selectedPositions.remove(selectedIndex);
                        ((GridItemView) v).display(false);
                        selectedStrings.remove((String) parent.getItemAtPosition(position));

                    }

                    else {
                        adapter.selectedPositions.add(position);
                        ((GridItemView) v).display(true);
                        selectedStrings.add((String) parent.getItemAtPosition(position));
                    }
                }
        });

        //set listener for Button event for verification
        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // to check if user submits an empty challenge before verifying the user is a bot.
                if (selectedStrings.isEmpty()) {
                    Toast position = Toast.makeText(MainActivity.this, "Please complete the challenge.", Toast.LENGTH_SHORT);
                    position.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL,0, 250);
                    position.show();
                }

                else{
                    Intent intent = new Intent(MainActivity.this, SelectedItemsActivity.class);
                    intent.putStringArrayListExtra("SELECTED_IMAGES", selectedStrings);
                    startActivity(intent);
                }
            }
        });
    }
}