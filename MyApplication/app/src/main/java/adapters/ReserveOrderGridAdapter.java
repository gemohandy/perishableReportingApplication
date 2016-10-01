package adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import ca.team5.perishablereportingapplication.R;
import data.Order;

public class ReserveOrderGridAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Order> orders = new ArrayList<>();

    public ReserveOrderGridAdapter(Context context) {
        this.context = context;
        this.orders = orders;
    }

    public ReserveOrderGridAdapter(Context context, ArrayList<Order> orders) {
        this.context = context;
        this.orders = orders;
    }
    @Override
    public int getCount() {
        return orders.size();
    }

    @Override
    public Order getItem(int i) {
        return orders.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.item_fro_grid, viewGroup, false);
        TextView tvDetails = (TextView)view.findViewById(R.id.fro_details);
        TextView tvQuantity = (TextView)view.findViewById(R.id.fro_quantity);
        tvDetails.setText(getItem(i).toString());
        tvQuantity.setText(getItem(i).getItems().size() + " Item(s)");
        return view;
    }
}
