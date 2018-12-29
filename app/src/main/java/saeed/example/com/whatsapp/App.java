package saeed.example.com.whatsapp;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize( new Parse.Configuration.Builder( this )
                .applicationId("kfKzK1tQOH8MeFa18ySLWPt1AdZBXFnJ6XNTj8xB")
                // if defined
                .clientKey("QUYyH3pETMr2n6V0YdJJ4kVjHtZFjNAQ64Xtak5G")
                .server("https://parseapi.back4app.com/")

                .build()
        );

    }
}
