package com.pitercapistrano.calculadoradenotaskotlin

import android.content.res.ColorStateList
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.pitercapistrano.calculadoradenotaskotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btResultado.setOnClickListener{
            var nota1 = binding.nota1.text.toString()
            var nota2 = binding.nota2.text.toString()
            var nota3 = binding.nota3.text.toString()
            var nota4 = binding.nota4.text.toString()
            var numeroFaltas = binding.faltas.text.toString()



            if (nota1.isEmpty() || nota2.isEmpty() || nota3.isEmpty() || nota4.isEmpty() || numeroFaltas.isEmpty()){
                binding.txtResultado.setText("Preencha todos os campos!")
                binding.txtResultado.setTextColor(getColor(R.color.red))
            }else{
                calcularMedia(nota1.toDouble(), nota2.toDouble(), nota3.toDouble(), nota4.toDouble(), numeroFaltas.toInt())
            }

        }

    }
    fun calcularMedia(nota1: Double, nota2: Double, nota3: Double, nota4: Double, numeroFaltas: Int){
        var media = (nota1+nota2+nota3+nota4)/4

        if (numeroFaltas > 20 ){
            binding.txtResultado.setText("Aluno reprovado por falta! \n Número de Faltas: $numeroFaltas \n Média Final: $media")
            binding.txtResultado.setTextColor(getColor(R.color.red))
        }else if (media < 4){
            binding.txtResultado.setText("Aluno reprovado por nota! \n Média Final: $media")
            binding.txtResultado.setTextColor(getColor(R.color.red))
        }else if (media >= 4 && media <= 5){
            binding.txtResultado.setText("Aluno em recuperação! \n Média Final: $media")
            binding.txtResultado.setTextColor(getColor(R.color.orange))
        }else if (media > 5){
            binding.txtResultado.setText("Aluno aprovado! \n Média Final: $media")
            binding.txtResultado.setTextColor(getColor(R.color.green))
        }
    }
}