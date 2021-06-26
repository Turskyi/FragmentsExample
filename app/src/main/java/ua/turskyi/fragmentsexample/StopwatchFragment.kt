package ua.turskyi.fragmentsexample

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_stopwatch.*
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
class StopwatchFragment : Fragment(), View.OnClickListener {
    //Number of milliseconds displayed on the stopwatch.
    private var milliseconds: Int = 0
    //    private int seconds = 0;

    /* Is the stopwatch running? */
    private var running = false
    private var wasRunning = false
    private val uiHandler = Handler(Looper.getMainLooper())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        savedInstanceState?.run {
            milliseconds = getInt("milliseconds")
            //            seconds = getInt("seconds");
            running = getBoolean("running")
            wasRunning = getBoolean("wasRunning")
        }
    }

    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        super.onSaveInstanceState(savedInstanceState)
        savedInstanceState.putInt("seconds", milliseconds)
        //        savedInstanceState.putInt("seconds", seconds);
        savedInstanceState.putBoolean("running", running)
        savedInstanceState.putBoolean("wasRunning", wasRunning)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_stopwatch, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        runTimer()
        //         runTimer(layout);
        startButton.setOnClickListener(this)
        stopButton.setOnClickListener(this)
        resetButton.setOnClickListener(this)
    }

    /**
     * Sets the number of milliseconds on the timer.
     */
    private fun runTimer() {
        val timer = Timer()
        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                if (running) {
                    milliseconds++
                    showInfo()
                }
            }
        }, 0, 10)
    }

    private fun showInfo() {
        uiHandler.post {
            val hours = milliseconds / 100 / 3600
            val minutes = milliseconds / 100 % 3600 / 60
            val secs = milliseconds / 100 % 60
            val millis = milliseconds % 100
            val time = String.format(
                Locale.getDefault(),
                "%d:%02d:%02d" +
                        ":%02d" +
                        "", hours, minutes, secs, millis
            )
            timeView?.text = time
        }
    }

    override fun onPause() {
        super.onPause()
        wasRunning = running
        running = false
    }

    override fun onResume() {
        super.onResume()
        if (wasRunning) running = true
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.startButton -> onClickStart()
            R.id.stopButton -> onClickStop()
            R.id.resetButton -> onClickReset()
        }
    }

    /* Start the stopwatch running when the Start button is clicked. */
    private fun onClickStart() {
        running = true
    }

    /* Stop the stopwatch running when the Stop button is clicked. */
    private fun onClickStop() {
        running = false
    }

    /* Reset the stopwatch when the Reset button is clicked. */
    private fun onClickReset() {
        running = false
        milliseconds = 0
        //        seconds = 0;
    }

//    private fun runTimer(view: View) {
//        val timeView = view.findViewById<View>(R.id.timeView) as TextView
//        val handler = Handler()
//        handler.post(object : Runnable {
//            override fun run() {
//                val hours = seconds / 3600
//                val minutes = seconds % 3600 / 60
//                val secs = seconds % 60
//                val time = String.format(
//                    Locale.getDefault(),
//                    "%d:%02d:%02d", hours, minutes, secs
//                )
//                timeView.text = time
//                if (running) {
//                    seconds++
//                }
//                handler.postDelayed(this, 1000)
//            }
//        })
//    }
}
