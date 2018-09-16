package learning.rada.raul.testrecyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {

    /**
     * Default index to display the first {@link Country} in the list, in case for any reason the
     * MainActivity Intent launching DetailsActivity does not include the index of a clicked item.
     */
    private static final int DEFAULT_ITEM_POSITION = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        // set the action bar title for the activity
        getSupportActionBar().setTitle(R.string.details_bar_title);

        // find the relevant TextViews in the layout of the present activity
        TextView countryNameTextView = (TextView) findViewById(R.id.details_country_name_text_view);
        TextView countryCapitalTextView = (TextView)
                findViewById(R.id.details_country_capital_text_view);

        int clickedItemIndex = getIntent().getIntExtra(MainActivity.CLICKED_POSITION_KEY,
                DEFAULT_ITEM_POSITION);

        // display the name and the capital of the selected Country object
        countryNameTextView.setText(getString(R.string.country_name,
                MainActivity.countries.get(clickedItemIndex).getCountryName()));
        countryCapitalTextView.setText(getString(R.string.country_capital,
                MainActivity.countries.get(clickedItemIndex).getCountryCapital()));
    }
}
