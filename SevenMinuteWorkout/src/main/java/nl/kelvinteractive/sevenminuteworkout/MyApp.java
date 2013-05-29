package nl.kelvinteractive.sevenminuteworkout;

import android.app.Application;

public class MyApp extends Application {

    private String[] aWorkouts;

    public String getWorkout(int i) {
        return aWorkouts[i];
    }

    public String[] getWorkouts() {
        return aWorkouts;
    }

    public void setWorkouts(String[] workouts) {
        this.aWorkouts = workouts;
    }
}
