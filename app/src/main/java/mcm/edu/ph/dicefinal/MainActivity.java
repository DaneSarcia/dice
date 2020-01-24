package mcm.edu.ph.dicefinal;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    public static final Random RANDOM = new Random();

    Button btnroll;
    ImageView diceleft, diceright;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnroll = findViewById(R.id.btnroll);
        diceleft = findViewById(R.id.diceleft);
        diceright = findViewById(R.id.diceright);

        btnroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Animation anim1 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.effect);
                final Animation anim2 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.effect);
                final Animation.AnimationListener animationListener = new Animation.AnimationListener(){
                    @Override
                    public void onAnimationStart(Animation animation){
                    }
                    @Override
                    public void onAnimationEnd(Animation animation){
                        int val = randomDiceValue();
                        int res = getResources().getIdentifier("di_" + val, "drawable", "mcm.edu.ph.dicefinal");
                        if (animation == anim1){
                            diceleft.setImageResource(res);
                        }else if (animation == anim2) {
                            diceright.setImageResource(res);
                        }
                    }

                    @Override
                    public void onAnimationRepeat (Animation animation){

                    }
                };

                anim1.setAnimationListener(animationListener);
                anim2.setAnimationListener(animationListener);

                diceleft.startAnimation(anim1);
                diceright.startAnimation(anim2);

                btnroll.setEnabled(false);

                Timer buttonTimer = new Timer();
                buttonTimer.schedule(new TimerTask() {

                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                btnroll.setEnabled(true);
                            }
                        });
                    }
                }, 3000);
            }
        });
    }

    public static int randomDiceValue(){
        return RANDOM.nextInt(6) + 1;
    }
}
