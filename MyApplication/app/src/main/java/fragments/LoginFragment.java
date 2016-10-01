package fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import ca.team5.perishablereportingapplication.R;

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
        //TODO Authenticate
    }
}
