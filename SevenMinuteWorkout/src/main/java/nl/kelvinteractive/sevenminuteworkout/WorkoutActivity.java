package nl.kelvinteractive.sevenminuteworkout;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by kelvin on 27-05-13.
 */
public class WorkoutActivity extends Activity {

    TextView timeLabel;
    TextView workoutLabel;
    ImageView workoutImage;

    int cWorkoutIndex;
    int cTimer = 5;

    String cWorkout = null;

    private Handler handler = new Handler();

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);

        timeLabel = (TextView) findViewById(R.id.cTimer);
        workoutLabel = (TextView) findViewById(R.id.cWorkoutLabel);
        workoutImage = (ImageView) findViewById(R.id.workoutImage);

        Intent intent = getIntent();
        /* get data from intent (workout_id) */
        cWorkoutIndex = intent.getIntExtra("workout_id", 0);
        /* set workout as current */
        cWorkout = ((MyApp) this.getApplication()).getWorkout(cWorkoutIndex);

        /* set workout title */
        workoutLabel.setText(cWorkout);

        /* set workout image */
        String icon = "w" + (cWorkoutIndex + 1);
        int resID = getResources().getIdentifier("w" + (cWorkoutIndex + 1), "drawable",  getPackageName());
        workoutImage.setImageResource(resID);

        /* start the timer */
        handler.postDelayed(updateTimerTask, 1000);
    }

    private Runnable updateTimerTask;
    {
        updateTimerTask = new Runnable() {

            public void run() {

                cTimer--;
                timeLabel.setText(Integer.toString(cTimer));

                if (cTimer > 0) {
                    handler.postDelayed(this, 1000);
                } else {
                    /* start new activity with the current workout */
                    int nextWorkout = cWorkoutIndex + 1;
                    int totalWorkouts = ((MyApp) WorkoutActivity.this.getApplication()).getWorkouts().length;

                    Log.i("NextWorkout", Integer.toString(nextWorkout));
                    Log.i("TotalWorkouts", Integer.toString(totalWorkouts));

                    if(nextWorkout == totalWorkouts){
                        Intent intent = new Intent(WorkoutActivity.this, EndActivity.class);
                        WorkoutActivity.this.startActivity(intent);
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        closeWorkout();
                    }
                    else {
                        Intent intent = new Intent(WorkoutActivity.this, PreWorkoutActivity.class);
                        intent.putExtra("workout_id", nextWorkout);
                        WorkoutActivity.this.startActivity(intent);
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        closeWorkout();
                    }


                }

            }
        };
    }

    @Override
    public void onPause() {
        super.onPause();
        closeWorkout();
    }

    public void closeWorkout() {
        handler.removeCallbacks(updateTimerTask);
        finish();
    }
}