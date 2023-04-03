package sv.edu.udb.desafiopractico2dsm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth

lateinit var emailShow : TextView
lateinit var providerShow : TextView
lateinit var logOut : Button
lateinit var medicShow : Button
lateinit var orderShow : Button

enum class ProviderType{
    BASIC
}
class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        emailShow = findViewById(R.id.lblEmail)
        providerShow = findViewById(R.id.lblProvider)
        medicShow = findViewById(R.id.btnMedicamentos)
        orderShow = findViewById(R.id.btnOrden)
        logOut = findViewById(R.id.btnLogOut)

        val bundle = intent.extras
        val email = bundle?.getString("email")
        val provider = bundle?.getString("provider")

        configs(email ?: "", provider ?: "")
    }

    private fun configs(email: String, provider: String){
        title = "Menu Principal"
        emailShow.text = email
        providerShow.text = provider

        medicShow.setOnClickListener{
            val moviendoMedicamentos = Intent(this,Medicamentos::class.java)
            startActivity(moviendoMedicamentos)
        }

        orderShow.setOnClickListener{
            val moviendoseOrden = Intent(this,Compras::class.java)
            startActivity(moviendoseOrden)
        }

        logOut.setOnClickListener{
            FirebaseAuth.getInstance().signOut()
            onBackPressed()
        }

    }
}