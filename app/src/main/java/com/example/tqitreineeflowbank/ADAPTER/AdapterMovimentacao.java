package com.example.tqitreineeflowbank.ADAPTER;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.tqitreineeflowbank.MODEL.Emprestimos;
import com.example.tqitreineeflowbank.R;

import java.util.List;

/**
 * Created by Jamilton Damasceno
 */

public class AdapterMovimentacao extends RecyclerView.Adapter<AdapterMovimentacao.MyViewHolder> {

    List<Emprestimos> emprestimos;
    Context context;

    public AdapterMovimentacao(List<Emprestimos> movimentacoes, Context context) {
        this.emprestimos = movimentacoes;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLista = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_emprestimos, parent, false);
        return new MyViewHolder(itemLista);
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Emprestimos movimentacao = emprestimos.get(position);

        holder.id.setText(movimentacao.getId());
        holder.valor.setText(String.valueOf(movimentacao.getValor()));
        holder.parcelas.setText(movimentacao.getParcelas());

    }


    @Override
    public int getItemCount() {
        return emprestimos.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView id, valor, parcelas;

        public MyViewHolder(View itemView) {
            super(itemView);

            id = itemView.findViewById(R.id.textAdapterTitulo);
            valor = itemView.findViewById(R.id.textAdapterValor);
            parcelas = itemView.findViewById(R.id.textAdapterCategoria);
        }

    }

}
