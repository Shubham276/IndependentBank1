package ten.independentbank;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class Transfer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);
        RadioGroup radioGroup= (RadioGroup) findViewById(R.id.group);
        int i = radioGroup.getCheckedRadioButtonId();
        if(radioGroup.getId()==R.id.r1)
        {
            FragmentManager fragmentManager=getSupportFragmentManager();
            FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.transferlayout,new Transfer_frag(),"bbb");
            fragmentTransaction.addToBackStack("bbb");
            fragmentTransaction.commit();
        }
        if(radioGroup.getId()==R.id.r2)
        {
            startActivity(new Intent(Transfer.this,Welcome.class));
        }
    }
}
