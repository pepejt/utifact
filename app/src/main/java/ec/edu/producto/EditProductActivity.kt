package ec.edu.producto

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity

class EditProductActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_product)

        val editCodigo = findViewById<EditText>(R.id.edit_codigo)
        val editNombre = findViewById<EditText>(R.id.edit_nombre)
        val editPrecio = findViewById<EditText>(R.id.edit_precio)
        val btnGuardar = findViewById<Button>(R.id.btn_guardar)


        val codigo = intent.getIntExtra("Prod:cod", -1)
        val nombre = intent.getStringExtra("Prod:nom") ?: ""
        val precio = intent.getDoubleExtra("Prod:pre", 0.0)

        editCodigo.setText(codigo.toString())
        editNombre.setText(nombre)
        editPrecio.setText(precio.toString())

        btnGuardar.setOnClickListener {
            val nuevoCodigo = editCodigo.text.toString().toIntOrNull() ?: codigo
            val nuevoNombre = editNombre.text.toString()
            val nuevoPrecio = editPrecio.text.toString().toDoubleOrNull() ?: precio


            val resultIntent = Intent().apply {
                putExtra("Prod:cod", nuevoCodigo)
                putExtra("Prod:nom", nuevoNombre)
                putExtra("Prod:pre", nuevoPrecio)
            }
            setResult(Activity.RESULT_OK, resultIntent)

            Toast.makeText(this, "Producto Guardado:\nCódigo: $nuevoCodigo\nNombre: $nuevoNombre\nPrecio: $nuevoPrecio", Toast.LENGTH_LONG).show()


            finish()
        }
    }
}
