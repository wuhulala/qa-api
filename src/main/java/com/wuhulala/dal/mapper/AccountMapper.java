package com.wuhulala.dal.mapper;

import org.apache.ibatis.annotations.*;
import com.wuhulala.dal.model.Account;

/**
 * 账户
 *
 * @author Wuhulala
 * @version 1.0
 * @updateTime 2016/12/25
 */
@Mapper
public interface AccountMapper {

    @Select("select * from tb_account where name = #{name} and password = #{password} limit 1")
    Account login(Account account);

    @Select("select * from tb_account where name = #{name}")
    Account findByName(@Param("name") String name);

    @Update("update tb_account set last_login = #{lastLogin} where id = #{id}")
    void updateLastLogin(Account account);

    @Insert("insert into tb_account(name,password) values(#{name},#{password})")
    void insert(Account account);

    @Update("update tb_account set password = #{password} where id = #{id}")
    void updatePassword(Account account);

    @Select("select * from tb_account where id = #{id} limit 1")
    Account findById(@Param("id") Long id);
}
