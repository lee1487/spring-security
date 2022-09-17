package io.security.basicsecurity.service;

import java.util.List;

import io.security.basicsecurity.domain.dto.AccountDto;
import io.security.basicsecurity.domain.entity.Account;

public interface UserService {

	void createUser(Account account);

	void modifyUser(AccountDto accountDto);

	List<Account> getUsers();

	AccountDto getUser(Long id);

	void deleteUser(Long idx);
}
