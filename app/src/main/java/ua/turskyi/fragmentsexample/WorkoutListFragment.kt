package ua.turskyi.fragmentsexample

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.ListFragment

/**
 * A simple [ListFragment] subclass.
 */
class WorkoutListFragment : ListFragment() {
    internal interface Listener {
        fun itemClicked(id: Long)
    }

    private var listener: Listener? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val names = arrayOfNulls<String>(Workout.workouts.size)
        for (i in names.indices) {
            names[i] = Workout.workouts[i].name
        }
        val adapter = ArrayAdapter(
            inflater.context,
            R.layout.simple_list_item, names
        )
        listAdapter = adapter
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as Listener?
    }

    override fun onListItemClick(
        listView: ListView,
        itemView: View,
        position: Int,
        id: Long
    ) {
        listener?.itemClicked(id)
    }
}
