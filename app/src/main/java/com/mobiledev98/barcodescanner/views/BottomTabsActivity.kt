package com.mobiledev98.barcodescanner.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationBarView
import com.mobiledev98.barcodescanner.R
import com.mobiledev98.barcodescanner.databinding.ActivityBottomTabsBinding
import com.mobiledev98.barcodescanner.views.create.CreateBarcodeFragment
import com.mobiledev98.barcodescanner.views.history.HistoryBarcodeFragment
import com.mobiledev98.barcodescanner.views.scan.ScanBarcodeFragment
import com.mobiledev98.barcodescanner.views.settings.SettingsFragment

class BottomTabsActivity : AppCompatActivity(), NavigationBarView.OnItemSelectedListener {
    private lateinit var binding: ActivityBottomTabsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBottomTabsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        initBottomNavigationView()

        if (savedInstanceState == null) {
            showFragment(R.id.item_scan)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        if (item.itemId == binding.bottomNavigationView.selectedItemId) {
            return false
        }
        showFragment(item.itemId)
        return true
    }

    private fun initBottomNavigationView() {
        binding.bottomNavigationView.apply {
            setOnItemSelectedListener(this@BottomTabsActivity)
        }
    }

    private fun showFragment(bottomItemId: Int) {
        val fragment = when (bottomItemId) {
            R.id.item_scan -> ScanBarcodeFragment()
            R.id.item_create -> CreateBarcodeFragment()
            R.id.item_history -> HistoryBarcodeFragment()
            R.id.item_settings -> SettingsFragment()
            else -> null
        }
        fragment?.apply(::replaceFragment)
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.layout_fragment_container, fragment)
            .setReorderingAllowed(true)
            .commit()
    }
}