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

public class Frag_Menu1 extends Fragment implements View.OnClickListener {
    public EditText suhuAwal,suhuAkhir, Massa, wujudAwal, wujudAkhir;
    public Button btnsubmit;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_menu1,container,false);
        suhuAwal = (EditText) view.findViewById(R.id.suhuawal);
        suhuAkhir = (EditText) view.findViewById(R.id.suhuakhir);
        wujudAwal = (EditText) view.findViewById(R.id.wawal);
        wujudAkhir = (EditText) view.findViewById(R.id.wakhir);
        Massa = (EditText) view.findViewById(R.id.massa);
        btnsubmit = (Button) view.findViewById(R.id.btn_submit);
        wujudAwal.setOnClickListener(this);
        wujudAkhir.setOnClickListener(this);
        suhuAwal.setOnClickListener(this);
        suhuAkhir.setOnClickListener(this);
        Massa.setOnClickListener(this);
        btnsubmit.setOnClickListener(this);
        return view;
    }

    public void onClick(View view){
        TextView Hitung = getView().findViewById(R.id.hasil);
        TextView Hitung2 = getView().findViewById(R.id.hasil2);

        if (view.getId() == R.id.btn_submit) {
            String wujudawal = wujudAwal.getText().toString().trim();
            String wujudakhir = wujudAkhir.getText().toString().trim();
            String awal = suhuAwal.getText().toString().trim();
            String akhir = suhuAkhir.getText().toString().trim();
            String massa = Massa.getText().toString().trim();
            boolean isEmptyFields = false;
            boolean invalidInput = false;
            if (TextUtils.isEmpty(awal)) {
                isEmptyFields = true;
                suhuAwal.setError("Field ini tidak boleh kosong");
            }
            if (TextUtils.isEmpty(akhir)) {
                isEmptyFields = true;
                suhuAkhir.setError("Field ini tidak boleh kosong");
            }
            if (TextUtils.isEmpty(wujudawal)) {
                isEmptyFields = true;
                wujudAwal.setError("Field ini tidak boleh kosong");
            }
            if (Integer.valueOf(wujudawal) < 1 || Integer.valueOf(wujudawal) > 3) {
                invalidInput = true;
                wujudAwal.setError("Input tidak valid, input harus 1, 2, atau 3");
            }
            if (TextUtils.isEmpty(wujudakhir)) {
                isEmptyFields = true;
                wujudAkhir.setError("Field ini tidak boleh kosong");
            }
            if (Integer.valueOf(wujudakhir) < 1 || Integer.valueOf(wujudakhir) > 3) {
                invalidInput = true;
                wujudAkhir.setError("Input tidak valid, input harus 1, 2, atau 3");
            }
            if (!isEmptyFields && !invalidInput) {
                double M = Double.valueOf(massa);
                double B = Double.valueOf(awal);
                double D = Double.valueOf(akhir);
                int A = Integer.valueOf(wujudawal);
                int C = Integer.valueOf(wujudakhir);

                double Kalor_Joule = 0, Kalor_Kalori = 0, Kalor_Wujud_Joule = 0, Kalor_Wujud_Kalori = 0, Total_Joule = 0, Total_Kalori = 0;
                double[] jenisjoule = {2.100, 4.200, 2.010};
                double[] jeniskalori = {0.500, 1.000, 0.480};

                //perpindahan kalor antar suhu
                if (A == C) {
                    Kalor_Joule = M * jenisjoule[A - 1] * (D - B);
                    Kalor_Kalori = M * jeniskalori[A - 1] * (D - B);
                } else if ((A == 1 && C == 2) || (A == 2 && C == 1)) {
                    Kalor_Joule = M * jenisjoule[0] * (0 - B) + jenisjoule[1] * (D);
                    Kalor_Kalori = M * jeniskalori[0] * (0 - B) + jeniskalori[1] * (D);
                } else if ((A == 2 && C == 3) || (A == 3 && C == 2)) {
                    Kalor_Joule = M * jenisjoule[1] * (0 - B) + jenisjoule[2] * (D);
                    Kalor_Kalori = M * jeniskalori[1] * (0 - B) + jeniskalori[2] * (D);
                } else if ((A == 1 && C == 3) || (A == 3 && C == 1)) {
                    Kalor_Joule = M * jenisjoule[0] * (0 - B) + jenisjoule[1] * (100) + jenisjoule[2] * (D - 100);
                    Kalor_Kalori = M * jeniskalori[0] * (0 - B) + jeniskalori[1] * (100) + jeniskalori[2] * (D - 100);
                }

                //Perpindahan kalor dalam perubahan wujud
                if ((A == 1 && C == 2) || (A == 2 && C == 1)) {
                    Kalor_Wujud_Joule = M * 334;
                    Kalor_Wujud_Kalori = M * 79.500;
                } else if ((A == 2 && C == 3) || (A == 3 && C == 2)) {
                    Kalor_Wujud_Joule = M * 2256;
                    Kalor_Wujud_Kalori = M * 539;
                } else if ((A == 1 && C == 3) || (A == 3 && C == 1)) {
                    Kalor_Wujud_Joule = M * 334 + M * 2256;
                    Kalor_Wujud_Kalori = M * 79.5 + M * 539;
                }

                //menentukan arah perpindahan kalor dan totalnya
                int E = C - A;

                Total_Joule = Kalor_Joule + Kalor_Wujud_Joule;
                Total_Kalori = Kalor_Kalori + Kalor_Wujud_Kalori;

                if (E < 0) {
                    Total_Joule = Total_Joule * -1;
                    Total_Kalori = Total_Kalori * -1;
                }

                Hitung.setText(Total_Joule + " Joule");
                Hitung2.setText(Total_Kalori + " Kalori");
            }
        }
    }
}
