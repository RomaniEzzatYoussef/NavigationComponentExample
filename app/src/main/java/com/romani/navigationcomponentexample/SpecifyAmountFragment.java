package com.romani.navigationcomponentexample;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class SpecifyAmountFragment extends Fragment implements View.OnClickListener{

    NavController navController = null;
    String recipient = null;

    TextView recipientTXTVIEW;
    TextInputEditText input_amount;

    public SpecifyAmountFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        recipient  = getArguments().getString("recipient");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
//        recipient  = getArguments().getString("recipient");

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_specify_amount, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);

        view.findViewById(R.id.send_btn).setOnClickListener(this);
        view.findViewById(R.id.cancel_btn).setOnClickListener(this);
        String message = "Sending money to " + recipient;
        recipientTXTVIEW = view.findViewById(R.id.recipient);
        recipientTXTVIEW.setText(message);

        input_amount = view.findViewById(R.id.input_amount);
    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.send_btn :
                if (!TextUtils.isEmpty(input_amount.getText().toString()))
                {
                    Bundle bundle = new Bundle();

                    bundle.putString("recipient",recipient);
                    bundle.putString("amount", input_amount.getText().toString() + "");
                    navController.navigate(R.id.action_specifyAmountFragment_to_confirmationFragment , bundle);
                }

                else
                    Toast.makeText(getActivity() , "Enter an amount" , Toast.LENGTH_LONG).show();

                break;

            case R.id.cancel_btn : getActivity().onBackPressed();
                break;
        }
    }
}
