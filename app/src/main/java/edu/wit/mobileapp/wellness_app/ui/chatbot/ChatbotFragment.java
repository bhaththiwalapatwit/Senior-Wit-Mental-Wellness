package edu.wit.mobileapp.wellness_app.ui.chatbot;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.applozic.mobicommons.commons.core.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import edu.wit.mobileapp.wellness_app.LoginActivity;
import edu.wit.mobileapp.wellness_app.R;
import edu.wit.mobileapp.wellness_app.databinding.FragmentChatbotBinding;
import edu.wit.mobileapp.wellness_app.ui.chatbot.ChatbotViewModel;

import io.kommunicate.KmChatBuilder;
import io.kommunicate.Kommunicate;
import io.kommunicate.callbacks.KmCallback;


public class ChatbotFragment extends Fragment {

    private static final String APP_ID = "fa7e088ca39634cb807890ee63bf701f";
    private ChatbotViewModel chatbotViewModel;
    private FragmentChatbotBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        Kommunicate.init(getActivity(), APP_ID);

        chatbotViewModel =
                new ViewModelProvider(this).get(ChatbotViewModel.class);

        binding = FragmentChatbotBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        List<String> botList = new ArrayList(); botList.add("wellness-e4dgw"); //enter your integrated bot Ids
        new KmChatBuilder(getActivity()).setChatName("Support")
                .setBotIds(botList)
                .launchChat(new KmCallback() {
                    @Override
                    public void onSuccess(Object message) {
                        Utils.printLog(getActivity(), "ChatTest", "Success : " + message);
                    }

                    @Override
                    public void onFailure(Object error) {
                        Utils.printLog(getActivity(), "ChatTest", "Failure : " + error);
                    }
                });


        return root;
    }


    /* public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        Kommunicate.init(getActivity(), APP_ID);

        chatbotViewModel =
                new ViewModelProvider(this).get(ChatbotViewModel.class);

        binding = FragmentChatbotBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        Button logOut = root.findViewById(R.id.logout);
        //Button button = binding.contactBtn;
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);

                startActivity(intent);

            }
        });

        final TextView textView = binding.textView3;
        chatbotViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    } */

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
