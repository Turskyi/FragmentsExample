package ua.turskyi.fragmentsexample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_workout_detail.*

/**
 * A simple [Fragment] subclass.
 */
class WorkoutDetailFragment : Fragment() {
    private var workoutId: Long = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        savedInstanceState?.run {
            workoutId = getLong("workoutId")
        }
        return inflater.inflate(R.layout.fragment_workout_detail, container, false)
    }

    override fun onStart() {
        super.onStart()
        view?.run {
            val workout = Workout.workouts[workoutId.toInt()]
            textTitle.text = workout.name
            textDescription.text = workout.description
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putLong("workoutId", workoutId)
    }

    fun setWorkout(id: Long) {
        workoutId = id
    }
}
