package fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import adapters.GridViewReviewAdapter;
import ca.team5.perishablereportingapplication.R;
import data.Order;

public class ReviewReservedOrdersFragment extends Fragment implements AdapterView.OnItemClickListener {
    private GridView gridView;
    private GridViewReviewAdapter adapter;

    public static ReviewReservedOrdersFragment newInstance() {
        ReviewReservedOrdersFragment rrof = new ReviewReservedOrdersFragment();
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
        bind();
    }

    private void bind() {
        if (getActivity() != null) {
            gridView = (GridView)getView().findViewById(R.id.frro_gridview);
            adapter = new GridViewReviewAdapter(getActivity());
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
}
