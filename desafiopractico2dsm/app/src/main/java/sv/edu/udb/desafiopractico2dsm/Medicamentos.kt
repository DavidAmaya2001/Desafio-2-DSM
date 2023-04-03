package sv.edu.udb.desafiopractico2dsm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import com.google.firebase.database.ktx.getValue

class Medicamentos : AppCompatActivity() {

    private lateinit var db : DatabaseReference
    private lateinit var medicRecyclerView: RecyclerView
    private lateinit var medicArrayList: ArrayList<Medicina>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_medicamentos)

        medicRecyclerView = findViewById(R.id.medicsList)
        medicRecyclerView.layoutManager = LinearLayoutManager(this)
        medicRecyclerView.setHasFixedSize(true)

        medicArrayList = arrayListOf<Medicina>()
        getMedicinaData()
    }

    private fun getMedicinaData(){
        db = FirebaseDatabase.getInstance().getReference("Medicamentos")

        db.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    for(medicineSnapshot in snapshot.children){
                        val takeMedicine = medicineSnapshot.getValue(Medicina::class.java)
                        medicArrayList.add(takeMedicine!!)
                    }
                    medicRecyclerView.adapter = MyAdapter(medicArrayList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}