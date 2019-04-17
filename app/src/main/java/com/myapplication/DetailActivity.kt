package com.myapplication

import android.os.Bundle
import android.os.Handler
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.navArgs

import kotlinx.android.synthetic.main.activity_launch.*

class DetailActivity : AppCompatActivity() {

    val args by navArgs<DetailActivityArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        //This is a programmatic nav fragment derived for portrait phones,
        //and requires manually setting the nav graph to forward the arguments
        val detail = supportFragmentManager.findFragmentById(R.id.detail_nav_fragment) as NavHostFragment?
        if(detail != null){
            val navController = detail.navController
            val navInflater = navController.navInflater
            val graph = navInflater.inflate(R.navigation.detail)

            detail.navController.setGraph(graph, args.toBundle())
        }
    }

}
