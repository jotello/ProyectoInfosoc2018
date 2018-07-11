package proyects.tello.ehelper.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import java.util.List;
import proyects.tello.ehelper.Entidades.PreguntaExcluyente;
import proyects.tello.ehelper.R;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.ViewHolder> {

    private List<PreguntaExcluyente> mDataSet;
    private LayoutInflater layoutInflater;

    public QuestionAdapter(Context context, List<PreguntaExcluyente> data) {
        layoutInflater = LayoutInflater.from(context);
        this.mDataSet = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.card_question, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        PreguntaExcluyente current = mDataSet.get(position);
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
        private PreguntaExcluyente currentObject;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.enunciado_card);
            checkBox1 = itemView.findViewById(R.id.check_1_card);
            checkBox2 = itemView.findViewById(R.id.check_2_card);

        }

        public void setData(PreguntaExcluyente current, int position) {
            textView.setText(current.getEnunciado());
            checkBox1.setText(current.getAlternativa1());
            checkBox2.setText(current.getAlternativa2());

            this.position = position;
            this.currentObject = current;
        }
    }
}
