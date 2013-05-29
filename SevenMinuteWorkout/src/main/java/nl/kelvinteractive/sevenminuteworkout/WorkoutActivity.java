package nl.kelvinteractive.sevenminuteworkout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.ImageView;
import android.widget.TextView;

public class WorkoutActivity extends Activity {

    TextView timeLabel;
    TextView workoutLabel;
    ImageView workoutImage;

    int totalWorkouts;
    int cWorkoutIndex;
    int cTimer = 5;

    String cWorkout = null;

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
        int resID = getResources().getIdentifier("w" + (cWorkoutIndex + 1), "drawable",  getPackageName());
        workoutImage.setImageResource(resID);

        totalWorkouts = ((MyApp) WorkoutActivity.this.getApplication()).getWorkouts().length;

        new CountDownTimer(cTimer * 1000, 1000) {

            public void onTick(long millisUntilFinished) {
                timeLabel.setText(Long.toString(millisUntilFinished / 1000));
            }

            public void onFinish() {

                int nextWorkout = cWorkoutIndex + 1;

                /* start new activity with the current workout */
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