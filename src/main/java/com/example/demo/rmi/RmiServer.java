package com.example.demo.rmi;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Hashtable;
import java.util.Properties;

import static javax.naming.Context.INITIAL_CONTEXT_FACTORY;
import static javax.naming.Context.PROVIDER_URL;

public class RmiServer {
   public static Boolean messageIsBeingWritten = false;
    private static final int RANDOM_PORT_HINT = 0;
    private static final String RMI_PORT_KEY = "rmi.port";
    private static final String INITIAL_CONTEXT = "com.sun.jndi.fscontext.RefFSContextFactory";
    private static final String CONFIGURATION_FILE_NAME = "conf.properties";
    private static final String PROVIDER_URL_LOC= "file:/c:/";


    public static void main(String[] args) {
        try {
            Properties properties = new Properties();
            properties.setProperty(INITIAL_CONTEXT_FACTORY,  INITIAL_CONTEXT);
            properties.setProperty(PROVIDER_URL,PROVIDER_URL_LOC);

            InitialContext ctx = new InitialContext(properties);
            Object configurationFileNameObject = ctx.lookup(CONFIGURATION_FILE_NAME);


             Properties props = new Properties();
             props.load(new FileReader(configurationFileNameObject.toString()));
             String rmiPortString = props.getProperty(RMI_PORT_KEY);

             Registry registry = LocateRegistry.createRegistry(Integer.parseInt(rmiPortString));
             RemoteService remoteService = new RemoteServiceImpl();
             RemoteService skeleton = (RemoteService) UnicastRemoteObject.exportObject(remoteService, RANDOM_PORT_HINT);
            registry.rebind(RemoteService.REMOTE_OBJECT_NAME, skeleton);
            System.err.println("Object registered in RMI registry");
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NamingException e) {
             throw new RuntimeException(e);
         } catch (IOException e) {
             throw new RuntimeException(e);
         }

    }

    private static Hashtable<?, ?> configureEnvironment() {
        return new Hashtable<>() {
            {
                put(INITIAL_CONTEXT_FACTORY, INITIAL_CONTEXT_FACTORY);
                put(PROVIDER_URL, PROVIDER_URL);
            }
        };
    }
}
