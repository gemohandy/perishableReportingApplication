package fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import adapters.OrganizationAdapter;
import ca.team5.perishablereportingapplication.R;
import data.Login;

public class RegisterFragment extends Fragment implements View.OnClickListener {
    /*
       private int Id = -1;
    private String Username = "";
    private String Password = "";
    private String Name = "";
    private String Phone = "";
    private String Email = "";
    private int fk_CompanyID = -1;
    private int fk_CharityID = -1;
     */
    private EditText etUsername;
    private EditText etPassword;
    private EditText etName;
    private EditText etPhone;
    private EditText etEmail;
    private Spinner spOrg;
    private EditText etConfirmPassword;
    private Button btnRegister;
    private OrganizationAdapter adapter;
    public static RegisterFragment newInstance() {
        RegisterFragment rFrag = new RegisterFragment();
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
        }
    }

}
