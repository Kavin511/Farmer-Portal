package com.mart.ammanmart;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link User_Account#newInstance} factory method to
 * create an instance of this fragment.
 */
public class User_Account extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public User_Account() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment User_Account.
     */
    // TODO: Rename and change types and number of parameters
    public static User_Account newInstance(String param1, String param2) {
        User_Account fragment = new User_Account();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_account, container, false);
//        final TextView account_type=v.findViewById(R.id.account_type);
//                final EditText name=v.findViewById(R.id.name);
//                name.setClickable(false);
//        final TextView save_text=v.findViewById(R.id.save_text);
//        final EditText mail=v.findViewById(R.id.mail);
//        mail.setClickable(false);
//        final EditText mobile=v.findViewById(R.id.mobile);
//        mobile.setClickable(false);
//        final TextView  edit_text=v.findViewById(R.id.edit_text);
//        final FirebaseFirestore db = FirebaseFirestore.getInstance();
//        FirebaseAuth mAuth=FirebaseAuth.getInstance();
//        final String userid = Objects.requireNonNull(mAuth.getCurrentUser()).getUid();
//        db.collection("users").document(userid).collection("user_details").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                if (task.isSuccessful())
//                {
//                    for (QueryDocumentSnapshot documentSnapshot: Objects.requireNonNull(task.getResult()))
//                    {
//                        name.setText(Objects.requireNonNull(documentSnapshot.getData().get("name")).toString());
////                        mail.setText(Objects.requireNonNull(documentSnapshot.getData().get("mail")).toString());
//                        mobile.setText(Objects.requireNonNull(documentSnapshot.getData().get("mobile")).toString());
//                        account_type.setText(Objects.requireNonNull(documentSnapshot.getData().get("type")).toString());
//                    }
//                }
//            }
//        });
//        edit_text.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                name.setClickable(true);
//                mobile.setClickable(true);
//                edit_text.setVisibility(View.GONE);
//                save_text.setVisibility(View.VISIBLE);
//            }
//        });
//        save_text.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                edit_text.setVisibility(View.VISIBLE);
//                save_text.setVisibility(View.GONE);
//                name.setClickable(false);
//                mobile.setClickable(false);
//                db.collection("users").document(userid).collection("user_details").document().update("name",name.getText().toString());
//                db.collection("users").document(userid).collection("user_details").document().update("mobile",mobile.getText().toString());
//            }
//        });


        return v;
    }
}