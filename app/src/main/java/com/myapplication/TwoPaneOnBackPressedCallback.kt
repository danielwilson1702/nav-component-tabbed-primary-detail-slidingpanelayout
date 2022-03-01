package com.myapplication

import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.slidingpanelayout.widget.SlidingPaneLayout

class TwoPaneOnBackPressedCallback(
    private val slidingPaneLayout: SlidingPaneLayout
) : OnBackPressedCallback(
    // Set the default 'enabled' state to true only if it is slidable (i.e., the panes
    // are overlapping) and open (i.e., the detail pane is visible).
    slidingPaneLayout.isSlideable && slidingPaneLayout.isOpen
), SlidingPaneLayout.PanelSlideListener {

    init {
        slidingPaneLayout.addPanelSlideListener(this)
    }

    override fun handleOnBackPressed() {
        // Return to the list pane when the system back button is pressed.
        slidingPaneLayout.closePane()
    }

    override fun onPanelSlide(panel: View, slideOffset: Float) { }

    override fun onPanelOpened(panel: View) {
        // Intercept the system back button when the detail pane becomes visible.
        isEnabled = true
    }

    override fun onPanelClosed(panel: View) {
        // Disable intercepting the system back button when the user returns to the
        // list pane.
        isEnabled = false
    }

    fun onTabResumed(){
        // Reset pane state when we change tabs.
        // A call to onLayout is required to reset mFirstLayout to false, allowing closePane to function.
        isEnabled = slidingPaneLayout.isSlideable && slidingPaneLayout.isOpen
        slidingPaneLayout.requestLayout()
    }

    fun onTabPaused(){
        isEnabled = false
    }
}