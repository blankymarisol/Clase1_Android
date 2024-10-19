package gt.edu.umg.p2c1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;

import gt.edu.umg.p2c1.Adaptadores.ListaContactosAdapter;
import gt.edu.umg.p2c1.BaseDatos.DbContactos;
import gt.edu.umg.p2c1.BaseDatos.DbHelper;

public class MainActivity extends AppCompatActivity {

    Button bntSaludo, btnCrearDb;
    TextView tvSaludo;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        //nuevo inicio
        //poner la lista
        String error;
        try{
            listaContactos = findViewById(R.id.listaContactos);

            listaContactos.setLayoutManager(new LinearLayoutManager(this));
        } catch (Exception e) {
            Toast.makeText(this, "Error al cargar lista:"+ e.getMessage(), Toast.LENGTH_SHORT).show();
            error = e.getMessage();
        }



        //
        DbContactos dbContactos = new DbContactos(this);
        listaArrayContactos =  new ArrayList<>();   //dbContactos.mostrarContactos() ;


        //llamamos a nuestro adaptador y le mandamos todos los contactos de nuestra consulta.
        ListaContactosAdapter adapter = new ListaContactosAdapter(dbContactos.mostrarContactos());
        //le pasamos el adaptador a nuestro recycler view y nuestra información estructurada.
        listaContactos.setAdapter(adapter);

        //codigo nuevo
        bntSaludo = findViewById(R.id.btnSaludo);
        tvSaludo = findViewById(R.id.tvSaludo);
        btnCrearDb = findViewById(R.id.btncrearDb);

        bntSaludo.setOnClickListener(v -> {
            Toast.makeText(this, "Aviso Blanky", Toast.LENGTH_SHORT).show();
            tvSaludo.setText("Hola Blanky");
        });


        btnCrearDb.setOnClickListener(v -> {
            // Mostrar mensaje de éxito
            Toast.makeText(this, "Base de datos creada", Toast.LENGTH_SHORT).show();
            tvSaludo.setText("Base de datos creada");

            // Crear base de datos
            DbHelper dbHelper = new DbHelper(this);
            dbHelper.getWritableDatabase();

            // Iniciar la nueva activity
            Intent intent = new Intent(this, NuevoActivity.class);
            startActivity(intent);
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}