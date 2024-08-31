package com.dysaniaer.usercenter.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dysaniaer.usercenter.model.domain.User;
import org.apache.ibatis.annotations.Mapper;

/**
* @author RationalDysaniaer
* @description 针对表【user(用户)】的数据库操作Mapper
* @createDate 2024-08-30 23:23:28
* @Entity com.dysaniaer.model.domain.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {

}




