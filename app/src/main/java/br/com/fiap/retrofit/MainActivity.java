package br.com.fiap.retrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://douglascabral.com.br/aula-api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        NossosEndPoints api = retrofit.create(NossosEndPoints.class);

        api.getContatos().enqueue(new Callback<List<Contato>>() {

            //Response é o retorno que contem uma lista de contatos
            @Override
            public void onResponse(Call<List<Contato>> call, Response<List<Contato>> response) {
                List<Contato> contatos = response.body();
                //Manda logar cada nome //for
                for (Contato c: contatos) {
                    Log.d("NossoDebug", c.getNome());
                }
            }

            @Override
            public void onFailure(Call<List<Contato>> call, Throwable t) {
                Toast.makeText(
                        MainActivity.this,
                        R.string.Erro,
                        Toast.LENGTH_SHORT

                ).show();
            }
        });
    }
}
