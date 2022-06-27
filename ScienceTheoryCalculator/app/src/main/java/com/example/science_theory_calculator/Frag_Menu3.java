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

public class Frag_Menu3 extends Fragment implements View.OnClickListener {
    public EditText gravitasi, Luas, Massa;
    public Button btnsubmit;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_menu3,container,false);
        gravitasi = (EditText) view.findViewById(R.id.gravitasi);
        Luas = (EditText) view.findViewById(R.id.alas);
        Massa = (EditText) view.findViewById(R.id.massa);
        btnsubmit = (Button) view.findViewById(R.id.btn_submit);
        gravitasi.setOnClickListener(this);
        Luas.setOnClickListener(this);
        Massa.setOnClickListener(this);
        btnsubmit.setOnClickListener(this);
        return view;
    }

    public void onClick(View view){
        TextView Hitung = getView().findViewById(R.id.force);
        TextView Hitung2 = getView().findViewById(R.id.pressure);

        if (view.getId() == R.id.btn_submit) {
            String Gravity = gravitasi.getText().toString().trim();
            String Alas = Luas.getText().toString().trim();
            String massa = Massa.getText().toString().trim();
            boolean isEmptyFields = false;
            boolean invalidInput = false;
            if (TextUtils.isEmpty(Gravity)) {
                isEmptyFields = true;
                gravitasi.setError("Field ini tidak boleh kosong");
            }
            if (Integer.valueOf(Gravity) < 1 || Integer.valueOf(Gravity)> 2) {
                invalidInput = true;
                gravitasi.setError("Input tidak valid, input harus 1 atau 2");
            }
            if (TextUtils.isEmpty(Alas)) {
                isEmptyFields = true;
                Luas.setError("Field ini tidak boleh kosong");
            }
            if (TextUtils.isEmpty(massa)) {
                isEmptyFields = true;
                Massa.setError("Field ini tidak boleh kosong");
            }
            if (!isEmptyFields && !invalidInput) {
                double M = Double.valueOf(massa);
                int G = Integer.valueOf(Gravity);
                double A = Double.valueOf(Alas);
                double Gravi = 0, F = 0, P = 0;

                //Memilih percepatan gravitasi (soalnya soal fisika sering pakai 9,8 m/(s^2) dan 10 m/(s^2))
                if(G == 1){
                    Gravi = 9.8;
                } else if (G == 2){
                    Gravi = 10.0;
                }

                //Menghitung Gaya tekan (berat tekan)
                F = M * Gravi;

                //Menghitung Tekanan
                P = F/A;


                Hitung.setText(F + " Newton");
                Hitung2.setText(P + " Pascal");
            }
        }
    }
}
