package com.example.cm.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity(name = "UserAccountTable")
public class cmUserAccountEntity {
    cmUserAccountEntity(){}
    @Id
    @Column(name = "UserId", nullable = false, length = 20)
    private String userid;

    @Column(name = "UserPw", nullable = false, length = 80)
    private String userpw;

    @Column(name = "RoleName", nullable = false, length = 80)
    private String rolename;
}
