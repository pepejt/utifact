package ec.edu.producto

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.recyclerview.widget.RecyclerView
import ec.edu.producto.ui.theme.ProductoTheme
import android.content.Intent
import androidx.recyclerview.widget.LinearLayoutManager
import ec.edu.producto.objeto.Producto
import ec.edu.producto.objeto.ProductoAdapter

class MainActivity : ComponentActivity() {
        private val productos = mutableListOf(
            Producto(1, "Libros", 90.00),
            Producto(2, "Maletas", 7.50),
            Producto(3, "Lapices", 6.50),
            Producto(4, "Cartucheras", 5.25),
            Producto(5, "Cuadernos", 3.75)
        )

        private lateinit var recyclerView: RecyclerView

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)

            recyclerView = findViewById(R.id.recyclerView)
            recyclerView.layoutManager = LinearLayoutManager(this)
            recyclerView.adapter = ProductoAdapter(this, productos) { producto ->
                val intent = Intent(this, EditProductActivity::class.java).apply {
                    putExtra("Prod:cod", producto.id)
                    putExtra("Prod:nom", producto.nombre)
                    putExtra("Prod:pre", producto.precio)
                }
                startActivityForResult(intent, REQUEST_CODE_EDIT_PRODUCT)
            }
        }

        override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
            super.onActivityResult(requestCode, resultCode, data)
            if (requestCode == REQUEST_CODE_EDIT_PRODUCT && resultCode == Activity.RESULT_OK) {
                data?.let {
                    val codigo = it.getIntExtra("Prod:cod", -1)
                    val nombre = it.getStringExtra("Prod:nom") ?: ""
                    val precio = it.getDoubleExtra("Prod:pre", 0.0)


                    val producto = productos.find { it.id == codigo }
                    producto?.let {
                        it.nombre = nombre
                        it.precio = precio
                        recyclerView.adapter?.notifyDataSetChanged()
                    }
                }
            }
        }

        companion object {
            private const val REQUEST_CODE_EDIT_PRODUCT = 1
        }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ProductoTheme {
        Greeting("Android")
    }
}