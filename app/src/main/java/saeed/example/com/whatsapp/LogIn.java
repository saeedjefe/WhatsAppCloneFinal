package saeed.example.com.whatsapp;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class LogIn extends AppCompatActivity implements View.OnClickListener {

    Button logIn, signUp;
    EditText user, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_log_in );

        logIn = findViewById( R.id.logIn );
        signUp = findViewById( R.id.signUp );

        user = findViewById( R.id.user );
        pass = findViewById( R.id.pass );

        logIn.setOnClickListener( this  );
        signUp.setOnClickListener( this );


    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.signUp:

                final Intent intent = new Intent( LogIn.this, SignUp.class  );
                startActivity( intent );
                break;

            case R.id.logIn:

                ParseUser.logInInBackground( user.getText().toString(), pass.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if(e == null && user != null)
                        {
                            Toast.makeText( LogIn.this, "successfully logged in ", Toast.LENGTH_LONG ).show();
                            Intent intent1 = new Intent( LogIn.this, UsersList.class );
                            startActivity( intent1 );
                        }
                        else
                        {
                            Toast.makeText( LogIn.this, "failed ", Toast.LENGTH_LONG ).show();

                        }
                    }
                } );
                break;

        }
    }


}
