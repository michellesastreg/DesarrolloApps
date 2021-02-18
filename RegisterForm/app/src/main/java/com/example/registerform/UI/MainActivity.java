package com.example.registerform.UI;

import android.os.Bundle;

import com.example.registerform.R;
import com.example.registerform.databinding.ActivityMainBinding;

import androidx.appcompat.app.AppCompatActivity;

import android.text.method.PasswordTransformationMethod;
import android.util.Patterns;
import android.view.View;

import android.widget.CompoundButton;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    private boolean isPasswordHidden = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.ivHidden.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isPasswordHidden = !isPasswordHidden;
                if (isPasswordHidden) {
                    binding.ivHidden.setImageResource(R.drawable.hidden);
                    binding.etPassword.setTransformationMethod(new PasswordTransformationMethod());
                } else {
                    binding.ivHidden.setImageResource(R.drawable.showing);
                    binding.etPassword.setTransformationMethod(null);
                }
                binding.etPassword.setSelection(binding.etPassword.getText().length());
            }
        });

        binding.cbTerms.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                binding.bnRegistrar.setEnabled(isChecked);
            }
        });
    }
    public void runValidations(View view) {
        validateForEmptyField(binding.etName);
        validateForEmptyField(binding.etLastName);
        validateForEmptyField(binding.etPassword);
        validateForEmptyField(binding.etMail);
        validateEmailAddressField();
        validatePasswordLength();
    }

    private void validateForEmptyField(EditText editText){
        String text = getTrimmedText(editText);
        if (text.isEmpty()) {
            editText.setError(getString(R.string.error_empty_field));
        }
    }
    private String getTrimmedText(EditText editText){
        return editText.getText().toString().trim();
    }


    private void validateEmailAddressField(){
        if(!Patterns.EMAIL_ADDRESS.matcher(getTrimmedText(binding.etMail)).matches()){
            binding.etMail.setError(getString(R.string.error_invalid_email));
        }
    }

    private void validatePasswordLength() {
        String password = getTrimmedText(binding.etPassword);
        if (password.length() < 10) {
            binding.etPassword.setError(getString(R.string.error_password_length));
        }
    }

}