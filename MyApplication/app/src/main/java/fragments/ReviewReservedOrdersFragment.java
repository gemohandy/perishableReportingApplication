package fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;

import adapters.GridViewReviewAdapter;
import ca.team5.perishablereportingapplication.R;
import data.Order;
import data.Reservation;

public class ReviewReservedOrdersFragment extends Fragment implements AdapterView.OnItemClickListener {
    private GridView gridView;
    private GridViewReviewAdapter adapter;
    ArrayList<Reservation> reservations = new ArrayList<>();
    ArrayList<Order> orders = new ArrayList<>();

    public static ReviewReservedOrdersFragment newInstance(ArrayList<Reservation> reservations, ArrayList<Order> orders) {
        ReviewReservedOrdersFragment rrof = new ReviewReservedOrdersFragment();
        Bundle b = new Bundle();
        b.putParcelableArrayList("reservations", reservations);
        b.putParcelableArrayList("orders", orders);
        rrof.setArguments(b);
        return rrof;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_review_reserved_orders, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstance) {
        super.onViewCreated(view, savedInstance);
        if (getArguments() != null) {
            reservations = getArguments().getParcelableArrayList("reservations");
            orders = getArguments().getParcelableArrayList("orders");
        }
        bind();
    }

    private void bind() {
        if (getActivity() != null) {
            gridView = (GridView)getView().findViewById(R.id.frro_gridview);
            adapter = new GridViewReviewAdapter(getActivity(), reservations, orders);
            gridView.setAdapter(adapter);
            gridView.setOnItemClickListener(this);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Order temp = adapter.getOrders().get(i);
        ReserveOrderOrderDetailsFragment rof = ReserveOrderOrderDetailsFragment.newInstance(temp);
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment_container, rof, rof.getTag()).addToBackStack(rof.getTag()).commitAllowingStateLoss();
    }

    public void setReservations(ArrayList<Reservation> reservations) {
        this.reservations = reservations;
        getArguments().clear();
        getArguments().putParcelableArrayList("reservations", reservations);
        getArguments().putParcelableArrayList("orders", orders);
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
        getArguments().clear();
        getArguments().putParcelableArrayList("reservations", reservations);
        getArguments().putParcelableArrayList("orders", orders);
    }
}
