package sg.edu.rp.c346.id18004536.demomyprofile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etName, etGPA;

    RadioGroup rgGender;

    Button save;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.EditTextName);
        etGPA = findViewById(R.id.EditTextGPA);
        rgGender = findViewById(R.id.radioGroupGender);
        save = findViewById(R.id.buttonSave);


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strname = etName.getText().toString();
                float gpa = Float.parseFloat(etGPA.getText().toString());
                int gender = rgGender.getCheckedRadioButtonId();

                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);

                SharedPreferences.Editor prefEdit = prefs.edit();

                prefEdit.putString("Name", strname);
                prefEdit.putFloat("GPA", gpa);
                prefEdit.putInt("Gender", gender);

                prefEdit.commit();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();

        String strname = etName.getText().toString();
        float gpa = Float.parseFloat(etGPA.getText().toString());
        int gender = rgGender.getCheckedRadioButtonId();

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);

        SharedPreferences.Editor prefEdit = prefs.edit();

        prefEdit.putString("Name", strname);
        prefEdit.putFloat("GPA", gpa);
        prefEdit.putInt("Gender", gender);

        prefEdit.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        String shmsg = prefs.getString("Name", "No Value!");
        float shgpa = prefs.getFloat("GPA", 0);
        int shgender = prefs.getInt("Gender", R.id.radioButtonGenderMale);

        etName.setText(shmsg);
        etGPA.setText(shgpa+"");
        rgGender.check(shgender);
    }
}

