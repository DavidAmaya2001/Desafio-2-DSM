package sv.edu.udb.desafiopractico2dsm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth

lateinit var emailShow : TextView
lateinit var providerShow : TextView
lateinit var logOut : Button

enum class ProviderType{
    BASIC
}
class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        emailShow = findViewById(R.id.lblEmail)
        providerShow = findViewById(R.id.lblProvider)
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

        logOut.setOnClickListener{
            FirebaseAuth.getInstance().signOut()
            onBackPressed()
        }

    }
}