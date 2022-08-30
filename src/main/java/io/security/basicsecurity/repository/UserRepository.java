package io.security.basicsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.security.basicsecurity.domain.Account;

public interface UserRepository extends JpaRepository<Account, Long>{

	Account findByUsername(String username);


}
