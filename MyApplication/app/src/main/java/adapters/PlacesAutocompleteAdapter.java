package adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;

import ca.team5.perishablereportingapplication.R;
import datasources.PlacesAutoCompleteDataSource;


/**
 * Created by Jeremy on 15/01/2016.
 */
public class PlacesAutocompleteAdapter extends BaseAdapter implements Filterable {
    private Context context = null;
    private static final String TAG = "PlacesAutoCompAdapter";
    private ArrayList<String> suggestions = new ArrayList<>();
    private PlacesAutoCompleteDataSource dataSource = null;

    public PlacesAutocompleteAdapter(Context context) {
        this.context = context;
        dataSource = new PlacesAutoCompleteDataSource(context);
    }

    @Override
    public int getCount() {
        return suggestions.size();
    }

    @Override
    public Object getItem(int position) {
        return suggestions.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.item_organization, parent, false);
        TextView tv = (TextView) convertView;
        tv.setText(((String) getItem(position)));
        return convertView;
    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults result = new FilterResults();
                if (constraint != null) {
                    dataSource.getParams().clear();
                    dataSource.getSuggestions().clear();
                    dataSource.makeUnchangeableKeys();
                    dataSource.makeInputKey(constraint.toString());
                    dataSource.makeGeocodeKey();
                    dataSource.makeSensorKey();
                    try {
                        dataSource.getHttpGETInputStream();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    suggestions = dataSource.getSuggestions();
                    result.values = suggestions;
                    result.count = suggestions.size();
                }
                return result;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                if (results != null && results.count > 0) {
                    notifyDataSetChanged();
                } else {

                    notifyDataSetInvalidated();
                }
            }
        };
        return filter;
    }
}
