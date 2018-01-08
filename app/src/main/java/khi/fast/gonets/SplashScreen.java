package khi.fast.gonets;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.ProgressBar;

/**
 * Created by Hamza Ahmed on 08-Jan-18.
 */
public class SplashScreen extends Activity {

    ProgressBar mprogressBar;
    ImageView img;

    ScaleAnimation makeBigger;

    ScaleAnimation makeSmaller;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);
        Animation anim1 = AnimationUtils.loadAnimation(this, R.anim.anim_down);
        img = (ImageView) findViewById(R.id.imageView);
        img.setAnimation(anim1);

        mprogressBar = (ProgressBar) findViewById(R.id.progressBar);
        ObjectAnimator anim = ObjectAnimator.ofInt(mprogressBar, "progress", 0, 100);
        anim.setDuration(4000);
        anim.setInterpolator(new DecelerateInterpolator());
        anim.start();
        makeBigger = new ScaleAnimation((float)1.0, (float)5.0, (float)1.0, (float)5.0, Animation.RELATIVE_TO_SELF, (float)0.5, Animation.RELATIVE_TO_SELF, (float)0.5);
        makeBigger.setAnimationListener(new MyAnimationListener());
        makeBigger.setFillAfter(true);
        makeBigger.setDuration(1000);
        makeSmaller = new ScaleAnimation((float)10.0, (float)1.0, (float)10.0, (float)1.0, Animation.RELATIVE_TO_SELF, (float)0.5, Animation.RELATIVE_TO_SELF, (float)0.5);
        makeSmaller.setAnimationListener(new MyAnimationListener());
        makeSmaller.setFillAfter(true);
        makeSmaller.setDuration(1000);

        final View view = findViewById(R.id.imageView);

        final Animation anim2 = AnimationUtils.loadAnimation(this, R.anim.scale);
        final Animation anim3 = AnimationUtils.loadAnimation(this, R.anim.scale2);

        Handler handler = new Handler();

        if (isFirstTime()) {

            new Handler().postDelayed(new Runnable() {
                public void run() {
                    view.startAnimation(anim2);


                    new Handler().postDelayed(new Runnable() {
                        public void run() {

                            view.startAnimation(anim3);
                            new Handler().postDelayed(new Runnable() {
                                public void run() {

                                    Intent i = new Intent(SplashScreen.this, MainActivity.class);
                                    startActivity(i);
                                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                                    finish();


                                }
                            }, 2200);


                        }
                    }, 1700);

                }
            }, 300);
        }else{
            new Handler().postDelayed(new Runnable() {
                public void run() {

                    Intent i = new Intent(SplashScreen.this, MainActivity.class);
                    startActivity(i);
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    finish();


                }
            }, 0);

        }


       /* new Handler().postDelayed(new Runnable() {
            public void run() {



            }
        }, 2200);*/

    }
    class MyAnimationListener implements Animation.AnimationListener {

        public void onAnimationEnd(Animation animation) {

            ScaleAnimation sa = (ScaleAnimation)animation;
            if (sa.equals(makeSmaller))
                img.startAnimation(makeBigger);
            else
                img.startAnimation(makeSmaller);
        }

        public void onAnimationRepeat(Animation animation) {
        }

        public void onAnimationStart(Animation animation) {
        }

    }
    private boolean isFirstTime()
    {
        SharedPreferences preferences = this.getPreferences(MODE_PRIVATE);
        boolean ranBefore = preferences.getBoolean("RanBefore", false);
        if (!ranBefore) {
            // first time
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("RanBefore", true);
            editor.commit();
        }
        return !ranBefore;
    }
}