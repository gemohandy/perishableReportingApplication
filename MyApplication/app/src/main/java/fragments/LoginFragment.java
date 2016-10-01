package fragments;

import android.support.v4.app.Fragment;
import android.widget.Button;
import android.widget.EditText;

public class LoginFragment extends Fragment {
    private static final String TAG = "LoginFrag";
    private EditText etUsername;
    private EditText etPassword;
    private Button btnGo;

    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();

        return fragment;
    }
}
