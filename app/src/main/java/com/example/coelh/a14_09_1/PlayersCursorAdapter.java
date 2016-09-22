package com.example.coelh.a14_09_1;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

/**
 * Created by coelh on 21/09/2016.
 */
public class PlayersCursorAdapter extends CursorAdapter {

    public PlayersCursorAdapter(Context context, int simple_list_item_1,
                                Cursor cursor, String[] strings,
                                int[] ints, int i){
        super(context, cursor, 0);
    }

     @Override
    public void bindView(View view, Context context, Cursor cursor){
         TextView txtName =  (TextView)view.findViewById(R.id.txtName);
         TextView txtStatus =  (TextView)view.findViewById(R.id.txtStatus);
         ImageView imgState = (ImageView)view.findViewById(R.id.imgState);
         RatingBar rtbRating = (RatingBar)view.findViewById(R.id.rtbEstrelas);
         Button btnRemove = (Button)view.findViewById(R.id.btnRemove);
         Button btnUpdate = (Button)view.findViewById(R.id.btnModify);


         txtName.setText(cursor.getString(1));

         int status = cursor.getInt(3);
         int formacao = cursor.getInt(4);

         if(status == 0){
             txtStatus.setText("Programador");
         }
         else{
             txtStatus.setText("Curioso");
         }
         rtbRating.setRating(formacao);

         int state = cursor.getInt(2);
         TypedArray flags = context.getResources().obtainTypedArray(R.array.flags);
         imgState.setImageDrawable(flags.getDrawable(state));

         btnRemove.setTag(0x80000001, cursor.getInt(0));
         btnUpdate.setTag(0x80000002, cursor.getInt(0));

     }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup){
        return LayoutInflater.from(context).inflate(R.layout.item_player, null);
    }

}
