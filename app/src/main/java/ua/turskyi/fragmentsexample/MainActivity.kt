package ua.turskyi.fragmentsexample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(R.layout.activity_main), WorkoutListFragment.Listener {

   override fun itemClicked(id: Long) {
       fragmentContainer?.run {
           val details = WorkoutDetailFragment()
           details.setWorkout(id)
           supportFragmentManager.beginTransaction().apply {
               replace(R.id.fragmentContainer, details)
               addToBackStack(null)
               setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
               commit()
           }
       } ?: run {
           val intent = Intent(this, DetailActivity::class.java)
           intent.putExtra(DetailActivity.EXTRA_WORKOUT_ID, id.toInt())
           startActivity(intent)
       }
    }
}
