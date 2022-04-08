package com.bartoszek.lab7.DAO;

import com.bartoszek.lab7.DTO.Installation;

import java.util.Set;

public interface InstallationDAO {
    Installation getInstallation(Integer installationID);
    Set<Installation> getAllInstallations();
    boolean insertInstallation(Installation installation);
    boolean removeInstallation(Integer installationID);
    boolean updateInstallation(Installation installation);

}
