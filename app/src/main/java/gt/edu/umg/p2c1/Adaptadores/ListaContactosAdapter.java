package gt.edu.umg.p2c1.Adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import gt.edu.umg.p2c1.BaseDatos.Entidades.Contactos;
import gt.edu.umg.p2c1.R;

public class ListaContactosAdapter extends RecyclerView.Adapter<ListaContactosAdapter.ContactoViewHolder> {

    ArrayList<Contactos> listaContactos;

    //crear el constructor de  esta clase
    public ListaContactosAdapter(ArrayList<Contactos> listaContactos) {
        this.listaContactos = listaContactos;
    }


    @NonNull
    @Override
    public ListaContactosAdapter.ContactoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_contactos, parent, false);
        return new ContactoViewHolder((ViewGroup) view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListaContactosAdapter.ContactoViewHolder holder, int position) {
        holder.viewNombre.setText(listaContactos.get(position).getNombre());
        holder.viewTelefono.setText(listaContactos.get(position).getTelefono());
        holder.viewEmail.setText(listaContactos.get(position).getEmail());

    }

    @Override
    public int getItemCount() {
        return listaContactos.size();
    }

    public class ContactoViewHolder extends RecyclerView.ViewHolder {

        TextView viewNombre,viewTelefono,viewEmail;

        public ContactoViewHolder(@NonNull ViewGroup parent) {
            super(parent);
            viewNombre = parent.findViewById(R.id.viewNombre);
            viewTelefono = parent.findViewById(R.id.viewTelefono);
            viewEmail = parent.findViewById(R.id.viewEmail);
        }
    }
}
