package ten.independentbank;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class Helppage extends AppCompatActivity {
    private String arr[]={"Registration","Login","Forgot Password"};
    private String reg[]=new String[]{"Enter all information","Enter Valid Username","Enter Valid Account Number"};
    private String login[]=new String[]{"Enter all information","Enter Valid Email","Enter Valid Password"};
    private String fp[]=new String[]{"Forgot Password"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helppage);
        final TextView textView= (TextView) findViewById(R.id.help2);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,arr);
        Spinner sp= (Spinner) findViewById(R.id.spin);
        final Spinner sp2=(Spinner)findViewById(R.id.spin2);
        sp.setAdapter(adapter);
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                TextView tv= (TextView) view;
                TextView tv2=(TextView)view;
                if(arr[i]=="Registration")
                {
                    ArrayAdapter<String> adapter1=new ArrayAdapter<String>(Helppage.this,android.R.layout.simple_spinner_dropdown_item,reg);
                    sp2.setAdapter(adapter1);
                }
                if(arr[i]=="Login")
                {
                    ArrayAdapter<String> adapter2=new ArrayAdapter<String>(Helppage.this,android.R.layout.simple_spinner_dropdown_item,login);
                    sp2.setAdapter(adapter2);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        try {
            sp2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    if(reg[i]=="Enter all information")
                                {
                                    textView.setText("Fill all of the fields. Do not left any field\n"+
                                            "take care of your username note it down somewhere");
                                    textView.setVisibility(View.VISIBLE);
                                }
                                if(reg[i]=="Enter Valid Email")
                                {
                                    textView.setText("enter a valid email id\n"+
                                            "for ex-username@(your-mail-provider).(com/in)");
                                    textView.setVisibility(View.VISIBLE);
                                }if(reg[i]=="Enter Valid Account Number")
                                {
                                    textView.setText("Enter valid 16 digit account number\n"+
                                            "compulsary field");
                                    textView.setVisibility(View.VISIBLE);
                                }
                    if(login[i]=="Enter all information")
                                {
                                    textView.setText("Fill all of the fields. Do not left any field\n"+
                                            "if field is empty you can't be redirected to welcome page");
                                }
                                if(login[i]=="Enter Valid Username")
                                {
                                    textView.setText("enter a valid username\n" +
                                            "it is case sensitive, on registration screen we requested you to note it down somewhere");

                                }if(login[i]=="Enter Valid Password")
                                {
                                    textView.setText("enter a valid password\n" + "it is case sensitive, on registration screen we requested you to note it down somewhere\n"+
                                            "without access can't be given, if forget please re-generate it");
                                }
                    if(fp[i]=="Forgot Password")
                                {
                                    textView.setText("If you forget your password\n"+
                                            "then you need to re-generate it by our forgot password option\n"
                                            +"you need enter your registred email and phone number, after that in your registered\n"+
                                            "email/password a otp is provided enter that otp in app and rest your password");
                                }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
