package com.twilight.repository;

import com.twilight.model.SysPermission;
import com.twilight.orm.base.BaseRepository;

/**
 * SysPermission持久化类
 * @author twilight
 * Repository：仅仅是一个标识，表明任何继承它的均为仓库接口类。
  CrudRepository：继承Repository，实现了一组CRUD操作相关的方法。 PagingAndSortingRepository：继承CrudRepository，实现了一组分页排序相关的方法。
  JpaRepository：继承PagingAndSortingRepository，实现一组JPA规范的相关方法。
      自定义的XxxxRepository：需要继承JpaRepository，这样的XxxxRepository接口就具备了通用的数据访问控制层的能力。
  JpaSpecificationExecutor：不属于Repository体系，实现一组JPACriteria查询相关的方法。
 *
 */
public interface SysPermissionRepository extends BaseRepository<SysPermission, Integer>{

}
