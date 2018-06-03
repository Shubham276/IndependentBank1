package ten.independentbank;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    private ImageButton login,signup;
    private EditText uname,password;
    MyDbAdapter myDbAdapter;
    Session session;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //code for setting custom toolbar
        Toolbar toolbar= (Toolbar) findViewById(R.id.tool);
        toolbar.setTitle("Indepenent Bank");
        toolbar.setTitleTextColor(Color.RED);
        toolbar.setNavigationIcon(R.mipmap.ic_launcher_round);
        toolbar.inflateMenu(R.menu.mymenu);

        //toolbar help
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
               if(item.getItemId()==R.id.help) {
                        Intent intent=new Intent(Login.this,Helppage.class);
                        startActivity(intent);
                }
                    return false;
                }
        });

        //registering id
        signup= (ImageButton) findViewById(R.id.signupbtn);
        uname= (EditText) findViewById(R.id.userName);
        password= (EditText) findViewById(R.id.userPass);
        login= (ImageButton) findViewById(R.id.loginbtn);

        //database
        myDbAdapter=new MyDbAdapter(this);

        //session class
        session=new Session(this);

        //moving to registration page
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Login.this,SignUp.class);
                startActivity(intent);
            }
        });

       //action to be performed by login button
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

        //chcking sesssion at initial level
        if(session.loggedIn())
        {
            startActivity(new Intent(Login.this,Welcome.class));
        }
        }
        public  void  login(){
            //recieving values from edittexts
            String tname=uname.getText().toString();
            String tpass=password.getText().toString();

            //fetchinging values from database
            String data=myDbAdapter.getData(tname,tpass);

            //main try block which checks all exception
            try
            {
                //spitting data array via spaces
                String arr[]=data.split(" ");
                String sub1=arr[0];
                String sub2=arr[1];

                //validation
                if(tname.isEmpty()||tpass.isEmpty())
                {
                    Toast.makeText(Login.this,"please fill all of the field",Toast.LENGTH_SHORT).show();
                }

                //login check
                else if(tname.equals(sub1)&&tpass.equals(sub2))
                {
                    session.setLoggedIn(true);
                    Toast.makeText(Login.this,"login successful",Toast.LENGTH_SHORT).show();

                    //internal try block
                    try{

                        //fetching values from database
                        String show = myDbAdapter.welcomeData(tname, tpass);
                        String val[]=show.split(" ");
                        String v1=val[0];
                        String v2=val[1];
                        String v3=val[2];
                        Toast.makeText(Login.this,"login successful",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Login.this, Welcome.class);
                        //using an intent to move data from here to welcome activity textviews

                        preferences=getSharedPreferences("index",Context.MODE_PRIVATE);
                        editor=preferences.edit();
                        editor.putString("aapt1",v1);
                        editor.putString("aapt2",v2);
                        editor.putString("aapt3",v3);
                        editor.commit();
                        startActivity(intent);

                    }

                    //catch block internal
                    catch (Exception e){
                        Toast.makeText(Login.this,"unable to your fetch data",Toast.LENGTH_SHORT).show();
                    }
                }
                else
                    Toast.makeText(Login.this,"Login Unsuccesfful",Toast.LENGTH_SHORT).show();
            }

            //main catch block
            catch(Exception e){
                Toast.makeText(Login.this,"Login Unsuccesfful Try Again",Toast.LENGTH_SHORT).show();
            }
        }

}
