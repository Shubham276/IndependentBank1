package ten.independentbank;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class Transfer_frag extends Fragment {
    private EditText anumber,aname,cash,pass;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.transfer_frag,null,false);
        anumber= (EditText) v.findViewById(R.id.anum);
        aname= (EditText) v.findViewById(R.id.aname);
        cash= (EditText) v.findViewById(R.id.acash);
        Button transbutton= (Button) v.findViewById(R.id.trans);
        transbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                transfer();
            }
        });
        return v;
    }
//    public void transfer( )
//    {
//        MyDbAdapter helper=new MyDbAdapter(getContext());
//        SQLiteDatabase sqLiteDatabase=helper.myhelper.getReadableDatabase();
//        java.util.Date date=new java.util.Date();
//        String transferdate=new SimpleDateFormat("dd/MM/yyyy").format(date);
//        String accountnumber=anumber.getText().toString();
//        String accountname=aname.getText().toString();
//        Bundle b = getIntent().getExtras();
//        try {
//            uname.setTeb.getCharSequence("aapt"));
//            accnumb.setText(b.getCharSequence("aap"));
//            email.setText(b.getCharSequence("aa"));
//            name.setText(b.getCharSequence("aapt"));
//            mail.setText(b.getCharSequence("aa"));
//        }
//        catch (Exception e)
//        {
//            Toast.makeText(getContext(),"login again for getting your information",Toast.LENGTH_SHORT).show();
//        }
//        String amount=cash.getText().toString();
//        if(accountnumber==){
//         ContentValues contentValues = new ContentValues();
//        contentValues.put(helper.myhelper.TRANSACTIONS_TRANSDATE,transferdate);
//        contentValues.put(helper.myhelper.TRANSACTIONS_TRANSAMOUNT,amount);
//
//        sqLiteDatabase.insert(helper.myhelper.TRANSACTIONS_TABLE_NAME,null,contentValues);
//        }
//    }
}
