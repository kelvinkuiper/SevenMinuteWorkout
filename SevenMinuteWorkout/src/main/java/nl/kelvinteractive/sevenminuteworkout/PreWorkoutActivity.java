package nl.kelvinteractive.sevenminuteworkout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.widget.TextView;

/**
 * Created by kelvin on 23-05-13.
 */
public class PreWorkoutActivity extends Activity {

    TextView timeLabel;
    TextView workoutLabel;

    //workout_id
    int cWorkoutIndex;
    //waiting timer in seconds
    int cTimer = 3;
    //Store the current workout
    String cWorkout = null;

    private Handler handler = new Handler();

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prepare_workout);

        timeLabel = (TextView) findViewById(R.id.pTimer);
        workoutLabel = (TextView) findViewById(R.id.nWorkoutLabel);

        /* get data from intent */
        Intent intent = getIntent();
        cWorkoutIndex = intent.getIntExtra("workout_id", 0);
        cWorkout = ((MyApp) this.getApplication()).getWorkout(cWorkoutIndex);

        //set labels
        workoutLabel.setText(cWorkout);
        timeLabel.setText(Integer.toString(cTimer));

        new CountDownTimer(cTimer * 1000, 1000) {

            public void onTick(long millisUntilFinished) {
                timeLabel.setText(Long.toString(millisUntilFinished / 1000));
            }

            public void onFinish() {
                /* start new activity with the current workout */
                Intent intent = new Intent(PreWorkoutActivity.this, WorkoutActivity.class);
                intent.putExtra("workout_id", cWorkoutIndex);
                PreWorkoutActivity.this.startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                closeWorkout();
            }
        }.start();
    }

    @Override
    public void onPause() {
        super.onPause();
        closeWorkout();
    }

    public void closeWorkout() {
        finish();
    }
}