package fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;

import application.PreferencesApplication;
import ca.team5.perishablereportingapplication.R;
import data.Login;
import datasources.LoginDataSource;

public class LoginFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = "LoginFrag";
    private EditText etUsername;
    private EditText etPassword;
    private Button btnGo;

    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstance) {
        super.onViewCreated(view, savedInstance);
        bind();
    }

    private void bind() {
        if (getActivity() != null) {
            etUsername = (EditText) getView().findViewById(R.id.fl_et_username);
            etPassword = (EditText) getView().findViewById(R.id.fl_et_password);
            btnGo = (Button) getView().findViewById(R.id.fl_btn_go);
            btnGo.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View view) {
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();
        LoginDataSource lds = new LoginDataSource();
        if (lds.authenticate(username, password)) {
            PreferencesApplication app = (PreferencesApplication)getActivity().getApplication();
            SharedPreferences.Editor editor = app.getPreferences(getActivity()).edit();
            Gson gson = new Gson();
            Login login = new Login();
            login.setUsername(username);
            login.setPassword(password);
            editor.putString("loginData", gson.toJson(login));
            editor.putBoolean("loggedIn", true);
            editor.commit();
            getActivity().getSupportFragmentManager().popBackStack();
            //TODO logout
        }
    }
}
