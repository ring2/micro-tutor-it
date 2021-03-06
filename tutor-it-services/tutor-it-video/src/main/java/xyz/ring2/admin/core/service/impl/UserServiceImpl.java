package xyz.ring2.admin.core.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import xyz.ring2.admin.common.CommonStatus;
import xyz.ring2.admin.common.RestResult;
import xyz.ring2.admin.core.entity.Role;
import xyz.ring2.admin.core.entity.User;
import xyz.ring2.admin.core.entity.vo.UserVo;
import xyz.ring2.admin.core.mapper.UserMapper;
import xyz.ring2.admin.core.service.IRoleService;
import xyz.ring2.admin.core.service.IUserService;
import xyz.ring2.admin.exception.ServiceException;
import xyz.ring2.admin.security.jwt.JWTConfig;
import xyz.ring2.admin.utils.JwtTokenUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author ring2
 * @since 2020-02-04
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    IRoleService roleService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Override
    public User findUserByUsername(String username) {
        User user = getOne(new QueryWrapper<User>().eq("username", username));
        if (ObjectUtil.isNotEmpty(user)) {
            List<Role> roles = roleService.selectRolesByUserId(user.getId());
            user.setRoles(roles);
        } else {
            throw new ServiceException(CommonStatus.FAILED_FOUND_NAME);
        }
        return user;
    }

    @Override
    public Map<String, Object> selUserListWithRoleInfo(Integer pageNo, Integer pageSize, String userName) {
        Map<String, Object> data = new HashMap<>();
        Page<User> page = new Page<>();
        page.setCurrent(pageNo);
        page.setSize(pageSize);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (StrUtil.isNotEmpty(userName)) {
            queryWrapper.like("username", "%" + userName + "%");
        }
        queryWrapper.orderByAsc("u.id");

        Page<UserVo> userPage = this.baseMapper.selUserListWithRoleInfo(page, queryWrapper);
        data.put("total", userPage.getTotal());
        data.put("userList", userPage.getRecords());
        return data;
    }

    /**
     * 新增用户
     *
     * @param user
     * @return
     */
    @Override
    public boolean saveUser(User user) {
        List<User> list = this.lambdaQuery().eq(User::getUsername, user.getUsername()).list();
        if (list.size() > 0) {
            return false;
        }
        String encodePwd = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodePwd);
        return save(user);
    }

    @Override
    public RestResult validateUser(String username, String password, String userType) {
        Map<String, String> data = new HashMap<>();
        User user = null;
        if ("教师".equals(userType)) {
            user = findUserByUsername("teacher");
        } else {
            user = findUserByUsername(username);
        }
        if (user == null) {
            return RestResult.failureOfUsername();
        }
        boolean matches = passwordEncoder.matches(password, user.getPassword());
        if (!matches) {
            return RestResult.failureOfPassword();
        }
        List<User> list = lambdaQuery().eq(User::getUsername, username).list();
        if (list.size() > 0) {
            if ("教师".equals(userType)) {
                data.put("token", jwtTokenUtil.generateToken(new User().setUsername("teacher")));
            } else {
                data.put("token", jwtTokenUtil.generateToken(new User().setUsername(username)));
            }
            data.put("tokenHead", JWTConfig.tokenHead);
            return RestResult.success(data);
        }
        return RestResult.failure();
    }
}
