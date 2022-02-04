package com.wolt.restaurants.ui.ext

import android.view.View
import android.widget.ImageView
import androidx.test.annotation.UiThreadTest
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import junit.framework.TestCase
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ViewExtTest : TestCase() {

    @Test
    fun testSetVisible_true() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        val view = View(appContext)

        view.setVisible(true)

        assertEquals(view.visibility, View.VISIBLE)
    }

    @Test
    fun testSetVisible_false() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        val view = View(appContext)

        view.setVisible(false)

        assertEquals(view.visibility, View.GONE)
    }

    @Test
    @UiThreadTest
    fun testLoadImageFromUrl() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        val view = ImageView(appContext)

        view.loadImageFromUrl("https://wolt-frontpage-images.s3-eu-west-1.amazonaws.com/city-states/night_750x500px.png")

        assertNotNull(view.drawable)
    }

    @Test
    @UiThreadTest
    fun testLoadImageFromUrl_not() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        val view = ImageView(appContext)

        assertNull(view.drawable)
    }
}
