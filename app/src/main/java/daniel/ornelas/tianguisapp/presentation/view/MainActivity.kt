package daniel.ornelas.tianguisapp.presentation.view

import android.app.DatePickerDialog
import android.app.Dialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.CheckBox
import android.widget.RadioButton
import android.widget.Toast
import androidx.annotation.RequiresApi
import daniel.ornelas.tianguisapp.R
import daniel.ornelas.tianguisapp.databinding.ActivityMainBinding
import daniel.ornelas.tianguisapp.util.Operacion
import java.text.SimpleDateFormat
import java.util.*

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
            mostrarConsultarComprasDialog()
        }

    }

    fun mostrarConsultarComprasDialog(){

        var dialog =  Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.consultar_ventas_dialog)
        val window = dialog.window
        window?.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)

        val checkBoxFechas = dialog.findViewById(R.id.radioBtn_fechas) as RadioButton
        val btnFechaDesde = dialog.findViewById(R.id.btn_fecha_desde) as Button
        val btnFechaHasta = dialog.findViewById(R.id.btn_fecha_hasta) as Button
        val checkBoxTodas = dialog.findViewById(R.id.radioBtn_todas) as RadioButton
        val btnCancelar = dialog.findViewById(R.id.btn_cancelar_consulta) as Button
        val btnConsultar = dialog.findViewById(R.id.btn_consultar_compra)  as Button

        btnFechaDesde.setOnClickListener {
            val calendario = Calendar.getInstance()
            val dateSetListener = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->

                calendario.set(Calendar.YEAR, year)
                calendario.set(Calendar.MONTH, month)
                calendario.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                btnFechaDesde.text = SimpleDateFormat("yyyy-MM-dd").format(calendario.time)

            }

            DatePickerDialog(this, dateSetListener, calendario.get(Calendar.YEAR),
                calendario.get(Calendar.MONTH), calendario.get(Calendar.DAY_OF_MONTH)).show()

        }

        btnFechaHasta.setOnClickListener {
            val calendario = Calendar.getInstance()
            val dateSetListener = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->

                calendario.set(Calendar.YEAR, year)
                calendario.set(Calendar.MONTH, month)
                calendario.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                btnFechaHasta.text = SimpleDateFormat("yyyy-MM-dd").format(calendario.time)

            }

            DatePickerDialog(this, dateSetListener, calendario.get(Calendar.YEAR),
                    calendario.get(Calendar.MONTH), calendario.get(Calendar.DAY_OF_MONTH)).show()

        }

        btnCancelar.setOnClickListener {
            dialog.dismiss()
        }

        btnConsultar.setOnClickListener {

            val intent = Intent(this, ConsultarCompras::class.java)

            if (checkBoxFechas.isChecked){
                intent.putExtra("operacion", Operacion.FECHAS)
                intent.putExtra("fechaDesde", btnFechaDesde.text)
                intent.putExtra("fechaHasta", btnFechaHasta.text)
                startActivity(intent)

            } else if(checkBoxTodas.isChecked){
                intent.putExtra("operacion",  Operacion.TODAS)
                startActivity(intent)

            } else {
                Toast.makeText(this, "Debe seleccionar un metodo de consulta", Toast.LENGTH_SHORT).show()
            }
        }

        dialog.show()

    }
}