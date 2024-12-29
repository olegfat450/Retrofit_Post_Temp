package com.example.retrofit_post_temp
import com.squareup.picasso.Picasso
import android.annotation.SuppressLint
import android.graphics.BitmapFactory
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.net.toUri
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

import com.example.retrofit_post_temp.utils.Retrofininstance
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response
import retrofit2.http.HTTP
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private lateinit var button: Button
    private lateinit var progressbar: ProgressBar
    private lateinit var toolbar: Toolbar
    private lateinit var imageTv: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        progressbar = findViewById(R.id.progressBar)
        imageTv = findViewById(R.id.imageTv)

        button = findViewById(R.id.button)
        toolbar = findViewById(R.id.toolbar)

        setSupportActionBar(toolbar)
        title = "Случайные собаки"
        toolbar.setBackgroundColor(Color.GRAY)





       button.setOnClickListener { progressbar.visibility = View.VISIBLE; postRequest()  }

    }


    @SuppressLint("SetTextI18n", "SuspiciousIndentation")
    @OptIn(DelicateCoroutinesApi::class)
    private fun postRequest(){

        GlobalScope.launch(Dispatchers.IO) {

            var data: Response<ApiData>? = null

             data = try {

                Retrofininstance.api.getRandomDog()

            } catch (e: HttpException) {Toast.makeText(this@MainActivity,"http error ${e.message()}",Toast.LENGTH_LONG).show(); return@launch}
              catch (e: IOException)   {Toast.makeText(this@MainActivity,"app error ${e.message}",Toast.LENGTH_LONG).show(); return@launch}

            if (data != null) {

                withContext(Dispatchers.Main) {

                    Picasso.get().load(data.body()?.url).into(imageTv)


                }


            }
        }



    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_exit,menu)

        return super.onCreateOptionsMenu(menu) }

    @SuppressLint("SuspiciousIndentation")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val builder = AlertDialog.Builder(this)
          builder.setTitle("Выход из программы")
              .setNegativeButton("Нет") { _,_ -> }
              .setPositiveButton("да") { _,_ -> finishAffinity()}
        builder.create().show()

        return super.onOptionsItemSelected(item)


    }

}












