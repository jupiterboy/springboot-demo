package com.jupiterboy.springboot.jpa.transaction;

import com.jupiterboy.springboot.jpa.transaction.service.StudentService;
import com.jupiterboy.springboot.jpa.transaction.service.TeacherService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

// ref: https://blog.csdn.net/qq_38262266/article/details/108709840

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TransactionTest {

    @Autowired
    private StudentService studentService;
    @Autowired
    private TeacherService teacherService;

    /**
     * 外围无事务，内部有事务，外围管不着内部
     */
    @Test
    public void testPropagationRequired(){
        // add 方法  @Transactional(propagation= Propagation.REQUIRED)
        studentService.add();
        // add 方法  @Transactional(propagation= Propagation.REQUIRED)
        teacherService.add();
        throw new RuntimeException();
    }

    /**
     * 外围方法Propagation.REQUIRED
     * 内部方法Propagation.REQUIRED
     * 修饰的内部方法会加入到外围方法的事务中
     * 内部方法和外围方法均属于同一事务，只要一个方法回滚，整个事务均回滚
     */
    @Transactional
    @Test
    public void testPropagationRequired1(){
        // add 方法  @Transactional(propagation= Propagation.REQUIRED)
        studentService.add();
        // add 方法  @Transactional(propagation= Propagation.REQUIRED)
        teacherService.add();
        throw new RuntimeException();
    }

    /**
     * 支持当前事务，如果当前存在事务，就加入该事务，如果当前不存在事务，就以非事务执行
     * 外围方法没有开启事务，add方法直接无事务执行
     */
    @Test
    public void testPropagationSupports(){
        // add 方法  @Transactional(propagation= Propagation.SUPPORTS)
        studentService.add();
        // add 方法  @Transactional(propagation= Propagation.SUPPORTS)
        teacherService.add();
        throw new RuntimeException();
    }

    /**
     * 外围加入事务，默认Propagation.REQUIRED
     * 内部使用Propagation.SUPPORTS
     * 内部发现有事务，加入，外部异常，内部回滚
     */
    @Transactional
    @Test
    public void testPropagationSupports1(){
        // add 方法  @Transactional(propagation= Propagation.SUPPORTS)
        studentService.add();
        // add 方法  @Transactional(propagation= Propagation.SUPPORTS)
        teacherService.add();
        throw new RuntimeException();
    }

    /**
     * 支持当前事务，如果当前存在事务，就加入该事务，如果当前不存在事务，就抛出异常
     * 外围不存在事务
     * 内部add方法使用@Transactional(propagation = Propagation.MANDATORY)
     * 内部发现当前没事务，直接抛出异常
     */
    @Test
    public void testPropagationMandatory(){
        // add 方法  @Transactional(propagation= Propagation.MANDATORY)
        studentService.add();
        // add 方法  @Transactional(propagation= Propagation.MANDATORY)
        teacherService.add();
        throw new RuntimeException();
    }

    /**
     * 支持当前事务，如果当前存在事务，就加入该事务，如果当前不存在事务，就抛出异常
     * 外围存在事务
     * 内部add方法使用@Transactional(propagation = Propagation.MANDATORY)
     * 内部发现有事务，加入，外部异常，内部回滚
     */
    @Transactional
    @Test
    public void testPropagationMandatory1(){
        // add 方法  @Transactional(propagation= Propagation.MANDATORY)
        studentService.add();
        // add 方法  @Transactional(propagation= Propagation.MANDATORY)
        teacherService.add();
        throw new RuntimeException();
    }

    /**
     * 创建新事务，无论当前存不存在事务，都创建新事务
     * 外围不存在事务
     * 内部add方法使用@Transactional(propagation = Propagation.REQUIRES_NEW)
     * 开启新事务执行，外部异常，内部不回滚
     */
    @Test
    public void testPropagationRequiresNew(){
        // add 方法  @Transactional(propagation= Propagation.REQUIRES_NEW)
        studentService.add();
        // add 方法  @Transactional(propagation= Propagation.REQUIRES_NEW)
        teacherService.add();
        throw new RuntimeException();
    }


    /**
     * 创建新事务，无论当前存不存在事务，都创建新事务
     * 外围存在事务
     * 内部add方法使用@Transactional(propagation = Propagation.REQUIRES_NEW)
     * 开启新事务执行，外部异常，内部不回滚
     */
    @Transactional
    @Test
    public void testPropagationRequiresNew1(){
        // add 方法  @Transactional(propagation= Propagation.REQUIRES_NEW)
        studentService.add();
        // add 方法  @Transactional(propagation= Propagation.REQUIRES_NEW)
        teacherService.add();
        throw new RuntimeException();
    }


    /**
     * 以非事务方式执行操作，如果当前存在事务，就把当前事务挂起
     * 外围不存在事务
     * 内部add方法使用@Transactional(propagation = Propagation.NOT_SUPPORTED)
     * 以非事务运行，外围异常，内部不回滚
     */
    @Test
    public void testPropagationNotSupported(){
        // add 方法  @Transactional(propagation= Propagation.NOT_SUPPORTED)
        studentService.add();
        // add 方法  @Transactional(propagation= Propagation.NOT_SUPPORTED)
        teacherService.add();
        throw new RuntimeException();
    }

    /**
     * 以非事务方式执行操作，如果当前存在事务，就把当前事务挂起
     * 外围存在事务
     * 内部add方法使用@Transactional(propagation = Propagation.NOT_SUPPORTED)
     * 外围挂起，内部以非事务执行，外围异常，内部不回滚
     */
    @Transactional
    @Test
    public void testPropagationNotSupported1(){
        // add 方法  @Transactional(propagation= Propagation.NOT_SUPPORTED)
        studentService.add();
        // add 方法  @Transactional(propagation= Propagation.NOT_SUPPORTED)
        teacherService.add();
        throw new RuntimeException();
    }

    /**
     * 以非事务方式执行，如果当前存在事务，则抛出异常
     * 外围不存在事务
     * 内部add方法使用@Transactional(propagation = Propagation.NEVER)
     * 外围异常，内部不回滚
     */
    @Test
    public void testPropagationNotNever(){
        // add 方法  @Transactional(propagation= Propagation.NEVER)
        studentService.add();
        // add 方法  @Transactional(propagation= Propagation.NEVER)
        teacherService.add();
        throw new RuntimeException();
    }

    /**
     * 以非事务方式执行，如果当前存在事务，则抛出异常
     * 外围存在事务
     * 内部add方法使用@Transactional(propagation = Propagation.NEVER)
     * 发现外围存在事务，抛出异常，内部不回滚
     */
    @Transactional
    @Test
    public void testPropagationNotNever1(){
        // add 方法  @Transactional(propagation= Propagation.NEVER)
        studentService.add();
        // add 方法  @Transactional(propagation= Propagation.NEVER)
        teacherService.add();
        throw new RuntimeException();
    }
}
