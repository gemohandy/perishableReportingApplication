package fragments;

import android.os.Bundle;
import android.support.v17.leanback.widget.HorizontalGridView;
import android.support.v17.leanback.widget.OnChildSelectedListener;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.Calendar;

import adapters.ItemAddedAdapter;
import adapters.OrderItemAdapter;
import ca.team5.perishablereportingapplication.R;
import data.Order;
import data.OrderItem;

public class MakeOrderFragment extends Fragment implements View.OnClickListener, OnChildSelectedListener, AdapterView.OnItemClickListener {
    private static final String TAG = "MakeOrderF";
    private HorizontalGridView gridView;
    private ListView listView;
    private OrderItemAdapter adapter;
    private EditText etOtherName;
    private EditText etQuantity;
    private Button btnAddItem;
    private Button btnAddOrder;
    private ItemAddedAdapter addedAdapter;
    public static MakeOrderFragment newInstance() {
        MakeOrderFragment mof = new MakeOrderFragment();

        return mof;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_make_order, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstance) {
        super.onViewCreated(view, savedInstance);
        bind();
    }

    private void bind() {
        if (getActivity() != null) {
            gridView = (HorizontalGridView)getView().findViewById(R.id.fmo_grid_items);
            listView = (ListView)getView().findViewById(R.id.fmo_list_items);
            etOtherName = (EditText)getView().findViewById(R.id.fmo_et_name);
            etQuantity = (EditText)getView().findViewById(R.id.fmo_et_qnty);
            btnAddItem = (Button)getView().findViewById(R.id.fmo_btn_add_item);
            btnAddOrder = (Button)getView().findViewById(R.id.fmo_btn_add_whole_order);
            adapter = new OrderItemAdapter(getActivity());
            addedAdapter = new ItemAddedAdapter(getActivity());
            gridView.setAdapter(adapter);
            listView.setAdapter(addedAdapter);
            btnAddItem.setOnClickListener(this);
            btnAddOrder.setOnClickListener(this);
            gridView.setOnChildSelectedListener(this);
            listView.setOnItemClickListener(this);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fmo_btn_add_item:
                OrderItem item = new OrderItem();
                if (adapter.getSelectedIndex() != -1) {
                    item.setName(adapter.getSelectedItem().getName());
                } else if (etOtherName.getText() != null && !etOtherName.getText().toString().equals("")) {
                    item.setName(etOtherName.getText().toString());
                } else {
                    return;
                }
                if (etQuantity.getText() != null && etQuantity.getText().toString().matches("\\d+")) {
                    item.setQuantity(Integer.parseInt(etQuantity.getText().toString()));
                } else {
                    item.setQuantity(1);
                }
                addedAdapter.add(item);
                etOtherName.getText().clear();
                etQuantity.getText().clear();
                adapter.resetSelectedItem();
                break;
            case R.id.fmo_btn_add_whole_order:
                Order order = new Order();
                order.setDateTime(order.getSdf().format(Calendar.getInstance().getTime()));
                order.setActive(true);
                order.setItems(addedAdapter.getItems());

                //TODO send upstream
                break;
        }

    }

    @Override
    public void onChildSelected(ViewGroup parent, View view, int position, long id) {
        adapter.setSelectedItem(position);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        addedAdapter.removeAt(i);

    }
}
