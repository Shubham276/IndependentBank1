package ten.independentbank;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.util.Calendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {
    private String[] countryNameList = {"India"};
    private String[] cityList = {"Ajmer","Agra","Aligarh","Dehradun","Rishikesh","Faridabad","Mumbai","Pune","New Delhi","Bangalore","Hyderabad",
            "Kolkata","Chennai","Ahmedabad","Allahabad","Ranchi","Kanpur",
            "Noida","Gurugram","Hyderabad","Jaipur","Nagpur","Lucknow",
            "Amritsar","Chandigarh","Mohali","Kochi","Nashik","Gwalior","Kota",
            "Patna","Haridwar","Ghaziabad","Meerut","Varanasi","Dharamsala","Kashmir",
            "Mohali","Surat","Vishakhapatnam","Coimbatore","Indore","Bhopal","Vadodara",
            "Rajkot","Patiala","Mysore","Aurangabad","Raipur","Shimla","Jamshedpur",
            "Jodhpur","Gaya","Mughal Sarai","Tata Nagar","Daltonganj","Hazaribagh","Roorkee",
            "Saharanpur","Muzzafarnagar","Haldwani","Rudrapur","Hawrah","Bhubneshwar",
            "Dehradun","Gandhinagar","Gangtok","Jaisalmer","Jammu","Madurai","Nainital","Kerela"
            ,"Odhisa","Srinagar","Pondicherry","Tirupati","Darjeeling","Almora","Kashipur","Kotdwar",
            "Jhansi","Mathura","Bareilly","Moradabad","Gorakhpur","Vrindavan"};
    private EditText txtname, txtemail, txtmobile, txtaddress, txtlocality, txtdob, txtpassword, txtconfirmpass,txtaccnum;
    private Button signup;
    private AutoCompleteTextView txtcity,txtcountry;
    private DatePickerDialog datePickerDialog;
    String name, email,number, address, locality, city, dob, country, pass, conpass,accnum;
    MyDbAdapter helper;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        txtcity = (AutoCompleteTextView) findViewById(R.id.city);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, cityList);
        txtcity.setAdapter(adapter);
        txtcity.setThreshold(1);
         txtcountry = (AutoCompleteTextView) findViewById(R.id.country);
        ArrayAdapter adapter2 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, countryNameList);
        txtcountry.setAdapter(adapter2);
        txtcountry.setThreshold(1);
        txtname = (EditText) findViewById(R.id.name);
        txtemail = (EditText) findViewById(R.id.email);
        txtmobile = (EditText) findViewById(R.id.mobile);
        txtaddress = (EditText) findViewById(R.id.address);
        txtlocality = (EditText) findViewById(R.id.locality);
        txtdob = (EditText) findViewById(R.id.dob);
        txtpassword = (EditText) findViewById(R.id.pass);
        txtconfirmpass = (EditText) findViewById(R.id.cpass);
        txtaccnum = (EditText) findViewById(R.id.accnum);
        signup = (Button) findViewById(R.id.btn);
        helper = new MyDbAdapter(this);
        txtdob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calender class's instance and get current date , month and year from calender
                final java.util.Calendar c = java.util.Calendar.getInstance();
                int mYear = c.get(java.util.Calendar.YEAR); // current year
                int mMonth = c.get(java.util.Calendar.MONTH); // current month
                int mDay = c.get(java.util.Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                datePickerDialog = new DatePickerDialog(SignUp.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                txtdob.setText(dayOfMonth + "/"
                                        + (monthOfYear + 1) + "/" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = txtname.getText().toString();
                email = txtemail.getText().toString();
                number = txtmobile.getText().toString();
                address = txtaddress.getText().toString();
                locality = txtlocality.getText().toString();
                city = txtcity.getText().toString();
                dob = txtdob.getText().toString();
                country = txtcountry.getText().toString();
                pass = txtpassword.getText().toString();
                conpass = txtconfirmpass.getText().toString();
                accnum = txtaccnum.getText().toString();
                if (name.isEmpty() || email.isEmpty() || number.isEmpty() || address.isEmpty() || locality.isEmpty() || city.isEmpty()
                        || dob.isEmpty() || country.isEmpty() || pass.isEmpty() || conpass.isEmpty()||accnum.isEmpty()) {
                    Toast.makeText(SignUp.this, "please fill all of the fields", Toast.LENGTH_SHORT).show();
                } else {
                    if (number.length() > 10 || number.length() < 10) {
                        txtmobile.setError("Number is not valid");
                    }
                    else
                        if (accnum.length() > 16 || accnum.length() < 16) {
                            txtaccnum.setError("Account Number is not valid");
                        }
                    else {
                        if (pass.equals(conpass) == false) {
                            txtconfirmpass.setError("Password does not match");
                        } else {
                            long id=helper.insertData(name, pass,conpass,email,number, address, city,locality,dob,country,accnum);
                        if(id<0)
                        {
                            Toast.makeText(SignUp.this,"insertion not okay",Toast.LENGTH_SHORT).show();
                        }
                        else
                            Toast.makeText(SignUp.this,"SignUp Successsfuly",Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(SignUp.this,Login.class);
                            startActivity(intent);
                        }
                    }
                }
            }
        });
}
}
