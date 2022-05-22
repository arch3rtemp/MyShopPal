package eu.tutorials.myshoppal.presentation.main

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.annotation.IntegerRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import eu.tutorials.myshoppal.R
import eu.tutorials.myshoppal.databinding.ActivityMainBinding
import eu.tutorials.myshoppal.utils.Constants

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityMainBinding
    val binding: ActivityMainBinding get() = _binding

    private var backPressedTimestamp: Long = 0L
    private var backPressedCallback = initBackPressedCallback()

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_container_view) as NavHostFragment
        val navController = navHostFragment.navController

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.productsFragment,
                R.id.dashboardFragment,
                R.id.ordersFragment,
                R.id.soldFragment
            )
        )

        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            checkVisibility(listOf(
                R.id.profileFragment,
                R.id.settingsFragment,
                R.id.loginFragment,
                R.id.recoverFragment,
                R.id.registerFragment
            ), destination.id)

            checkDoubleTapExit(
                listOf(
                    R.id.loginFragment,
                    R.id.dashboardFragment
                ), destination.id
            )
        }

        setupActionBar(navController ,appBarConfiguration)
        setupBottomNavMenu(navController)
    }

    private fun setupActionBar(navController: NavController ,appBarConfiguration: AppBarConfiguration) {
        setSupportActionBar(binding.toolbarActivity)
        supportActionBar?.setBackgroundDrawable(
            ContextCompat.getDrawable(
                this@MainActivity,
                R.drawable.app_gradient_color_background
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    private fun setupBottomNavMenu(navController: NavController) {
        binding.navView.setupWithNavController(navController)
    }

    private fun checkVisibility(@IntegerRes destinationIds: List<Int>, destinationId: Int) {
        if (destinationIds.contains(destinationId)) {
            supportActionBar?.hide()
            binding.navView.visibility = View.GONE
        } else {
            supportActionBar?.show()
            binding.navView.visibility = View.VISIBLE
        }
    }

    private fun checkDoubleTapExit(@IntegerRes destinationIds: List<Int>, destinationId: Int) {
        backPressedCallback.isEnabled = destinationIds.contains(destinationId)
    }

    private fun initBackPressedCallback(): OnBackPressedCallback {
        return onBackPressedDispatcher.addCallback(this) {
            if (backPressedTimestamp + Constants.TIME_INTERVAL > System.currentTimeMillis()) {
                isEnabled = false
                onBackPressed()
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
}