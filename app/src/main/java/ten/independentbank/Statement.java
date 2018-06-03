package ten.independentbank;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.Calendar;

public class Statement extends AppCompatActivity
{
    DatePickerDialog dp;
    EditText editText1,editText2;
    Button show;
    ImageView cal1,cal2;
    java.util.Calendar c=java.util.Calendar.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statement);
        editText1= (EditText) findViewById(R.id.et1);
        editText2= (EditText) findViewById(R.id.et2);
        cal1= (ImageView) findViewById(R.id.datepicker1);
        cal2= (ImageView) findViewById(R.id.datepicker2);
        show= (Button) findViewById(R.id.statementButton);
        cal1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int dd=c.get(Calendar.DAY_OF_MONTH);
                int mm=c.get(Calendar.MONTH);
                int yy=c.get(Calendar.YEAR);
                dp=new DatePickerDialog(Statement.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        String date= i+ "/"+i1+ "/"+i2;
                        editText1.setText(date);
                    }
                },yy,mm,dd);
                dp.show();
            }
        });
        cal2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int dd=c.get(Calendar.DAY_OF_MONTH);
                int mm=c.get(Calendar.MONTH);
                int yy=c.get(Calendar.YEAR);
                dp=new DatePickerDialog(Statement.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        String date= i+ "/"+i1+ "/"+i2;
                        editText2.setText(date);
                    }
                },yy,mm,dd);
                dp.show();

            }
        });
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
