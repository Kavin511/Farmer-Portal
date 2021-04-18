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
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link billed#newInstance} factory method to
 * create an instance of this fragment.
 */
public class billed extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public billed() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment billed.
     */
    // TODO: Rename and change types and number of parameters
    public static billed newInstance(String param1, String param2) {
        billed fragment = new billed();
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

        final View v= inflater.inflate(R.layout.fragment_billed, container, false);
        ExtendedFloatingActionButton extendedFloatingActionButton=v.findViewById(R.id.add_);
        final FirebaseFirestore db1=FirebaseFirestore.getInstance();
        final LinearLayout linearLayout=v.findViewById(R.id.linearlayout);
        extendedFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                        FirebaseFirestore db = FirebaseFirestore.getInstance();
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
                        db.collection("bill").add(user).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Toast.makeText(getContext(), "Billed", Toast.LENGTH_SHORT).show();
                                Map<String, Object> filler = new HashMap<>();
                                filler.put("bill", "billed");
                                db1.collection("requests").document().update(filler);
                                Intent intent=new Intent(getContext(),Trader.class);
                                startActivity(intent);
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getContext(), "Error Occurred ,try again later", Toast.LENGTH_LONG).show();
                            }
                        });

                    }
                });


            }
        });
        final int[] i = {0};
        db1.collection("bill").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful())
                {
                    for(QueryDocumentSnapshot queryDocumentSnapshot: Objects.requireNonNull(task.getResult()))
                    {
                        TextView bill_text=v.findViewById(R.id.request_view);
                        bill_text.setVisibility(View.GONE);
                        String item_from_=queryDocumentSnapshot.getData().get("item_from").toString();
                                String product_name_=queryDocumentSnapshot.getData().get("product_name").toString();
                        String pt_commision_=queryDocumentSnapshot.getData().get("pt_commision").toString();
                                String pt_damage_=queryDocumentSnapshot.getData().get("pt_damage").toString();
                        String pt_debit_=queryDocumentSnapshot.getData().get("pt_debit").toString();
                                String pt_price_=queryDocumentSnapshot.getData().get("pt_price").toString();
                        String pt_quantity_=queryDocumentSnapshot.getData().get("pt_quantity").toString();
                                String pt_rent_=queryDocumentSnapshot.getData().get("pt_rent").toString();
                        String pt_total_=queryDocumentSnapshot.getData().get("pt_total").toString();
                                String trader_name_=queryDocumentSnapshot.getData().get("trader_name").toString();
                        TextView textView = new TextView(getContext());
                        String k="<b>"+getString(R.string.trader_name_)+"\t</b>"+trader_name_+"<br><b>"+getString(R.string.product_name_)+"\t</b>"+product_name_+"<br>"+"<b>"+getString(R.string.item_from_)+"\t</b>"+"\t</b>"+item_from_+"<br>"+"<b>"+getString(R.string.pt_damage_)+"\t</b>"+pt_damage_+ "<br><b>"+getString( R.string.pt_price_)+"\t</b>"+pt_price_+
                                "<br><b>"+getString( R.string.pt_quantity_)+"\t</b>"+pt_quantity_+
                        "<br><b>"+getString(R.string.pt_debit_)+"\t</b>"+
                                pt_debit_+"<br><b>"+getString(R.string.pt_commision_)+pt_commision_+
                          "<br><b>"+getString(R.string.pt_rent_)+"\t</b>"+pt_rent_+
                        "<br><b>"+getString(R.string.pt_total_)+"\t</b>"+pt_total_;
                            textView.setBackground(getResources().getDrawable(R.drawable.items_bg));
                        int padding = getResources().getDimensionPixelOffset(R.dimen.padding);
                        textView.setPadding(padding,padding,padding,padding);
                        textView.setElevation(4);
                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                        params.setMargins(10,10,10,10);
                        textView.setLayoutParams(params);
                        textView.setText(Html.fromHtml(k));
                        linearLayout.addView(textView);
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