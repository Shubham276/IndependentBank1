package ten.independentbank;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Shubham on 06-Oct-17.
 */

public class Session {
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    Context context;
    public Session(Context context)
    {
        this.context=context;
        preferences=context.getSharedPreferences("inde",Context.MODE_PRIVATE);
        editor=preferences.edit();
    }
    public void setLoggedIn(boolean loggedIn)
    {
        editor.putBoolean("loggedInmode",loggedIn);
        editor.commit();
    }
    public boolean loggedIn()
    {
        return  preferences.getBoolean("loggedInmode",false);
    }
}
