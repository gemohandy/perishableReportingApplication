package adapters;

import android.content.Context;
import android.drm.DrmUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import ca.team5.perishablereportingapplication.R;
import data.Order;
import data.Reservation;

public class GridViewReviewAdapter extends BaseAdapter {
    private ArrayList<Reservation> reservations = new ArrayList<>();
    private Context context;
    private ArrayList<Order> orders = new ArrayList<>();

    public GridViewReviewAdapter(Context context, ArrayList<Reservation> reservations, ArrayList<Order> orders) {
        this.context = context;
        this.orders = orders;
        this.reservations = reservations;
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
        view = inflater.inflate(R.layout.item_gridview_review, viewGroup, false);
        TextView tvDate = (TextView)view.findViewById(R.id.igr_details);
        TextView tvQuant = (TextView)view.findViewById(R.id.igr_quantity);
        Button btnNavigate = (Button)view.findViewById(R.id.igr_navigate);
        tvDate.setText(orders.get(i).toString());
        tvQuant.setText(orders.get(i).getItems().size() + " Items");
        btnNavigate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO search co. address
            }
        });
        return view;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }
}
