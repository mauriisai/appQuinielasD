package adaptadores;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.appquinielas.R;
import com.example.appquinielas.infoEquipo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import entidades.Equipos;


public class ListaEquiposAdaptador extends RecyclerView.Adapter<ListaEquiposAdaptador.EquipoViewHolder> {

    ArrayList<Equipos> listaEquipos;
    ArrayList<Equipos> listaOriginal;

    public ListaEquiposAdaptador(ArrayList<Equipos> listaEquipos) {
        this.listaEquipos = listaEquipos;
        listaOriginal = new ArrayList<>();
        listaOriginal.addAll(listaEquipos);
    }

    @NonNull
    @Override
    public EquipoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_equipo, null, false);
        return new EquipoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EquipoViewHolder holder, int position) {
        holder.nomEq.setText(listaEquipos.get(position).getnombreEq());
        holder.paisEq.setText(listaEquipos.get(position).getpaisEq());
        holder.correoEq.setText(listaEquipos.get(position).getcorreoEq());
    }


    public void filtrado(final String txtBuscar) {
        int longitud = txtBuscar.length();
        if (longitud == 0) {
            listaEquipos.clear();
            listaEquipos.addAll(listaOriginal);
        } else {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                List<Equipos> coleccion = listaEquipos.stream()
                        .filter(i -> i.getnombreEq().toLowerCase().contains(txtBuscar.toLowerCase()))
                        .collect(Collectors.toList());
                listaEquipos.clear();
                listaEquipos.addAll(coleccion);
            } else {
                for (Equipos c : listaOriginal) {
                    if (c.getnombreEq().toLowerCase().contains(txtBuscar.toLowerCase())) {
                        listaEquipos.add(c);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return listaEquipos.size();
    }

    public class EquipoViewHolder extends RecyclerView.ViewHolder {
        TextView nomEq, paisEq, correoEq;

        public EquipoViewHolder(@NonNull View itemView) {
                super(itemView);

                nomEq = itemView.findViewById(R.id.tvNomEquipo);
                paisEq = itemView.findViewById(R.id.tvPais);
                correoEq = itemView.findViewById(R.id.tvCorreo);

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Context context = view.getContext();
                        Intent intent = new Intent(context, infoEquipo.class);
                        intent.putExtra("idEquipo", listaEquipos.get(getAdapterPosition()).getId());
                        context.startActivity(intent);
                    }
                });
            }
        }
    }
