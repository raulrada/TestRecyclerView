/*
 * Small app demonstrating the use of RecyclerViews with custom click listener
 */

package learning.rada.raul.testrecyclerview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements
        CountryAdapter.ListItemClickListener {

    /**
     * Constant value used as key for passing the clicked position in the Intent starting
     * DetailsActivity. Make it public and static so that it can be accessed from DetailsActivity,
     * in order to recover the value of the clicked position.
     */
    public static final String CLICKED_POSITION_KEY = "clicked_position";
    /**
     * List of {@link Country} objects.
     */
    public static ArrayList<Country> countries;
    /**
     * Constant holding the number of {@link Country} objects to be created and displayed
     */
    private final int NUMBER_OF_COUNTRIES = 50;
    /**
     * {@link CountryAdapter} whose data source is a list of {@link Country} objects. The adapter
     * knows how to create list items for each item in the list.
     */
    private CountryAdapter countryAdapter;
    /**
     * RecyclerView object in activity_main.xml
     */
    private RecyclerView countriesRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // change programatically the title in the action bar
        getSupportActionBar().setTitle(getResources().getString(R.string.app_bar_title));

        /**
         * ArrayList holding a list of {@link Country} objects;
         */
        countries = new ArrayList<>();

        // Populate the list of Countries
        for (int i = 0; i < NUMBER_OF_COUNTRIES; i++) {
            // The first index used in the Country name should be 1
            countries.add(new Country(getResources().getString(R.string.country_string)
                    + " " + (i + 1), getResources().getString(R.string.country_capital_string)
                    + " " + (i + 1)));
        }

        // find the countries_recycler_view from activity_main.xml
        countriesRecyclerView = (RecyclerView) findViewById(R.id.countries_recycler_view);

        // create a LinearLayoutManager for measuring and positioning item views within a
        // RecyclerView into a linear list. Since we want to create a vertical linear list, and
        // this is the default orientation of the LinearLayoutManager, we do not need to include an
        // orientation flag in the the LinearLayoutManager constructor.
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        countriesRecyclerView.setLayoutManager(layoutManager);

        // improve performance if you know that changes in content do not change the child layout
        // size in the RecyclerView
        countriesRecyclerView.setHasFixedSize(true);

        // create a divider to be shown between the items displayed in the RecyclerView
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(
                countriesRecyclerView.getContext(), layoutManager.getOrientation());

        // add the divider between the items displayed in the RecyclerView
        countriesRecyclerView.addItemDecoration(dividerItemDecoration);

        // the CountryAdapter is responsible for displaying each item in the list.
        countryAdapter = new CountryAdapter(NUMBER_OF_COUNTRIES, countries, this);

        // set the created CountryAdapter on the RecyclerView
        countriesRecyclerView.setAdapter(countryAdapter);
    }

    /**
     * Override the onListItemClick method from the ListItemClickListener interface defined
     * in CountryAdapter.
     * This is where we receive the callback from the {@link CountryAdapter.ListItemClickListener}.
     * This callback is invoked when the user clicks on a list item.
     *
     * @param clickedItemIndex position in the list of the clicked item.
     */
    @Override
    public void onListItemClick(int clickedItemIndex) {
        Intent startDetails = new Intent(this, DetailsActivity.class);
        startDetails.putExtra(CLICKED_POSITION_KEY, clickedItemIndex);
        startActivity(startDetails);
    }
}
