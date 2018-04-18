package com.hrm.dao;


import com.hrm.dao.provider.UserDynaSqlProvider;
import com.hrm.entity.User;
import com.hrm.util.common.HrmConstants;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

public interface UserDao {
    /**
     * 根据用户登陆名和密码查询用户信息
     * @param loginname
     * @param password
     * @return
     */
    @Select("select * from " + HrmConstants.USERTABLE + " where loginname = #{loginname} and password = #{password}")
    User findByLoginnameAndPassword(@Param("loginname") String loginname, @Param("password") String password);

    /**
     * 根据用户id查询用户
     * @param id
     * @return
     */
    @Select("select * from " + HrmConstants.USERTABLE + " where id = #{id}")
    User selectById(Integer id);

    /**
     * 根据用户Id删除用户
     * @param id
     */
    @Delete("delete from " + HrmConstants.USERTABLE + " where id = #{id}")
    void deleteById(Integer id);

    /**
     * 更新用户信息
     * @param user
     */
    @SelectProvider(type= UserDynaSqlProvider.class, method = "updateUser")
    void update(User user);


    /**
     * 分页查询
     * @param params
     * @return
     */
    @SelectProvider(type=UserDynaSqlProvider.class, method="selectWithParam")
    List<User> findByPage(Map<String, Object> params);

    /**
     * 查询用户数量
     * @param params
     * @return
     */
    @SelectProvider(type=UserDynaSqlProvider.class, method="count")
    Integer count(Map<String, Object> params);

    /**
     * 插入用户信息
     * @param user
     */
    @SelectProvider(type=UserDynaSqlProvider.class, method = "insertUser")
    void save(User user);
}
