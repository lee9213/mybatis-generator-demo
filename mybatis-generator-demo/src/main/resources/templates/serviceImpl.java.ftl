package ${package.serviceImpl};

import com.giants.common.exception.BusinessException;
import com.giants.common.tools.Page;
import com.giants.common.tools.PageCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ${package.entity}.${table.entityName};
import ${package.mapper}.${table.mapperName};
import ${package.service}.${table.serviceName};
<#if strategy.superServiceImplClass?default("")?length gt 1>
import ${strategy.superServiceImplClass};
</#if>

/**
 * <p>
 * ${table.comment!} 服务实现类
 * </p>
 *
 * @author ${global.author}
 * @since ${global.date}
 */
@Service
<#if strategy.superServiceImplClass?default("")?length gt 1>
public class ${table.serviceImplName} extends ${strategy.superServiceImplClass}<${table.mapperName}, ${table.entityName}> implements ${table.serviceName} {
<#else>
public class ${table.serviceImplName} implements ${table.serviceName} {
</#if>
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ${table.mapperName} ${table.mapperName};

     /**
     * add 新增接口
     *
     * @param scmTransactionSourceVO
     * @param loginEmployee
     */
    @Override
    public ${table.entityName} add(ScmTransactionSourceVO scmTransactionSourceVO, LoginEmployee loginEmployee) throws BusinessException {
        validAdd(scmTransactionSourceVO, loginEmployee);
        ${table.entityName} scmTransactionSource = convertScmTransactionSource(scmTransactionSourceVO, loginEmployee);
        scmTransactionSourceMapper.insert(scmTransactionSource);
        return scmTransactionSource;
    }

    /**
     * 新增接口验证数据
     *
     * @param scmTransactionSourceVO
     * @param loginEmployee
     */
    private void validAdd(ScmTransactionSourceVO scmTransactionSourceVO, LoginEmployee loginEmployee) throws BusinessException {
        if (scmTransactionSourceVO == null) {
            throw new BusinessException("add.scmTransactionSource.error", "新增内容为空");
        }
        //TODO 新增接口的其他部分的验证

    }

    /**
     * 从ScmTransactionSourceVO 转换到 ScmTransactionSource
     *
     * @param scmTransactionSourceVO
     * @param loginEmployee
     */
    private ScmTransactionSource convertScmTransactionSource(ScmTransactionSourceVO scmTransactionSourceVO, LoginEmployee loginEmployee) throws BusinessException {
        if (scmTransactionSourceVO == null) {
            return new ScmTransactionSource();
        }
        //TODO 类似的验证自己添加
        ScmTransactionSource scmTransactionSource = new ScmTransactionSource();
        BeanUtils.copyProperties(scmTransactionSourceVO, scmTransactionSource);
        return scmTransactionSource;
    }

    /**
     * detail 详情接口
     *
     * @param id
     * @param loginEmployee
     */
    @Override
    public ScmTransactionSourceVO detail(Integer id, LoginEmployee loginEmployee) throws BusinessException {
        if (id == null) {
            throw new BusinessException("detail.scmTransactionSource.error", "id为空");
        }
        ScmTransactionSource scmTransactionSource = scmTransactionSourceMapper.selectByPrimaryKey(id);
        ScmTransactionSourceVO scmTransactionSourceVO = convertScmTransactionSourceVO(scmTransactionSource, loginEmployee);
        return scmTransactionSourceVO;
    }

    /**
     * 从 ScmTransactionSource	转换到 	ScmTransactionSourceVO
     *
     * @param scmTransactionSource
     * @param loginEmployee
     */
    private ScmTransactionSourceVO convertScmTransactionSourceVO(ScmTransactionSource scmTransactionSource, LoginEmployee loginEmployee) {
        if (scmTransactionSource == null) {
            return new ScmTransactionSourceVO();
        }
        ScmTransactionSourceVO scmTransactionSourceVO = new ScmTransactionSourceVO();
        BeanUtils.copyProperties(scmTransactionSource, scmTransactionSourceVO);
        return scmTransactionSourceVO;
    }

    /**
     * update 更新接口
     *
     * @param scmTransactionSourceVO
     * @param loginEmployee
     */
    @Override
    public void update(ScmTransactionSourceVO scmTransactionSourceVO, LoginEmployee loginEmployee) throws BusinessException {
        validUpdate(scmTransactionSourceVO, loginEmployee);
        ScmTransactionSource scmTransactionSource = convertUpdateScmTransactionSource(scmTransactionSourceVO, loginEmployee);
        scmTransactionSourceMapper.updateByPrimaryKeySelective(scmTransactionSource);
    }

    /**
     * 验证能否修改
     *
     * @param scmTransactionSourceVO
     * @param loginEmployee
     */
    private void validUpdate(ScmTransactionSourceVO scmTransactionSourceVO, LoginEmployee loginEmployee) throws BusinessException {
        if (scmTransactionSourceVO == null) {
            throw new BusinessException("update.scmTransactionSource.error", "更新内容为空");
        }
        //TODO 其他验证自己加
    }

    /**
     * 修改对象转换成数据库对象
     *
     * @param scmTransactionSourceVO
     * @param loginEmployee
     */
    private ScmTransactionSource convertUpdateScmTransactionSource(ScmTransactionSourceVO scmTransactionSourceVO, LoginEmployee loginEmployee) throws BusinessException {

        ScmTransactionSource scmTransactionSource = scmTransactionSourceMapper.selectByPrimaryKey(scmTransactionSourceVO.getId());
        BeanUtils.copyProperties(scmTransactionSourceVO, scmTransactionSource);
//		scmTransactionSource.setLastUpdateUserId(loginEmployee.getUserId());
//		scmTransactionSource.setLastUpdateTime(new Date());
        return scmTransactionSource;
    }

    /**
     * delete  删除接口
     *
     * @param id
     * @param loginEmployee
     */
    @Override
    public void delete(Integer id, LoginEmployee loginEmployee) throws BusinessException {
        validDelete(id, loginEmployee);
        ScmTransactionSource scmTransactionSource = scmTransactionSourceMapper.selectByPrimaryKey(id);
//		scmTransactionSource.setLastUpdateTime(new Date());
//		scmTransactionSource.setLastUpdateUserId(loginEmployee.getUserId());
        // 默认逻辑删除
//		scmTransactionSource.setIsDelete(true);
        scmTransactionSourceMapper.updateByPrimaryKey(scmTransactionSource);
    }

    /**
     * 验证能否删除
     *
     * @param id
     * @param loginEmployee
     */
    private void validDelete(Integer id, LoginEmployee loginEmployee) {
        if (id == null) {
            throw new BusinessException("delete.scmTransactionSource.error", "id为空");
        }

        ScmTransactionSource scmTransactionSource = scmTransactionSourceMapper.selectByPrimaryKey(id);
        if (scmTransactionSource == null) {
            throw new BusinessException("delete.scmTransactionSource.error", "数据异常");
        }
        //TODO 删除方面其他验证自己加,例如状态
    }

    /**
     * 列表
     *
     * @param pageCondition
     * @param loginEmployee
     * @return
     */
    @Override
    public Page<ScmTransactionSourceVO> list(PageCondition pageCondition, LoginEmployee loginEmployee) throws BusinessException {
    pageCondition.getFilters().put("tenant_id", loginEmployee.getTenantId());
    pageCondition.getFilters().put("tenantCode", loginEmployee.getTenantCode());
    //		return new Page<>(pageCondition.getPageNo(), pageCondition.getPageSize(),
    //			scmTransactionSourceMapper.count(pageCondition),
    //			converScmTransactionSourceList(scmTransactionSourceMapper.select(pageCondition)));
    return null;
    }
}