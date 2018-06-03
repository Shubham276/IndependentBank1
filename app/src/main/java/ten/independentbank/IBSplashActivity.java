package ten.independentbank;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

public class IBSplashActivity extends AppCompatActivity {
ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lay_ib_splash);
        progressBar = (ProgressBar) findViewById(R.id.pb);
        progressBar.setVisibility(View.VISIBLE);
        progressBar.setHorizontalScrollBarEnabled(true);
        getSupportActionBar().hide();


        new  Thread()
        {
            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(3000);
                    Intent intent=new Intent(IBSplashActivity.this,Login.class);
                    startActivity(intent);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }

    @Override
    protected void onStop() {
        super.onStop();
        this.finish();

    }
}
