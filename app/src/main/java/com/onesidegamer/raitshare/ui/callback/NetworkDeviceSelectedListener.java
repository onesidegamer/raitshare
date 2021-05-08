package com.onesidegamer.raitshare.ui.callback;

import com.onesidegamer.raitshare.object.NetworkDevice;

public interface NetworkDeviceSelectedListener
{
    boolean onNetworkDeviceSelected(NetworkDevice networkDevice, NetworkDevice.Connection connection);

    boolean isListenerEffective();
}
