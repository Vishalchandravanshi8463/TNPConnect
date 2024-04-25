package com.test.tnpconnect

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import com.test.tnpconnect.databinding.FragmentHomeBinding
import kotlin.random.Random

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Home.newInstance] factory method to
 * create an instance of this fragment.
 */
class Home : Fragment() {
    private lateinit var binding : FragmentHomeBinding
    private lateinit var createMeeting : CardView
    private lateinit var joinMeeting : CardView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

//        return inflater.inflate(R.layout.fragment_home, container, false)
        binding = FragmentHomeBinding.inflate(layoutInflater)
        createMeeting = binding.createMeetingCardView
        joinMeeting = binding.joinMeetingCardView

        createMeeting.setOnClickListener {
            val id = getRandomId()
            val intent = Intent(requireContext(), EnterMeeting::class.java)
            intent.putExtra("id", id.toString())
            startActivity(intent)
        }

        joinMeeting.setOnClickListener {
            val intent = Intent(requireContext(), EnterMeeting::class.java)
            intent.putExtra("id", "00")
            startActivity(intent)
        }

        return binding.root
    }

    private fun getRandomId(): Any {
        val id = StringBuilder()

        while(id.length < 10) {
            id.append(Random.nextInt(10));
        }

        return id.toString()
    }
}