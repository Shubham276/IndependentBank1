package ten.independentbank;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;


public class MyDbAdapter {
    myDBhelper myhelper;

    public MyDbAdapter(Context context)
    {
        myhelper = new myDBhelper(context);
    }

    public long insertData(String name, String pass, String cpass, String emailid, String contact,
                           String address, String city, String locality, String dob, String country, String accnum) {
         SQLiteDatabase db = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDBhelper.NAME,name);
        contentValues.put(myDBhelper.dpassword, pass);
        contentValues.put(myDBhelper.dconfirm, cpass);
        contentValues.put(myDBhelper.demail, emailid);
        contentValues.put(myDBhelper.dphone, contact);
        contentValues.put(myDBhelper.daddress, address);
        contentValues.put(myDBhelper.dcity, city);
        contentValues.put(myDBhelper.dlocality, locality);
        contentValues.put(myDBhelper.ddob, dob);
        contentValues.put(myDBhelper.dcountry, country);
        contentValues.put(myDBhelper.daccountnumber, accnum);
        long id = db.insert(myDBhelper.tb_name, null, contentValues);
        return id;
    }
    public String welcomeData(String name,String pass) {
        SQLiteDatabase db=myhelper.getReadableDatabase();
        String[] columns ={myDBhelper.NAME,myDBhelper.daccountnumber,myDBhelper.demail};
        String whereArgs[]={name,pass};
        Cursor c = db.query(myhelper.tb_name,columns,myDBhelper.NAME+" =? AND "+myDBhelper.dpassword+" =?",whereArgs,null,null,null);
        StringBuffer buffer=new StringBuffer();
        while (c.moveToNext())
        {
            String naam =c.getString(c.getColumnIndex(myDBhelper.NAME));
            int ac =c.getInt(c.getColumnIndex(myDBhelper.daccountnumber));
            String mail =c.getString(c.getColumnIndex(myDBhelper.demail));
            buffer.append(naam+" "+ac+" "+mail);
        }
        String s=buffer.toString();
        return s;
    }

  public String getData(String name,String pass) {
        SQLiteDatabase db=myhelper.getReadableDatabase();
        String[] columns ={myDBhelper.NAME,myDBhelper.dpassword};
        String whereArgs[]={name,pass};
        Cursor c = db.query(myhelper.tb_name,columns,myDBhelper.NAME+" =? AND "+myDBhelper.dpassword+" =?",whereArgs,null,null,null);
        StringBuffer buffer=new StringBuffer();
        while (c.moveToNext())
        {
            String naam =c.getString(c.getColumnIndex(myDBhelper.NAME));
            String paas =c.getString(c.getColumnIndex(myDBhelper.dpassword));
            buffer.append(naam+" "+paas);
        }
        String s=buffer.toString();
        return s;
    }

    class myDBhelper extends SQLiteOpenHelper {
        private static final String db_name = "Independent";
        private static final String tb_name = "Information";
        private static final int Version = 1;
        private static final String UID = "_id";
        private static final String NAME = "Name";
        private static final String dpassword = "password";
        private static final String dconfirm = "c_password";
        private static final String demail = "mail";
        private static final String dphone = "mobile";
        private static final String daddress = "address";
        private static final String dcity = "city";
        private static final String dlocality = "locality";
        private static final String ddob = "dob";
        private static final String dcountry = "country";
        private static final String daccountnumber = "accnum";
        public static final String TRANSACTIONS_TABLE_NAME = "transactions";
        public static final String TRANSACTIONS_ID = "_id";
        public static final String TRANSACTIONS_ACCOUNT_ID = "account_id";
        public static final String TRANSACTIONS_TRANSDATE = "transdate";
        public static final String TRANSACTIONS_TRANSTYPE = "transtype";
        public static final String TRANSACTIONS_TRANSAMOUNT = "transamount";
        private static final String create_query = "CREATE TABLE "+tb_name+" ("+UID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+NAME+
                " VARCHAR(50), "+dpassword+" VARCHAR(20), " +dconfirm+" VARCHAR(20), "+demail+" VARCHAR(100), "+dphone+
                " NUMBER(10), "+daddress+" VARCHAR(256), "+dcity+" VARCHAR(100), "+dlocality+" VARCHAR(100), "+ddob+
                " VARCHAR(100), "+dcountry+" VARCHAR(100), "+daccountnumber+" NUMBER(16))";
        private Context context;
        public myDBhelper(Context context) {
            super(context, db_name, null, Version);
            this.context = context;
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase)
        {
            try
            {
                Toast.makeText(context, "onCreate called", Toast.LENGTH_SHORT).show();
                sqLiteDatabase.execSQL(create_query);
            } catch (SQLException e)
            {
                Toast.makeText(context, "database not created", Toast.LENGTH_SHORT).show();
            }
            }
        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            try {
                if(Version==2)
                {
                    sqLiteDatabase.execSQL("create table " + myhelper.TRANSACTIONS_TABLE_NAME + " ( " +
                            myhelper.TRANSACTIONS_ID 	+ " integer  primary key autoincrement," +
                            myhelper.TRANSACTIONS_ACCOUNT_ID + " TEXT," +
                            myhelper.TRANSACTIONS_TRANSDATE + " TEXT," +
                            myhelper.TRANSACTIONS_TRANSAMOUNT + " FLOAT," +
                            TRANSACTIONS_TRANSTYPE+ " TEXT)" );
                }
                this.onCreate(sqLiteDatabase);
            } catch (SQLException e) {
                Toast.makeText(context, "upgradation error", Toast.LENGTH_SHORT).show();
            }
        }
    }
    }

