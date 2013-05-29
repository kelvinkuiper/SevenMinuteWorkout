package nl.kelvinteractive.sevenminuteworkout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class WorkoutActivity extends Activity {

    int totalWorkouts;
    int cWorkoutIndex;
    int cTimer = 5;

    String cWorkout = null;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);

        Intent intent = getIntent();

        final TextView timeLabel = (TextView) findViewById(R.id.cTimer);
        final TextView workoutLabel = (TextView) findViewById(R.id.cWorkoutLabel);
        final ImageView workoutImage = (ImageView) findViewById(R.id.workoutImage);
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.workoutProgressBar);

        totalWorkouts = ((MyApp) WorkoutActivity.this.getApplication()).getWorkouts().length;
        cWorkoutIndex = intent.getIntExtra("workout_id", 0);
        cWorkout = ((MyApp) this.getApplication()).getWorkout(cWorkoutIndex);

        workoutLabel.setText(cWorkout);

        int resID = getResources().getIdentifier("w" + (cWorkoutIndex + 1), "drawable",  getPackageName());
        workoutImage.setImageResource(resID);

        final long millisDuration = cTimer * 1000;

        progressBar.setMax((int)millisDuration);

        new CountDownTimer(millisDuration, 1000) {
            public void onTick(long millisUntilFinished) {
                timeLabel.setText(Long.toString(millisUntilFinished / 1000));
                progressBar.setProgress((int)millisDuration - (int)millisUntilFinished);
            }
            public void onFinish() {
                Intent intent = null;

                int nextWorkout = cWorkoutIndex + 1;

                if(nextWorkout < totalWorkouts) {
                    intent = new Intent(WorkoutActivity.this, PreWorkoutActivity.class);
                    intent.putExtra("workout_id", nextWorkout);
                }
                else {
                    intent = new Intent(WorkoutActivity.this, EndActivity.class);
                }

                WorkoutActivity.this.startActivity(intent);
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