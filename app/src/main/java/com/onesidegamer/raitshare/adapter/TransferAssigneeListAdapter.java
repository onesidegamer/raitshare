package com.onesidegamer.raitshare.adapter;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.onesidegamer.raitshare.R;
import com.onesidegamer.raitshare.graphics.drawable.TextDrawable;
import com.onesidegamer.raitshare.object.ShowingAssignee;
import com.onesidegamer.raitshare.object.TransferGroup;
import com.onesidegamer.raitshare.util.AppUtils;
import com.onesidegamer.raitshare.util.NetworkDeviceLoader;
import com.onesidegamer.raitshare.util.TextUtils;
import com.onesidegamer.raitshare.util.TransferUtils;
import com.onesidegamer.raitshare.widget.EditableListAdapter;

import java.util.List;
public class TransferAssigneeListAdapter extends EditableListAdapter<ShowingAssignee, EditableListAdapter.EditableViewHolder>
{
    private TransferGroup mGroup;
    private final TextDrawable.IShapeBuilder mIconBuilder;

    public TransferAssigneeListAdapter(Context context)
    {
        super(context);
        mIconBuilder = AppUtils.getDefaultIconBuilder(context);
    }

    @NonNull
    @Override
    public EditableViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        return new EditableViewHolder(getInflater().inflate(
                isHorizontalOrientation() || isGridLayoutRequested()
                        ? R.layout.list_assignee_grid
                        : R.layout.list_assignee, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull EditableViewHolder holder, int position)
    {
        ShowingAssignee assignee = getList().get(position);

        ImageView image = holder.getView().findViewById(R.id.image);
        TextView text1 = holder.getView().findViewById(R.id.text1);
        TextView text2 = holder.getView().findViewById(R.id.text2);

        text1.setText(assignee.device.nickname);
        text2.setText(TextUtils.getAdapterName(getContext(), assignee.connection));
        NetworkDeviceLoader.showPictureIntoView(assignee.device, image, mIconBuilder);
    }

    @Override
    public List<ShowingAssignee> onLoad()
    {
        return TransferUtils.loadAssigneeList(AppUtils.getDatabase(getContext()), mGroup.groupId);
    }

    public TransferAssigneeListAdapter setGroup(TransferGroup group)
    {
        mGroup = group;
        return this;
    }
}
