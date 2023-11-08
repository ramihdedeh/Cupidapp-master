package org.costums.lebanesecustomss;
import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Calendar;


public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {


    boolean enter = false;
    boolean ex = false;

    //booleans used for the submit button to not crash

    boolean sub=false;

    //entrance widgets
    Button entrance;
    TextView tv_Entrance;

    //exit widgets
    Button exit;
    TextView tv_Exit;

    EditText input_mass;
    Button submitButton;

    //widgets that show the results
    TextView tv_result;
    TextView tv_result1;
    TextView tv_result2;

    // variables used in calculations
    int day, month, year, day1, month1, year1 ,dayFinal, monthFinal, yearFinal;
    double df, mf, yf, mDays, weeks, n1Days, n2Days, days;
    double mass=-1;

    double VAT, storage, total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        entrance = (Button) findViewById(R.id.entrance);
        tv_Entrance = (TextView) findViewById(R.id.tv_Entrance);

        entrance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enter = true;
                Calendar c = Calendar.getInstance();
                year = c.get(Calendar.YEAR);
                month = c.get(Calendar.MONTH);
                day = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, MainActivity.this,
                        year, month, day);
                datePickerDialog.show();
            }
        });


        exit = (Button) findViewById(R.id.exit);
        tv_Exit = (TextView) findViewById(R.id.tv_Exit);

        exit.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                ex = true;
                Calendar c1 = Calendar.getInstance();
                year1 = c1.get(Calendar.YEAR);
                month1 = c1.get(Calendar.MONTH);
                day1 = c1.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog1 = new DatePickerDialog(MainActivity.this, MainActivity.this,
                        year1, month1, day1);
                datePickerDialog1.show();
            }
        });
        input_mass = (EditText) findViewById(R.id.input_mass);
        input_mass.setInputType(InputType.TYPE_CLASS_NUMBER);
        submitButton = (Button) findViewById(R.id.submitButton);
        tv_result=(TextView)findViewById(R.id.result);
        tv_result1=(TextView)findViewById(R.id.result1);
        tv_result2=(TextView)findViewById(R.id.result2);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //condition to not let the app crash
                try {
                    mass=Integer.valueOf(input_mass.getText().toString());
                }
                catch(NumberFormatException ex){
                    showToast("fill the fields!!");
                    return;
                }
                if (sub == false) {
                    showToast("fill the fields!!");
                    return;
                }
                    mass = Integer.valueOf(input_mass.getText().toString());
                    mass = Math.ceil(mass / 100) * 100;

                    if (submit() == -1) {
                        tv_result.setText("INVALID");
                        tv_result1.setText("INVALID");
                        tv_result2.setText("INVALID");
                        showToast("Change the Dates!!");
                    } else {
                        tv_result.setText(String.valueOf((int) storage));
                        tv_result1.setText(String.valueOf((int) VAT));
                        tv_result2.setText(String.valueOf(submit()));

                    }

            }



        });
}


    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        yearFinal = i;
        monthFinal = i1 + 1;
        dayFinal = i2;

        if (enter == true) {
            tv_Entrance.setText(dayFinal + "/" + monthFinal + "/" + yearFinal);
            day=dayFinal;
            month=monthFinal;
            year=yearFinal;
        }
        if (ex == true) {

            tv_Exit.setText(dayFinal + "/" + monthFinal + "/" + yearFinal);
            day1=dayFinal;
            month1=monthFinal;
            year1=yearFinal;
        }
        sub=true;
        enter = false;
        ex = false;
    }

    //the method that does all the calculations
    public int submit() {
        yf = year1 - year;
        mf = month1 - month;
        df = day1 - day;

        if((yf<0 || mf<0)||(((yf==0)&&(mf==0))&&df<0)){
            return (int) (total=-1);
        }


        days = yf * 365 + mf * 30 + df;
        mDays = days - 4;
        weeks = (int)Math.ceil(mDays / 7);
        if (mDays > 30 && mDays <= 60) {
            n1Days = mDays - 30;
            n2Days = 0;
        }
        if (mDays > 60) {
            n2Days = mDays - 60;
            n1Days = 0;
        }
        storage = weeks * 10000 * (mass / 100) + n1Days * 2000 * (mass / 100) + n2Days * 4000 * (mass / 100);
        VAT = storage * 11 / 100;
        VAT=Math.ceil(VAT/1000)*1000;
        total = VAT + storage;
        return (int)total;
    }


    private void showToast(String text){
        Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
    }
}
