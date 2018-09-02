package proyects.tello.ehelper.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import proyects.tello.ehelper.Entidades.Pregunta;
import proyects.tello.ehelper.Entidades.Sintoma;
import proyects.tello.ehelper.R;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.ViewHolder> {

    private List<Pregunta> mDataSet;
    private LayoutInflater layoutInflater;
    public List<String> sintomasPaciente = new ArrayList<>();

    public QuestionAdapter(Context context, List<Pregunta> data) {
        layoutInflater = LayoutInflater.from(context);
        this.mDataSet = data;
    }

    public List<String> getSintomasPaciente(){
        return sintomasPaciente;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.card_question, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Pregunta current = mDataSet.get(position);
        holder.setData(current, position);
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;
        private CheckBox checkBox1, checkBox2 ;
        private int position;
        private Pregunta currentObject;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.enunciado_card);
            checkBox1 = itemView.findViewById(R.id.check_1_card);

            checkBox2 = itemView.findViewById(R.id.check_2_card);

        }

        public void setData(final Pregunta current, int position) {
            textView.setText(current.getEnunciado());
            checkBox1.setText(current.getAlternativa1());
            checkBox1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    sintomasPaciente.add(current.getSintoma());
                    if(checkBox2.isChecked()){
                        checkBox2.toggle();
                    }
                }
            });
            checkBox2.setText(current.getAlternativa2());
            checkBox2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(checkBox1.isChecked()){
                        checkBox1.toggle();
                    }
                    for(int i=0; i < sintomasPaciente.size(); i++){
                        if(sintomasPaciente.get(i).equals(current.getSintoma())){
                            sintomasPaciente.remove(i);
                        }
                    }

                }
            });

            this.position = position;
            this.currentObject = current;
        }
    }

}
