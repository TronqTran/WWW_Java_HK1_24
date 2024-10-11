package iuh.fit.se.lab_01.services;

import iuh.fit.se.lab_01.repositories.GrantAccessRepository;

public class GrantAccessService {
    private GrantAccessRepository grantAccessRepository;
    public GrantAccessService() {
        this.grantAccessRepository = new GrantAccessRepository();
    }
    public void addGrantAccess(String role_id, String account_id) {
        grantAccessRepository.addGrantAccess(role_id, account_id);
    }
    public void removeGrantAccess(String role_id, String account_id) {
        grantAccessRepository.removeGrantAccess(role_id, account_id);
    }
}
