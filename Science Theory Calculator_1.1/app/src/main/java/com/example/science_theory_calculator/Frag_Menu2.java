package com.example.science_theory_calculator;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Frag_Menu2 extends Fragment implements View.OnClickListener {
    public EditText gravitasi, ketinggian, Massa, vel_awal, waktu;
    public Button btnsubmit;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_menu2,container,false);
        gravitasi = (EditText) view.findViewById(R.id.gravitasi);
        ketinggian = (EditText) view.findViewById(R.id.ketinggian);
        vel_awal = (EditText) view.findViewById(R.id.velocity);
        waktu = (EditText) view.findViewById(R.id.waktu);
        Massa = (EditText) view.findViewById(R.id.massa);
        btnsubmit = (Button) view.findViewById(R.id.btn_submit);
        vel_awal.setOnClickListener(this);
        waktu.setOnClickListener(this);
        gravitasi.setOnClickListener(this);
        ketinggian.setOnClickListener(this);
        Massa.setOnClickListener(this);
        btnsubmit.setOnClickListener(this);
        return view;
    }

    public void onClick(View view){
        TextView Hitung = getView().findViewById(R.id.Mekanik);
        TextView Hitung2 = getView().findViewById(R.id.kecepatan);
        TextView Hitung3 = getView().findViewById(R.id.jarak);

        if (view.getId() == R.id.btn_submit) {
            String Kecawal = vel_awal.getText().toString().trim();
            String time = waktu.getText().toString().trim();
            String Gravity = gravitasi.getText().toString().trim();
            String tinggi = ketinggian.getText().toString().trim();
            String massa = Massa.getText().toString().trim();
            boolean isEmptyFields = false;
            if (TextUtils.isEmpty(Gravity)) {
                isEmptyFields = true;
                gravitasi.setError("Field ini tidak boleh kosong");
            }
            if (TextUtils.isEmpty(tinggi)) {
                isEmptyFields = true;
                ketinggian.setError("Field ini tidak boleh kosong");
            }
            if (TextUtils.isEmpty(Kecawal)) {
                isEmptyFields = true;
                vel_awal.setError("Field ini tidak boleh kosong");
            }
            if (TextUtils.isEmpty(time)) {
                isEmptyFields = true;
                waktu.setError("Field ini tidak boleh kosong");
            }
            if (TextUtils.isEmpty(massa)) {
                isEmptyFields = true;
                Massa.setError("Field ini tidak boleh kosong");
            }
            if (!isEmptyFields) {
                double M = Double.valueOf(massa);
                int G = Integer.valueOf(Gravity);
                double H = Double.valueOf(tinggi);
                double V0 = Double.valueOf(Kecawal);
                double t = Double.valueOf(time);
                double Gravi = 0, EM = 0, S = 0, V1 = 0;

                //Memilih percepatan gravitasi (soalnya soal fisika sering pakai 9,8 m/(s^2) dan 10 m/(s^2))
                if(G == 1){
                    Gravi = 9.8;
                } else if (G == 2){
                    Gravi = 10.0;
                }

                //Energi Mekanik (Energi potensial + Energi Kinetik)
                EM = (M * Gravi * H) + (M * V0 * V0)/2;

                //Kecepatan setelah menempuh waktu tertentu
                V1 = V0 + (Gravi * t);

                //Jarak setelah menempuh waktu tertentu
                S = V0 * t + (Gravi * t * t)/2;

                Hitung.setText(EM + " Joule");
                Hitung2.setText(V1+ " m/s");
                Hitung3.setText(S + " m");
            }
        }
    }
}
