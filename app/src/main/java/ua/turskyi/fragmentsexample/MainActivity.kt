package ua.turskyi.fragmentsexample

import android.content.Intent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction

class MainActivity : AppCompatActivity(R.layout.activity_main), WorkoutListFragment.Listener {

    override fun itemClicked(id: Long) {
        /**
         * The magic is here: depending on phone orientation it will be displayed different layout,
         * because fragmentContainer does not exist in default orientation layout
         */
        val fragmentContainer: View? = findViewById(R.id.fragmentContainer)
        if (fragmentContainer != null) {
            val details = WorkoutDetailFragment()
            details.setWorkout(id)
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragmentContainer, details)
                addToBackStack(null)
                setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                commit()
            }
        } else {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_WORKOUT_ID, id.toInt())
            startActivity(intent)
        }
    }
}
