package ten.independentbank;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import java.util.ArrayList;

public class Welcome extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private TextView uname,accnumb,email,name,mail;
    TextView profileImage;
    private ViewFlipper top,mid;
    Session session;
    SharedPreferences preferences;
    Context context;
    LinearLayout laywel;
    ListView lview;
    private ArrayList<Model> List;
    TextView orderid,mobile_number,amount,status,date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View header=navigationView.getHeaderView(0);

        name=(TextView) header.findViewById(R.id.user);
        mail=(TextView) header.findViewById(R.id.email);
        uname= (TextView) findViewById(R.id.username);
        accnumb= (TextView) findViewById(R.id.accnumb);
        email= (TextView) findViewById(R.id.mail);
        laywel= (LinearLayout) findViewById(R.id.welcome);
        profileImage = (TextView) header.findViewById(R.id.profileImage);
        List = new ArrayList<Model>();
        lview = (ListView) findViewById(R.id.listview);
        orderid = (TextView) findViewById(R.id.Orderid);
        mobile_number = (TextView)findViewById(R.id.mobile_Number);
        amount = (TextView) findViewById(R.id.amount);
        status = (TextView) findViewById(R.id.status);
        date = (TextView) findViewById(R.id.date);
        ListviewAdapter adapter = new ListviewAdapter(Welcome.this, List);
        lview.setAdapter(adapter);

        adapter.notifyDataSetChanged();

        lview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                String torderid = orderid.getText().toString();
                String tmobile_number = mobile_number.getText().toString();
                String tamount = amount.getText().toString();
                String tstatus = status.getText().toString();
                String tdate = date.getText().toString();

                Toast.makeText(Welcome.this,
                        "Orderid " + torderid + "\n"
                                + "Mobile Number : " + tmobile_number + "\n"
                                + "Amount : " + tamount + "\n"
                                + "Status : " + tstatus + "\n" + "Date : " + tdate, Toast.LENGTH_SHORT).show();
            }
        });



        //code for viewflipper in the top
        int images[]=new int[]{R.drawable.bankimage1,R.drawable.bankimage2,R.drawable.bankimage5,R.drawable.bankimage4,R.drawable.bankimage3};
        top= (ViewFlipper) findViewById(R.id.top);
        for(int i=0;i<images.length;i++)
        {
            ImageView imageView=new ImageView(Welcome.this);
            imageView.setImageResource(images[i]);
            top.addView(imageView);
        }
        top.setFlipInterval(2000);
        top.setAutoStart(true);

        //code for viewflipper in the middle
        int images2[]=new int[]{R.drawable.secure,R.drawable.transfer,R.drawable.balance,R.drawable.services};
        mid= (ViewFlipper) findViewById(R.id.middle);
        for(int i=0;i<images2.length;i++)
        {
            ImageView imageView=new ImageView(Welcome.this);
            imageView.setImageResource(images2[i]);
            mid.addView(imageView);
        }
        mid.setFlipInterval(3000);
        mid.setAutoStart(true);

        //Using session manager class for managing login session
        session=new Session(this);
        //bydefault false
        if(!session.loggedIn())
        {
            logout();
        }

        //code for setting values in textview
        if (savedInstanceState == null)
        {
        try {
            preferences=getSharedPreferences("index",Context.MODE_PRIVATE);
            uname.setText(preferences.getString("aapt1","POU"));
            accnumb.setText(preferences.getString("aapt2","POU"));
            email.setText(preferences.getString("aapt3","POU"));
            name.setText(preferences.getString("aapt1","POU"));
            mail.setText(preferences.getString("aapt3","POU"));
        }
        catch (Exception e)
        {
            Toast.makeText(Welcome.this,"login again for getting your information",Toast.LENGTH_SHORT).show();
        }
        }
        else
        {
            uname.setText(savedInstanceState.getString("uname",""));
            accnumb.setText(savedInstanceState.getString("accnumb",""));
            email.setText(savedInstanceState.getString("email",""));
            name.setText(savedInstanceState.getString("uname",""));
            mail.setText(savedInstanceState.getString("email",""));
        }

        String userNameText=preferences.getString("aapt1","POU");
        String[] uName = userNameText.split("\\s+");
        String user = "";
        for (int i = 0; i < uName.length; i++) {
            user = user + uName[i].substring(0, 1);
        }
        profileImage.setText(user);

    }

    @Override
    public void onBackPressed() {
        FragmentManager fm = getSupportFragmentManager();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        else if (fm.getBackStackEntryCount() > 0) {
            fm.popBackStack();
        } else {

//            Intent intent = new Intent(Intent.ACTION_MAIN);
//            intent.addCategory(Intent.CATEGORY_HOME);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//            startActivity(intent);

            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);

        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.welcome, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent intent=new Intent(Settings.ACTION_SETTINGS);
            startActivity(intent);
        }
        if(id==R.id.logout)
        {
            logout();
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.transfer)
        {
            Intent intent=new Intent(Welcome.this,Transfer.class);
            startActivity(intent);
        }
        else if (id == R.id.balance)
        {
            Intent intent=new Intent(Welcome.this,Balance.class);
            startActivity(intent);
        }
        else if (id == R.id.wallet)
        {
            Intent intent=new Intent(Welcome.this,Wallet.class);
            startActivity(intent);
        }
        else if (id == R.id.rules) {
            laywel.setVisibility(View.GONE);
            WebView webView= (WebView) findViewById(R.id.web);
            webView.setVisibility(View.VISIBLE);
            lview.setVisibility(View.GONE);
            LinearLayout linearLayout= (LinearLayout) findViewById(R.id.relativeLayout1);
            linearLayout.setVisibility(View.GONE);
            webView.getSettings().setJavaScriptEnabled(true);
            webView.getSettings().setLoadWithOverviewMode(true);
            webView.getSettings().setUseWideViewPort(true);
            webView.getSettings().setSupportZoom(true);
            webView.getSettings().setBuiltInZoomControls(true);
            webView.setWebViewClient(new WebViewClient());
            String url="https://rbi.org.in/scripts/BS_ViewMasCirculardetails.aspx?id=9862";
            webView.loadUrl(url);
        }
        else if (id == R.id.about) {
            FragmentManager fragmentManager=getSupportFragmentManager();
            FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.drawer_layout,new About_Frag(),"aaaa");
            fragmentTransaction.addToBackStack("aaaa");
            fragmentTransaction.commit();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        preferences=getSharedPreferences("index",Context.MODE_PRIVATE);
        outState.putString("uname",preferences.getString("aapt1","POU"));
        outState.putString("accnumb",preferences.getString("aap2","POU"));
        outState.putString("email",preferences.getString("aapt3","POU"));
    }

    private void logout() {
        session.setLoggedIn(false);
        finish();
        startActivity(new Intent(Welcome.this, Login.class));
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        savedInstanceState.getString("uname","");
        savedInstanceState.getString("accnumb","");
        savedInstanceState.getString("email","");
        onCreate(savedInstanceState);
    }

}
