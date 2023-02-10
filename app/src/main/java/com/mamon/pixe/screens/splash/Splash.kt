package com.mamon.pixe.screens.splash

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.mamon.pixe.R
import com.mamon.pixe.databinding.SplashBinding
import com.mamon.pixe.utils.hide
import com.mamon.pixe.utils.showMessage

class Splash : Fragment() {

    private lateinit var binding: SplashBinding

    companion object{
        private const val THREE_SECOND = 3000L
        private const val ONE_SECOND = 1000L
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        binding = SplashBinding.inflate(inflater,container,false)



        splash()


        return binding.root
    }


    private fun splash() {
        object: CountDownTimer(THREE_SECOND, ONE_SECOND){
            override fun onTick(millisUntilFinished: Long) {}

            override fun onFinish() {
                binding.loader.hide()
                findNavController().navigate(R.id.action_splash_to_photos)
            }
        }.start()
    }




}