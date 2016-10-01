package fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;

import adapters.GridMenuAdapter;
import ca.team5.perishablereportingapplication.R;
import data.Order;
import data.Reservation;

public class GridMenuFragment extends Fragment implements AdapterView.OnItemClickListener {
    private static final String TAG = "GridMenuF";
    private GridView gridView;
    private GridMenuAdapter adapter;
    private ArrayList<Order> orders = new ArrayList<>();
    private ArrayList<Reservation> reservations = new ArrayList<>();

    public static GridMenuFragment newInstance() {
        GridMenuFragment frag = new GridMenuFragment();
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_grid_menu, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstance) {
        super .onViewCreated(view, savedInstance);
        bind();
    }

    private void bind() {
        if (getActivity() != null) {
            gridView = (GridView)getView().findViewById(R.id.fgm_gridview);
            adapter = new GridMenuAdapter(getActivity(), orders, reservations);
            gridView.setAdapter(adapter);
            gridView.setOnItemClickListener(this);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        switch (i) {
            case 0:
                if (getActivity() != null) {
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment_container, adapter.getItem(i), adapter.getItem(i).getTag()).addToBackStack(adapter.getItem(i).getTag()).commitAllowingStateLoss();
                }
                break;
            case 1:
                if (getActivity() != null) {
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment_container, adapter.getItem(i), adapter.getItem(i).getTag()).addToBackStack(adapter.getItem(i).getTag()).commitAllowingStateLoss();
                }
                break;
            default:
                if (getActivity() != null) {
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment_container, adapter.getItem(i), adapter.getItem(i).getTag()).addToBackStack(adapter.getItem(i).getTag()).commitAllowingStateLoss();
                }
                break;
        }
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
        adapter.setOrders(orders);
    }

    public void setReservations(ArrayList<Reservation> reservations) {
        this.reservations = reservations;
        adapter.setReservations(reservations);
    }
}
