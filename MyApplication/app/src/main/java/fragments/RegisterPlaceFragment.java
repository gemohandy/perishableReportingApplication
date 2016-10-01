package fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;

import adapters.PlacesAutocompleteAdapter;
import ca.team5.perishablereportingapplication.R;

import static android.content.ContentValues.TAG;

public class RegisterPlaceFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener {
    /*
    PlaceAutocompleteFragment autocompleteFragment = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.autocomplete_fragment);

        // Register a listener to receive callbacks when a place has been selected or an error has
        // occurred.
        autocompleteFragment.setOnPlaceSelectedListener(this);
     */
    private PlaceAutocompleteFragment autocompleteFragment;
    private Place place;
    private EditText etName;
    private Button btnRegister;
    private AutoCompleteTextView actv;

    public static RegisterPlaceFragment newInstance() {
        return new RegisterPlaceFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register_place, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstance) {
        super.onViewCreated(view, savedInstance);
        bind();
    }

    private void bind() {
        if (getActivity() != null) {
            etName = (EditText)getView().findViewById(R.id.frp_et_name);
            btnRegister = (Button)getView().findViewById(R.id.frp_btn_register);
            actv = (AutoCompleteTextView)getView().findViewById(R.id.frp_actv_places);
            actv.setAdapter(new PlacesAutocompleteAdapter(getActivity()));
            actv.setOnItemClickListener(this);
            actv.setOnTouchListener(new View.OnTouchListener(){
                @Override
                public boolean onTouch(View v, MotionEvent event){
                    actv.showDropDown();
                    return false;
                }
            });
            btnRegister.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View view) {
        data.Place p = new data.Place();
        p.setName(etName.getText().toString());
        p.setAddress(place.getAddress().toString());
        p.setCity(place.getLocale().toString());
        RegisterFragment rF = RegisterFragment.newInstance(p);
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment_container, rF, rF.getTag()).addToBackStack(rF.getTag()).commitAllowingStateLoss();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        String picked = (String)adapterView.getItemAtPosition(i);
        actv.setText(picked);
        actv.invalidate();
    }
}
