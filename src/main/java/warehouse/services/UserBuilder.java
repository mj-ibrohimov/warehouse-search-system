package warehouse.services;

import warehouse.domains.UserRole;

@FunctionalInterface
public interface UserBuilder<T> {

    T create(Long id, String username, String password,String fullName,String phoneNumber, UserRole role);

}
