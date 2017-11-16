package com.giulliano.send2whats
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import android.widget.TextView
import com.giulliano.send2whats.Adapters.SpinnerAdapter
import com.giulliano.send2whats.Class.ItemData
import kotlinx.android.synthetic.main.activity_main.*
import com.microsoft.appcenter.crashes.Crashes
import com.microsoft.appcenter.analytics.Analytics
import com.microsoft.appcenter.AppCenter



class MainActivity : AppCompatActivity() {

    private var url: String = "https://api.whatsapp.com/send?phone="
    private var lista: ArrayList<ItemData> = generateCountryList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AppCenter.start(application, "63d47ce6-117a-4d2d-aa30-b0f4a4926108",
                Analytics::class.java, Crashes::class.java)

        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val sp = findViewById<Spinner>(R.id.spinner_country)
        val adapter = SpinnerAdapter(this, R.layout.spinner_layout, R.id.txt, lista)
        sp.adapter = adapter


        sp.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>, view: View, position: Int, l: Long) {
                var valor = lista[position]
                val code = findViewById<TextView>(R.id.txt_country)
                code.text = valor.code
            }

            override fun onNothingSelected(adapterView: AdapterView<*>) {
                return
            }
        }

        fab.setOnClickListener { view ->
            val number = findViewById<TextView>(R.id.text_Number)
            val code = findViewById<TextView>(R.id.txt_country)

            when {
                code.text.isEmpty() -> Snackbar.make(view, R.string.codeError, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
                number.text.isEmpty() -> Snackbar.make(view, R.string.numberError, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
                else -> {
                    Analytics.trackEvent("Nova Conversa");
                    val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url + code.text + number.text))
                    startActivity(browserIntent)
                }
            }
        }

        val txtNumber = findViewById<TextView>(R.id.text_Number)
        txtNumber.requestFocus()
    }

    private fun generateCountryList() : ArrayList<ItemData>
    {
        val list = ArrayList<ItemData>()
        list.add(ItemData("BR", "55", R.mipmap.br))
        list.add(ItemData("US", "1", R.mipmap.us))
        list.add(ItemData("UK", "44", R.mipmap.uk))
        list.add(ItemData("AR", "54", R.mipmap.ar))
        list.add(ItemData("UY", "598", R.mipmap.uy))

        return list
    }


}
