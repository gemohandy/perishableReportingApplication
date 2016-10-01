package adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import ca.team5.perishablereportingapplication.R;

public class OrganizationAdapter extends ArrayAdapter<String> implements AdapterView.OnItemSelectedListener{
    private Context context;
    private int selectedIndex = 0;

    public OrganizationAdapter(Context context) {
        super(context, R.layout.item_fmo_list);
        this.context = context;
    }

    @Override
    public int getCount() {
        return 3;
    }

    public String getItemAt(int position) {
        switch (position) {
            case 0:
                return "N/A";
            case 1:
                return "Non-Profit";
            case 2:
                return "Business";
            default:
                return "N/A";
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(R.layout.item_fmo_list, parent, false);
        TextView tv = (TextView)convertView.findViewById(R.id.ifl_tv_text);
        tv.setText(getItemAt(position));
        return convertView;
    }


    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(R.layout.item_organization, parent, false);
        TextView tv = (TextView)convertView.findViewById(R.id.io_tv_text);
        tv.setText(getItemAt(position));
        return convertView;
    }

    public int getSelectedIndex() {
        return selectedIndex;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        selectedIndex = i;
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        selectedIndex = 0;
    }
}
