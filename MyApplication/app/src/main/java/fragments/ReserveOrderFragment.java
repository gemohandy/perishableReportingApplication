package fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import adapters.ReserveOrderGridAdapter;
import ca.team5.perishablereportingapplication.R;
import data.Order;

public class ReserveOrderFragment extends Fragment implements AdapterView.OnItemClickListener {

    private GridView gridView;
    private ReserveOrderGridAdapter adapter;

    public static ReserveOrderFragment newInstance() {
        ReserveOrderFragment rof = new ReserveOrderFragment();

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
        bind();
    }

    private void bind() {
        if (getActivity() != null) {
            gridView = (GridView)getView().findViewById(R.id.fro_gridview);
            adapter = new ReserveOrderGridAdapter(getActivity());
            gridView.setAdapter(adapter);
            gridView.setOnItemClickListener(this);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Order temp = adapter.getItem(i);

    }
}