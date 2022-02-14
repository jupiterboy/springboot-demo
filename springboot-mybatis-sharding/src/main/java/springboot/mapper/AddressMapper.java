package springboot.mapper;

import org.apache.ibatis.annotations.Mapper;
import springboot.entity.Address;

@Mapper
public interface AddressMapper {
    /**
     * 保存
     */
    void save(Address address);

    /**
     * 查询
     * @param id
     * @return
     */
    Address get(Long id);
}
