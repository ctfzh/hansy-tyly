package com.hansy.tyly.admin.system.service;

import java.util.List;

import com.hansy.tyly.admin.dao.model.SysOrg;

public interface ISysOrgService {

	public List<SysOrg> queryOrgList();

	public void saveOrg(SysOrg sysOrg);

	public void updateOrg(SysOrg sysOrg);

	public void deleteOrg(SysOrg sysOrg);

}
