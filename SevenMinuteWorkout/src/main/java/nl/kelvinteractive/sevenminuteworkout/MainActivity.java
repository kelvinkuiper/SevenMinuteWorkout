//TODO: Add strings.xml
//TODO: Add launcher icon
//TODO: Cleanup resources

package nl.kelvinteractive.sevenminuteworkout;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
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


    }
    
}