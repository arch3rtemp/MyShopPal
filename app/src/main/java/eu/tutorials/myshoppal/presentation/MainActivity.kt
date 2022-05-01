package eu.tutorials.myshoppal.presentation

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import dagger.hilt.android.AndroidEntryPoint
import eu.tutorials.myshoppal.R
import eu.tutorials.myshoppal.presentation.register.RegisterFragment

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private lateinit var navController: NavController
    private var backPressedTimestamp: Long = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_container_view) as NavHostFragment
        navController = navHostFragment.navController
        navController.navigateUp()
    }

    fun doubleBackToExit() {
        onBackPressedDispatcher.addCallback(this) {
            if (backPressedTimestamp + TIME_INTERVAL > System.currentTimeMillis()) {
                navController.popBackStack()
            } else {
                Toast.makeText(
                    this@MainActivity,
                    "Press back again to exit",
                    Toast.LENGTH_SHORT
                ).show()
            }
            backPressedTimestamp = System.currentTimeMillis()
        }
    }

    companion object {
        private const val TIME_INTERVAL = 2000
    }
}