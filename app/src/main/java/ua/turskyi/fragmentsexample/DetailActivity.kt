package ua.turskyi.fragmentsexample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity(R.layout.activity_detail) {
    companion object {
        const val EXTRA_WORKOUT_ID = "id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(intent.extras?.get(EXTRA_WORKOUT_ID) is Int && detailFragment is WorkoutDetailFragment){
            val workoutId = intent.extras?.get(EXTRA_WORKOUT_ID) as Int
            (detailFragment as WorkoutDetailFragment).setWorkout(workoutId.toLong())
        }
    }
}
