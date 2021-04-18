package com.mart.ammanmart;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link history#newInstance} factory method to
 * create an instance of this fragment.
 */
public class history extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public history() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment history.
     */
    // TODO: Rename and change types and number of parameters
    public static history newInstance(String param1, String param2) {
        history fragment = new history();
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
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View v= inflater.inflate(R.layout.fragment_history, container, false);
        final FirebaseFirestore db1=FirebaseFirestore.getInstance();
        final LinearLayout linearLayout=v.findViewById(R.id.linearlayout);
//        db1.collection("new_users").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                if (task.isSuccessful())
//                {
//
//                    for (final QueryDocumentSnapshot documentSnapshot: Objects.requireNonNull(task.getResult()))
//                    {
//                        final LinearLayout signin_Requests=v.findViewById(R.id.signin_requests);
//                        final View item=inflater.inflate(R.layout.item_view,container,false);
//                        TextView mail=item.findViewById(R.id.mail_id);
//                        TextView type=item.findViewById(R.id.type_);
//                        String type_u=Objects.requireNonNull(documentSnapshot.getData().get("type")).toString();
//                        type.setText(Html.fromHtml("<b>Account Type:"+type_u));
//                        final String mail_=Objects.requireNonNull(documentSnapshot.getData().get("mail")).toString();
//                        mail.setText(Html.fromHtml("<b>Mail: </b>"+mail_));
//                        final TextView name=item.findViewById(R.id.name_);
//                        String name_=Objects.requireNonNull(documentSnapshot.getData().get("name")).toString();
//                        name.setText(Html.fromHtml("<b>Name: </b>"+name_));
//                        final String pass= Objects.requireNonNull(documentSnapshot.getData().get("pass")).toString();
//                        MaterialButton decline,accept;
//                        decline=item.findViewById(R.id.decline);
//                        accept=item.findViewById(R.id.accept);
//                        decline.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View view) {
//                                signin_Requests.removeView(item);
//                                db1.collection("new_users").document(documentSnapshot.getId()).delete();
//                                Log.d("requests", "onClick: "+documentSnapshot.getId());
//                            }
//                        });
//                        accept.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View view) {
//                               final FirebaseAuth mAuth = FirebaseAuth.getInstance();
//                                                    mAuth.createUserWithEmailAndPassword(mail_, pass).addOnCompleteListener(Objects.requireNonNull(getActivity()), new OnCompleteListener<AuthResult>() {
//                        @Override
//                        public void onComplete(@NonNull Task<AuthResult> task) {
////                            SharedPreferences sharedPreferences= Objects.requireNonNull(getContext()).getSharedPreferences("com.mart.ammanmart", Context.MODE_PRIVATE);
//                            if (task.isSuccessful()) {
//                                String userid = mAuth.getUid();
//                                FirebaseFirestore db = FirebaseFirestore.getInstance();
//                                Map<String, Object> user = new HashMap<>();
//                                user.put("name", name);
//                                user.put("pass", pass);
//                                user.put("mobile", Objects.requireNonNull(documentSnapshot.getData().get("mobile")).toString());
//                                user.put("type",Objects.requireNonNull(documentSnapshot.getData().get("type")).toString());
//                                signin_Requests.removeView(item);
//                                db1.collection("new_users").document(documentSnapshot.getId()).delete();
//                                assert userid != null;
//                                db.collection("users").document(userid).collection("user_details").add(user).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//                                    @Override
//                                    public void onSuccess(DocumentReference documentReference) {
//
//                                        Toast.makeText(getContext(), "Account created successfully!", Toast.LENGTH_LONG).show();
//                                    }
//                                }).addOnFailureListener(new OnFailureListener() {
//                                    @Override
//                                    public void onFailure(@NonNull Exception e) {
//                                        Toast.makeText(getContext(), "Error Occurred ,try again later", Toast.LENGTH_LONG).show();
//                                    }
//                                });
//                            }
//                            else
//                            {
//                                Toast.makeText(getContext(),"Mail id already registered !",Toast.LENGTH_LONG).show();
//                            }
//                            mAuth.signInWithEmailAndPassword("kowsalyakowsi106@gmail.com","Kowsi@106");
//                        }
//                    });
//                            }
//                        });
//                        signin_Requests.addView(item);
//                    }
//                }
//            }
//        });
        db1.collection("request").orderBy("date", Query.Direction.DESCENDING).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful())
                {
                    for(final QueryDocumentSnapshot queryDocumentSnapshot: Objects.requireNonNull(task.getResult())) {
                        final String billed = Objects.requireNonNull(queryDocumentSnapshot.getData().get("bill")).toString();
                        String userid=queryDocumentSnapshot.getData().get("userid").toString();
                        if (userid.equals(FirebaseAuth.getInstance().getUid())) {
                            final FirebaseFirestore db = FirebaseFirestore.getInstance();
                            if (!billed.equals("billed")) {
                                final TextView bill_text = v.findViewById(R.id.request_view);
                                bill_text.setVisibility(View.GONE);
                                final View item = inflater.inflate(R.layout.item_view, container, false);
                                final MaterialButton decline = item.findViewById(R.id.decline);
                                final MaterialButton accept = item.findViewById(R.id.accept);
                                final String[] accpet_data = {""};
//                            accept.setOnClickListener(new View.OnClickListener() {
//                                @Override
//                                public void onClick(View view) {
//                                    accpet_data[0] ="accepted";
//                                    accept.setText("Accepted");
//                                    decline.setVisibility(View.INVISIBLE);
//                                    Map<String, Object> accpet_da = new HashMap<>();
//                                    accpet_da.put("accept", accpet_data[0]);
//                                    db.collection("request").document(queryDocumentSnapshot.getId()).update(accpet_da);
//                                }
//                            });
//                            decline.setOnClickListener(new View.OnClickListener() {
//                                @Override
//                                public void onClick(View view) {
//                                    accpet_data[0] ="declined";
//                                    decline.setText("Declined");
//                                    accept.setVisibility(View.INVISIBLE);
//                                }
//                            });
                                String accep_ = "";
                                try {
                                    accep_ = Objects.requireNonNull(queryDocumentSnapshot.getData().get("accept")).toString();
                                } catch (Exception e) {
                                }
                                if (accep_.equals("accepted")) {
                                    accept.setText("Trader Accepted");
                                    decline.setVisibility(View.INVISIBLE);
                                    TextView textView = item.findViewById(R.id.review);
                                    textView.setVisibility(View.INVISIBLE);
                                } else if (accep_.equals("declined")) {
                                    decline.setText("Trader Declined");
                                    accept.setVisibility(View.INVISIBLE);
                                    TextView textView = item.findViewById(R.id.review);
                                    textView.setVisibility(View.INVISIBLE);
                                } else {
                                    accept.setVisibility(View.INVISIBLE);
                                    decline.setVisibility(View.INVISIBLE);
                                    TextView textView = item.findViewById(R.id.review);
                                    textView.setVisibility(View.VISIBLE);
                                    textView.setText("Trader is processing your request");
                                    textView.setElevation(2);
                                }
                                String type = Objects.requireNonNull(queryDocumentSnapshot.getData().get("type")).toString();
                                String pt_name = Objects.requireNonNull(queryDocumentSnapshot.getData().get("pt_name")).toString();
                                String pt_box = Objects.requireNonNull(queryDocumentSnapshot.getData().get("pt_box")).toString();
                                String pt_category = Objects.requireNonNull(queryDocumentSnapshot.getData().get("pt_category")).toString();
                                String pt_location = Objects.requireNonNull(queryDocumentSnapshot.getData().get("pt_location")).toString();
                                String date = Objects.requireNonNull(queryDocumentSnapshot.getData().get("date")).toString();
                                DateFormat simple = new SimpleDateFormat("dd/MMM/yyyy");
                                // Creating date from milliseconds
                                // using Date() constructor
                                Date result = new Date(Long.parseLong(date));
                                String mobile = Objects.requireNonNull(queryDocumentSnapshot.getData().get("mobile")).toString();
                                String k = "<b>Type: </b>" + type + "<br><b>" + getString(R.string.pt_name) + "\t</b>" + pt_name + "<br>" + "<b>" + getString(R.string.pt_category) + "\t</b>" + pt_category + "<br>" + "<b>" + getString(R.string.pt_location) + "\t</b>" + pt_location + "<br>" + "<b>" + getString(R.string.pt_mobile) + "\t</b>" + mobile;
                                if (type.equals("sell")) {
                                    String vehicle = Objects.requireNonNull(queryDocumentSnapshot.getData().get("vehicle")).toString();
                                    k += "<br><b>" + getString(R.string.pt_vehicle) + "\t</b>" + vehicle;
                                    item.setBackground(getResources().getDrawable(R.drawable.buy_item));
                                } else {
                                    item.setBackground(getResources().getDrawable(R.drawable.items_bg));
                                }
                                k += "<br><b>" + getString(R.string.no_of_box) + "\t</b>" + pt_box;
                                k += "<br><b>Date: <b>" + result;
                                TextView string_k = item.findViewById(R.id.string_k);
                                string_k.setText(Html.fromHtml(k));
                                final String finalAccep_ = accep_;
                                string_k.setOnLongClickListener(new View.OnLongClickListener() {
                                    @Override
                                    public boolean onLongClick(View v) {
                                        final AlertDialog.Builder updateDialogBuilder = new AlertDialog.Builder(getActivity());
                                        final View promt = getLayoutInflater().inflate(R.layout.billing, null);
                                        updateDialogBuilder.setView(promt);
                                        updateDialogBuilder.setPositiveButton("Update", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.dismiss();
                                            }
                                        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.cancel();
                                            }
                                        });
                                        final EditText trader_name, item_from, pt_price, pt_quantity, pt_damage, pt_commision, pt_rent, pt_debit, pt_total, product_name;
                                        trader_name = promt.findViewById(R.id.trader_name);
                                        product_name = promt.findViewById(R.id.product_name);
                                        item_from = promt.findViewById(R.id.item_from);
                                        pt_price = promt.findViewById(R.id.pt_price);
                                        pt_quantity = promt.findViewById(R.id.pt_quantity);
                                        pt_damage = promt.findViewById(R.id.pt_damage);
                                        pt_commision = promt.findViewById(R.id.pt_commision);
                                        pt_rent = promt.findViewById(R.id.pt_rent);
                                        pt_debit = promt.findViewById(R.id.pt_debit);
                                        pt_total = promt.findViewById(R.id.pt_total);
                                        updateDialogBuilder.setPositiveButton("Bill", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                dialogInterface.dismiss();
                                                dialogInterface.cancel();
                                            }
                                        });
                                        updateDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                dialogInterface.cancel();

                                            }
                                        });
                                        final AlertDialog updateDialog = updateDialogBuilder.create();
                                        updateDialog.show();
                                        updateDialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                updateDialog.dismiss();
                                                String trader_name_ = trader_name.getText().toString().length() > 0 ? trader_name.getText().toString().trim() : "N/A";
                                                String product_name_ = product_name.getText().toString().length() > 0 ? product_name.getText().toString().trim() : "N/A";
                                                String item_from_ = item_from.getText().toString().length() > 0 ? item_from.getText().toString().trim() : "N/A";
                                                String pt_price_ = pt_price.getText().toString().length() > 0 ? pt_price.getText().toString().trim() : "N/A";
                                                String pt_quantity_ = pt_quantity.getText().toString().length() > 0 ? pt_quantity.getText().toString().trim() : "N/A";
                                                String pt_damage_ = pt_damage.getText().toString().length() > 0 ? pt_damage.getText().toString().trim() : "N/A";
                                                String pt_commision_ = pt_commision.getText().toString().length() > 0 ? pt_commision.getText().toString().trim() : "N/A";
                                                String pt_rent_ = pt_rent.getText().toString().length() > 0 ? pt_rent.getText().toString().trim() : "N/A";
                                                String pt_debit_ = pt_debit.getText().toString().length() > 0 ? pt_debit.getText().toString().trim() : "N/A";
                                                String pt_total_ = pt_total.getText().toString().length() > 0 ? pt_total.getText().toString().trim() : "N/A";
                                                Map<String, Object> user = new HashMap<>();
                                                user.put("trader_name", trader_name_);
                                                user.put("product_name", product_name_);
                                                user.put("item_from", item_from_);
                                                user.put("pt_price", pt_price_);
                                                user.put("pt_quantity", pt_quantity_);
                                                user.put("pt_damage", pt_damage_);
                                                user.put("pt_commision", pt_commision_);
                                                user.put("pt_rent", pt_rent_);
                                                user.put("pt_debit", pt_debit_);
                                                user.put("pt_total", pt_total_);
                                                Map<String, Object> billing = new HashMap<>();
                                                billing.put("bill", "billed");
                                                billing.put("accept", accpet_data[0]);
                                                db.collection("request").document(queryDocumentSnapshot.getId()).update(billing);
                                                db.collection("bill").add(user).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                                    @Override
                                                    public void onSuccess(DocumentReference documentReference) {
                                                        Intent intent = new Intent(getContext(), Trader.class);
                                                        startActivity(intent);
                                                        Toast.makeText(getContext(), "Billed", Toast.LENGTH_SHORT).show();
                                                    }
                                                }).addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        Toast.makeText(getContext(), "Error Occurred ,try again later", Toast.LENGTH_LONG).show();
                                                    }
                                                });

                                            }
                                        });
                                        return true;
                                    }
                                });
                                linearLayout.addView(item);
                            }
                        }
                    }
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(), "Error occurred ,Try again later", Toast.LENGTH_LONG).show();
            }
        });

        return v;
    }
}