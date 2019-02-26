package saeed.example.com.whatsapp;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.LogOutCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class UsersList extends AppCompatActivity  implements AdapterView.OnItemClickListener {

    ListView lstView;
    ArrayList<String> arrayList;
    ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_users_list );


        //instantiate and initialize
        lstView = findViewById( R.id.lstView );
        arrayList = new ArrayList<>();
        arrayAdapter = new ArrayAdapter( UsersList.this, android.R.layout.simple_list_item_1, arrayList );
        final SwipeRefreshLayout swipeRefresh = findViewById( R.id.swipeRefresh );

        lstView.setOnItemClickListener( this );


        ParseQuery<ParseUser> parseQuery = ParseUser.getQuery();
        parseQuery.whereNotEqualTo( "username", ParseUser.getCurrentUser().getUsername() );
        parseQuery.findInBackground( new FindCallback<ParseUser>() {
            @Override
            public void done(List<ParseUser> objects, ParseException e) {
                if (e == null && objects.size() > 0) {
                    for (ParseUser usernames : objects) {
                        arrayList.add( usernames.getUsername() );
                    }
                    lstView.setAdapter( arrayAdapter );

                }
            }
        } );


        swipeRefresh.setOnRefreshListener( new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                ParseQuery<ParseUser> parseQuery = ParseUser.getQuery();
                parseQuery.whereNotEqualTo( "username", ParseUser.getCurrentUser().getUsername() );
                parseQuery.whereNotContainedIn( "username", arrayList );
                parseQuery.findInBackground( new FindCallback<ParseUser>() {
                    @Override
                    public void done(List<ParseUser> objects, ParseException e) {
                        if (e == null && objects.size() > 0) {
                            for (ParseUser usernames : objects) {
                                arrayList.add( usernames.getUsername() );
                            }
                            lstView.setAdapter( arrayAdapter );


                            arrayAdapter.notifyDataSetChanged();

                            if (swipeRefresh.isRefreshing()) {
                                swipeRefresh.setRefreshing( false );
                            }
                        }
                        else
                        {
                            if (swipeRefresh.isRefreshing())
                            {
                                swipeRefresh.setRefreshing( false );
                            }
                        }
                    }
                } );


            }
        } );

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate( R.menu.userslist_menu, menu );

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.usersTabMenu :

//                Toast.makeText( UsersList.this, "working", Toast.LENGTH_LONG ).show();

                ParseUser.logOutInBackground( new LogOutCallback() {
                    @Override
                    public void done(ParseException e) {
                        if(e == null)
                        {
                            finish();
                        }
                        else
                        {
                            Toast.makeText( UsersList.this,"failed",Toast.LENGTH_LONG ).show();
                        }
                    }
                } );



                break;
        }

        return true;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Intent intent = new Intent( UsersList.this, Chatting.class);
        intent.putExtra("username", arrayList.get( position ) );
        startActivity( intent );



    }
}
