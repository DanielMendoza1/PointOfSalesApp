package daniel.ornelas.tianguisapp.presentation.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import daniel.ornelas.tianguisapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCompraMenu.setOnClickListener {
            val intent = Intent(this, RealizarCompra::class.java)
            startActivity(intent)
        }

        binding.bntConsultarCompra.setOnClickListener {
            val intent = Intent(this, ConsultarCompras::class.java)
            startActivity(intent)
        }


    }
}