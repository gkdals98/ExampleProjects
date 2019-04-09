package com.example.cm.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.cm.entity.cmUserAccountEntity;
import com.example.cm.entity.repository.cmUserAccountRepository;

@Service("userDetailService")
public class cmUserAccountService implements UserDetailsService{
    @Autowired
    cmUserAccountRepository repository;

    //유저가 로그인을 하게되면 UserDetailsService의 loadUserByUsername으로 유저를 조회해
    //AuthencationProvider로 리턴해주고 (한 마디로 자동화 상태로 이 메서드만 있어도 알아서 ID를 검사.)
    //AuthencationProvider의 authenticate메소드에서 유저가 입력한 비밀번호와 DB의 비밀번호를 대조하게된다.
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new cmUserDetailsImpl(repository.findById(username).orElseThrow(() -> new UsernameNotFoundException(username)));
    }

    public void save(cmUserAccountEntity entity) {
        repository.save(entity);
    }

    public List<cmUserAccountEntity> findAll(){
        List<cmUserAccountEntity> list = new ArrayList<cmUserAccountEntity>();
        for(cmUserAccountEntity entity : repository.findAll()) {
            list.add(entity);
        }
        return list;
    }

    public boolean isAvailableID(String id) {
        if(repository.countByUserid(id) > 0) {
            return false;
        }else {
            return true;
        }
    }

    public boolean isAvailableLogin(String id, String pw) {
        Optional<cmUserAccountEntity> account = repository.findById(id);
        if(account == null) {
            return false;
        }else {
            if(account.get().getUserpw().equals(pw)) {
                return true;
            }else {
                return false;
            }
        }
    }
}
