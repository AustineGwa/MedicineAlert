package com.austinegwa64.medicinealert.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.austinegwa64.medicinealert.Models.Medicine;
import com.austinegwa64.medicinealert.R;

import java.util.ArrayList;

/**
 * Created by gwaza on 4/4/2018.
 */

public class SqliteDatabaseAdapter extends  RecyclerView.Adapter<SqliteDatabaseAdapter.MedicationViewHolder> {

    ArrayList<Medicine> allMedication;

    public SqliteDatabaseAdapter(ArrayList<Medicine> allMedication) {

        this.allMedication = allMedication;
    }
    @Override
    public MedicationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View MedicationLayout = LayoutInflater.from(parent.getContext()).inflate(R.layout.medication_single_row, parent, false);

        MedicationViewHolder med_View_Holder = new MedicationViewHolder(MedicationLayout);
        return med_View_Holder;

    }


    @Override
    public void onBindViewHolder(MedicationViewHolder holder, int position) {

        Medicine medicine = allMedication.get(position);

        holder.medName.setText(medicine.getMedName());
        holder.medDesc.setText(medicine.getMedDesc());
        holder.medPresc.setText(medicine.getMedPrescription());
        holder.medSDate.setText(medicine.getStartDate());

    }


    @Override
    public int getItemCount() {
        return allMedication.size();
    }

    public  static class MedicationViewHolder extends RecyclerView.ViewHolder {

        TextView medName, medDesc, medPresc, medSDate;

        public MedicationViewHolder(View itemView) {
            super(itemView);
            medName = itemView.findViewById(R.id.textView);
            medDesc = itemView.findViewById(R.id.textView2);
            medPresc = itemView.findViewById(R.id.textView3);
            medSDate = itemView.findViewById(R.id.textView4);
        }
    }
}
