package com.tuling.jucdemo.atomic;

import com.tuling.jucdemo.factory.UnsafeFactory;
import sun.misc.Unsafe;

/**
 * @author Fox
 */
public class CASTest {

    public static void main(String[] args) {
        Entity entity = new Entity();

        //1.获取unsafe对象
        Unsafe unsafe = UnsafeFactory.getUnsafe();

        //2.获取 entity 对象属性字段x 的内存偏移量
        //偏移量=对象头长度+前面属性字段长度
        //对象地址加上偏移量可以快速定位到 需要cas属性字段的内存地址
        long offset = UnsafeFactory.getFieldOffset(unsafe, Entity.class, "x");
        System.out.println(offset);
        boolean successful;

        // 4个参数分别是：对象实例、字段的内存偏移量、字段期望值、字段更新值
        successful = unsafe.compareAndSwapInt(entity, offset, 0, 3);
        System.out.println(successful + "\t" + entity.x);

        successful = unsafe.compareAndSwapInt(entity, offset, 3, 5);
        System.out.println(successful + "\t" + entity.x);

        successful = unsafe.compareAndSwapInt(entity, offset, 3, 8);
        System.out.println(successful + "\t" + entity.x);

    }


}


class Entity{
    int x;
}
