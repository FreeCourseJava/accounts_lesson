package org.homework.factory;

public class GeneralFactory {

    private static GeneralFactory instance;
    private ControllerFactory controllerFactory;
    private ServiceFactory serviceFactory;
    private RepositoryFactory repositoryFactory;

    private GeneralFactory() {
    }

    public static GeneralFactory getInstance() {
        if (instance == null) {
            instance = new GeneralFactory();
        }
        return instance;
    }

    public ControllerFactory getControllerFactory() {
        if (controllerFactory == null) {
            controllerFactory = new ControllerFactory();
        }
        return controllerFactory;
    }

    public ServiceFactory getServiceFactory() {
        if (serviceFactory == null) {
            serviceFactory = new ServiceFactory();
        }
        return serviceFactory;
    }

    public RepositoryFactory getRepositoryFactory() {
        if (repositoryFactory == null) {
            repositoryFactory = new RepositoryFactory();
        }
        return repositoryFactory;
    }
}
