package com.jsc.hotspot.api.service;

import com.github.pagehelper.PageHelper;
import com.jsc.hotspot.db.dao.AdminMapper;
import com.jsc.hotspot.db.domain.Admin;
import com.jsc.hotspot.db.domain.AdminExample;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class AdminService {
    private final Admin.Column[] result =new Admin.Column[]{
        Admin.Column.id, Admin.Column.username, Admin.Column.avatar, Admin.Column.roleIds};

    @Autowired
    private AdminMapper adminMapper;

    public List<Admin> findAdmin(String username){
        AdminExample example=new AdminExample();
        example.or().andUsernameEqualTo(username).andDeletedEqualTo(false);
        return adminMapper.selectByExample(example);
    }

    public Admin findAdmin(Long  id){
        return  adminMapper.selectByPrimaryKey(id);
    }

    public  void deleteById(Long  id){adminMapper.logicalDeleteByPrimaryKey(id);}

    public  void add(Admin admin){
        admin.setCreateTime(LocalDateTime.now());
        admin.setUpdateTime(LocalDateTime.now());
        adminMapper.insertSelective(admin);
    }

   public Admin fingById(Long id){
        return adminMapper.selectByPrimaryKeySelective(id,result);
   }

   public List<Admin> all(){
        AdminExample example=new AdminExample();
        example.or().andDeletedEqualTo(false);
        return adminMapper.selectByExample(example);
   }

   public int updateById(Admin admin){
       admin.setUpdateTime(LocalDateTime.now());
       return adminMapper.updateByPrimaryKeySelective(admin);
   }

    public List<Admin> querySelective(String username, Integer page, Integer limit) {
        AdminExample example = new AdminExample();
        AdminExample.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(username)) {
            criteria.andUsernameLike("%" + username + "%");
        }
        criteria.andDeletedEqualTo(false);
        PageHelper.startPage(page, limit);
        return adminMapper.selectByExampleSelective(example, result);
    }
}
