package khi.fast.gonets;
import android.content.Intent;
import android.hardware.camera2.params.Face;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInApi;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthCredential;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

import static android.R.attr.data;

public class MainActivity extends AppCompatActivity {
    private static final int RC_SIGN_IN =1 ;
    // The ListView

    ImageView facebook;
    ImageView google;
    private FirebaseDatabase mFirebaseDatabase;

    private DatabaseReference mMessageDatabaseReference;

    private ChildEventListener mChildEventListener;

    LoginButton loginButton;
    CallbackManager callbackManager;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListner;

    GoogleSignInClient mGoogleSignInClient;

    String email1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        setContentView(R.layout.activity_main);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        // textHide=(TextView)findViewById(R.id.textHide);
        mMessageDatabaseReference = mFirebaseDatabase.getReference().child("LoginEmails");

        // Configure sign-in to request the user's ID, email address, and basic
// profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        //updateUI(account);
        SignInButton signInButton =(SignInButton)findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_STANDARD);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    switch (view.getId()) {
                        case R.id.sign_in_button:
                            signIn();
                            break;
                        // ...
                    }

            }
        });

















    //updateUI(account);
        mAuth = FirebaseAuth.getInstance();
        System.out.println("hi");
        // Initialize Facebook Login button
        callbackManager = CallbackManager.Factory.create();

        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {

                        // App code
                    }

                    @Override
                    public void onCancel() {
                        // App code
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                    }
                });

        loginButton = (LoginButton)findViewById(R.id.button_facebook_login);
        loginButton.setReadPermissions("email", "public_profile");
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {


            @Override
            public void onSuccess(final LoginResult loginResult) {
                System.out.println("MainActivity"+loginResult);
               // handleFacebookAccessToken(loginResult.getAccessToken());
                GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {

                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        Log.i("LoginActivity", response.toString());
                        
                        // Get facebook data from login
                        Bundle bFacebookData = getFacebookData(object);
                        final String email=bFacebookData.getString("email");
                        final String Name=bFacebookData.getString("first_name")+" " +bFacebookData.getString("last_name");
                        System.out.println("Name: "+Name);

                        Query mHouseDatabaseReference3 =mFirebaseDatabase.getReference().child("LoginEmails");

                        mHouseDatabaseReference3.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                if (dataSnapshot.exists()) {
                                    int count1=0;
                                    for (DataSnapshot issue : dataSnapshot.getChildren()) {
                                        // do something with the individual "issues"
                                        System.out.println(issue.child("email").getValue().equals(email));
                                        if(issue.child("email").getValue().equals(email)) {
                                            System.out.println("YES");
                                            count1=0;
                                            break;
                                        }
                                        else{
                                            count1++;
                                            System.out.println("No");
                                        }

                                    }
                                    if(count1!=0){


                                        Email emaill = new Email(email);
                                        // Clear input box
                                        mMessageDatabaseReference.push().setValue(emaill);
                                        handleFacebookAccessToken(loginResult.getAccessToken(),email,1);
                                    }
                                    else {




                                        handleFacebookAccessToken(loginResult.getAccessToken(),email,2);

                                    }


                                }
                            }


                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });









                    }
                });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id, first_name, last_name, email,gender, birthday, location"); // Parámetros que pedimos a facebook
                request.setParameters(parameters);
                request.executeAsync();

            }

            @Override
            public void onCancel() {
                System.out.println("facebook:onCancel");
                // ...
            }

            @Override
            public void onError(FacebookException error) {
                System.out.println("facebook:onError"+ error);
                // ...
            }
        });
      // ...

        mAuthStateListner = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                onSignedInInitialize("");


                if (user != null) {
                    //user is signed in
                    onSignedInInitialize(user.getDisplayName());
                } else {
                    onSignedOutInitialize();
                    //user is signed out
                    //  onSignedOutInitialize();
                    /*startActivityForResult(
                            AuthUI.getInstance()
                                    .createSignInIntentBuilder()
                                    .setIsSmartLockEnabled(false)
                                    .setTheme(R.style.FirebaseLoginTheme)
                                    .setLogo(R.drawable.wb5)
                                    .setProviders(
                                            AuthUI.EMAIL_PROVIDER,
                                            AuthUI.GOOGLE_PROVIDER
                                    ).build(),
                            RC_SIGN_IN);
                }
                */
                }
                ;
            }

            ;

        };





       // facebook=(ImageView)findViewById(R.id.facebook);

        // Find the list view
    }

    private Bundle getFacebookData(JSONObject object) {


        try {
            Bundle bundle = new Bundle();
            String id = object.getString("id");



            try {
                URL profile_pic = new URL("https://graph.facebook.com/" + id + "/picture?width=200&height=150");
                Log.i("profile_pic", profile_pic + "");
                bundle.putString("profile_pic", profile_pic.toString());

            } catch (MalformedURLException e) {
                e.printStackTrace();
                return null;
            }


            System.out.println("Im here now"+object.has("first_name"));
            bundle.putString("idFacebook", id);
            if (object.has("first_name"))
                bundle.putString("first_name", object.getString("first_name"));
            if (object.has("last_name"))
                bundle.putString("last_name", object.getString("last_name"));
            if (object.has("email")) {
                bundle.putString("email", object.getString("email"));

            }
            if (object.has("gender"))
                bundle.putString("gender", object.getString("gender"));
            if (object.has("birthday"))
                bundle.putString("birthday", object.getString("birthday"));
            if (object.has("location"))
                bundle.putString("location", object.getJSONObject("location").getString("name"));

            return bundle;
        }
        catch(JSONException e) {
            System.out.println("Error parsing JSON");
        }
        return null;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                final GoogleSignInAccount account = task.getResult(ApiException.class);

                Query mHouseDatabaseReference3 =mFirebaseDatabase.getReference().child("LoginEmails");

                mHouseDatabaseReference3.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            int count1=0;
                            for (DataSnapshot issue : dataSnapshot.getChildren()) {
                                // do something with the individual "issues"
                                System.out.println(issue.child("email").getValue().equals(account.getEmail()));
                                if(issue.child("email").getValue().equals(account.getEmail())) {
                                    System.out.println("YES");
                                    count1=0;
                                    break;
                                }
                                else{
                                    count1++;
                                    System.out.println("No");
                                }

                            }
                            if(count1!=0){


                                Email emaill = new Email(account.getEmail());
                                // Clear input box
                                mMessageDatabaseReference.push().setValue(emaill);
                                firebaseAuthWithGoogle(account,1);
                            }
                            else {




                                firebaseAuthWithGoogle(account,2);

                            }


                        }
                    }


                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

















                System.out.println("Hamza email"+account.getEmail());
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                System.out.println("Google sign in failed"+ e);
                // ...
            }
        }
        else{
            callbackManager.onActivityResult(requestCode, resultCode, data);

        }
    }

    @Override
    public void onStart() {
        super.onStart();
        final FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser!=null){


            AccessToken accessToken=AccessToken.getCurrentAccessToken();
        GraphRequest request = GraphRequest.newMeRequest(
                accessToken,
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(
                            JSONObject object,
                            GraphResponse response) {

                        System.out.println("Hamza onStart");
                        Bundle bFacebookData = getFacebookData(object);
                        email1=bFacebookData.getString("email");
                     //   System.out.println("Hamza"+bFacebookData.getString("email"));
                        Intent i = new Intent(MainActivity.this,NetSessionsActivity.class);
                        i.putExtra("NAME", currentUser.getDisplayName());
                        System.out.println("=>Hamza"+currentUser.getEmail());
                        System.out.println("email ID: "+email1);
                        i.putExtra("ID", email1);
                        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        i.putExtra("Activity","MainActivityFacebook");
                        startActivity(i);
                        finish();
                        // Application code
                    }
                });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id, first_name, last_name, email,gender, birthday, location"); // Parámetros que pedimos a facebook

        request.setParameters(parameters);
        request.executeAsync();


        // Check if user is signed in (non-null) and update UI accordingly.

        }

     //   updateUI(currentUser);
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if(currentUser!=null){
            Intent i = new Intent(MainActivity.this,NetSessionsActivity.class);
            i.putExtra("NAME", currentUser.getDisplayName());
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.putExtra("Activity","MainActivity");
            i.putExtra("ID", currentUser.getEmail());
            startActivity(i);

            finish();
        }
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }


    private void handleFacebookAccessToken(final AccessToken token, final String email, final int flag) {
        System.out.println("handleFacebookAccessToken:" + token);

        final AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            System.out.println("signInWithCredential:success");
                         //   System.out.println(token.getUserId());
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            if (user != null) {
                                // User is signed in
                                if(flag==1) {
                                    Intent i = new Intent(MainActivity.this, GettingStartedActivity.class);
                                    i.putExtra("NAME", user.getDisplayName());
                                    i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    System.out.println("ID" + email);
                                    i.putExtra("ID", email);

                                    startActivity(i);
                                    finish();
                                }
                                else{
                                    Intent i = new Intent(MainActivity.this, NetSessionsActivity.class);
                                    i.putExtra("NAME", user.getDisplayName());

                                    System.out.println("Hamza handleFacebookAccessToken");
                                    System.out.println("USERRRRRRRRRR"+user.getEmail());
                                    i.putExtra("Activity","MainActivityFacebook");
                                    i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    System.out.println("ID" + email);
                                    i.putExtra("ID", email);
                                    startActivity(i);
                                    finish();
                                }

                            } else {
                                // No user is signed in
                            }
                         //   updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            System.out.println("signInWithCredential:failure"+ task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                          //  updateUI(null);
                        }

                        // ...
                    }
                });

        System.out.println("HII");

    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct, final int flag) {
        System.out.println("firebaseAuthWithGoogle:" + acct.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {


                            if(flag==1) {
                                // Sign in success, update UI with the signed-in user's information
                                System.out.println("signInWithCredential:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                Intent i = new Intent(MainActivity.this, GettingStartedActivity.class);
                                i.putExtra("NAME", user.getDisplayName());
                                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                System.out.println("ID" + user.getEmail());
                                i.putExtra("ID", user.getEmail());
                                startActivity(i);
                                finish();
                            }
                            else{
                                System.out.println("signInWithCredential:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                Intent i = new Intent(MainActivity.this, NetSessionsActivity.class);
                                i.putExtra("NAME", user.getDisplayName());
                                i.putExtra("Activity","MainActivityGoogle");
                                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                System.out.println("USERRRRRRRRRR"+user.getDisplayName());
                                System.out.println("ID" + user.getEmail());
                                startActivity(i);
                                finish();
                            }
                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            System.out.println("signInWithCredential:failure"+ task.getException());
                            //Snackbar.make(findViewById(R.id.main_layout), "Authentication Failed.", Snackbar.LENGTH_SHORT).show();
                         //   updateUI(null);
                        }

                    }
                });
    }
    @Override
    protected void onPause(){
        super.onPause();
        if(mAuthStateListner!=null) {
            mAuth.removeAuthStateListener(mAuthStateListner);
        }
        detachDatabaseReadListener();
    }


    @Override
    protected void onResume(){
        super.onResume();
        mAuth.addAuthStateListener(mAuthStateListner);
    }

    private void  onSignedInInitialize(String username){
        attachDatabaseReadListener();

    }
    private void  onSignedOutInitialize(){

        detachDatabaseReadListener();
    }
    private void attachDatabaseReadListener(){
        if(mChildEventListener==null) {
            mChildEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                    //  textHide.setVisibility(View.GONE);

                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            };
            mMessageDatabaseReference.addChildEventListener(mChildEventListener);
            mMessageDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {

                        //  Log.d("mom ",""+mom.getPICTURE());

                    }






                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
    }
    private void detachDatabaseReadListener(){
        if(mChildEventListener!=null)
            mMessageDatabaseReference.removeEventListener(mChildEventListener);
        mChildEventListener=null;
    }


}