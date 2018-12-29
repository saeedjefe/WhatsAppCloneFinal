package saeed.example.com.whatsapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;

public class SignUp extends AppCompatActivity implements View.OnClickListener{

    EditText edtEmail, edtUser, edtPassword;
    Button btnSigUp, btnLogIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_sign_up );

        ParseInstallation.getCurrentInstallation().saveInBackground();

        edtEmail = findViewById( R.id.edtEmail );
        edtUser = findViewById( R.id.edtUser );
        edtPassword = findViewById( R.id.edtPassword );
        btnSigUp = findViewById( R.id.btnSigUp );
        btnLogIn = findViewById( R.id.btnLogIn );

        btnSigUp.setOnClickListener( this );
        btnLogIn.setOnClickListener( this );


    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.btnSigUp:

                final ProgressDialog progressDialog = new ProgressDialog( SignUp.this);
                progressDialog.setMessage( "signing up" );
                progressDialog.show();

                final ParseUser parseUser = new ParseUser();

                parseUser.setEmail( edtEmail.getText().toString() );
                parseUser.setUsername( edtUser.getText().toString() );
                parseUser.setPassword( edtPassword.getText().toString() );

                parseUser.signUpInBackground( new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if(e==null)
                        {
                            Toast.makeText( SignUp.this, "you are signed up", Toast.LENGTH_LONG ).show();
                        }
                        else {

                            Toast.makeText(     SignUp.this, "failed", Toast.LENGTH_LONG ).show();

                        }
                        progressDialog.dismiss();
                    }
                } );




                break;


            case R.id.btnLogIn:

                Intent intent = new Intent( SignUp.this, LogIn.class );
                 startActivity( intent );


        }

    }
}
