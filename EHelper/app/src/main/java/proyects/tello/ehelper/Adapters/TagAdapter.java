package proyects.tello.ehelper.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import java.util.ArrayList;
import java.util.List;

import proyects.tello.ehelper.R;

public class TagAdapter extends RecyclerView.Adapter<TagAdapter.ViewHolder> {
    private List<String> dataSet;
    private List<String> tagsPaciente = new ArrayList<>();
    private LayoutInflater layoutInflater;

    public TagAdapter(Context context, List<String> datos){
        layoutInflater = LayoutInflater.from(context);
        this.dataSet = datos;
    }

    public List<String> getTagsPaciente(){
        return tagsPaciente;
    }

    @Override
    public TagAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.card_tag, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;

    }

    @Override
    public void onBindViewHolder(TagAdapter.ViewHolder holder, int position) {
        String sintoma = dataSet.get(position);
        holder.setData(sintoma);
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CheckBox checkBox;

        public ViewHolder(View itemView) {
            super(itemView);
            this.checkBox = itemView.findViewById(R.id.check_tag);
        }

        public void setData(final String data){

            checkBox.setText(data);
            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    tagsPaciente.add(data);
                }
            });
        }
    }
}
