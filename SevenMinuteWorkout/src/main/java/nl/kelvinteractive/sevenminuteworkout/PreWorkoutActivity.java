package nl.kelvinteractive.sevenminuteworkout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

public class PreWorkoutActivity extends Activity {

    int cWorkoutIndex;
    int cTimer = 30;
    String cWorkout = null;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prepare_workout);

        final TextView timeLabel = (TextView) findViewById(R.id.pTimer);
        final TextView workoutLabel = (TextView) findViewById(R.id.nWorkoutLabel);
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.preProgressBar);

        Intent intent = getIntent();

        cWorkoutIndex = intent.getIntExtra("workout_id", 0);
        cWorkout = ((MyApp) this.getApplication()).getWorkout(cWorkoutIndex);

        workoutLabel.setText(cWorkout);
        timeLabel.setText(Integer.toString(cTimer));

        final long millisDuration = cTimer * 1000;
        progressBar.setMax((int)millisDuration);

        new CountDownTimer(millisDuration, 1000) {
            public void onTick(long millisUntilFinished) {
                timeLabel.setText(Long.toString(millisUntilFinished / 1000));
                progressBar.setProgress((int)millisDuration - (int)millisUntilFinished);
            }
            public void onFinish() {
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