package com.onesidegamer.raitshare.config;

public class AppConfig
{
    public final static int
            SERVER_PORT_COMMUNICATION = 1128,
            SERVER_PORT_SEAMLESS = 58762,
            SERVER_PORT_WEBSHARE = 58732,
            SERVER_PORT_UPDATE_CHANNEL = 58765,
            DEFAULT_SOCKET_TIMEOUT = 5000,
            DEFAULT_SOCKET_TIMEOUT_LARGE = 40000,
            DEFAULT_NOTIFICATION_DELAY = 2000,
            SUPPORTED_MIN_VERSION = 62,
            NICKNAME_LENGTH_MAX = 32,
            BUFFER_LENGTH_DEFAULT = 8096,
            BUFFER_LENGTH_SMALL = 1024,
            DELAY_CHECK_FOR_UPDATES = 21600,
            PHOTO_SCALE_FACTOR = 100,
            WEB_SHARE_CONNECTION_MAX = 20;

    public final static String EMAIL_DEVELOPER = "kailasrathod9898@gmail.com";
    public final static String URI_REPO_APP_UPDATE = "https://api.github.com/repos/onesidegamer/raitshare/releases";
    public final static String URI_REPO_APP_CONTRIBUTORS = "https://api.github.com/repos/onesidegamer/raitshare/contributors";
    public final static String URI_GOOGLE_PLAY = "https://play.google.com/store/apps/details?id=com.onesidegamer.raitshare";
    public final static String URI_REPO_APP = "http://github.com/onesidegamer/raitshare";
    public final static String URI_REPO_ORG = "http://github.com/onesidegamer";
    public final static String URI_GITHUB_PROFILE = "https://github.com/kailas-rathod";
    public final static String PREFIX_ACCESS_POINT = "TS_";
    public final static String EXT_FILE_PART = "tshare";
    public final static String NETWORK_INTERFACE_WIFI = "wlan0";
    public final static String NDS_COMM_SERVICE_NAME = "TSComm";
    public final static String NDS_COMM_SERVICE_TYPE = "_tscomm._tcp.";

    public final static String[] DEFAULT_DISABLED_INTERFACES = new String[]{"rmnet"};

}
