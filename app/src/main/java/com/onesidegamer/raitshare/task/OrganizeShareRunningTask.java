package com.onesidegamer.raitshare.task;

import android.net.Uri;

import com.onesidegamer.raitshare.R;
import com.onesidegamer.raitshare.activity.AddDevicesToTransferActivity;
import com.onesidegamer.raitshare.activity.ShareActivity;
import com.onesidegamer.raitshare.activity.ViewTransferActivity;
import com.onesidegamer.raitshare.database.AccessDatabase;
import com.onesidegamer.raitshare.object.TransferGroup;
import com.onesidegamer.raitshare.object.TransferObject;
import com.onesidegamer.raitshare.service.WorkerService;
import com.onesidegamer.raitshare.util.AppUtils;
import com.genonbeta.android.database.SQLQuery;
import com.genonbeta.android.database.SQLiteDatabase;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class OrganizeShareRunningTask extends WorkerService.RunningTask<ShareActivity>
{
    private final List<Uri> mFileUris;
    private final List<CharSequence> mFileNames;

    public OrganizeShareRunningTask(List<Uri> fileUris, List<CharSequence> fileNames)
    {
        mFileUris = fileUris;
        mFileNames = fileNames;
    }

    @Override
    public void onRun()
    {
        final WorkerService.RunningTask thisTask = this;

        if (getAnchorListener() != null) {
            getAnchorListener().getProgressBar().setMax(mFileUris.size());
            getAnchorListener().updateText(thisTask, getService().getString(R.string.mesg_organizingFiles));
        }

        final List<ShareActivity.SelectableStream> measuredObjects = new ArrayList<>();
        final List<TransferObject> pendingObjects = new ArrayList<>();
        final TransferGroup groupInstance = new TransferGroup(AppUtils.getUniqueNumber());

        for (int position = 0; position < mFileUris.size(); position++) {
            if (getInterrupter().interrupted())
                break;

            publishStatusText(getService().getString(R.string.text_transferStatusFiles,
                    position, mFileUris.size()));

            if (getAnchorListener() != null) {
                getAnchorListener().updateProgress(getAnchorListener().getProgressBar().getMax(),
                        getAnchorListener().getProgressBar().getProgress() + 1);
            }

            Uri fileUri = mFileUris.get(position);
            String fileName = mFileNames != null ? String.valueOf(mFileNames.get(position)) : null;

            try {
                ShareActivity.SelectableStream selectableStream =
                        new ShareActivity.SelectableStream(getService(), fileUri, null);

                if (selectableStream.getDocumentFile().isDirectory())
                    ShareActivity.createFolderStructure(selectableStream.getDocumentFile(),
                            selectableStream.getDocumentFile().getName(), measuredObjects,
                            this);
                else {
                    if (fileName != null)
                        selectableStream.setFriendlyName(fileName);

                    measuredObjects.add(selectableStream);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        for (ShareActivity.SelectableStream selectableStream : measuredObjects) {
            if (getInterrupter().interrupted())
                break;

            publishStatusText(selectableStream.getSelectableTitle());

            long requestId = AppUtils.getUniqueNumber();

            TransferObject transferObject = new TransferObject(requestId,
                    groupInstance.groupId,
                    selectableStream.getSelectableTitle(),
                    selectableStream.getDocumentFile().getUri().toString(),
                    selectableStream.getDocumentFile().getType(),
                    selectableStream.getDocumentFile().length(), TransferObject.Type.OUTGOING);

            if (selectableStream.getDirectory() != null)
                transferObject.directory = selectableStream.getDirectory();

            pendingObjects.add(transferObject);
        }

        if (getAnchorListener() != null)
            getAnchorListener().updateText(thisTask, getService().getString(R.string.mesg_completing));

        AppUtils.getDatabase(getService()).insert(pendingObjects, new SQLiteDatabase.ProgressUpdater()
        {
            @Override
            public void onProgressChange(int total, int current)
            {
                if (getAnchorListener() != null)
                    getAnchorListener().updateProgress(total, current);
            }

            @Override
            public boolean onProgressState()
            {
                return !getInterrupter().interrupted();
            }
        });

        if (getInterrupter().interrupted()) {
            AppUtils.getDatabase(getService()).remove(new SQLQuery.Select(AccessDatabase.TABLE_TRANSFER)
                    .setWhere(String.format("%s = ?", AccessDatabase.FIELD_TRANSFER_GROUPID),
                            String.valueOf(groupInstance.groupId)));
        } else {
            AppUtils.getDatabase(getService()).insert(groupInstance);

            ViewTransferActivity.startInstance(getService(), groupInstance.groupId);
            AddDevicesToTransferActivity.startInstance(getService(), groupInstance.groupId);
        }

        if (getAnchorListener() != null)
            getAnchorListener().finish();
    }
}
