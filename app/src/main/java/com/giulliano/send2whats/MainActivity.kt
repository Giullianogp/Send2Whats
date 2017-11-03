package com.giulliano.send2whats

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import android.content.Intent
import android.net.Uri
import android.widget.AdapterView
import android.widget.TextView
import android.widget.Spinner
import com.giulliano.send2whats.Adapters.SpinnerAdapter
import com.giulliano.send2whats.Class.ItemData


class MainActivity : AppCompatActivity() {

    private var url: String = "https://api.whatsapp.com/send?phone="

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val sp = findViewById<Spinner>(R.id.spinner_country)
        val adapter = SpinnerAdapter(this, R.layout.spinner_layout, R.id.txt, generateCountryList())
        sp.adapter = adapter

        fab.setOnClickListener { view ->
            val number = findViewById<TextView>(R.id.text_Number)

            if (number.text.isEmpty())
            {
                Snackbar.make(view, "Informe o número", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
            }
            else
            {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url + number.text))
                startActivity(browserIntent)
            }
        }
    }

    private fun generateCountryList() : ArrayList<ItemData>
    {
        val list = ArrayList<ItemData>()
        list.add(ItemData("BR", 55, R.mipmap.br))
        list.add(ItemData("US", 1, R.mipmap.us))
        list.add(ItemData("UK", 44, R.mipmap.uk))
        list.add(ItemData("AR", 54, R.mipmap.ar))
        list.add(ItemData("UY", 598, R.mipmap.uy))

        return list
    }


}
