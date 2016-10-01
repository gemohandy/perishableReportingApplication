package fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.gson.Gson;

import adapters.OrganizationAdapter;
import application.PreferencesApplication;
import ca.team5.perishablereportingapplication.R;
import data.Charity;
import data.Company;
import data.Login;
import data.Place;
import datasources.CharityDatasource;
import datasources.CompanyDatasource;
import datasources.LoginDataSource;
import datasources.PlacesDataSource;

public class RegisterFragment extends Fragment implements View.OnClickListener {
    private EditText etUsername;
    private EditText etPassword;
    private EditText etName;
    private EditText etPhone;
    private EditText etEmail;
    private Spinner spOrg;
    private EditText etConfirmPassword;
    private Button btnRegister;
    private OrganizationAdapter adapter;
    private Place place;
    private String dropDownSelection ="";
    public static RegisterFragment newInstance(Place place) {
        RegisterFragment rFrag = new RegisterFragment();
        Bundle b = new Bundle();
        b.putParcelable("place", place);
        rFrag.setArguments(b);
        return rFrag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstance) {
        super.onViewCreated(view, savedInstance);
        place = getArguments().getParcelable("place");
        bind();
    }

    private void bind() {
        if (getActivity() != null) {
            etName = (EditText)getView().findViewById(R.id.fr_et_name);
            etUsername = (EditText)getView().findViewById(R.id.fr_et_username);
            etPassword = (EditText)getView().findViewById(R.id.fr_et_password);
            etConfirmPassword = (EditText)getView().findViewById(R.id.fr_et_confirm_password);
            etEmail = (EditText)getView().findViewById(R.id.fr_et_email);
            etPhone = (EditText)getView().findViewById(R.id.fr_et_phone);
            spOrg = (Spinner)getView().findViewById(R.id.fr_sp_org);
            btnRegister = (Button)getView().findViewById(R.id.fr_btn_register);
            adapter = new OrganizationAdapter(getActivity());
            spOrg.setAdapter(adapter);
            spOrg.setOnItemSelectedListener(adapter);
            btnRegister.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View view) {
        if (etPassword.getText().toString().equals(etConfirmPassword.getText().toString())) {
            Login login = new Login();
            login.setName(etName.getText().toString());
            login.setUsername(etUsername.getText().toString());
            login.setPassword(etPassword.getText().toString());
            login.setEmail(etEmail.getText().toString());
            login.setPhone(etPhone.getText().toString());
            PlacesDataSource pds =new PlacesDataSource();
            place.setId(null);
            int pId = pds.insertPlace(place);
            if (adapter.getSelectedIndex() != 0) {
                switch (adapter.getSelectedIndex()) {
                    case 1:
                        Charity charity = new Charity();
                        CharityDatasource chds = new CharityDatasource();
                        charity.setFk_PlaceID(pId);
                        charity.setId(null);
                        int chid = chds.insertCharity(charity);
                        login.setFk_CharityID(chid);
                        login.setFk_CompanyID(null);
                        break;
                    case 2:
                        Company company = new Company();
                        CompanyDatasource cds = new CompanyDatasource();
                        company.setFk_PlaceID(pId);
                        company.setId(null);
                        int cId = cds.insertCompany(company);
                        login.setFk_CompanyID(cId);
                        login.setFk_CharityID(null);
                        break;
                }
            }

            LoginDataSource lds = new LoginDataSource();
            login.setId(null);
            lds.insertLoginData(login);
            PreferencesApplication app = (PreferencesApplication)getActivity().getApplication();
            SharedPreferences.Editor editor = app.getPreferences(getActivity()).edit();
            Gson gson = new Gson();
            editor.putString("loginData", gson.toJson(login));
            editor.putBoolean("loggedIn", true);
            editor.commit();
            getActivity().getSupportFragmentManager().popBackStack();
            getActivity().getSupportFragmentManager().popBackStack();

        }
    }

}
