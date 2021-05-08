package com.onesidegamer.raitshare.dialog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.EditText;

import com.onesidegamer.raitshare.R;
abstract public class AbstractSingleTextInputDialog extends AbstractFailureAwareDialog
{
    private final EditText mEditText;
    private final ViewGroup mView;

    public AbstractSingleTextInputDialog(final Context context)
    {
        super(context);

        mView = (ViewGroup) LayoutInflater.from(getContext()).inflate(R.layout.layout_dialog_single_text_input, null);
        mEditText = mView.findViewById(R.id.layout_dialog_single_text_input_text);

        setView(mView);
        setTitle(R.string.text_createFolder);
        setNegativeButton(R.string.butn_close, null);

        mEditText.requestFocus();
    }

    public ViewGroup getContainerView()
    {
        return mView;
    }

    public EditText getEditText()
    {
        return mEditText;
    }
}
