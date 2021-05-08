package com.onesidegamer.raitshare.fragment.inner;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.onesidegamer.raitshare.R;
import com.genonbeta.android.framework.app.Fragment;
import com.genonbeta.android.framework.app.FragmentImpl;
public class ConcurrentTaskFragment
        extends Fragment
        implements FragmentImpl
{
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.layout_concurrent_task_fragment, container, false);

        return view;
    }
}
