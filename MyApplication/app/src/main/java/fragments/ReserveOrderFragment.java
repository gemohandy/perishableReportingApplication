package fragments;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;

import adapters.ReserveOrderGridAdapter;
import ca.team5.perishablereportingapplication.R;
import data.Order;
import datasources.OrderDataSource;

public class ReserveOrderFragment extends Fragment implements AdapterView.OnItemClickListener {

    private GridView gridView;
    private ReserveOrderGridAdapter adapter;
    private ArrayList<Order> orders;

    public static ReserveOrderFragment newInstance(ArrayList<Order> orders) {
        ReserveOrderFragment rof = new ReserveOrderFragment();
        Bundle b = new Bundle();
        b.putParcelableArrayList("orders", orders);
        rof.setArguments(b);
        return rof;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_reserve_order, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstance) {
        super.onViewCreated(view, savedInstance);
        if (getArguments() != null) {
            orders = getArguments().getParcelableArrayList("orders");
        }
        bind();
    }

    private void bind() {
        if (getActivity() != null) {
            gridView = (GridView)getView().findViewById(R.id.fro_gridview);
            adapter = new ReserveOrderGridAdapter(getActivity(), orders);
            gridView.setAdapter(adapter);
            gridView.setOnItemClickListener(this);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Order temp = adapter.getItem(i);
        ReserveOrderOrderDetailsFragment rof = ReserveOrderOrderDetailsFragment.newInstance(temp);
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment_container, rof, rof.getTag()).addToBackStack(rof.getTag()).commitAllowingStateLoss();
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
        getArguments().clear();
        getArguments().putParcelableArrayList("orders", orders);
        adapter.setOrders(orders);

    }
}
