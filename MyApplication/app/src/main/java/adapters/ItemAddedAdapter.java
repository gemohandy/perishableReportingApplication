package adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import ca.team5.perishablereportingapplication.R;
import data.OrderItem;

public class ItemAddedAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<OrderItem> items = new ArrayList<>();

    public ItemAddedAdapter(Context context) {
        super();
        this.context = context;
    }

    public ItemAddedAdapter(Context context, ArrayList<OrderItem> items) {
        super();
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public OrderItem getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.item_fmo_list, viewGroup, false);
        TextView tv = (TextView)view.findViewById(R.id.ifl_tv_text);
        tv.setText(getItem(i).getName());
        return view;
    }

    public void add(OrderItem item) {
        items.add(item);
        notifyDataSetChanged();
    }

    public void removeAt(int i) {
        items.remove(i);
        notifyDataSetChanged();
    }

    public ArrayList<OrderItem> getItems() {
        return items;
    }
}
