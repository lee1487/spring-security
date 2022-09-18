package io.security.basicsecurity.security.service;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import io.security.basicsecurity.domain.entity.Account;
import lombok.Data;

@Data
public class AccountContext extends User {
  private Account account;

  public AccountContext(Account account, List<GrantedAuthority> roles) {
    super(account.getUsername(), account.getPassword(), roles);
    this.account = account;
  }
}
