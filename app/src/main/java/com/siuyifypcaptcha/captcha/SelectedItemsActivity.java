package com.siuyifypcaptcha.captcha;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class SelectedItemsActivity extends AppCompatActivity {
    private TextView txt;
    private TextView txtsub;
    private Button admin_see_questions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        txt = (TextView)findViewById(R.id.textmsg);
        txtsub = (TextView)findViewById(R.id.textsubmsg);
        admin_see_questions = (Button)findViewById(R.id.button);

        //get data from intent
        getIntentData();
    }

    public void getIntentData() {
        ArrayList<String> stringArrayList = getIntent().getStringArrayListExtra("SELECTED_IMAGES");
        // to check if user select the correct images based on arrayRnd.
        List list = new ArrayList();
        list.add("m_1");
        list.add("m_5");
        list.add("m_9");

        List listMasking = new ArrayList();
        listMasking.add("m_1");
        listMasking.add("m_2");
        listMasking.add("m_5");
        listMasking.add("m_9");

        List list2 = new ArrayList();
        list2.add("n2");
        list2.add("n7");

        List listMaskingTwo = new ArrayList();
        listMaskingTwo.add("n2");
        listMaskingTwo.add("n6");
        listMaskingTwo.add("n7");

        List list3 = new ArrayList();
        list3.add("o4");
        list3.add("o6");
        list3.add("o8");

        List listMaskingThree = new ArrayList();
        listMaskingThree.add("o4");
        listMaskingThree.add("o5");
        listMaskingThree.add("o6");
        listMaskingThree.add("o8");
        

        assert stringArrayList != null;
        if (stringArrayList.size() == 3 && (stringArrayList.containsAll(list))) {
            txt.setText("Verification Successful");
            admin_see_questions.setVisibility(View.GONE);
        }

        else if (stringArrayList.size() == 4 && (stringArrayList.containsAll(listMasking))){
            txt.setText("Verification Questionable");
            txtsub.setText("It appears your mobile device is sending automated requests.");
            admin_see_questions.setVisibility(View.VISIBLE);
        }

        else if (stringArrayList.size() == 2 && (stringArrayList.containsAll(list2))){
            txt.setText("Verification Successful!");
            admin_see_questions.setVisibility(View.GONE);
        }

        else if (stringArrayList.size() == 3 && (stringArrayList.containsAll(listMaskingTwo))){
            txt.setText("Verification Questionable");
            txtsub.setText("It appears your mobile device is sending automated requests.");
            admin_see_questions.setVisibility(View.VISIBLE);
        }

        else if (stringArrayList.size() == 3 && (stringArrayList.containsAll(list3))){
            txt.setText("Verification Successful!");
            admin_see_questions.setVisibility(View.GONE);
        }

        else if (stringArrayList.size() == 4 && (stringArrayList.containsAll(listMaskingThree))){
            txt.setText("Verification Questionable");
            txtsub.setText("It appears your mobile device is sending automated requests.");
            admin_see_questions.setVisibility(View.VISIBLE);
        }

        else {
            txt.setText("Verification Unsuccessful.");
            admin_see_questions.setVisibility(View.VISIBLE);
        }
    }

    // this is to restart the activity if user answers incorrectly. 
    public void back (View v){
        Intent i = new Intent(this, MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }
}

