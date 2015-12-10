package com.example.dell.parselogintutorial;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

/**
 * Created by DELL on 12/10/2015.
 */
public class SignUp extends Activity {

    Button SignUpButton;
    TextView login;
    String usernametxt;
    String nametxt;
    String passwordtxt;
    String emailtxt;
    String phonetxt;
    EditText password;
    EditText name;
    EditText username;
    EditText phone;
    EditText email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        username = (EditText) findViewById(R.id.username);
        name = (EditText) findViewById(R.id.name);
        password = (EditText) findViewById(R.id.password);
        phone = (EditText) findViewById(R.id.phone);
        email = (EditText) findViewById(R.id.email);

        // Locate Buttons in main.xml
        SignUpButton = (Button) findViewById(R.id.btn_signup);
        login = (TextView) findViewById(R.id.link_login);
        // Login Button Click Listener
        login.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                Intent intent = new Intent(
                        SignUp.this,
                        LoginSignupActivity.class);
                startActivity(intent);
            }
        });
        // Sign up Button Click Listener
        SignUpButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                // Retrieve the text entered from the EditText


                usernametxt = username.getText().toString();
                nametxt = name.getText().toString();
                passwordtxt = password.getText().toString();
                phonetxt = phone.getText().toString();
                emailtxt = email.getText().toString();

                // Force user to fill up the form
                /*if (usernametxt.equals("") || passwordtxt.equals("") || phone) {
                    Toast.makeText(getApplicationContext(),
                            "Please complete the sign up form",
                            Toast.LENGTH_LONG).show();

                } */
                if (usernametxt.isEmpty()) {
                    username.setError("Username is null");
                }
                else if (nametxt.isEmpty()) {
                    name.setError("name is null");
                }

                else if (emailtxt.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(emailtxt).matches()) {
                    email.setError("enter a valid email address");
                }

                else if (passwordtxt.isEmpty() || passwordtxt.length() < 4) {
                    password.setError("Atleast 4 digits");
                }
                else if (phonetxt.isEmpty() || phonetxt.length()!=10) {
                    phone.setError("invalid");
                }
                else {
                    // Save new user data into Parse.com Data Storage
                    username.setError(null);
                    email.setError(null);
                    password.setError(null);
                    phone.setError(null);
                    name.setError(null);

                    ParseUser user = new ParseUser();
                    user.setUsername(usernametxt);
                    user.setPassword(passwordtxt);
                    user.setEmail(emailtxt);
                    //user.put("Username", usernametxt);
                    //user.put("Password",passwordtxt);
                    user.put("Name", nametxt);
                    user.put("Phone", phonetxt);

                    /*ParseObject user = new ParseObject("UserData");
                    user.put("Username", usernametxt);
                    user.put("Password",passwordtxt);
                    user.put("Name", nametxt);
                    user.put("Email", emailtxt);
                    user.put("Phone", phonetxt);
*/

                    user.signUpInBackground(new SignUpCallback() {
                        public void done(ParseException e) {
                            if (e == null) {
                                // Show a simple Toast message upon successful registration
                                Toast.makeText(getApplicationContext(),
                                        "Successfully Signed up, please log in.",
                                        Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(
                                        SignUp.this,
                                        LoginSignupActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(getApplicationContext(),
                                        "Sign up Error", Toast.LENGTH_LONG)
                                        .show();
                            }
                        }
                    });
                }

            }
        });
    }
}
