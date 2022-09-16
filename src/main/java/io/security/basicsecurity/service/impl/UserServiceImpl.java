package io.security.basicsecurity.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.security.basicsecurity.domain.entity.Account;
import io.security.basicsecurity.repository.UserRepository;
import io.security.basicsecurity.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;

	@Transactional
	@Override
	public void createUser(Account account) {

		userRepository.save(account);

	}

}
