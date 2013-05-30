//TODO: Add strings.xml
//TODO: Naming conventions
//TODO: Add launcher icon
//TODO: Cleanup resources
//TODO: Add settings
//TODO: update progressbar on final

/*
* Could-haves:
* - Navigate between workouts
* - A nice design / different workout images
* - Workout Reminder (show alarm / notification)
* - Implement different workouts
*/

package nl.kelvinteractive.sevenminuteworkout;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String aWorkouts[] = {"Jumping Jacks", "Wall sit", "Push-up", "Abdominal crunch", "Chair step", "Squat", "Chair push", "Plank", "Running, high knees", "Lunge", "Push-up and rotate", "Side plank"};

        //TODO: Add NullPointer check
        ((MyApp) this.getApplicationContext()).setWorkouts(aWorkouts);

        Button startButton = (Button) findViewById(R.id.startButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MainActivity.this, PreWorkoutActivity.class);
                MainActivity.this.startActivity(myIntent);
            }
        });

















        String namen[] = {"Kelvin", "Ruurd", "Eelco", "Jan Hendrik"};

        int aantal = namen.length;

        Log.i("Naam", namen[(int)Math.random() * aantal - 1]);
        Log.i("Naam", namen[(int)Math.random() * aantal - 1]);
        Log.i("Naam", namen[(int)Math.random() * aantal - 1]);
        Log.i("Naam", namen[(int)Math.random() * aantal - 1]);












    }
    
}
