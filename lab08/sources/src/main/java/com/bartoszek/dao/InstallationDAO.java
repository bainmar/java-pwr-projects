package com.bartoszek.dao;

import java.util.Set;

import com.bartoszek.dto.Installation;

public interface InstallationDAO {
    Installation getInstallation(Integer installationID);
    Set<Installation> getAllInstallations();
    boolean insertInstallation(Installation installation);
    boolean removeInstallation(Integer installationID);
    boolean updateInstallation(Installation installation);

}
