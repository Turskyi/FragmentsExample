package ua.turskyi.fragmentsexample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(R.layout.activity_main), WorkoutListFragment.Listener {

   override fun itemClicked(id: Long) {
       if (fragmentContainer != null){
           val details = WorkoutDetailFragment()
           val ft = supportFragmentManager.beginTransaction()
           details.setWorkout(id)
           ft.replace(R.id.fragmentContainer, details)
           ft.addToBackStack(null)
           ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
           ft.commit()
       } else {
           val intent = Intent(this, DetailActivity::class.java)
           intent.putExtra(DetailActivity.EXTRA_WORKOUT_ID, id.toInt())
           startActivity(intent)
       }
    }
}
