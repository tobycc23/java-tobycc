package com.tobycc.java.learning.rmi;

/**
 * Created by toby.christey-clover on 20/04/2018.
 *
 * SERVER SIDE RMI
 */
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Compute extends Remote {
    <T> T executeTask(Task<T> t) throws RemoteException;
}