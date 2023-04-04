package sv.edu.udb.desafiopractico2dsm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth

lateinit var email : EditText
lateinit var password : EditText
lateinit var registrar : Button
lateinit var login : Button
lateinit var labelemail : TextView
lateinit var labelpass : TextView

class AuthenticationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authentication)

        email = findViewById(R.id.txtEmail)
        password = findViewById(R.id.txtPassword)
        registrar = findViewById(R.id.btnSingUp)
        login = findViewById(R.id.btnLogin)
        labelemail = findViewById(R.id.lblCorreoVacio)
        labelpass = findViewById(R.id.lblPasswordVacia)

        configs()
    }

    private fun configs() {
        title = "Login"
        val btnSingUp =
        registrar.setOnClickListener{
            if(email.text.isNotEmpty()&& password.text.isNotEmpty()){
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(email.text.toString(),password.text.toString()).addOnCompleteListener{
                    if(it.isSuccessful){
                        loginAccedido(it.result?.user?.email ?: "", ProviderType.BASIC)
                    }
                    else{
                        mostrarAlerta()
                    }
                }
            }
            else{
                if(email.text.isEmpty()){
                    labelemail.text =  "Campo de Correo no puede estar vacio"
                }
                else if(password.text.isEmpty()){
                    labelpass.text = "Campo de Contraseña no puede estar vacio"
                }
                else{
                    labelemail.text = ""
                    labelpass.text = ""
                }
            }
        }

        login.setOnClickListener{
            if(email.text.isNotEmpty()&& password.text.isNotEmpty()){
                FirebaseAuth.getInstance().signInWithEmailAndPassword(email.text.toString(),password.text.toString()).addOnCompleteListener{
                    if(it.isSuccessful){
                        loginAccedido(it.result?.user?.email ?: "", ProviderType.BASIC)
                    }
                    else{
                        mostrarAlerta()
                    }
                }
            }
            else{
                if(email.text.isEmpty()){
                    labelemail.text =  "Campo de Correo no puede estar vacio"
                }
                else if(password.text.isEmpty()){
                    labelpass.text = "Campo de Contraseña no puede estar vacio"
                }
                else{
                    labelemail.text = ""
                    labelpass.text = ""
                }
            }
        }
    }
    private fun mostrarAlerta(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error autenticando al usuario")
        builder.setPositiveButton("Aceptar",null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun loginAccedido(email: String, provider: ProviderType){
        val moviendoHome = Intent(this,HomeActivity::class.java).apply{
            putExtra("email",email)
            putExtra("provider", provider.name)
        }
        startActivity(moviendoHome)
    }
}