package fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import adapters.GridMenuAdapter;
import ca.team5.perishablereportingapplication.R;

public class GridMenuFragment extends Fragment implements AdapterView.OnItemClickListener {
    private static final String TAG = "GridMenuF";
    private GridView gridView;
    private GridMenuAdapter adapter;
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
            adapter = new GridMenuAdapter(getActivity());
            gridView.setAdapter(adapter);
            gridView.setOnItemClickListener(this);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        switch (i) {
            case 0:

                break;
            default:

                break;
        }
    }
}
