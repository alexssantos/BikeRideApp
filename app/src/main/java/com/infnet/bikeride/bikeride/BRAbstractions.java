package com.infnet.bikeride.bikeride;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class BRAbstractions {

    Activity activity;

    public BRAbstractions(Activity context) {
        this.activity = context;
    }

    public void connectVariableToViewIdAndOnClickMethod(Object... data) {

        if (data.length%3 != 0) {
            Log.i("ABSTRACTIONS", "Incorrect number of arguments passed (" +
                    data.length + "). Must be multiples of 3.");
            return;
        }

        // ---> Loops through ARGUMENTS ARRAY (data)
        for (int i = 0; i<data.length; i += 3) {

            // ---> Assigns and casts
            String        vName           = (String) data[i];
            int           viewId          = (int) data[i+1];
            final String  methodToInvoke  = (String) data[i+2];
            View          v               = this.activity.findViewById(viewId);

            try {
                Field field = activity.getClass().getDeclaredField(vName);
                field.setAccessible(true);
                field.set(activity, v);
            }

            catch (NoSuchFieldException e)   {
                e.printStackTrace();
            }

            catch (IllegalAccessException e) {
                e.printStackTrace();
            }


            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (methodToInvoke.equals("")) return;

                    try {
                        Method sentMethod = activity.getClass().getDeclaredMethod(methodToInvoke);
                        sentMethod.setAccessible(true);
                        sentMethod.invoke(activity);
                    }

                    catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    public void connectVariableToViewIdAndOnChangeMethod(Object... data) {

        if (data.length%3 != 0) {
            Log.i("ABSTRACTIONS", "Incorrect number of arguments passed (" +
                    data.length + "). Must be multiples of 3.");
            return;
        }

        // ---> Loops through ARGUMENTS ARRAY (data)
        for (int i = 0; i<data.length; i += 3) {

            // ---> Assigns and casts
            String        vName           = (String) data[i];
            int           viewId          = (int) data[i+1];
            final String  methodToInvoke  = (String) data[i+2];
            View          v               = this.activity.findViewById(viewId);

            try {
                Field field = activity.getClass().getDeclaredField(vName);
                field.setAccessible(true);
                field.set(activity, v);
            }

            catch (NoSuchFieldException e)   {
                e.printStackTrace();
            }

            catch (IllegalAccessException e) {
                e.printStackTrace();
            }


            ((EditText) v).addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                    if (methodToInvoke.equals("")) return;

                    try {
                        Method sentMethod = activity.getClass().getDeclaredMethod(methodToInvoke);
                        sentMethod.setAccessible(true);
                        sentMethod.invoke(activity);
                    }

                    catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        }
    }

    public boolean checkForNullEditTextContent (EditText... views) {

        for (EditText view : views) {

            if (view.getText().toString().equals("")) {
                return true;
            }
        }

        return false;
    }

    public boolean checkForViewVisibility (View... views) {

        for (View view : views) {

            if (view.getVisibility() == View.VISIBLE) {

                return true;
            }
        }

        return false;
    }

    public void setEditTextContentToEmpty (EditText... views) {

        for (EditText view : views) {

            view.setText("");
        }
    }

}
