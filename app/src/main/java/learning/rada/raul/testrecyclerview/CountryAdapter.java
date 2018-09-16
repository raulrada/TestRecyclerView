// Based on Udacity code at https://github.com/udacity/ud851-Exercises/blob/T03.04-Exercise-WiringUpRecyclerView/app/src/main/java/com/example/android/recyclerview/GreenAdapter.java

package learning.rada.raul.testrecyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * RecyclerView adapter providing the layout for each element in a list of {@link Country} objects
 */
public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.CountryViewHolder> {

    /**
     * on-click handler used to make it easy for the activity to interface with the RecyclerView
     */
    private final ListItemClickListener onClickListener;
    /**
     * member variable holding the number of items to display
     */
    private int numberOfItems;
    /**
     * member variable holding the {@link Country} objects to be displayed in the RecyclerView
     */
    private ArrayList<Country> countries;

    /**
     * Constructor for {@link CountryAdapter} objects
     *
     * @param numberOfItems   number of items to display
     * @param countries       ArrayList containing {@link Country} objects which should be displayed
     * @param onClickListener listener for list item clicks
     */
    public CountryAdapter(int numberOfItems, ArrayList<Country> countries,
                          ListItemClickListener onClickListener) {
        this.numberOfItems = numberOfItems;
        this.countries = countries;
        this.onClickListener = onClickListener;
    }

    /**
     * Method called when each new ViewHolder is created. This happens when the RecyclerView is
     * laid out. Enough ViewHolders will be created to fill the screen and allow for scrolling.
     *
     * @param parent   ViewGroup containing the created ViewHolders.
     * @param viewType useful only if our RecyclerView had more than one type of item, for
     *                 providing different layouts (it is not the case).
     * @return a new {@link CountryViewHolder} that holds the View for each list item
     */
    @Override
    public CountryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // inflate a View.
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.list_item, parent, false);

        // create a CountryViewHolder for the created view
        CountryViewHolder countryViewHolder = new CountryViewHolder(view);

        // return the created CountryViewHolder
        return countryViewHolder;
    }

    /**
     * OnBindViewHolder is called by the RecyclerView to display the data at the specified position.
     * The contents of the ViewHolder are updated to display the correct information about the
     * current {@link Country} object.
     *
     * @param holder   The ViewHolder which should be updated to represent the contents of
     *                 the item at the given position in the data set.
     * @param position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(CountryViewHolder holder, int position) {
        holder.bind(position, countries.get(position));
    }

    /**
     * This method simply returns the number of items to display. It is used behind the scenes to
     * help layout our Views and for animations.
     *
     * @return The number of items available in our forecast
     */
    @Override
    public int getItemCount() {
        return numberOfItems;
    }

    public interface ListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }

    /**
     * Cache of the children views for a list item.
     */
    class CountryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        /**
         * TextView displaying the index position for the current item - from 1 to getItemCount
         */
        TextView listItemIndexTextView;

        /**
         * TextView displaying the capital of the {@link Country} in the current itemView
         */
        TextView listItemCountryCapitalTextView;

        /**
         * TextView displaying the name of the {@link Country} in the current itemView
         */
        TextView listItemCountryNameTextView;

        /**
         * Constructor for the ViewHolder. Within the constructor, we get a reference to our
         * TextViews.
         *
         * @param itemView the view inflated in
         *                 {@link CountryAdapter#onCreateViewHolder(ViewGroup, int)}
         */
        public CountryViewHolder(View itemView) {
            super(itemView);
            listItemIndexTextView = (TextView) itemView.findViewById(R.id.list_item_index);
            listItemCountryNameTextView = (TextView)
                    itemView.findViewById(R.id.list_item_country_name);
            listItemCountryCapitalTextView = (TextView)
                    itemView.findViewById(R.id.list_item_country_capital);
            itemView.setOnClickListener(this);
        }

        /**
         * Helper method for updating the information displayed in the current view.
         *
         * @param listIndex current position in the list of displayed countries
         * @param country   current {@link Country} object whose info should be displayed in the
         *                  current view.
         */
        public void bind(int listIndex, Country country) {
            listItemIndexTextView.setText(String.valueOf(listIndex + 1)); //the index should start at 1
            listItemCountryNameTextView.setText(country.getCountryName());
            listItemCountryCapitalTextView.setText(country.getCountryCapital());
        }

        /**
         * Override onClick, passing the clicked item's position (getAdapterPosition()) to
         * onClickListener via its onListItemClick method.
         * Called whenever a user clicks on an item in the list.
         *
         * @param view The View that was clicked
         */
        @Override
        public void onClick(View view) {
            int clickedPosition = getAdapterPosition();
            onClickListener.onListItemClick(clickedPosition);
        }
    }
}
