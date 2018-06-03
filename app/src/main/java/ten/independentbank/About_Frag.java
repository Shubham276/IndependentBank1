package ten.independentbank;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

/**
 * Created by Shubham on 02-Oct-17.
 */

public class About_Frag extends Fragment {
    Button button,submit;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.about_frag,null,false);
        button= (Button) v.findViewById(R.id.location);
        final RatingBar simpleRatingBar = (RatingBar)v.findViewById(R.id.simpleRatingBar);
        Button submitButton = (Button) v.findViewById(R.id.submitButton);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get values and then displayed in a toast
                String totalStars = "Total Stars:: " + simpleRatingBar.getNumStars();
                String rating = "Rating :: " + simpleRatingBar.getRating();
                Toast.makeText(getContext(), totalStars + "\n" + rating, Toast.LENGTH_LONG).show();
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i2 = new Intent();
                i2.setAction(Intent.ACTION_VIEW);
                i2.setData(Uri.parse("geo:28.585393,77.313216"));
                startActivity(i2);
            }
        });
        return v;

    }
}
