package fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.Calendar;

import adapters.ItemAddedAdapter;
import application.PreferencesApplication;
import ca.team5.perishablereportingapplication.R;
import data.Login;
import data.Order;
import data.Reservation;
import datasources.OrderDataSource;
import datasources.ReservationDataSource;

public class ReserveOrderOrderDetailsFragment extends Fragment implements View.OnClickListener {
    private Order order;
    private ListView listView;
    private ItemAddedAdapter adapter;
    private Button btnReserve;
    private TextView tvDetails;

    public static ReserveOrderOrderDetailsFragment newInstance(Order order) {
        ReserveOrderOrderDetailsFragment roodFrag = new ReserveOrderOrderDetailsFragment();
        Bundle b = new Bundle();
        b.putParcelable("order", order);
        roodFrag.setArguments(b);
        return roodFrag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_reserve_order_details, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstance) {
        super.onViewCreated(view, savedInstance);
        order = getArguments().getParcelable("order");
        bind();
    }

    private void bind() {
        if (getActivity() != null) {
            listView = (ListView)getView().findViewById(R.id.frod_listview);
            tvDetails = (TextView)getView().findViewById(R.id.frod_company_details);
            btnReserve = (Button)getView().findViewById(R.id.frod_btn_reserve);
            adapter = new ItemAddedAdapter(getActivity(), order.getItems());
            listView.setAdapter(adapter);
            btnReserve.setOnClickListener(this);
//            tvDetails.setText(order.getFk_CompanyID() +"");
        }
    }

    @Override
    public void onClick(View view) {
        Reservation reservation = new Reservation();
        reservation.setActive(true);
        reservation.setDateTime(reservation.getSdf().format(Calendar.getInstance().getTime()));
        PreferencesApplication app = (PreferencesApplication)getActivity().getApplication();

        SharedPreferences prefs = app.getPreferences(getActivity());
        Gson gson = new Gson();
        try {
            Login log = gson.fromJson(prefs.getString("login", ""), Login.class);
            reservation.setFk_CharityID(log.getFk_CharityID());
            reservation.setFk_OrderID(order.getId());
            ReservationDataSource rds = new ReservationDataSource();
            rds.insertReservationData(reservation);
        } catch(Exception e) {
            Toast.makeText(getActivity(), "Error Reserving Order For Pickup!", Toast.LENGTH_SHORT).show();
        }
    }
}
