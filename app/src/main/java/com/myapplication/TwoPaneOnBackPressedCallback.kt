package com.myapplication

import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.core.view.doOnLayout
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
        // A call to onLayout is required to reset mFirstLayout to false, allowing closePane to function.
        // This is a consequence of the spl assuming that a layout pass always happens with an attachment.
        // When switching tabs, when we reattach there is no layout pass.
        slidingPaneLayout.requestLayout()

        // During rotations, isSlideable is not correct until we have laid out the view,
        // so we reset the back button behavior only after a layout pass.
        slidingPaneLayout.doOnLayout {
            isEnabled = slidingPaneLayout.isSlideable && slidingPaneLayout.isOpen
        }
    }

    fun onTabPaused(){
        // Disable intercepting the system back button when a tab is changed.
        // Each tab handles it's own back button.
        isEnabled = false
    }
}