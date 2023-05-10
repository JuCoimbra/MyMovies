package com.example.mymovies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

public class FavoritosActivity extends AppCompatActivity {

    ImageButton home_btn;
    ImageButton favorite_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoritos);

        home_btn = findViewById(R.id.btn_home);
        favorite_btn = findViewById(R.id.btn_favorites);

        favorite_btn.setImageResource(R.drawable.baseline_account_white);
        home_btn.setImageResource(R.drawable.baseline_home);

        Button[] buttonAdd = {findViewById(R.id.buttonF1),findViewById(R.id.buttonF2),findViewById(R.id.buttonF3),findViewById(R.id.buttonF4)};
        Button buttonConf = findViewById(R.id.buttonConf);
        Button[] buttonCheck = {findViewById(R.id.buttonCheck1),findViewById(R.id.buttonCheck2),findViewById(R.id.buttonCheck3),findViewById(R.id.buttonCheck4),};
        ToggleButton[] buttonCurtir = {findViewById(R.id.buttonCurtir1),findViewById(R.id.buttonCurtir2),findViewById(R.id.buttonCurtir3),findViewById(R.id.buttonCurtir4)};
        Button[] buttonEdit = {findViewById(R.id.buttonEdit1),findViewById(R.id.buttonEdit2),findViewById(R.id.buttonEdit3),findViewById(R.id.buttonEdit4),};
        TextView[] textViewFNome = {findViewById(R.id.textViewF1nome),findViewById(R.id.textViewF2nome),findViewById(R.id.textViewF3nome),findViewById(R.id.textViewF4nome)};
        TextView[] textCurtidas = {findViewById(R.id.textViewC1),findViewById(R.id.textViewC2),findViewById(R.id.textViewC3),findViewById(R.id.textViewC4),};
        EditText[]  editTextFNome = {findViewById(R.id.editTextFilme1),findViewById(R.id.editTextFilme2),findViewById(R.id.editTextFilme3),findViewById(R.id.editTextFilme4)};
        ImageView[] imageFilme = {findViewById(R.id.imageViewF1),findViewById(R.id.imageViewF2),findViewById(R.id.imageViewF3),findViewById(R.id.imageViewF4)};

                for(int i =0; i<4; i++){
                    int I = i;
                    buttonAdd[I].setOnClickListener(v-> {
                        if (buttonAdd[I].getVisibility() == View.VISIBLE) {
                            buttonAdd[I].setVisibility(View.INVISIBLE);
                            editTextFNome[I].setVisibility(View.VISIBLE);
                            editTextFNome[I].requestFocus();
                            buttonCheck[I].setVisibility(View.VISIBLE);
                        }
                    });
                    buttonCheck[I].setOnClickListener(v-> {
                                if(!TextUtils.isEmpty(editTextFNome[I].getText().toString().trim())){
                                    String FilmeNome = editTextFNome[I].getText().toString().trim();
                                    editTextFNome[I].setVisibility(View.GONE);
                                    textViewFNome[I].setText(FilmeNome);
                                    textViewFNome[I].setVisibility(View.VISIBLE);
                                    textCurtidas[I].setText("Curtidas: 0");
                                    textCurtidas[I].setVisibility(View.VISIBLE);
                                    buttonCurtir[I].setVisibility(View.VISIBLE);
                                    imageFilme[I].setVisibility(View.VISIBLE);
                                    buttonEdit[I].setVisibility(View.VISIBLE);
                                    buttonCheck[I].setVisibility(View.GONE);

                                }else {
                                    imageFilme[I].setVisibility(View.GONE);
                                    editTextFNome[I].setVisibility(View.GONE);
                                    buttonCheck[I].setVisibility(View.GONE);
                                    textViewFNome[I].setVisibility(View.GONE);
                                    textCurtidas[I].setVisibility(View.GONE);
                                    buttonCurtir[I].setVisibility(View.GONE);
                                    buttonAdd[I].setVisibility(View.VISIBLE);
                                }
                            });
                    buttonEdit[I].setOnClickListener(v->{
                        imageFilme[I].setVisibility(View.GONE);
                        textViewFNome[I].setVisibility(View.GONE);
                        textCurtidas[I].setVisibility(View.GONE);
                        buttonCurtir[I].setVisibility(View.GONE);
                        editTextFNome[I].setVisibility(View.VISIBLE);
                        editTextFNome[I].requestFocus();
                        buttonEdit[I].setVisibility(View.GONE);
                        buttonCheck[I].setVisibility(View.VISIBLE);
                    });

                }
        home_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FavoritosActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }
}