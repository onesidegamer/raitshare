package com.onesidegamer.raitshare.app;

import com.onesidegamer.raitshare.widget.EditableListAdapter;
public interface EditableListFragmentModelImpl<V extends EditableListAdapter.EditableViewHolder>
{
    void setLayoutClickListener(EditableListFragment.LayoutClickListener<V> clickListener);
}
