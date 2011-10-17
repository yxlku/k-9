package com.fsck.k9.mail;

import java.util.Map;
import com.fsck.k9.Account;

/**
 * This is an abstraction to get rid of the store- and transport-specific URIs.
 *
 * <p>
 * Right now it's only used for settings import/export. But the goal is to get rid of
 * store/transport URIs altogether.
 * </p>
 *
 * @see Account#getStoreUri()
 * @see Account#getTransportUri()
 */
public class ServerSettings {
    /**
     * Name of the store or transport type (e.g. "IMAP").
     */
    public final String type;

    /**
     * The host name of the server.
     *
     * {@code null} if not applicable for the store or transport.
     */
    public final String host;

    /**
     * The port number of the server.
     *
     * {@code -1} if not applicable for the store or transport.
     */
    public final int port;

    /**
     * The type of connection security to be used when connecting to the server.
     *
     * {@link ConnectionSecurity#NONE} if not applicable for the store or transport.
     */
    public final ConnectionSecurity connectionSecurity;

    /**
     * The authentication method to use when connecting to the server.
     *
     * {@code null} if not applicable for the store or transport.
     */
    public final String authenticationType;

    /**
     * The username part of the credentials needed to authenticate to the server.
     *
     * {@code null} if not applicable for the store or transport.
     */
    public final String username;

    /**
     * The password part of the credentials needed to authenticate to the server.
     *
     * {@code null} if not applicable for the store or transport.
     */
    public final String password;


    /**
     * Creates a new {@code ServerSettings} object.
     *
     * @param type
     *         see {@link ServerSettings#type}
     * @param host
     *         see {@link ServerSettings#host}
     * @param port
     *         see {@link ServerSettings#port}
     * @param connectionSecurity
     *         see {@link ServerSettings#connectionSecurity}
     * @param authenticationType
     *         see {@link ServerSettings#authenticationType}
     * @param username
     *         see {@link ServerSettings#username}
     * @param password
     *         see {@link ServerSettings#password}
     */
    public ServerSettings(String type, String host, int port,
            ConnectionSecurity connectionSecurity, String authenticationType, String username,
            String password) {
        this.type = type;
        this.host = host;
        this.port = port;
        this.connectionSecurity = connectionSecurity;
        this.authenticationType = authenticationType;
        this.username = username;
        this.password = password;
    }

    /**
     * Creates an "empty" {@code ServerSettings} object.
     *
     * Everything but {@link ServerSettings#type} is unused.
     *
     * @param type
     *         see {@link ServerSettings#type}
     */
    public ServerSettings(String type) {
        this.type = type;
        host = null;
        port = -1;
        connectionSecurity = ConnectionSecurity.NONE;
        authenticationType = null;
        username = null;
        password = null;
    }

    /**
     * Returns store- or transport-specific settings as key/value pair.
     *
     * <p>Classes that inherit from this one are expected to override this method.</p>
     */
    public Map<String, String> getExtra() {
        return null;
    }

    protected void putIfNotNull(Map<String, String> map, String key, String value) {
        if (value != null) {
            map.put(key, value);
        }
    }

    public ServerSettings newPassword(String newPassword) {
        return new ServerSettings(type, host, port, connectionSecurity, authenticationType,
                username, newPassword);
    }
}