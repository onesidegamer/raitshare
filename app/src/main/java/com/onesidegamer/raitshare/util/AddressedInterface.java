package com.onesidegamer.raitshare.util;

import java.net.NetworkInterface;
public class AddressedInterface
{
    private final NetworkInterface networkInterface;
    private final String associatedAddress;

    public AddressedInterface(NetworkInterface networkInterface, String associatedAddress)
    {
        this.networkInterface = networkInterface;
        this.associatedAddress = associatedAddress;
    }

    public String getAssociatedAddress()
    {
        return associatedAddress;
    }

    public NetworkInterface getNetworkInterface()
    {
        return networkInterface;
    }
}
