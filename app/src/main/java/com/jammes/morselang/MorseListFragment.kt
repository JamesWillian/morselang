package com.jammes.morselang

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.jammes.morselang.databinding.FragmentMorseListBinding
import com.jammes.morselang.dummy.MockMorse

class MorseListFragment : Fragment() {

    private var _binding: FragmentMorseListBinding? = null

    private val binding get() = _binding!!

    private lateinit var adapter: MorseListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = MorseListAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMorseListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.morseRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.morseRecyclerView.adapter = adapter

        adapter.updateMorseList(MockMorse.randomMorseList)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}