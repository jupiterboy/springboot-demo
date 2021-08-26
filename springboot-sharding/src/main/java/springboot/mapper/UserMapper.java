package springboot.mapper;

import org.apache.ibatis.annotations.Mapper;
import springboot.entity.User;

@Mapper
public interface UserMapper {
    /**
     * 保存
     */
    void save(User user);

    /**
     * 查询
     * @param id
     * @return
     */
    User get(Long id);
}