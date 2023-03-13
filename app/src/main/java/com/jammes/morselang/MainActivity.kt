package com.jammes.morselang

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.jammes.morselang.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val viewModel: MorseLangViewModel by viewModels {
        MorseLangViewModel.Factory()
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val textEditText = binding.editTextText
        val morseEditText = binding.editTextMorse

        binding.card.visibility = View.GONE
        binding.copyMorse.visibility = View.GONE

        binding.fab.setOnClickListener {
            if (binding.card.visibility == View.GONE) {
                binding.card.visibility = View.VISIBLE
            } else {
                binding.card.visibility = View.GONE
            }
        }

        binding.copyMorse.setOnClickListener {

            val text = morseEditText.text.toString()

            val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("morsetxt", text)

            clipboard.setPrimaryClip(clip)

            Toast.makeText(this, "Morse copiado com sucesso!", Toast.LENGTH_SHORT).show()

        }

        textEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            /**
             *
             * Apos o texto ser alterado, chama a funcao para converter o texto para morse
             *
             */
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                val txt = s.toString().uppercase().trim()

                binding.editTextMorse.setText(viewModel.convertToMorse( txt ))

            }

            /**
             *
             * Controla a visibilidade dos icones de Limpar Texto, Copiar e Salvar
             *
             */
            override fun afterTextChanged(s: Editable?) {

                if (binding.editTextMorse.text.toString() == "") {
                    binding.copyMorse.visibility = View.GONE
                } else {
                    binding.copyMorse.visibility = View.VISIBLE
                }
            }
        })
    }

}
