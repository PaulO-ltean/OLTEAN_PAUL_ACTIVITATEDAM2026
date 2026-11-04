package com.example.seminar4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

public class OperatieAdapter extends BaseAdapter {

    private List<Operatie> operatii = null;
    private Context ctx;
    private int resourceLayout;

    public OperatieAdapter(List<Operatie> operatii, Context ctx, int resourceLayout) {
        this.operatii = operatii;
        this.ctx = ctx;
        this.resourceLayout = resourceLayout;
    }

    @Override
    public int getCount() {
        return operatii.size();
    }

    @Override
    public Object getItem(int position) {
        return operatii.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=LayoutInflater.from(ctx);
        View v = inflater.inflate(resourceLayout, parent, false);

        TextView tip= v.findViewById(R.id.Display_Tip);
        TextView nume = v.findViewById(R.id.Display_Nume);
        TextView operator= v.findViewById(R.id.Display_Operator);
        Button ore = v.findViewById(R.id.Display_Ore);
        CheckBox completat = v.findViewById(R.id.Display_completata);

        Operatie operatie  =(Operatie)getItem(position);

        tip.setText(operatie.getTip());
        nume.setText(operatie.getnOpeartie());
        operator.setText(operatie.getnOperator());
        ore.setText("" +operatie.getDurataOre());
        Boolean checked = operatie.getCompletata();
        completat.setChecked(checked);

        return v;
    }
}
